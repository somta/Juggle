import { InputParams, OutputParams } from '@/typings/parameter.ts';

export interface ApiHeader {
  headerKey: string;
  headerName: string;
  dataType: string;
  required: boolean;
}

export interface ApiInfo {
  id: number | null;
  suiteId: number | null;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiRequestContentType: string;
  apiHeaders: ApiHeader[];
  apiInputParams: InputParams[];
  apiOutputParams: OutputParams[];
}
