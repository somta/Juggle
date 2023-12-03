<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue';
import ZoomTool from './design/ZoomTool.vue';
import { FlowRenderer } from './design/renderer';
import { flowDefineService } from '@/service';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import { RawData } from './design/types';
import { generateDataTree } from './design/renderer/data';
import { layoutTree } from './design/renderer/layout/vertical';

const flowData = reactive({
  flowKey: '',
  flowName: '',
  flowType: '',
  flowContent: '',
  flowInputParams: [],
  flowOutputParams: [],
  id: null,
  remark: null,
});
const route = useRoute();
async function queryFlowDefineInfo() {
  const res = await flowDefineService.getDefineInfo(route.params.flowDefinitionId as unknown as number);
  if (res.success) {
    Object.assign(flowData, res.result);
  } else {
    ElMessage({type: 'error', message: res.errorMsg});
  }
}

const flowCanvas = ref();
let flowRenderer: FlowRenderer;
const scale = ref(1);
function onZoomToolChange (value: number) {
  scale.value = flowRenderer.scaleFromTop(value);;
}
let content: RawData[] = [];

onMounted(async () => {
  await queryFlowDefineInfo();
  content = [];
  try {
    content = JSON.parse(flowData.flowContent);
  } catch (error) {
    ElMessage({type: 'error', message: '流程定义内容解析失败'});
    return;
  }
  const tree = generateDataTree(content);
  console.log(tree);
  const layout = layoutTree(tree);
  console.log(layout);
  // flowRenderer = new FlowRenderer(flowCanvas.value, {
  //   datas: content,
  //   onZoom: (event: any) => {
  //     scale.value = event.transform.k;
  //   },
  //   onAdd: (d) => {
  //     const newData = {
  //       key: 'method_' + Date.now(),
  //       elementType: ElementType.METHOD,
  //       outgoings: d.data.outgoings,
  //       name: '新节点',
  //     };
  //     // 条件节点 - 修改自身及条件节点的出口
  //     if (d.data.elementType === ElementType.CONDITION) {
  //       if (d.data.conditions) {
  //         d.data.conditions.forEach((item) => {
  //           if (item.outgoing === d.data.outgoings[0]) {
  //             item.outgoing = newData.key;
  //           }
  //         });
  //         d.getChildren().forEach((item) => {
  //           if (item.data.outgoings[0] === d.data.outgoings[0]) {
  //             item.data.outgoings[0] = newData.key;
  //           }
  //         });
  //       }
  //     } else if (d.data.elementType === ElementType.CONDITION_BRANCH) {
  //       // 分支节点 - 修改条件的出口
  //       const parent = d.getParent();
  //       const arr = d.data.key.split('_');
  //       const index = arr[arr.length - 1];
  //       if (parent.data.conditions) {
  //         const condition = parent.data.conditions[+index];
  //         condition.outgoing = newData.key;
  //       }
  //     }
  //     // 常规节点 - 修改自身出口、入口的出口
  //     newData.outgoings = d.data.outgoings;
  //     d.data.outgoings = [newData.key];
  //     content = [...content, newData];
  //     flowRenderer.updateDatas(content);
  //   },
  //   onEdit: (d) => {
  //     console.log(d);
  //   },
  //   onDelete: (d) => {
  //     function deleteByKey (key: string) {
  //       const index = content.findIndex((item) => item.key === key);
  //       if (index > -1) {
  //         content.splice(index, 1);
  //       }
  //     }
  //     if (d.data.elementType === ElementType.CONDITION_START) {
  //       const parent = d.getParent();
  //       // 更改上下节点出口
  //       const from = content.find((item) => item.outgoings?.[0] === parent.data.key);
  //       const to = content.find((item) => item.key === parent.data.outgoings?.[0]);
  //       if (from && to) {
  //         from.outgoings = [to.key];
  //       }
  //       // 删除节点
  //       deleteByKey(parent.data.key);
  //       // 删除所有子节点
  //       parent.getChildren().forEach(item => {
  //         deleteByKey(item.data.key);
  //       });
  //       // 删除布局节点
  //       d.delete();
  //       flowRenderer.updateDatas(content);
  //     } else {
  //       // 更改上下节点出口
  //       const from = content.find((item) => item.outgoings?.[0] === d.data.key);
  //       const to = content.find((item) => item.key === d.data.outgoings?.[0]);
  //       if (from && to) {
  //         from.outgoings = [to.key];
  //       }
  //       // 删除节点
  //       deleteByKey(d.data.key);
  //       // 删除布局节点
  //       d.delete();
  //       flowRenderer.updateDatas(content);
  //     }
  //   },
  // });
});

</script>

<template>
  <div class="page-flow-design">
    <div class="flow-canvas" ref="flowCanvas">
      <ZoomTool :scale="scale" @change="onZoomToolChange" />
    </div>
  </div>
</template>

<style lang="less">
.page-flow-design {
  .flow-canvas {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;

    .flow-btn {
      cursor: pointer;
    }
    .flow-btn-edit, .flow-btn-delete {
      display: none;
    }
    .flow-node:hover {
      .flow-btn-edit, .flow-btn-delete {
        display: block;
      }
    }
  }
}
</style>