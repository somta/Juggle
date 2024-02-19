import { request, ResponseResult } from '@/service/base';

export function listDataType (): ResponseResult {
  return request.get('/v1/dataType/list');
}
