
import * as d3 from 'd3';
import { NodeData, ElementType, D3Element } from '../../types';
import { LayoutNode } from './LayoutNode';
import { FlowRenderer } from '../renderer';

type D3Point = [number, number];

export class VerticalLayout {

  constructor (renderer: FlowRenderer) {
    // 居中
    renderer.container.attr('transform', `translate(${renderer.boundary.width / 2}, 0)`);
    this.lineContainer = renderer.container.append('g');
    this.nodeContainer = renderer.container.append('g');
    this.renderer = renderer;
  }
  
  private renderer: FlowRenderer;

  private nodeContainer: D3Element;

  private lineContainer: D3Element;

  private nodeSize = { width: 160, height: 60 };

  private spacing = { vertical: 80, horizontal: 100 };

  private startTop = 40;

  readonly dataMap: Map<string, NodeData> = new Map();

  readonly nodeMap: Map<string, LayoutNode> = new Map();

  private mainBranch: LayoutNode[] = [];

  private line = d3.line();

  public calculate (nodes: NodeData[]) {
    this.analysis(nodes);
    this.nodeMap.clear();
    const start = this.dataMap.get('_start') as NodeData;
    const end = this.dataMap.get('_end') as NodeData;
    // 起始位置
    const pos = { left: 0, top: this.startTop };
    // 从开始依次处理节点
    const layouts = this.listNodeInOrder(start, end, pos);
    const last = layouts[layouts.length - 1];
    // 处理结束节点
    const endPos = { left: 0, top: last.bottom + this.spacing.vertical };
    const endLayout = this.handleNormalNode(end, endPos);
    // 主分支
    this.mainBranch = [...layouts, endLayout];
    // 返回主分支
    return this.mainBranch;
  }

  private analysis (datas: NodeData[]) {
    this.dataMap.clear();
    datas.forEach((data) => {
      this.dataMap.set(data.key, data);
      if (data.elementType === ElementType.START) {
        this.dataMap.set('_start', data);
      }
      if (data.elementType === ElementType.END) {
        this.dataMap.set('_end', data);
      }
    });
    if (!this.dataMap.get('_start')) {
      throw new Error('Start node not found');
    }
  }

  private listNodeInOrder (start: NodeData, end: NodeData, position: { left: number, top: number }) {
    let current: NodeData = start;
    let pos = position;
    const result = [];
    while (current) {
      // 退出循环
      if (current === end) {
        break;
      }
      let node: LayoutNode;
      if (current.elementType === ElementType.CONDITION) {
        node = this.handleConditionNode(current, pos);
      } else {
        node = this.handleNormalNode(current, pos);
      }
      result.push(node);
      // 加上下边距
      pos = { left: pos.left, top: pos.top + node.height + this.spacing.vertical };
      // 下一个节点
      const nextKey = current.outgoings?.[0];
      current = this.dataMap.get(nextKey) as NodeData;
    }
    return result;
  }

  private adjustNodeRect (node: LayoutNode) {
    node.setRelative(node.left - node.width / 2, node.top);
  }

  private handleNormalNode (data: NodeData, position: { left: number, top: number }) {
    const out = data.outgoings?.[0];
    const linesTo = out ? [out] : [];
    const node = new LayoutNode({
      left: position.left,
      top: position.top,
      width: this.nodeSize.width,
      height: this.nodeSize.height,
      data,
      linesTo,
    });
    this.nodeMap.set(data.key, node);
    this.adjustNodeRect(node);
    return node;
  }

  private handleConditionNode (data: NodeData, position: { left: number, top: number }) {
    // 条件父节点
    const conditionLayout = new LayoutNode({
      left: position.left,
      top: position.top,
      width: 0,
      height: 0,
      data,
      linesTo: [],
    });

    // 创建分支开始节点
    const startNode = {
      key: `branch_${data.key}_start`,
      name: data.name,
      outgoings: [],
      elementType: ElementType.CONDITION_START,
    };
    this.dataMap.set(startNode.key, startNode);
    const startLayout = this.handleNormalNode(startNode, { left: 0, top: 0 });
    conditionLayout.addChild(startLayout);

    // 处理分支节点
    let maxRight = 0;
    let maxBottom = 0;
    if (data.conditions && Array.isArray(data.conditions)) {
      data.conditions.forEach((condition, index) => {
        const branchNode = {
          key: `branch_${data.key}_${index}`,
          name: condition.conditionName,
          outgoings: [condition.outgoing],
          elementType: ElementType.CONDITION_BRANCH,
        };
        startLayout.line(branchNode.key);
        const end = this.dataMap.get(data.outgoings?.[0]) as NodeData;
        const position = {
          left: maxRight + (index === 0 ? 0 : this.spacing.horizontal),
          top: startLayout.bottom + this.spacing.vertical / 2,
        };
        const layouts = this.listNodeInOrder(branchNode, end, position);
        const last = layouts[layouts.length - 1];

        // 最大宽度
        const maxWidth = Math.max(...layouts.map((node) => node.width));
        layouts.forEach((node) => {
          // 整体右移动最大宽度的一半
          node.setRelative(node.left + maxWidth / 2, node.top);
          conditionLayout.addChild(node);
        });
        
        // 最右
        maxRight = Math.max(maxRight, Math.max(...layouts.map((node) => node.right)));
        // 最底
        maxBottom = Math.max(maxBottom, last.bottom);
      });
    }

    // 设置条件父节点宽度
    conditionLayout.setSize(maxRight, maxBottom + this.spacing.vertical);
    // 条件开始节点居中
    startLayout.setRelative(startLayout.left + conditionLayout.width / 2, startLayout.top);
    // 条件整体居中
    this.adjustNodeRect(conditionLayout);
    this.nodeMap.set(data.key, conditionLayout);
    return conditionLayout;
  }

  public draw () {
    this.drawLines();
    this.drawNodes(this.nodeContainer, this.mainBranch);
  }

  drawLines () {
    const lines: { from: string; to: string; }[] = [];
    this.nodeMap.forEach((node) => {
      const { linesTo, data } = node;
      linesTo.forEach((to) => {
        const line = {
          from: data.key,
          to,
        };
        lines.push(line);
      });
    });
    const flowLines = this.lineContainer.selectAll('.flow-line')
      .data(lines, (d: any) => `${d.from}-${d.to}`);
    
    // 更新
    flowLines.filter('path')
      .attr('d', (d) => this.getPathD(d));

    // 新增
    flowLines.enter()
      .append('path')
        .attr('d', (d) => this.getPathD(d))
        .attr('class', 'flow-line')
        .attr('fill', 'none')
        .attr('stroke', '#aaa');
    // 删除
    flowLines.exit()
      .remove();
  }

  getPathD (d: { from: string; to: string; }) {
    const fromNode = this.nodeMap.get(d.from);
    let toNode = this.nodeMap.get(d.to);
    // 条件节点的线条指向条件开始节点
    if (toNode && toNode.data.elementType === ElementType.CONDITION) {
      toNode = toNode.getChildren().find((child) => child.data.elementType === ElementType.CONDITION_START);
    }
    if (fromNode && toNode) {
      const polyLine = this.getPolyline(fromNode, toNode);
      return this.line(polyLine);
    }
    return '';
  }

  drawNodes (container: D3Element, nodes: LayoutNode[]) {
    container.selectChildren('.node-wrap')
      .data(nodes, (d: any) => d.data.key)
      .join('g')
        .attr('class', 'node-wrap')
        .attr('transform', (d) => `translate(${d.left}, ${d.top})`)
        .each((d, i, nodes) => {
          const g = d3.select(nodes[i]);
          this.drawNode(g, d);
        });
  }

  private drawNode (container: D3Element, node: LayoutNode) {
    switch (node.data.elementType) {
      case ElementType.START:
      case ElementType.END:
      case ElementType.METHOD:
      case ElementType.CONDITION_START:
      case ElementType.CONDITION_BRANCH:
        this.drawNormalNode(container, node);
        break;
      case ElementType.CONDITION:
        this.drawConditionNode(container, node);
        break;
    }
  }

  drawNormalNode (container: D3Element, node: LayoutNode) {
    const { width, height, data } = node;
    const flowNode = container.append('g')
      .attr('class', 'flow-node');

    flowNode.append('rect')
      .attr('width', width)
      .attr('height', height)
      .attr('fill', '#fff')
      .attr('stroke', '#aaa')
      .attr('stroke-width', 1)
      .attr('rx', 4)
      .attr('ry', 4);

    flowNode.append('text')
      .attr('text-anchor', 'middle')
      .attr('dominant-baseline', 'middle')
      .attr('x', width / 2)
      .attr('y', height / 2)
      .text(data.name);

    this.drawHoverButtons(flowNode, node);

    if ([
      ElementType.START,
      ElementType.METHOD,
      ElementType.CONDITION_BRANCH,
    ].includes(data.elementType)) {
      this.drawAddIcon(container, node);
    }
  }

  drawConditionNode (container: D3Element, node: LayoutNode) {
    const children = node.getChildren();
    this.drawAddIcon(container, node);
    if (children && Array.isArray(children)) {
      this.drawNodes(container, children);
    }
  }

  drawAddIcon (container: D3Element, node: LayoutNode) {
    const { width, height } = node;
    const btn_radius = 16;
    const addButton = container.append('g')
      .attr('class', 'flow-btn flow-btn-add')
      .attr('transform', `translate(${width / 2}, ${height + this.spacing.vertical / 2})`)
      .on('click', (_, d) => {
        this.renderer.options.onAdd?.(d);
      });
    addButton.append('circle')
      .attr('cx', 0)
      .attr('cy', 0)
      .attr('r', btn_radius)
      .attr('fill', '#fff')
      .attr('stroke', '#777');
    
    addButton.append('use')
      .attr('href', '#icon-plus')
      .attr('width', 24)
      .attr('height', 24)
      .attr('x', -12)
      .attr('y', -12)
      .attr('fill', '#777');
  }

  drawHoverButtons (container: D3Element, node: LayoutNode) {
    const { width, data } = node;
    let btns = ['delete', 'edit'];
    if ([ElementType.START, ElementType.END, ElementType.CONDITION_BRANCH].includes(data.elementType)) {
      btns = ['edit'];
    }
    btns.forEach((btn, i) => {
      const flowBtns = container.append('g')
        .attr('class', `flow-btn flow-btn-${btn}`)
        .attr('transform', `translate(${width - i * 36}, 0)`)
        .on('click', (_, d) => {
          if (btn === 'edit') {
            this.renderer.options.onEdit?.(d);
          }
          if (btn === 'delete') {
            this.renderer.options.onDelete?.(d);
          }
        });
      flowBtns.append('circle')
        .attr('cx', 0)
        .attr('cy', 0)
        .attr('r', '15')
        .attr('fill', '#fff')
        .attr('stroke', '#777');
      
      flowBtns.append('use')
        .attr('href', `#icon-${btn}`)
        .attr('width', 16)
        .attr('height', 16)
        .attr('x', -8)
        .attr('y', -8)
        .attr('fill', '#777');
    });
  }

  // drawRemoveIcon (container: D3Element, node: LayoutNode) {
  //   const { width, height } = node;
  //   const btn_radius = 16;
  //   const addButton = container.append('g')
  //     .attr('class', 'flow-add-btn')
  //     .attr('transform', `translate(${width / 2}, ${height + this.spacing.vertical / 2})`);
  //   addButton.append('circle')
  //     .attr('cx', 0)
  //     .attr('cy', 0)
  //     .attr('r', btn_radius)
  //     .attr('fill', '#fff')
  //     .attr('stroke', '#777');
    
  //   addButton.append('use')
  //     .attr('href', '#icon-plus')
  //     .attr('width', 24)
  //     .attr('height', 24)
  //     .attr('x', -12)
  //     .attr('y', -12)
  //     .attr('fill', '#777');
  // }

  getPolyline (fromNode: LayoutNode, toNode: LayoutNode): D3Point[] {
    const from: D3Point = [fromNode.x + fromNode.width / 2, fromNode.y + fromNode.height / 2];
    const to: D3Point = [toNode.x + toNode.width / 2, toNode.y + toNode.height / 2];
    if (from[0] === to[0]) {
      return [from, to];
    }
    let turningPoint;
    if (fromNode.data.elementType === ElementType.CONDITION_START) {
      turningPoint = from[1];
    } else {
      turningPoint = toNode.y - this.spacing.vertical;
    }
    return [
      from,
      [from[0], turningPoint],
      [to[0], turningPoint],
      to,
    ];
  }
}
