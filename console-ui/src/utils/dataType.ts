
import { DataTypeItem } from '@/typings/flowDesign';

const DATA_TYPE_TEMP: Map<string, DataTypeItem> = new Map();

export function getDataTypeObject (json: string): DataTypeItem {
  if (DATA_TYPE_TEMP.has(json)) {
    return DATA_TYPE_TEMP.get(json) as DataTypeItem;
  }
  let result = null as unknown as DataTypeItem;
  try {
    if (json) {
      const dataType = JSON.parse(json);
      DATA_TYPE_TEMP.set(json, dataType);
      if (dataType.objectStructure && typeof dataType.objectStructure === 'string') {
        dataType.objectStructure = JSON.parse(dataType.objectStructure);
      }
      result = dataType;
    }
  } catch (error) {
    const message = `数据类型解析失败: 类型字符串为"${json}"'`;
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
