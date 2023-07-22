

export interface ApiInputParams {
  id: number | null;	
  paramType: number;	
  paramKey: string;	
  paramName: string;	
  dataType: string;	
  required: boolean;	
  sourceType: string;	
  sourceId: number;
}

export interface ApiOutputParams {
  id: number | null;
  paramType: number;
  paramKey: string;
  paramName: string;
  dataType: string;
  required: boolean;
  sourceType: string;
  sourceId: number;
}

export interface ApiInfo {
  id: number | null;
  domainId: number | null;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiInputParams: ApiInputParams[];
  apiOutputParams: ApiOutputParams[];
}