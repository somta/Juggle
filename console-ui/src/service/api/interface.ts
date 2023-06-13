
import { request, type ResponsePageResult, type ResponseResult } from '../base';

export async function domainQuery (params: {
  pageNum: number;
  pageSize: number;
  domainName: string;
}): ResponsePageResult {
  return request.post('/v1/domain/page', params);
}

export async function domainAdd (params: {
  domainCode: string;
  domainName: string;
  domainDesc: string;
}): ResponseResult<boolean> {
  return request.post('/v1/domain/add', params);
}

export async function domainUpdate (params: {
  id: string;
  domainCode: string;
  domainName: string;
  domainDesc: string;
}): ResponseResult<boolean> {
  return request.put('/v1/domain/update', params);
}

export async function domainDelete (id: string): ResponseResult<boolean> {
  return request.delete(`/v1/domain/delete/${id}`);
}
