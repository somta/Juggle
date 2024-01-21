
import { FlowVariableDataType } from './types';

const VARIABLE_TYPE_TEMP: Map<string, FlowVariableDataType> = new Map();

export function getVariableDataType (json: string): FlowVariableDataType {
  if (VARIABLE_TYPE_TEMP.has(json)) {
    return VARIABLE_TYPE_TEMP.get(json) as FlowVariableDataType;
  }
  try {
    const dataType = JSON.parse(json);
    VARIABLE_TYPE_TEMP.set(json, dataType);
    return dataType;
  } catch (error) {
    throw new Error(`数据类型解析失败: 类型字符串为"${json}"'`);
  }
}
