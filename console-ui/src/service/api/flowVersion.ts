import {request, ResponsePageResult, ResponseResult} from "@/service/base";

export function updateFlowVersionStatus(flowId: number, flowStatus: number):ResponsePageResult {
    return request.put('/v1/flow/status', {flowId:flowId,flowStatus:flowStatus});
}


export async function flowVersionPage (params: {
    pageNum: number;
    pageSize: number;
    flowId: number;
}): ResponsePageResult {
    return request.post('/v1/flow/page', params);
}

export async function deleteFlowVersion (id: number): ResponseResult<boolean> {
    return request.delete(`/v1/flow/delete/${id}`);
}