
import { DataTypeItem } from '@/typings/flowDesign';

const DATA_TYPE_TEMP: Map<string, DataTypeItem> = new Map();

export function getDataTypeObject (dataType: any): DataTypeItem {
  console.log("getDataTypeObject",dataType)
  const key = JSON.stringify(dataType);
  if (DATA_TYPE_TEMP.has(key)) {
    return DATA_TYPE_TEMP.get(key) as DataTypeItem;
  }
  let result = null as unknown as DataTypeItem;
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

export function isDataTypeEqual (typeA: string, typeB: string) {
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
