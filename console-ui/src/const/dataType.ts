
export const DataTypeMap = {
    String: '字符串',
    Integer: '整数',
    Double: '小数',
    Boolean: '布尔',
    Date: '日期',
    Time: '时间',
    Object: '对象',
    List: '集合',
};

export type DataTypeMap = keyof typeof DataTypeMap;