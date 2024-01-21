
import { provide, inject } from 'vue';
import { FlowData } from '../types';
import { useImmer } from './immer';

export function useFlowDataProvide() {
  const [data, update] = useImmer<FlowData>({
    flowKey: '',
    flowName: '',
    flowType: '',
    flowContent: [],
    flowInputParams: [],
    flowOutputParams: [],
    flowVariables: [],
    id: null,
    remark: '',
  });

  const flowData = {
    data,
    update,
  };

  provide('flowData', flowData);

  return flowData;
}

export function useFlowDataInject() {
  return inject('flowData') as ReturnType<typeof useFlowDataProvide>;
}
