import { ObjectProperty } from '@/typings/object.ts';

export enum DataTypeEnum {
    Basic = 1,
    List = 2,
    Object = 3,
}

export type DataTypeInfo = {
    id: number;
    dataTypeClassify: DataTypeEnum;
    type: string;
    displayName: string;
    objectKey: string;
    objectStructure: string;
};

export interface DataType {
    type: string;
    itemType?: string | null;
    objectKey?: string | null;
    objectStructure?: ObjectProperty[] | null;
}