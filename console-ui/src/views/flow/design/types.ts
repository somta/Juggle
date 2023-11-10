
export type NodeCondition = {
  conditionName: string;
  conditionType: string;
  expression: string;
  outgoing: string;
};

export type NodeData = {
  key: string;
  name: string;
  outgoings: string[];
  elementType: ElementType;
  conditions?: NodeCondition[];
};

export enum ElementType {
  START = 'START',
  METHOD = 'METHOD',
  CONDITION = 'CONDITION',
  END = 'END',

  // 前端创建的
  CONDITION_BRANCH = 'CONDITION_BRANCH',
  CONDITION_START = 'CONDITION_START',
};

