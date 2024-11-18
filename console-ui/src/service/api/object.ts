import { request, ResponsePageResult, type ResponseResult } from '@/service/base';
import { ObjectInfo, ObjectProperty } from '@/typings';

export async function addObject(params: {
  objectKey: string;
  objectName: string;
  objectDesc?: string;
  props?: ObjectProperty[];
}): ResponseResult<boolean> {
  return request.post(`/v1/object/add`, params);
}

export async function deleteObjectById(objectId: number): ResponseResult<boolean> {
  return request.delete(`/v1/object/delete/${objectId}`);
}

export async function updateObjectById(params: {
  objectKey: string;
  objectName: string;
  objectDesc?: string;
  props?: ObjectProperty[];
}): ResponseResult<boolean> {
  return request.put(`/v1/object/update`, params);
}

export async function queryObjectInfo(objectId: number): ResponseResult<ObjectInfo> {
  return request.get(`/v1/object/info/${objectId}`);
}

export async function objectPage(params: { pageNum: number; pageSize: number; objectName?: string }): ResponsePageResult {
  return request.post('/v1/object/page', params);
}
