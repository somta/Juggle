import {InputParams, OutputParams} from "@/typings/parameter.ts";

export interface FlowDefineInfo {
  id: number | null;
  flowName: string;
  flowType: string;
  remark: string;
  flowInputParams: InputParams[];
  flowOutputParams: OutputParams[];
}
