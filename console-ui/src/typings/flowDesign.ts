export enum valueType {
  VARIABLE = 'VARIABLE',
  CONSTANT = 'CONSTANT',
  INPUT_PARAM = 'INPUT_PARAM',
  OUTPUT_PARAM = 'OUTPUT_PARAM',
  HEADER = 'HEADER'
}

export type DataTypeItem = {
  type: string;
  itemType: string;
  objectKey: string;
  objectStructure: string;
}

export type RuleItem = {
  source: string;
  sourceDataType: string;
  sourceType: valueType;
  target: string;
  targetDataType: string;
  targetType: valueType;
  required?: boolean;
};
