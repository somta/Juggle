export interface flowInputParams {
  paramKey: string
  paramName: string
  dataType: string
  required: boolean
}

export interface flowOutputParams {
  paramKey: string
  paramName: string
  dataType: string
}

export interface FlowDefineInfo {
  id: number | null
  flowName: string
  flowType: string
  remark: string
  flowInputParams: []
  flowOutputParams: []
}
