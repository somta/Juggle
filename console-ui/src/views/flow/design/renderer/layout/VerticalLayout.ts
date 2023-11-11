
import { NodeData, ElementType, D3Element } from '../../types';
import { LayoutNode } from './LayoutNode';
import * as d3 from 'd3';

type D3Point = [number, number];

export class VerticalLayout {

  constructor (container: D3Element, boundary: DOMRect) {
    this.container = container;
    // 居中
    container.attr('transform', `translate(${boundary.width / 2}, 0)`);
  }
  
  private container: D3Element;

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
    console.log('startLayout', startLayout);

    // 处理分支节点
    let maxRight = 0;
    let maxBottom = 0;
    if (data.conditions && Array.isArray(data.conditions)) {
      data.conditions.forEach((condition, index) => {
        const branchNode = {
          key: `branch_${data.key}_${index}}`,
          name: condition.conditionName,
          outgoings: [condition.outgoing],
          elementType: ElementType.CONDITION_BRANCH,
        };
        startLayout.line(branchNode.key);
        const end = this.dataMap.get(data.outgoings?.[0]) as NodeData;
        const position = {
          left: maxRight + (index === 0 ? 0 : this.spacing.horizontal),
          top: startLayout.bottom + this.spacing.vertical,
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
    this.drawNodes();
  }

  drawLines () {
    this.nodeMap.forEach((node) => {
      this.drawLine(node);
    });
  }

  drawNodes () {
    this.mainBranch.forEach((node) => {
      this.drawNode(this.container, node);
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
    const { left, top, width, height, data } = node;
    const g = container.append('g');
    g.attr('class', 'node-warp')
      .attr('transform', `translate(${left}, ${top})`);

    const flowNode = g.append('g')
      .data([node])
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

    if (node.data.elementType !== ElementType.END) {
      this.drawAddIcon(g, node);
    }
  }

  drawConditionNode (container: D3Element, node: LayoutNode) {
    const { left, top } = node;
    const children = node.getChildren();
    const g = container.append('g')
      .attr('class', 'node-condition-warp')
      .attr('transform', `translate(${left}, ${top})`);
    this.drawAddIcon(g, node);
    if (children && Array.isArray(children)) {
      children.forEach((child) => {
        this.drawNode(g, child);
      });
    }
  }

  drawAddIcon (container: D3Element, node: LayoutNode) {
    const { width, height } = node;
    const btn_radius = 16;
    const icon_radius = 6;
    const addButton = container.append('g')
      .data([node])
      .attr('class', 'flow-add-btn')
      .attr('transform', `translate(${width / 2}, ${height + this.spacing.vertical / 2})`);
    addButton.append('circle')
      .attr('cx', 0)
      .attr('cy', 0)
      .attr('r', btn_radius)
      .attr('fill', '#fff')
      .attr('stroke', '#aaa');
    
    // 创建一个垂直的线，形成加号的一部分
    addButton.append('line')
      .attr('x1', 0)
      .attr('y1', -icon_radius)
      .attr('x2', 0)
      .attr('y2', icon_radius)
      .attr('stroke', '#aaa')
      .attr('stroke-width', 2);

    // 创建一个水平的线，形成加号的一部分
    addButton.append('line')
      .attr('x1', -icon_radius)
      .attr('y1', 0)
      .attr('x2', +icon_radius)
      .attr('y2', 0)
      .attr('stroke', '#aaa')
      .attr('stroke-width', 2);
  }

  drawLine (node: LayoutNode) {
    const { linesTo, data } = node;
    linesTo.forEach((key) => {
      const fromNode = this.nodeMap.get(data.key);
      let toNode = this.nodeMap.get(key);
      // 条件节点的线条指向条件开始节点
      if (toNode && toNode.data.elementType === ElementType.CONDITION) {
        toNode = toNode.getChildren().find((child) => child.data.elementType === ElementType.CONDITION_START);
      }
      if (fromNode && toNode) {
        const polyLine = this.getPolyline(fromNode, toNode);
        this.container.append('path')
          .attr('d', this.line(polyLine))
          .attr('class', 'flow-line')
          .attr('fill', 'none')
          .attr('stroke', '#aaa');
      }
    });
  }

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
