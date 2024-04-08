import { request, type ResponsePageResult, type ResponseResult } from '../base';

export async function addSuite(params: { suiteCode: string; suiteName: string; suiteDesc: string }): ResponseResult<boolean> {
  return request.post('/v1/suite/add', params);
}

export async function deleteSuite(id: number): ResponseResult<boolean> {
  return request.delete(`/v1/suite/delete/${id}`);
}

export async function updateSuite(params: { id: string; suiteCode: string; suiteName: string; suiteDesc: string }): ResponseResult<boolean> {
  return request.put('/v1/suite/update', params);
}

export async function suitePage(params: { pageNum: number; pageSize: number; suiteName?: string }): ResponsePageResult {
  return request.post('/v1/suite/page', params);
}

export async function suiteList(): ResponseResult {
  return request.get('/v1/suite/list');
}
