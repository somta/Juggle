import { request, ResponsePageResult, ResponseResult } from '@/service/base';

export function updateFlowStatus(flowId: number, flowStatus: number): ResponsePageResult {
  return request.put('/v1/flow/status', { flowId: flowId, flowStatus: flowStatus });
}

export async function flowPage(params: { pageNum: number; pageSize: number; flowName?: string; flowType?: string }): ResponsePageResult {
  return request.post('/v1/flow/page', params);
}

export async function deleteFlow(id: number): ResponseResult<boolean> {
  return request.delete(`/v1/flow/delete/${id}`);
}
