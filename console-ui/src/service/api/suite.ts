import { request, type ResponsePageResult, type ResponseResult } from '../base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function addSuite(params: { suiteCode: string; suiteName: string; suiteDesc: string }): ResponseResult<boolean> {
  return request.post(JUGGLE_API_PREFIX+'/suite/add', params);
}

export async function deleteSuite(id: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/suite/delete/${id}`);
}

export async function updateSuite(params: { id: string; suiteCode: string; suiteName: string; suiteDesc: string }): ResponseResult<boolean> {
  return request.put(JUGGLE_API_PREFIX+'/suite/update', params);
}

export async function suitePage(params: { pageNum: number; pageSize: number; suiteName?: string }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/suite/page', params);
}

export async function suiteList(): ResponseResult {
  return request.get(JUGGLE_API_PREFIX+'/suite/list');
}
