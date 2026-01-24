import { apiAPI } from '../api';


export async function listQuery(params: Parameters<typeof apiAPI.listQuery>[0]) {
  return apiAPI.listQuery(params);
}

export async function listAdd(params: Parameters<typeof apiAPI.listAdd>[0]) {
  return apiAPI.listAdd(params);
}

export async function listUpdate(params: Parameters<typeof apiAPI.listUpdate>[0]) {
  return apiAPI.listUpdate(params);
}

export async function listDelete(params: Parameters<typeof apiAPI.listDelete>[0]) {
  return apiAPI.listDelete(params);
}

export async function queryApiInfo(apiId:number) {
  return apiAPI.queryApiInfo(apiId);
}

export async function queryApiInfoByCode(apiCode: string) {
  return apiAPI.queryApiInfoByCode(apiCode);
}

export async function debugApi(apiId: number, params: { headerData: any; inputParamData: any }) {
  return apiAPI.debugApi(apiId, params);
}

export async function getApiListBySuiteId(params: Parameters<typeof apiAPI.getApiListBySuiteId>[0]) {
  return apiAPI.getApiListBySuiteId(params);
}

export async function getApiListBySuiteCode(suiteCode: string) {
  return apiAPI.getApiListBySuiteCode(suiteCode);
}
