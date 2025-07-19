import { request, ResponsePageResult, type ResponseResult } from '@/service/base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function addToken(tokenDesc: string): ResponseResult<string> {
  return request.post(JUGGLE_API_PREFIX+`/token/add`, tokenDesc);
}

export async function deleteTokenById(tokenId: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/token/delete/${tokenId}`);
}

export async function updateTokenById(params: { id: number; tokenDesc: string }): ResponseResult<boolean> {
  return request.put(JUGGLE_API_PREFIX+`/token/update`, params);
}

export async function tokenPage(params: { pageNum: number; pageSize: number }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/token/page', params);
}
