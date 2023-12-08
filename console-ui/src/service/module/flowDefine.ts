import { flowDefineAPI } from '@/service/api'

export async function getDefineInfo(id: string) {
  return flowDefineAPI.getDefineInfo(id)
}

export async function queryFlowDefinePage(params: Parameters<typeof flowDefineAPI.flowDefinePage>[0]) {
  return flowDefineAPI.flowDefinePage(params)
}

export async function deleteFlowDefineById(params: Parameters<typeof flowDefineAPI.deleteFlowDefine>[0]) {
  return flowDefineAPI.deleteFlowDefine(params)
}

export async function deployFlowDefine(params: Parameters<typeof flowDefineAPI.deployFlowDefine>[0]) {
  return flowDefineAPI.deployFlowDefine(params)
}

export async function debugFlow(flowKey: string, params: Parameters<typeof flowDefineAPI.debugFlow>[1]) {
  return flowDefineAPI.debugFlow(flowKey, params)
}
