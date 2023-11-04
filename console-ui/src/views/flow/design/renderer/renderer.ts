
import * as d3 from 'd3';

export class FlowRenderer {

  private svg: d3.Selection<any, any, any, any>;

  private pan: d3.Selection<any, any, any, any>;

  private container: d3.Selection<any, any, any, any>;

  private boundary: DOMRect;

  private scaleExtent: [number, number] = [0.5, 2];

  private zoom: d3.ZoomBehavior<Element, unknown>;

  private content: any[];

  constructor (el: HTMLElement, options: {
    content: any[];
    onZoom?: (event: any) => any
  }) {
    this.svg = d3.select(el)
      .append('svg')
      .attr('width', '100%')
      .attr('height', '100%');

    this.boundary = el.getBoundingClientRect();
    this.pan = this.svg.append('g');
    this.container = this.pan.append('g')
      .attr('transform', `translate(${this.boundary.width / 2}, 0)`);

    this.zoom = d3.zoom()
      .scaleExtent(this.scaleExtent)
      .on('zoom', (event) => {
        this.pan.attr('transform', event.transform);
        if (typeof options.onZoom === 'function') {
          options.onZoom(event);
        }
      });
    this.svg.call(this.zoom as any);

    this.content = options.content;
    this.draw();
  }

  getScaleExtent () {
    return this.scaleExtent;
  }

  draw () {
    this.drawEdges();
    this.drawNodes();
  }

  drawNodes () {
    const size = [160, 80];
    const spacing = [80, 80];
    const paddingTop = 40;
    const nodes = this.container
      .selectAll('.node-group')
      .data(this.content)
      .enter()
      .append('g')
      .attr('class', 'node-group')
      .attr('transform', (d, i) => {
        const y = i * size[1] + i * spacing[1] + paddingTop;
        return `translate(0, ${y})`;
      });

    nodes.append('rect')
      .attr('class', 'flow-node')
      .attr('width', size[0])
      .attr('height', size[1])
      .attr('fill', '#fff')
      .attr('stroke', '#aaa')
      .attr('stroke-width', 1)
      .attr('x', -size[0] / 2)
      .attr('rx', 4)
      .attr('ry', 4);

    nodes.append('text')
      .attr('text-anchor', 'middle')
      .attr('dominant-baseline', 'middle')
      .attr('x', 0)
      .attr('y', size[1] / 2)
      .text(d => d.key);
  }

  drawEdges () {
    const size = [160, 80];
    const spacing = [80, 80];
    const paddingTop = 40;
    const lines = this.container
      .selectAll('line')
      .data(this.content.slice(0, this.content.length - 1));
    lines.enter()
      .append('path')
      .attr('class', 'flow-edge')
      .attr('stroke', '#aaa')
      .attr('stroke-width', 1)
      .attr('d', (d, i) => {
        const x = 0;
        const y = i * size[1] + i * spacing[1] + paddingTop + size[1] / 2;
        const line = d3.line();
        return line([[x, y], [x, y + spacing[1] + size[1]]]);
      });
  }

  scaleFromTop (scale: number) {
    const scaleExtent = this.scaleExtent;
    if (scale < scaleExtent[0]) {
      scale = scaleExtent[0];
    }
    if (scale > scaleExtent[1]) {
      scale = scaleExtent[1];
    }
    const transform = d3.zoomTransform(this.svg.node());
    const point: [number, number] = [
      transform.x + this.boundary.width * transform.k / 2,
      0,
    ];
    this.zoom.scaleTo(this.svg, scale, point);
    return scale;
  }
}
