
import { NodeData, ElementType } from '../types';
import { LayoutNode } from './layoutNode';

export class FlowLayout {

  private nodeSize = { width: 160, height: 80 };

  private spacing = { vertical: 80, horizontal: 80 };

  private startTop = 40;

  readonly nodeMap: Map<string, NodeData> = new Map();

  readonly layoutMap: Map<string, LayoutNode> = new Map();

  private mainBranch: LayoutNode[] = [];

  public calculate (nodes: NodeData[]) {
    this.analysis(nodes);
    this.layoutMap.clear();
    const start = this.nodeMap.get('_start') as NodeData;
    const end = this.nodeMap.get('_end') as NodeData;
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
    // 计算绝对位置
    return this.mainBranch;
  }

  private analysis (nodes: NodeData[]) {
    this.nodeMap.clear();
    nodes.forEach((node) => {
      this.nodeMap.set(node.key, node);
      if (node.elementType === ElementType.START) {
        this.nodeMap.set('_start', node);
      }
      if (node.elementType === ElementType.END) {
        this.nodeMap.set('_end', node);
      }
    });
    if (!this.nodeMap.get('_start')) {
      throw new Error('Start node not found');
    }
  }

  listNodeInOrder (start: NodeData, end: NodeData, position: { left: number, top: number }) {
    let current: NodeData = start;
    let pos = position;
    const result = [];
    while (current) {
      // 退出循环
      if (current === end) {
        break;
      }
      let layout: LayoutNode;
      if (current.elementType === ElementType.CONDITION) {
        layout = this.handleConditionNode(current, pos);
      } else {
        layout = this.handleNormalNode(current, pos);
      }
      result.push(layout);
      // 加上下边距
      pos = { left: pos.left, top: pos.top + layout.height + this.spacing.horizontal };
      // 下一个节点
      const nextKey = current.outgoings?.[0];
      current = this.nodeMap.get(nextKey) as NodeData;
    }
    return result;
  }

  adjustNodeRect (layout: LayoutNode) {
    layout.setRelative(layout.left - layout.width / 2, layout.top);
  }

  handleNormalNode (data: NodeData, position: { left: number, top: number }) {
    const layout = new LayoutNode({
      left: position.left,
      top: position.top,
      width: this.nodeSize.width,
      height: this.nodeSize.height,
      data,
      linesTo: [],
    });
    this.layoutMap.set(data.key, layout);
    this.adjustNodeRect(layout);
    return layout;
  }

  handleConditionNode (data: NodeData, position: { left: number, top: number }) {
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
      outgoings: data.outgoings,
      elementType: ElementType.CONDITION_START,
    };
    this.nodeMap.set(startNode.key, startNode);
    const startLayout = this.handleNormalNode(startNode, { left: 0, top: 0 });
    conditionLayout.addChild(startLayout);

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
        const end = this.nodeMap.get(data.outgoings?.[0]) as NodeData;
        const position = {
          left: maxRight + (index === 0 ? 0 : this.spacing.horizontal),
          top: startLayout.bottom + this.spacing.vertical,
        };
        const layouts = this.listNodeInOrder(branchNode, end, position);
        const last = layouts[layouts.length - 1];

        // 最大宽度
        const maxWidth = Math.max(...layouts.map((layout) => layout.width));
        layouts.forEach((layout) => {
          // 整体右移动最大宽度的一半
          layout.setRelative(layout.left + maxWidth / 2, layout.top);
          conditionLayout.addChild(layout);
        });
        
        // 最右
        maxRight = Math.max(maxRight, Math.max(...layouts.map((layout) => layout.right)));
        // 最底
        maxBottom = Math.max(maxBottom, last.bottom);
      });
    }

    // 设置条件父节点宽度
    conditionLayout.setSize(maxRight, maxBottom);
    // 条件开始节点居中
    startLayout.setRelative(startLayout.left + conditionLayout.width / 2, startLayout.top);
    // 条件整体居中
    this.adjustNodeRect(conditionLayout);
    this.layoutMap.set(data.key, conditionLayout);
    return conditionLayout;
  }
}
