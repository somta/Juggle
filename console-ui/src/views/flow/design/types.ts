
export type ConditionItem = {
  conditionName: string;
  conditionType: string;
  expression: string;
  outgoing: string;
};

export type RawData = {
  key: string;
  name: string;
  outgoings: string[];
  incomings: string[];
  elementType: ElementType;
  conditions?: ConditionItem[];
};

export enum ElementType {
  START = 'START',
  METHOD = 'METHOD',
  CONDITION = 'CONDITION',
  END = 'END',

  // 前端创建的
  BRANCH = 'BRANCH',
  ROOT = 'ROOT',
};

export type D3Element = d3.Selection<any, any, any, any>;
