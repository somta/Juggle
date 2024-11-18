import {DataType} from "@/typings/dataType.ts";

export enum valueType {
  VARIABLE = 'VARIABLE',
  CONSTANT = 'CONSTANT',
  INPUT_PARAM = 'INPUT_PARAM',
  OUTPUT_PARAM = 'OUTPUT_PARAM',
  HEADER = 'HEADER',
}

export type RuleItem = {
  source: string;
  sourceDataType: DataType | null;
  sourceType: valueType;
  target: string;
  targetDataType: DataType | null;
  targetType: valueType;
  required?: boolean;
};
