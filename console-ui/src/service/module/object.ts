import { objectAPI} from "@/service/api";
import {ResponseResult} from "@/service/base";
import {ObjectInfo} from "@/typings";

export async function addObject(params: Parameters<typeof objectAPI.addObject>[0]) {
    console.log(params);
    return objectAPI.addObject(params);
}

export async function deleteObject(objectId:number) {
    return objectAPI.deleteObjectById(objectId);
}

export async function updateObject(params: Parameters<typeof objectAPI.updateObjectById>[0]) {
    return objectAPI.updateObjectById(params);
}

export async function queryObjectInfo(objectId: number): ResponseResult<ObjectInfo> {
    return objectAPI.queryObjectInfo(objectId);
}

export async function queryObjectPage(params: Parameters<typeof objectAPI.objectPage>[0]) {
    return objectAPI.objectPage(params);
}