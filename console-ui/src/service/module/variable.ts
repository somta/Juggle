import {variableAPI} from '@/service/api';
import {Variable} from "@/typings";


export async function queryFlowDefineVariableList(flowDefinitionId: number) {
  const variableInfoList: Array<Variable>= variableAPI.getVariableInfoList(flowDefinitionId);
  if(variableInfoList.length !== 0){
    //let inputParamVariableList = variableInfoList.
    //todo 组装变量的treeData数据
  }
  return variableAPI.getVariableInfoList(flowDefinitionId);
}


