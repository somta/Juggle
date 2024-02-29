export enum valueType {
  VARIABLE = 'VARIABLE',
  INPUT_rule = 'INPUT_rule',
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
};
