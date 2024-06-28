export interface ObjectProperty {
    propKey: string;
    propName: string;
    dataType: string;
}

export interface ObjectInfo {
    id: number | null;
    objectKey: string;
    objectName: string;
    objectDesc: string;
    props: ObjectProperty[];
}