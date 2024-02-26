
import { provide, inject } from 'vue';
import { FlowData, RawData } from '../types';
import { useImmer } from './immer';

export function useFlowDataProvide() {
  const [data, update ] = useImmer<FlowData>({
    flowKey: '',
    flowName: '',
    flowType: '',
    flowContent: [],
    flowInputParams: [],
    flowOutputParams: [],
    flowVariables: [],
    id: null as unknown as number,
    remark: '',
  });

  function getFlowNodes () {
    return data.value.flowContent;
  }

  function getFlowNode (key: string) {
    return data.value.flowContent.find((node) => node.key === key);
  }

  function updateFlowNode (key: string, data: Partial<RawData>) {
    update((draft) => {
      const node = draft.flowContent.find((node) => node.key === key);
      if (node) {
        Object.assign(node, data);
      }
    });
  }

  const flowContext = {
    data,
    update,
    getFlowNodes,
    getFlowNode,
    updateFlowNode,
  };

  provide('flowContext', flowContext);

  return flowContext;
}

export type FlowContext = ReturnType<typeof useFlowDataProvide>; 

export function useFlowDataInject() {
  return inject('flowContext') as ReturnType<typeof useFlowDataProvide>;
}

export function getFlowNode (flowContext: FlowContext, key: string) {
  return flowContext.data.value.flowContent.find((node) => node.key === key);
}

export function updateFlowNode (flowContext: FlowContext, key: string, data: Partial<RawData>) {
  flowContext.update((draft) => {
    const node = draft.flowContent.find((node) => node.key === key);
    if (node) {
      Object.assign(node, data);
    }
  });
}
