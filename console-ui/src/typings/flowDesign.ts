export enum valueType {
  VARIABLE = 'VARIABLE',
  INPUT_PARAM = 'INPUT_PARAM',
}

export type DataTypeItem = {
  type: string;
  itemType: string;
  objectKey: string;
  objectStructure: string;
}

export type RuleItem = {
  source: string;
  sourceDataType: DataTypeItem;
  sourceType: valueType;
  target: string;
  targetDataType: DataTypeItem;
  targetType: valueType;
  required?: boolean;
};
