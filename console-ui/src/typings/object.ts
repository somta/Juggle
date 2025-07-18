import { DataType } from '@/typings/dataType.ts';

export interface ObjectProperty {
  propKey: string;
  propName: string;
  dataType: DataType;
}

export interface ObjectInfo {
  id: number | null;
  objectKey: string;
  objectName: string;
  objectDesc: string;
  props: ObjectProperty[];
}
