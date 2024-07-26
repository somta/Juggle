import { DataNode, initNewNode } from './data';
import { ElementType, RawData, MyOptional } from './types';

// 添加节点
export function addNode(params: { info: MyOptional<RawData, 'name' | 'elementType'>; prev: DataNode }) {
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
export function deleteNode(params: { current: DataNode }) {
  const { current } = params;
  const prev = DataNode.DataNodeMap.get(current.in);
  const next = DataNode.DataNodeMap.get(current.out);
  // 处理节点出口
  connectNodes({ node: prev!, next: next! });
  // 删除节点
  current.delete();
}

// 处理节点关系
function connectNodes(params: { node: DataNode; next: DataNode }) {
  const { node, next } = params;
  // out
  // node -> node/condition（正常）
  // branch -> node/condition（正常）
  // condition -> node/condition (额外工作 - 处理条件分支的outgoing(存在递归，在DataNode中处理))
  node.out = next.key;
  // in
  // node -> node/condition（正常）
  // branch -> node/condition (指向调整 - branch的父级)
  // condition -> node/condition （正常）
  next.in = node.key;
  if (node.type === ElementType.BRANCH) {
    next.in = node.getParent()?.key as string;
  }
}
