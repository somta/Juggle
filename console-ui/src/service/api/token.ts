import { request, ResponsePageResult, type ResponseResult } from '@/service/base';

export async function addToken(tokenDesc: string): ResponseResult<string> {
  return request.post(`/v1/token/add`, tokenDesc);
}

export async function deleteTokenById(tokenId: number): ResponseResult<boolean> {
  return request.delete(`/v1/token/delete/${tokenId}`);
}

export async function updateTokenById(params: { id: number; tokenDesc: string }): ResponseResult<boolean> {
  return request.put(`/v1/token/update`, params);
}

export async function tokenPage(params: { pageNum: number; pageSize: number }): ResponsePageResult {
  return request.post('/v1/token/page', params);
}
