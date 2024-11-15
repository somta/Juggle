import {DataType} from "@/typings/dataType.ts";

export interface InputParams {
  paramKey: string;
  paramName: string;
  dataType: DataType;
  paramPosition: string;
  required: boolean;
  paramDesc: string;
}

export interface OutputParams {
  paramKey: string;
  paramName: string;
  dataType: DataType;
  paramDesc: string;
}
