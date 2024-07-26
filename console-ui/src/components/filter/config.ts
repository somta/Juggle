export enum BaseDataType {
  String = 'String',
  Integer = 'Integer',
  Double = 'Double',
  Boolean = 'Boolean',
  Date = 'Date',
  List = 'List',
  Object = 'Object',
}

export enum Operator {
  Empty = 'empty',
  NotEmpty = 'notEmpty',
  Equal = 'equal',
  NotEqual = 'notEqual',
  GreaterThan = 'greaterThan',
  LessThan = 'lessThan',
  GreaterThanOrEqual = 'greaterThanOrEqual',
  LessThanOrEqual = 'lessThanOrEqual',
  Contains = 'contains',
  NotContains = 'notContains',
}

export const OperatorNameMap = {
  [Operator.Empty]: '为空',
  [Operator.NotEmpty]: '不为空',
  [Operator.Equal]: '等于',
  [Operator.NotEqual]: '不等于',
  [Operator.GreaterThan]: '大于',
  [Operator.LessThan]: '小于',
  [Operator.GreaterThanOrEqual]: '大于等于',
  [Operator.LessThanOrEqual]: '小于等于',
  [Operator.Contains]: '包含',
  [Operator.NotContains]: '不包含',
};

export const DataTypeOperatorMap = {
  [BaseDataType.String]: [Operator.Equal, Operator.NotEqual, Operator.Empty, Operator.NotEmpty, Operator.Contains, Operator.NotContains],
  [BaseDataType.Integer]: [
    Operator.Equal,
    Operator.NotEqual,
    Operator.GreaterThan,
    Operator.GreaterThanOrEqual,
    Operator.LessThan,
    Operator.LessThanOrEqual,
  ],
  [BaseDataType.Double]: [
    Operator.Equal,
    Operator.NotEqual,
    Operator.GreaterThan,
    Operator.GreaterThanOrEqual,
    Operator.LessThan,
    Operator.LessThanOrEqual,
  ],
  [BaseDataType.Boolean]: [Operator.Equal, Operator.NotEqual],
  [BaseDataType.Date]: [
    Operator.Equal,
    Operator.NotEqual,
    Operator.GreaterThan,
    Operator.GreaterThanOrEqual,
    Operator.LessThan,
    Operator.LessThanOrEqual,
  ],
  [BaseDataType.List]: [Operator.Empty, Operator.NotEmpty],
  [BaseDataType.Object]: [Operator.Empty, Operator.NotEmpty],
};
