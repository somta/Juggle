import { DataNode } from './renderer/data'
import { ElementType, RawData, MyOptional } from './types'

// 添加节点
export function addNode(params: { info: MyOptional<RawData, 'name' | 'elementType'>; prev: DataNode; dataMap: Map<string, DataNode> }) {
  const { info, prev, dataMap } = params
  // 创建节点
  const currentRaw: RawData = {
    key: generateNodeKey(info.elementType),
    outgoings: [],
    incomings: [],
    ...info,
  }
  const current = new DataNode(currentRaw)
  const next = dataMap.get(prev.out)
  // 处理节点入口
  connectNodes({ node: prev!, next: current, dataMap })
  // 处理节点出口
  connectNodes({ node: current, next: next!, dataMap })
  // 添加节点
  prev.getChildren().push(current)
  dataMap.set(current.key, current)
}

// 删除节点
export function deleteNode(params: { current: DataNode; dataMap: Map<string, DataNode> }) {
  const { current, dataMap } = params
  const prev = dataMap.get(current.in)
  const next = dataMap.get(current.out)
  // 处理节点入口
  connectNodes({ node: prev!, next: current, dataMap })
  // 处理节点出口
  connectNodes({ node: current, next: next!, dataMap })
  // 删除节点
  const parent = current.getParent()
  parent?.removeChild(current)
  dataMap.delete(current.key)
}

// 处理节点关系
function connectNodes(params: { node: DataNode; next: DataNode; dataMap: Map<string, DataNode> }) {
  const { node, next } = params
  node.out = next.key
  next.in = node.key
}

// 生成节点key
function generateNodeKey(type: ElementType) {
  return `${type.toLowerCase()}_${generateRandomKey()}`
}

// 生成8位随机字符串
function generateRandomKey() {
  return Math.random().toString(36).substring(2, 10)
}
