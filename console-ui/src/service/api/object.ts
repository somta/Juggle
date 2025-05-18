import { request, ResponsePageResult, type ResponseResult } from '@/service/base';
import { ObjectInfo, ObjectProperty } from '@/typings';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function addObject(params: {
  objectKey: string;
  objectName: string;
  objectDesc?: string;
  props?: ObjectProperty[];
}): ResponseResult<boolean> {
  return request.post(JUGGLE_API_PREFIX+`/object/add`, params);
}

export async function deleteObjectById(objectId: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/object/delete/${objectId}`);
}

export async function updateObjectById(params: {
  objectKey: string;
  objectName: string;
  objectDesc?: string;
  props?: ObjectProperty[];
}): ResponseResult<boolean> {
  return request.put(JUGGLE_API_PREFIX+`/object/update`, params);
}

export async function queryObjectInfo(objectId: number): ResponseResult<ObjectInfo> {
  return request.get(JUGGLE_API_PREFIX+`/object/info/${objectId}`);
}

export async function isExistObjectKey(params: {
  id: number | null;
  objectKey: string;
}): ResponseResult<boolean> {
  return request.post(JUGGLE_API_PREFIX+`/object/exist/key`,params);
}

export async function objectPage(params: { pageNum: number; pageSize: number; objectName?: string }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/object/page', params);
}
