import { InputParams, OutputParams } from '@/typings/parameter.ts';

export interface ApiHeader {
  paramKey: string;
  paramName: string;
  dataType: string;
  required: boolean;
  paramDesc: string;
}

export interface ApiInfo {
  id: number | null;
  suiteId: number | null;
  suiteFlag?: number | null;
  apiCode: string;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiRequestContentType: string;
  apiHeaders: ApiHeader[];
  apiInputParams: InputParams[];
  apiOutputParams: OutputParams[];
}
