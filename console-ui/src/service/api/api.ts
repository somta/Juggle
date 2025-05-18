import type { ApiInfo } from '@/typings';
import { request, type ResponsePageResult, type ResponseResult } from '../base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function listQuery(params: {
  pageNum: number;
  pageSize: number;
  suiteId?: number;
  apiName?: string;
  apiUrl?: string;
}): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/api/page', params);
}

export async function listAdd(params: {
  suiteId: number;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiRequestContentType: string;
}): ResponseResult<boolean> {
  return request.post(JUGGLE_API_PREFIX+'/api/add', params);
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
  return request.put(JUGGLE_API_PREFIX+'/api/update', params);
}

export async function listDelete(id: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/api/delete/${id}`);
}

export async function queryApiInfo(id: number): ResponseResult<ApiInfo> {
  return request.get(JUGGLE_API_PREFIX+`/api/info/${id}`);
}

export async function queryApiInfoByCode(apiCode: string): ResponseResult<ApiInfo> {
  return request.get(JUGGLE_API_PREFIX+`/api/info/code/${apiCode}`);
}

export function debugApi(apiId: number, params: { headerData: any; inputParamData: any }): ResponseResult<any> {
  return request.post(JUGGLE_API_PREFIX+`/api/debug/${apiId}`, params);
}

export async function getApiListBySuiteId(suiteId: number): ResponseResult<ApiInfo[]> {
  return request.post(JUGGLE_API_PREFIX+`/api/getApiListBySuiteId/${suiteId}`);
}

export async function getApiListBySuiteCode(suiteCode: string): ResponseResult<ApiInfo[]> {
  return request.post(JUGGLE_API_PREFIX+`/api/getApiListBySuiteCode/${suiteCode}`);
}
