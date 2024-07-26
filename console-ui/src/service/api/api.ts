import type { ApiInfo } from '@/typings';
import { request, type ResponsePageResult, type ResponseResult } from '../base';

export async function listQuery(params: {
  pageNum: number;
  pageSize: number;
  suiteId?: number;
  apiName?: string;
  apiUrl?: string;
}): ResponsePageResult {
  return request.post('/v1/api/page', params);
}

export async function listAdd(params: {
  suiteId: number;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiRequestContentType: string;
}): ResponseResult<boolean> {
  return request.post('/v1/api/add', params);
}

export async function listUpdate(params: {
  id: number;
  suiteId?: number;
  apiUrl?: string;
  apiName?: string;
  apiDesc?: string;
  apiRequestType?: string;
  apiRequestContentType?: string;
}): ResponseResult<boolean> {
  return request.put('/v1/api/update', params);
}

export async function listDelete(id: number): ResponseResult<boolean> {
  return request.delete(`/v1/api/delete/${id}`);
}

export async function queryApiInfo(id: number): ResponseResult<ApiInfo> {
  return request.get(`/v1/api/info/${id}`);
}

export function debugApi(apiId: number, params: { headerData: any; inputParamData: any }): ResponseResult<any> {
  return request.post(`/v1/api/debug/${apiId}`, params);
}

export async function getApiListBySuiteId(suiteId: number): ResponseResult<ApiInfo[]> {
  return request.post(`/v1/api/getApiListBySuiteId/${suiteId}`);
}
