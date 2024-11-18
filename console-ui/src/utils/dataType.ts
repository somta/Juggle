import {FlowVariable} from "@/views/flow/design";
import {DataType} from "@/typings";

const DATA_TYPE_TEMP: Map<string, DataType> = new Map();

export function getDataTypeObject(dataType: any): DataType {
  //console.log("getDataTypeObject",dataType)
  let result = null as unknown as DataType;
  if (!dataType) {
    return result;
  }
  const key = JSON.stringify(dataType);
  if (DATA_TYPE_TEMP.has(key)) {
    return DATA_TYPE_TEMP.get(key) as DataType;
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

export function isDataTypeEqual(typeA: DataType, typeB: DataType) {
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

/**
 *
 * @param sourceDataTypeItem
 * @param filterDataType 期望的数据类型
 */
export function isDataTypeMatch(sourceDataTypeItem: DataType, filterDataType: DataType) {
  if (!sourceDataTypeItem || !filterDataType) {
    return false;
  }
  if (sourceDataTypeItem === filterDataType) {
    return true;
  }
  if (sourceDataTypeItem.type == filterDataType.type) {
    return true;
  }

  if(filterDataType.type == "String" || filterDataType.type == "Integer" ||
      filterDataType.type == "Double" || filterDataType.type == "Boolean" ||
      filterDataType.type == "Date" || filterDataType.type == "Time"){
    if(sourceDataTypeItem.type == filterDataType.type || sourceDataTypeItem.type == 'Object'){
      return true;
    }
  }

  /*if(filterDataType.type == "Object"){
    if(sourceDataTypeItem.type == 'Object' && sourceDataTypeItem.objectKey == filterDataType.objectKey){
      return true;
    }
  }*/
  return false;
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

