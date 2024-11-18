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
  //console.log('添加节点，prev: %o, current: %o, next: %o', prev, current, next)
  connectNodes({ node: prev!, next: current, current: current });
  // 处理节点出口
  connectNodes({ node: current, next: next!, current: current });
  //console.dir(DataNode.DataNodeMap)
}

// 删除节点
export function deleteNode(params: { current: DataNode }) {
  const { current } = params;
  const prev = DataNode.DataNodeMap.get(current.in);
  const next = DataNode.DataNodeMap.get(current.out);
  // 删除节点
  current.delete();
  // 处理节点出口
  //console.log('删除节点，prev: %o, current: %o, next: %o', prev, current, next)
  connectNodes({ node: prev!, next: next!, current: current });
  //console.dir(DataNode.DataNodeMap)
}

// 处理节点关系：设置node的out, next的in
function connectNodes(params: { node: DataNode; next: DataNode; current: DataNode }) {
  const { node, next, current } = params;
  // out
  // node -> node/condition（正常）
  // branch -> node/condition（正常）
  // condition -> node/condition (额外工作 - 处理条件分支的outgoing(存在递归，在DataNode中处理))
  node.out = next.key;
  // in
  // node -> node/condition（正常）
  // branch -> node/condition (指向调整 - branch的父级)
  // condition -> node/condition （正常）
  if (current.type === ElementType.BRANCH) {
    next.in = current.getParent()?.key as string;
  } else {
    if (node.type === ElementType.BRANCH) {
      next.in = node.getParent()?.key as string;
    } else {
      next.in = node.key;
    }
  }
}
