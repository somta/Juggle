export interface ApiHeader {
  headerKey: string;
  headerName: string;
  dataType: string;
  required: boolean;
}

export interface ApiInputParams {
  paramKey: string;
  paramName: string;
  dataType: string;
  required: boolean;
}

export interface ApiOutputParams {
  paramKey: string;
  paramName: string;
  dataType: string;
}

export interface ApiInfo {
  id: number | null;
  domainId: number | null;
  apiUrl: string;
  apiName: string;
  apiDesc: string;
  apiRequestType: string;
  apiRequestContentType: string;
  apiHeaders: ApiHeader[];
  apiInputParams: ApiInputParams[];
  apiOutputParams: ApiOutputParams[];
}
