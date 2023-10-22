import {request, ResponsePageResult, ResponseResult} from "@/service/base";

export function getLatestDeployVersion(flowId: number):ResponsePageResult {
    return request.get('/v1/flow/version/latest/'+flowId);
}

export function updateFlowVersionStatus(flowVersionId: number, flowVersionStatus: number):ResponsePageResult {
    return request.put('/v1/flow/version/status', {flowVersionId:flowVersionId,flowVersionStatus:flowVersionStatus});
}

export async function flowVersionPage (params: {
    pageNum: number;
    pageSize: number;
    flowId: number;
    flowVersionStatus?: number;
}): ResponsePageResult {
    return request.post('/v1/flow/version/page', params);
}

export async function deleteFlowVersion (id: number): ResponseResult<boolean> {
    return request.delete(`/v1/flow/version/delete/${id}`);
}