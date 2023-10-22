import { request, type ResponsePageResult, type ResponseResult } from '../base';

export function deployFlowDefine(params: {
    flowDefinitionId: string,
    flowDeployVersion:string
}):ResponsePageResult {
    return request.post('/v1/flow/definition/deploy',params);
}

export async function flowDefinePage (params: {
    pageNum: number;
    pageSize: number;
    flowName?: string;
    flowType?: string;
}): ResponsePageResult {
    return request.post('/v1/flow/definition/page', params);
}

export async function deleteFlowDefine (id: number): ResponseResult<boolean> {
    return request.delete(`/v1/flow/definition/delete/${id}`);
}

