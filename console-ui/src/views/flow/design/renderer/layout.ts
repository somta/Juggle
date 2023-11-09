
import { NodeData, ElementType, NodeLayout } from '../types';

export class FlowLayout {

  private nodeSize = [160, 80];

  private nodeSpacing = [80, 80];

  private startTop = 40;

  readonly nodeMap: Map<string, NodeData> = new Map();

  readonly layoutMap: Map<string, NodeLayout> = new Map();

  private mainBranch: NodeLayout[] = [];

  public calculate (nodes: NodeData[]) {
    this.analysis(nodes);
    this.layoutMap.clear();
    const start = this.nodeMap.get('_start') as NodeData;
    const end = this.nodeMap.get('_end') as NodeData;
    // 起始位置
    const pos = [0, this.startTop];
    // 从开始依次处理节点
    const layouts = this.listNodeInOrder(start, end, pos);
    const last = layouts[layouts.length - 1];
    // 处理结束节点
    const endPos = [0, last.position[1] + last.size[1] + this.nodeSpacing[1]];
    const endLayout = this.handleNormalNode(end, endPos);
    // 返回主节点
    this.mainBranch = [...layouts, endLayout];
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

  listNodeInOrder (start: NodeData, end: NodeData, position: number[]) {
    let current: NodeData = start;
    let pos = position;
    const result = [];
    while (current) {
      // 退出循环
      if (current === end) {
        break;
      }
      let layout: NodeLayout;
      if (current.elementType === ElementType.CONDITION) {
        layout = this.handleConditionNode(current, pos);
      } else {
        layout = this.handleNormalNode(current, pos);
      }
      result.push(layout);
      // 加上下边距
      pos = [pos[0], pos[1] + layout.size[1] + this.nodeSpacing[1]];
      // 下一个节点
      const nextKey = current.outgoings?.[0];
      current = this.nodeMap.get(nextKey) as NodeData;
    }
    return result;
  }

  adjustNodePosition (layout: NodeLayout, offsetX: number) {
    layout.position[0] -= offsetX;
  }

  handleNormalNode (node: NodeData, position: number[]) {
    const size = this.nodeSize;
    const layout =  { size: [...size], position: [...position], node, linesTo: [] };
    this.layoutMap.set(node.key, layout);
    this.adjustNodePosition(layout, layout.size[0] / 2);
    return layout;
  }

  handleConditionNode (node: NodeData, position: number[]) {
    const size = this.nodeSize;
    // 条件父节点
    const layout: NodeLayout = { size: [0, 0], position: [...position], node, linesTo: [], children: [] };

    // 创建分支开始节点
    const startNode = {
      key: `branch_${node.key}_start`,
      name: node.name,
      outgoings: node.outgoings,
      elementType: ElementType.CONDITION_START,
    };
    this.nodeMap.set(startNode.key, startNode);
    const startLayout = this.handleNormalNode(startNode, [0, 0]);
    layout.children!.push(startLayout);

    // 处理分支节点
    if (node.conditions && Array.isArray(node.conditions)) {
      node.conditions.forEach((condition, index) => {
        const branchNode = {
          key: `branch_${node.key}_${index}}`,
          name: condition.conditionName,
          outgoings: [condition.outgoing],
          elementType: ElementType.CONDITION_BRANCH,
        };
        const end = this.nodeMap.get(node.outgoings?.[0]) as NodeData;
        const position = [
          index * (size[0] + this.nodeSpacing[0]),
          startLayout.size[1] + this.nodeSpacing[1],
        ];
        const layouts = this.listNodeInOrder(branchNode, end, position);
        layout.children!.push(...layouts);
        const listSize = this.getListSize(layouts);
        // 计算宽度
        layout.size[0] = layout.size[0] + listSize[0];
        // 加上右边距
        if (index < node.conditions!.length - 1) {
          layout.size[0] += this.nodeSpacing[0];
        }
        // 计算最高高度
        layout.size[1] = Math.max(layout.size[1], listSize[1]);
      });
    }

    // 条件开始节点居中
    this.adjustNodePosition(startLayout, -layout.size[0] / 2);
    // 条件整体居中
    this.adjustNodePosition(layout, layout.size[0] / 2);
    this.layoutMap.set(node.key, layout);
    return layout;
  }

  getListSize (list: NodeLayout[]) {
    const size = [0, 0];
    let top = 0;
    let bottom = 0;
    list.forEach((layout) => {
      size[0] = Math.max(size[0], layout.size[0]);
      top = Math.min(top, layout.position[1]);
      bottom = Math.max(bottom, layout.position[1] + layout.size[1]);
    });
    size[1] = bottom - top;
    return size;
  }
}
