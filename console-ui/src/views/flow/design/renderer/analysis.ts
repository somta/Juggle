import { NodeData, ElementType } from '../types';

export default function analysis (nodes: NodeData[]) {
  const nodeMap = new Map();
  let startKey: string = '';

  // 遍历原数据
  nodes.forEach((node) => {
    nodeMap.set(node.key, node);
    // 起始节点
    if (node.elementType === ElementType.START) {
      startKey = node.key;
      nodeMap.set('_start', node);
    }
    // 结束节点
    if (node.elementType === ElementType.END) {
      nodeMap.set('_end', node);
    }
  });

  // 如果没有起始节点，返回空
  if (!startKey) {
    return [];
  }


}

function listNodeInOrder () {}

function handleConditionNode () {}
