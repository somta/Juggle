import {request, ResponsePageResult, ResponseResult} from "@/service/base";

export function addVariable(params: { envKey: string; envName: string; dataType: string; flowDefinitionId: number}): ResponsePageResult {
    return request.post('/v1/variable/add', params);
}

export async function deleteVariable(flowDefinitionId: number, variableId: number): ResponseResult<boolean> {
    return request.delete(`/v1/variable/delete/${flowDefinitionId}/${variableId}`);
}

export function updateVariable(params: { id:number; envKey: string; envName: string; dataType: string; flowDefinitionId: number}): ResponsePageResult {
    return request.put('/v1/variable/update', params);
}

export function getVariableInfoList(flowDefinitionId: number): ResponsePageResult {
    return request.get('/v1/variable/list/' + flowDefinitionId);
}