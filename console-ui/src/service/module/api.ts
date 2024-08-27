import { apiAPI } from '../api';
import { ApiHeader } from '@/typings';

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

export async function queryApiInfo(params: Parameters<typeof apiAPI.queryApiInfo>[0]) {
  const res = await apiAPI.queryApiInfo(params);
  if (res.success) {
    const headerArray: ApiHeader[] = res.result.apiHeaders;
    if (Array.isArray(headerArray) && headerArray.length !== 0) {
      const paramArray = headerArray.map((item: ApiHeader) => {
        return {
          paramKey: item.headerKey,
          paramName: item.headerName,
          dataType: item.dataType,
          required: item.required,
          paramDesc: item.headerDesc,
        };
      });
      res.result.apiHeaders = paramArray as any;
    }
  }
  return res;
}

export async function debugApi(apiId: number, params: { headerData: any; inputParamData: any }) {
  return apiAPI.debugApi(apiId, params);
}

export async function getApiListBySuiteId(params: Parameters<typeof apiAPI.getApiListBySuiteId>[0]) {
  return apiAPI.getApiListBySuiteId(params);
}
