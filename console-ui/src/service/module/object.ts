import {objectAPI} from "@/service/api";

export async function deleteObject(objectId:number) {
    return objectAPI.deleteObjectById(objectId);
}

export async function queryObjectPage(params: Parameters<typeof objectAPI.objectPage>[0]) {
    return objectAPI.objectPage(params);
}