import {flowAPI, flowDefineAPI} from "@/service/api";

export async function updateFlowStatus(flowId: number, flowStatus: number) {
    return flowAPI.updateFlowStatus(flowId,flowStatus);
}

export async function queryFlowPage (params: Parameters<typeof flowAPI.flowPage>[0]) {
    return flowAPI.flowPage(params);
}

export async function deleteFlowById (params: Parameters<typeof flowAPI.deleteFlow>[0]) {
    return flowAPI.deleteFlow(params);
}