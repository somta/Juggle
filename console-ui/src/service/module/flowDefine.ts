import {flowDefineAPI} from "@/service/api";

export async function deployFlowDefine(params: Parameters<typeof flowDefineAPI.deployFlowDefine>[0]) {
    return flowDefineAPI.deployFlowDefine(params);
}


export async function queryFlowDefinePage (params: Parameters<typeof flowDefineAPI.flowDefinePage>[0]) {
    return flowDefineAPI.flowDefinePage(params);
}

export async function deleteFlowDefineById (params: Parameters<typeof flowDefineAPI.deleteFlowDefine>[0]) {
    return flowDefineAPI.deleteFlowDefine(params);
}
