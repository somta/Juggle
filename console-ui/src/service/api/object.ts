import {request, ResponsePageResult, type ResponseResult} from '@/service/base';

export async function deleteObjectById(objectId: number): ResponseResult<boolean> {
  return request.delete(`/v1/object/delete/${objectId}`);
}

export async function objectPage(params: { pageNum: number; pageSize: number;objectName?:string }): ResponsePageResult {
  return request.post('/v1/object/page', params);
}
