import { request, type ResponseResult } from '@/service/base';

export function login(data: { userName: string; password: string }): ResponseResult {
  return request.post('/v1/user/login', data);
}

export function logout() {}

export function check() {}
