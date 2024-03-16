import { DataNode, initNewNode } from './data';
import { ElementType, RawData, MyOptional } from './types';

// 添加节点
export function addNode(params: { info: MyOptional<RawData, 'name' | 'elementType'>; prev: DataNode; }) {
  const { info, prev } = params;
  const next = DataNode.DataNodeMap.get(prev.out);
  // 创建节点
  const current = initNewNode(info, next!);
  // 添加节点
  const parent = prev.type === ElementType.BRANCH ? prev : prev.getParent();
  if (parent === prev) {
    parent?.insertChild(current, 0);
  } else {
    parent?.insertAfter(current, prev);
  }
  // 处理节点入口
  connectNodes({ node: prev!, next: current });
  // 处理节点出口
  connectNodes({ node: current, next: next! });
}

// 删除节点
export function deleteNode(params: { current: DataNode; }) {
  const { current } = params;
  const prev = DataNode.DataNodeMap.get(current.in);
  const next = DataNode.DataNodeMap.get(current.out);
  // 处理节点出口
  connectNodes({ node: prev!, next: next! });
  // 删除节点
  const parent = current.getParent();
  parent?.removeChild(current);
}

// 处理节点关系
function connectNodes(params: { node: DataNode; next: DataNode; }) {
  const { node, next } = params;
  // out
  if (node.type === ElementType.BRANCH) {
    const branchNode = node;
    branchNode.out = next.key;
  } else {
    node.out = next.key;
  }
  // in
  if (node.getParent() === next.getParent()) {
    next.in = node.key;
  }
  if (node.type === ElementType.BRANCH && node === next.getParent()) {
    next.in = node.getParent()?.key as string;
  }
}
