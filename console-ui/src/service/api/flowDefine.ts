import { request, type ResponsePageResult, type ResponseResult } from '../base';
import { FlowDefineInfo, InputParams, OutputParams } from '@/typings';
import { FlowVariable } from '@/views/flow/design';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function addDefineInfo(params: {
  flowName: string;
  flowType: string;
  remark?: string;
  flowInputParams?: InputParams[];
  flowOutputParams?: OutputParams[];
}): ResponseResult<boolean> {
  return request.post(JUGGLE_API_PREFIX+`/flow/definition/add`, params);
}

export async function getDefineInfo(id: number): ResponseResult<FlowDefineInfo> {
  return request.get(JUGGLE_API_PREFIX+`/flow/definition/info/${id}`);
}

export async function flowDefinePage(params: { pageNum: number; pageSize: number; flowName?: string; flowType?: string }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/flow/definition/page', params);
}

export async function deleteFlowDefine(id: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/flow/definition/delete/${id}`);
}

export async function updateDefineInfo(params: {
  id: number;
  flowName: string;
  flowType: string;
  remark?: string;
  flowInputParams?: InputParams[];
  flowOutputParams?: OutputParams[];
}): ResponseResult<boolean> {
  return request.put(JUGGLE_API_PREFIX+`/flow/definition/update`, params);
}

export async function saveFlowContent(params: { id: number; flowContent: string; flowVariables?: FlowVariable[] }): ResponseResult<boolean> {
  return request.put(JUGGLE_API_PREFIX+'/flow/definition/save', params);
}

export function deployFlowDefine(params: { flowDefinitionId: string; flowDeployVersion: string; flowVersionRemark: string }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/flow/definition/deploy', params);
}

export async function debugFlow(
  flowKey: string,
  triggerData: {
    flowData?: Record<string, any>;
  }
): ResponseResult {
  return request.post(JUGGLE_API_PREFIX+`/flow/definition/debug/${flowKey}`, triggerData);
}
