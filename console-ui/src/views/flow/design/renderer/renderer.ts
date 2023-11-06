
import * as d3 from 'd3';

enum ElementType {
  START = 'START',
  METHOD = 'METHOD',
  CONDITION = 'CONDITION',
  END = 'END'
};

type D3Element = d3.Selection<any, any, any, any>;

type NodeCondition = {
  conditionName: string;
  conditionType: string;
  expression: string;
  outgoing: string;
};

type NodeData = {
  key: string;
  name: string;
  outgoings: string[];
  elementType: ElementType;
  conditions?: NodeCondition[];
};

export class FlowRenderer {

  private svg: D3Element;

  private pan: D3Element;

  private container: D3Element;

  private boundary: DOMRect;

  private scaleExtent: [number, number] = [0.5, 2];

  private zoom: d3.ZoomBehavior<Element, unknown>;

  private content: NodeData[];

  private nodeMap: Map<string, any> = new Map();

  private nodeSize = [160, 80];

  private nodeSpacing = [80, 80];

  private paddingTop = 40;

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
    this.analysis();
    this.draw();
  }

  analysis () {
    console.log(this.content, 'ww');
    this.nodeMap = new Map();
    let startKey: string = '';
    this.content.forEach((node) => {
      this.nodeMap.set(node.key, node);
      // 起始节点
      if (node.elementType === ElementType.START) {
        startKey = node.key;
        this.nodeMap.set('_start', node);
      }
      // 结束节点
      if (node.elementType === ElementType.END) {
        this.nodeMap.set('_end', node);
      }
    });
    if (!startKey) {
      throw new Error('Start node not found');
    }
  }

  // draw () {
  //   this.drawEdges();
  //   this.drawNodes();
  // }

  // drawNodes () {
  //   const size = [160, 80];
  //   const spacing = [80, 80];
  //   const paddingTop = 40;
  //   const nodes = this.container
  //     .selectAll('.node-group')
  //     .data(this.nodePath)
  //     .enter()
  //     .append('g')
  //     .attr('class', 'node-group')
  //     .attr('transform', (d, i) => {
  //       const y = i * size[1] + i * spacing[1] + paddingTop;
  //       return `translate(0, ${y})`;
  //     });

  //   nodes.filter((d) => this.nodeMap.get(d)?.elementType !== ElementType.CONDITION)
  //     .append('rect')
  //     .attr('class', 'flow-node')
  //     .attr('width', size[0])
  //     .attr('height', size[1])
  //     .attr('fill', '#fff')
  //     .attr('stroke', '#aaa')
  //     .attr('stroke-width', 1)
  //     .attr('x', -size[0] / 2)
  //     .attr('rx', 4)
  //     .attr('ry', 4);

  //   // nodes.append('text')
  //   //   .attr('text-anchor', 'middle')
  //   //   .attr('dominant-baseline', 'middle')
  //   //   .attr('x', 0)
  //   //   .attr('y', size[1] / 2)
  //   //   .text(d => d.name);
  // }

  // drawEdges () {
  //   // const size = [160, 80];
  //   // const spacing = [80, 80];
  //   // const paddingTop = 40;
  //   // const lines = this.container
  //   //   .selectAll('line')
  //   //   .data(this.content.slice(0, this.content.length - 1));
  //   // lines.enter()
  //   //   .append('path')
  //   //   .attr('class', 'flow-edge')
  //   //   .attr('stroke', '#aaa')
  //   //   .attr('stroke-width', 1)
  //   //   .attr('d', (d, i) => {
  //   //     const x = 0;
  //   //     const y = i * size[1] + i * spacing[1] + paddingTop + size[1] / 2;
  //   //     const line = d3.line();
  //   //     return line([[x, y], [x, y + spacing[1] + size[1]]]);
  //   //   });
  // }

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
    const start = this.nodeMap.get('_start');
    const end = this.nodeMap.get('_end');
    if (start) {
      const pos = this.drawInOrder(this.container, start, end, [0, this.paddingTop]);
      this.drawNode(end, this.container.append('g'), pos);
    }
  }

  drawInOrder (parent: D3Element, start: NodeData, end: NodeData, pos: number[]) {
    let current: NodeData = start;
    while (current) {
      // 退出循环
      if (current === end) {
        break;
      }
      const g = parent.append('g');
      let size: number[];
      if (current.elementType === ElementType.CONDITION) {
        size = this.drawCondition(current, g, pos);
      } else {
        size = this.drawNode(current, g, pos);
      }
      pos = pos.map((p, i) => p + size[i] + this.nodeSpacing[i]);
      // 下一个节点
      const nextKey = current.outgoings?.[0];
      current = this.content.find((node) => node.key === nextKey) as NodeData;
    }
    return pos;
  }

  drawNode (node: NodeData, g: D3Element, pos: number[]) {
    const size = this.nodeSize;
    g.attr('class', 'node-warp')
      .attr('transform', `translate(0, ${pos[1]})`);
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

    return [
      size[0],
      size[1],
    ];
  }

  drawCondition (node: NodeData, g: D3Element, pos: number[]) {
    g.attr('class', 'node-conditions')
      .attr('transform', `translate(0, ${pos[1]})`);
    const conditionNode = g.append('g');
    const nodeSize = this.drawNode(node, conditionNode, [0, 0]);
    const heights: number[] = [];
    const widths: number[] = [];
    const conditionTop = nodeSize[1] + this.nodeSpacing[1];
    if (Array.isArray(node.conditions)) {
      node.conditions.forEach((condition, index) => {
        const offsetX = index * (this.nodeSize[0] + this.nodeSpacing[0]);
        const conditionG = g.append('g')
          .attr('class', 'node-condition-wrap')
          .attr('transform', `translate(${offsetX}, ${conditionTop})`);
        const conditionSize = this.drawConditionNode(condition, conditionG, [0, 0]);
        const spacing = conditionSize[1] + this.nodeSpacing[1];
        const startKey = condition.outgoing;
        const start = this.nodeMap.get(startKey);
        const endKey = node.outgoings?.[0];
        const end = this.nodeMap.get(endKey);
        const [width, height] = this.drawInOrder(conditionG, start, end, [0, spacing]);
        widths.push(width);
        heights.push(height);
      });
    }
    return [
      widths.reduce((a, b) => a + b, 0),
      conditionTop + Math.max(...heights) - this.nodeSpacing[1],
    ];
  }

  drawConditionNode (condition: NodeCondition, g: D3Element, pos: number[]) {
    const size = this.nodeSize;
    g.attr('class', 'node-condition');
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
      .text(condition.conditionName);

    return [
      size[0],
      size[1],
    ];
  }
}
