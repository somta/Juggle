export enum valueType {
  VARIABLE = 'VARIABLE',
  CONSTANT = 'CONSTANT',
  INPUT_PARAM = 'INPUT_PARAM',
  OUTPUT_PARAM = 'OUTPUT_PARAM',
  HEADER = 'HEADER'
}

export type DataTypeItem = {
  type: string;
  itemType?: string | null;
  objectKey?: string | null;
  objectStructure?: string| null;
}

export type RuleItem = {
  source: string;
  sourceDataType: DataTypeItem | null;
  sourceType: valueType;
  target: string;
  targetDataType: DataTypeItem | null;
  targetType: valueType;
  required?: boolean;
};
