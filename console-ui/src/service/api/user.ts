import { request, type ResponseResult } from '@/service/base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export function login(data: { userName: string; password: string }): ResponseResult {
  return request.post(JUGGLE_API_PREFIX+'/user/login', data);
}

export function logout() {}

export function check() {}

export function getProductInfo(): ResponseResult<string>  {
  return request.get(JUGGLE_API_PREFIX+'/user/product/info');
}