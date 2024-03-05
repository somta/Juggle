import { flowDefineAPI } from '@/service/api';

export async function addDefineInfo(params: Parameters<typeof flowDefineAPI.addDefineInfo>[0]) {
  return flowDefineAPI.addDefineInfo(params);
}

export async function getDefineInfo(id: number) {
  return flowDefineAPI.getDefineInfo(id);
}

export async function queryFlowDefinePage(params: Parameters<typeof flowDefineAPI.flowDefinePage>[0]) {
  return flowDefineAPI.flowDefinePage(params);
}

export async function deleteFlowDefineById(params: Parameters<typeof flowDefineAPI.deleteFlowDefine>[0]) {
  return flowDefineAPI.deleteFlowDefine(params);
}

export async function updateDefineInfo(params: Parameters<typeof flowDefineAPI.updateDefineInfo>[0]) {
  return flowDefineAPI.updateDefineInfo(params);
}

export async function saveFlowContent(params: Parameters<typeof flowDefineAPI.saveFlowContent>[0]) {
  return flowDefineAPI.saveFlowContent(params);
}

export async function deployFlowDefine(params: Parameters<typeof flowDefineAPI.deployFlowDefine>[0]) {
  return flowDefineAPI.deployFlowDefine(params);
}

export async function debugFlow(flowKey: string, params: Parameters<typeof flowDefineAPI.debugFlow>[1]) {
  return flowDefineAPI.debugFlow(flowKey, params);
}
