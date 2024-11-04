import { DataTypeItem } from '@/typings/flowDesign';
import {FlowVariable} from "@/views/flow/design";

const DATA_TYPE_TEMP: Map<string, DataTypeItem> = new Map();

export function getDataTypeObject(dataType: any): DataTypeItem {
  //console.log("getDataTypeObject",dataType)
  let result = null as unknown as DataTypeItem;
  if (!dataType) {
    return result;
  }
  const key = JSON.stringify(dataType);
  if (DATA_TYPE_TEMP.has(key)) {
    return DATA_TYPE_TEMP.get(key) as DataTypeItem;
  }
  try {
    if (key) {
      //const dataType = JSON.parse(json);
      DATA_TYPE_TEMP.set(key, dataType);
      if (dataType.objectStructure && typeof dataType.objectStructure === 'string') {
        dataType.objectStructure = JSON.parse(dataType.objectStructure);
      }
      result = dataType;
    }
  } catch (error) {
    const message = `数据类型解析失败: 类型对象为"${key}"'`;
    console.error(new Error(message));
  }
  return result;
}

export function isDataTypeEqual(typeA: DataTypeItem, typeB: DataTypeItem) {
  if (typeA === typeB) {
    return true;
  }
  const dataTypeA = getDataTypeObject(typeA);
  const dataTypeB = getDataTypeObject(typeB);
  if (!dataTypeA || !dataTypeB) {
    return false;
  }
  if (dataTypeA.type !== dataTypeB.type) {
    return false;
  }
  if (dataTypeA.type === 'List') {
    if (dataTypeA.itemType !== dataTypeB.itemType) {
      return false;
    }
  }
  if (dataTypeA.type === 'Object' || dataTypeA.itemType === 'Object') {
    if (dataTypeA.objectKey !== dataTypeB.objectKey) {
      return false;
    }
  }
  return true;
}

export function isDataTypeMatch(sourceDataTypeItem: DataTypeItem, targetDataTypeItem: DataTypeItem) {
  if (!targetDataTypeItem || !targetDataTypeItem) {
    return false;
  }
  if (sourceDataTypeItem === targetDataTypeItem) {
    return true;
  }
  if (sourceDataTypeItem.type == sourceDataTypeItem.type) {
    return true;
  }
  //原始类似是对象的变量也要返回
  if(sourceDataTypeItem.type == "Object"){
    return true;
  }
}


export function getVariableDataType(envKey:string, envList:FlowVariable[]){
  let variable;
  if (envKey.includes('.')) {
    variable = envKey.split('.').reduce((acc, cur) => {
      if (acc) {
        return (acc?.dataType?.objectStructure).find((item: any) => item.propKey === cur);
      }
      return envList.find(item => item.envKey === cur);
    }, null as any);
  }else{
    variable = envList.find(item => item.envKey === envKey);
  }
  return variable;
}

