import {ElementType} from "@/views/flow/design/types.ts";

export const nodeMap = {
    [ElementType.START]: {
        nodeName:'开始节点',
        nodeHeaderColor:'#8c8a8a',
    },
    [ElementType.METHOD]: {
        nodeName:'方法节点',
        nodeHeaderColor:'#e5cf7a',
    },
    [ElementType.CONDITION]: {
        nodeName:'判断节点',
        nodeHeaderColor:'#47a155',
    },
    [ElementType.CODE]: {
        nodeName:'代码节点',
        nodeHeaderColor:'#8c2dd3',
    },
    [ElementType.MYSQL]: {
        nodeName:'MySql节点',
        nodeHeaderColor:'#48a6bb',
    },
    [ElementType.AI]: {
        nodeName:'AI节点',
        nodeHeaderColor:'#640505',
    },
    [ElementType.END]: {
        nodeName:'结束节点',
        nodeHeaderColor:'#8c8a8a',
    },
};