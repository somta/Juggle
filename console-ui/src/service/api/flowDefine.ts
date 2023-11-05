import { request, type ResponsePageResult, type ResponseResult } from '../base';

export async function getDefineInfo (id: number): ResponseResult {
    return request.get(`/v1/flow/definition/info/${id}`);
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

export function deployFlowDefine(params: {
    flowDefinitionId: string,
    flowDeployVersion:string
}):ResponsePageResult {
    return request.post('/v1/flow/definition/deploy',params);
}

export async function debugFlow (flowKey: string, triggerData:{
    flowData?: Record<string, any>
}): ResponseResult<boolean> {
    return request.post(`/v1/flow/definition/debug/${flowKey}`,triggerData);
}

