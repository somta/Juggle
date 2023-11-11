
import * as d3 from 'd3';
import { NodeData } from '../types';
import { VerticalLayout } from './layout';

type D3Element = d3.Selection<any, any, any, any>;

export class FlowRenderer {

  private svg: D3Element;

  private pan: D3Element;

  private container: D3Element;

  private boundary: DOMRect;

  private scaleExtent: [number, number] = [0.5, 2];

  private zoom: d3.ZoomBehavior<Element, unknown>;

  private datas: NodeData[];

  private layout: VerticalLayout;

  constructor (el: HTMLElement, options: {
    datas: any[];
    onZoom?: (event: any) => any
  }) {
    this.svg = d3.select(el)
      .append('svg')
      .attr('width', '100%')
      .attr('height', '100%');

    this.boundary = el.getBoundingClientRect();
    this.pan = this.svg.append('g');
    this.container = this.pan.append('g');

    this.zoom = d3.zoom()
      .scaleExtent(this.scaleExtent)
      .on('zoom', (event) => {
        this.pan.attr('transform', event.transform);
        if (typeof options.onZoom === 'function') {
          options.onZoom(event);
        }
      });
    this.svg.call(this.zoom as any);

    this.datas = options.datas;
    this.layout = new VerticalLayout(this.container, this.boundary);
    this.layout.calculate(this.datas);
    this.layout.draw();
    this.addEvents();
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

  addEvents () {
    d3.selectAll('.flow-node').on('click', function(event, d) {
      console.log('flow-node元素被点击了');
      console.log(event, d);
    });
    d3.selectAll('.flow-add-btn').on('click', function(event, d) {
      console.log('flow-add-btn元素被点击了');
      console.log(event, d);
    });
  }
}
