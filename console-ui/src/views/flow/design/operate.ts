import { DataBranchNode, DataNode } from './renderer/data';
import { ElementType, RawData, MyOptional } from './types';

// 添加节点
export function addNode(params: { info: MyOptional<RawData, 'name' | 'elementType'>; prev: DataNode; dataMap: Map<string, DataNode> }) {
  const { info, prev, dataMap } = params;
  const next = dataMap.get(prev.out);
  // 创建节点
  const currentRaw: RawData = {
    key: generateNodeKey(info.elementType),
    outgoings: [],
    incomings: [],
    ...info,
  };
  const current = initNewNode(currentRaw, next!);
  // 添加节点
  const parent = prev.type === ElementType.BRANCH ? prev : prev.getParent();
  if (parent === prev) {
    parent?.insertChild(current, 0);
  } else {
    parent?.insertAfter(current, prev);
  }
  dataMap.set(current.key, current);
  // 处理节点入口
  connectNodes({ node: prev!, next: current, dataMap });
  // 处理节点出口
  connectNodes({ node: current, next: next!, dataMap });
}

// 删除节点
export function deleteNode(params: { current: DataNode; dataMap: Map<string, DataNode> }) {
  const { current, dataMap } = params;
  const prev = dataMap.get(current.in);
  const next = dataMap.get(current.out);
  // 处理节点出口
  connectNodes({ node: prev!, next: next!, dataMap });
  // 删除节点
  const parent = current.getParent();
  parent?.removeChild(current);
  dataMap.delete(current.key);
}

// 处理节点关系
function connectNodes(params: { node: DataNode; next: DataNode; dataMap: Map<string, DataNode> }) {
  const { node, next } = params;
  // out
  if (node.type === ElementType.BRANCH) {
    const branchNode = node as DataBranchNode;
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

// 生成节点key
function generateNodeKey(type: ElementType) {
  return `${type.toLowerCase()}_${generateRandomKey()}`;
}

// 生成8位随机字符串
function generateRandomKey() {
  return Math.random().toString(36).substring(2, 7);
}

// 初始化节点
function initNewNode (info: MyOptional<RawData, 'name' | 'elementType'>, next: DataNode) {
  // 创建节点
  const currentRaw: RawData = {
    key: generateNodeKey(info.elementType),
    outgoings: [],
    incomings: [],
    ...info,
  };
  let current: DataNode;
  if (info.elementType === ElementType.CONDITION) {
    current = new DataNode(currentRaw);
    // 默认分支
    if (!currentRaw.conditions || currentRaw.conditions.length === 0) {
      currentRaw.conditions = [
        {
          conditionName: '条件1',
          conditionType: 'CUSTOM',
          expression: '',
          outgoing: next.key,
        },
        {
          conditionName: '默认else分支',
          conditionType: 'DEFAULT',
          expression: '',
          outgoing: next.key,
        },
      ];
    }
    currentRaw.conditions?.forEach((_, index) => {
      const branch = new DataBranchNode(currentRaw, index);
      current.addChild(branch);
    });
  } else {
    current = new DataNode(currentRaw);
  }
  return current;
}
