import { request, type ResponsePageResult, type ResponseResult } from '../base';
import {queryFlowDefinePage} from "@/service/module/flowDefine";

export async function flowDefinePage (params: {
    pageNum: number;
    pageSize: number;
}): ResponsePageResult {
    return request.post('/v1/flow/definition/page', params);
}

export async function deleteFlowDefine (id: number): ResponseResult<boolean> {
    return request.delete(`/v1/flow/definition/delete/${id}`);
}
