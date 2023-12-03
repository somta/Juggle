
import { LayoutNode } from './renderer/layout/LayoutNode';
import { NodeData, ElementType } from './types';

// 添加节点
export function addNode (datas: NodeData[], node: LayoutNode, info: { name:  string, type: ElementType }) {
  const newDatas = [...datas];
  // 创建节点
  const current: NodeData = {
    key: generateNodeKey(info.type),
    elementType: info.type,
    outgoings: [],
    incomings: [],
    name: info.name,
  };
  const prev = newDatas.find((item) => item.key === node.data.key)!;
  const next = newDatas.find((item) => item.key === node.data.outgoings?.[0])!;
  // 处理节点入口
  connectNodes(prev, current);
  // 处理节点出口
  connectNodes(current, next);
  // 添加节点
}

function connectNodes (node: NodeData, next: NodeData) {
  node.outgoings?.push(next.key);
  next.incomings?.push(node.key);
}


// 删除节点
export function deleteNode (node: LayoutNode, datas: NodeData[]) {
  // 处理节点入口节点出口
  // 处理节点入口
  // 处理节点出口节点入口
  // 处理节点出口
  // 其他处理
  // 删除节点
}

// 生成节点key
function generateNodeKey (type: ElementType) {
  return `${type.toLowerCase()}_${generateRandomKey()}`;
}

// 生成8位随机字符串
function generateRandomKey() {
  return Math.random().toString(36).substring(2, 10);
}
