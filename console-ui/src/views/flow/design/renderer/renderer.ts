
import * as d3 from 'd3';
import { DataNode, generateDataTree } from './data';
import { LayoutNode, VerticalLayout } from './layout';
import { loadSvgIcon } from './utils/icon';

type D3Element = d3.Selection<any, any, any, any>;

export class FlowRenderer {

  private svg: D3Element;

  private pan: D3Element;

  public container: D3Element;

  public boundary: DOMRect;

  private scaleExtent: [number, number] = [0.5, 2];

  private zoom: d3.ZoomBehavior<Element, unknown>;

  private dataRoot: DataNode;

  private layout: VerticalLayout;

  public options: {
    datas: any[];
    onZoom?: (event: any) => any
    onAdd?: (node: LayoutNode) => any
    onEdit?: (node: LayoutNode) => any
    onDelete?: (node: LayoutNode) => any
  };

  constructor (el: HTMLElement, options: FlowRenderer['options']) {
    this.svg = d3.select(el)
      .append('svg')
      .attr('width', '100%')
      .attr('height', '100%');

    loadSvgIcon(this.svg);

    this.boundary = el.getBoundingClientRect();
    this.pan = this.svg.append('g');
    this.container = this.pan.append('g');

    this.options = options;
    this.zoom = d3.zoom()
      .scaleExtent(this.scaleExtent)
      .on('zoom', (event) => {
        this.pan.attr('transform', event.transform);
        if (typeof options.onZoom === 'function') {
          options.onZoom(event);
        }
      });
    this.svg.call(this.zoom as any);

    this.dataRoot = generateDataTree(options.datas);
    this.layout = new VerticalLayout(this);
    this.refresh();
  }

  updateDatas (root: DataNode) {
    this.dataRoot = root;
    this.refresh();
  }

  refresh () {
    this.layout.analysis(this.dataRoot);
    this.layout.draw();
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
