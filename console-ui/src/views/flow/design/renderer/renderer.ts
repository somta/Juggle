
import * as d3 from 'd3';
import { NodeData, ElementType, NodeLayout } from '../types';
import { FlowLayout } from './layout';

type D3Element = d3.Selection<any, any, any, any>;

export class FlowRenderer {

  private svg: D3Element;

  private pan: D3Element;

  private container: D3Element;

  private boundary: DOMRect;

  private scaleExtent: [number, number] = [0.5, 2];

  private zoom: d3.ZoomBehavior<Element, unknown>;

  private nodes: NodeData[];

  private layout = new FlowLayout();

  constructor (el: HTMLElement, options: {
    nodes: any[];
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

    this.nodes = options.nodes;
    this.layout = new FlowLayout();
    this.draw();
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

  draw () {
    const mainBranch = this.layout.calculate(this.nodes);
    mainBranch.forEach((layout) => {
      this.drawNode(this.container, layout);
    });
  }

  drawNode (container: D3Element, layout: NodeLayout) {
    switch (layout.node.elementType) {
      case ElementType.START:
      case ElementType.END:
      case ElementType.METHOD:
      case ElementType.CONDITION_START:
      case ElementType.CONDITION_BRANCH:
        this.drawNormalNode(container, layout);
        break;
      case ElementType.CONDITION:
        this.drawConditionNode(container, layout);
        break;
    }
  }

  drawNormalNode (container: D3Element, layout: NodeLayout) {
    const { position, size, node } = layout;
    const g = container.append('g');
    g.attr('class', 'node-warp')
      .attr('transform', `translate(${position[0]}, ${position[1]})`);
    g.append('rect')
      .attr('class', 'flow-node')
      .attr('width', size[0])
      .attr('height', size[1])
      .attr('fill', '#fff')
      .attr('stroke', '#aaa')
      .attr('stroke-width', 1)
      .attr('rx', 4)
      .attr('ry', 4);

    g.append('text')
      .attr('text-anchor', 'middle')
      .attr('dominant-baseline', 'middle')
      .attr('x', size[0] / 2)
      .attr('y', size[1] / 2)
      .text(node.name);
  }

  drawConditionNode (container: D3Element, layout: NodeLayout) {
    const { position, children, size } = layout;
    const g = container.append('g')
      .attr('class', 'node-condition-warp')
      .attr('transform', `translate(${position[0]}, ${position[1]})`);
    g.append('rect')
      .attr('class', 'flow-node')
      .attr('width', size[0])
      .attr('height', size[1])
      .attr('fill', '#fff')
      .attr('stroke', '#aaa')
      .attr('stroke-width', 1)
      .attr('rx', 4)
      .attr('ry', 4);
    if (children && Array.isArray(children)) {
      children.forEach((child) => {
        this.drawNode(g, child);
      });
    }
  }
}
