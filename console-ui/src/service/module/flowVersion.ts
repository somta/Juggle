import {flowVersionAPI} from "@/service/api";

export async function updateFlowVersionStatus(flowId: number, flowStatus: number) {
    return flowVersionAPI.updateFlowVersionStatus(flowId,flowStatus);
}

export async function queryFlowVersionPage (params: Parameters<typeof flowVersionAPI.flowVersionPage>[0]) {
    return flowVersionAPI.flowVersionPage(params);
}

export async function deleteFlowVersionById (params: Parameters<typeof flowVersionAPI.deleteFlowVersion>[0]) {
    return flowVersionAPI.deleteFlowVersion(params);
}