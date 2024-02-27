import type { ApiInfo } from '@/typings';
import { request, type ResponsePageResult, type ResponseResult } from '../base';

// 领域
export async function domainQuery(params: { pageNum: number; pageSize: number; domainName?: string }): ResponsePageResult {
  return request.post('/v1/domain/page', params);
}

export async function domainList(): ResponseResult {
  return request.get('/v1/domain/list');
}

export async function domainAdd(params: { domainCode: string; domainName: string; domainDesc: string }): ResponseResult<boolean> {
  return request.post('/v1/domain/add', params);
}

export async function domainUpdate(params: { id: string; domainCode: string; domainName: string; domainDesc: string }): ResponseResult<boolean> {
  return request.put('/v1/domain/update', params);
}

export async function domainDelete(id: string): ResponseResult<boolean> {
  return request.delete(`/v1/domain/delete/${id}`);
}

// 列表
export async function listQuery(params: {
  pageNum: number;
  pageSize: number;
  domainId?: number;
  apiName?: string;
  apiUrl?: string;
}): ResponsePageResult {
  return request.post('/v1/api/page', params);
}

export async function listAdd(params: {
  domainId: number;
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
  domainId?: number;
  apiUrl?: string;
  apiName?: string;
  apiDesc?: string;
  apiRequestType?: string;
  apiRequestContentType?: string;
}): ResponseResult<boolean> {
  return request.put('/v1/api/update', params);
}

export async function listDelete(id: string): ResponseResult<boolean> {
  return request.delete(`/v1/api/delete/${id}`);
}

export async function queryApiInfo(id: string | number): ResponseResult<ApiInfo> {
  return request.get(`/v1/api/info/${id}`);
}

export async function getApiListByDomainId(domainId: string | number): ResponseResult<ApiInfo[]> {
  return request.post(`/v1/api/getApiListByDomainId/${domainId}`);
}
