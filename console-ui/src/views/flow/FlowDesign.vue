<script setup lang="ts">
import { onMounted, ref, shallowRef } from 'vue';
import {
  ZoomTool,
  FlowRenderer,
  RawData,
  ElementType,
  AddNodeModal,
  EditNodeDrawer,
  LeftMenu,
  ConditionItem,
  ConditionFilterModal,
} from './design';
import { flowDefineService } from '@/service';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import { addNode, deleteNode } from './design/operate';
import { useFlowDataProvide } from './design/hooks/flow-data';
import { DataBranchNode } from './design/renderer/data';

const flowContext = useFlowDataProvide();
const route = useRoute();
async function queryFlowDefineInfo() {
  const res = await flowDefineService.getDefineInfo(route.params.flowDefinitionId as number);
  if (res.success) {
    flowContext.update(draft => {
      Object.assign(draft, res.result);
      draft.flowContent = JSON.parse(res.result.flowContent);
    });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

const flowCanvas = shallowRef();
const addNodeModal = shallowRef();
const editNodeModal = shallowRef();
const conditionFilterModal = shallowRef();
let flowRenderer: FlowRenderer;
const scale = ref(1);
function onZoomToolChange(value: number) {
  scale.value = flowRenderer.scaleFromTop(value);
}

onMounted(async () => {
  try {
    await queryFlowDefineInfo();
  } catch (error) {
    ElMessage({ type: 'error', message: '流程定义内容解析失败' });
    return;
  }
  flowRenderer = new FlowRenderer(flowCanvas.value, {
    flowContext: flowContext,
    onZoom: (event: any) => {
      scale.value = event.transform.k;
    },
    onAdd: d => {
      console.log(d);
      addNodeModal.value.open({
        afterSelect: (info: { name: string; elementType: ElementType }) => {
          addNode({ info, prev: d.data, dataMap: flowRenderer.dataMap });
          flowRenderer.refresh();
        }
      });
    },
    onEdit: d => {
      console.log(d);
      if (d.data.type === ElementType.BRANCH) {
        const data = d.data as DataBranchNode;
        console.log(conditionFilterModal, 'sd')
        conditionFilterModal.value.open({
          data: data.getParent()?.raw,
          index: data.branchIndex,
          afterSelect: (val: ConditionItem) => {
            console.log(val);
            flowRenderer.refresh();
          }
        });
      } else {
        editNodeModal.value.open({
          data: d.data.raw,
          afterEdit: () => {
            flowRenderer.refresh();
          }
        });
      }
    },
    onDelete: d => {
      console.log(d);
      deleteNode({ current: d.data, dataMap: flowRenderer.dataMap });
      flowRenderer.refresh();
    },
  });
});

function flowSubmit () {
  const dataMap = flowRenderer.dataMap
  const result: RawData[] = [];
  dataMap.forEach(item => {
    // 过滤不需要提交的
    if (![ElementType.BRANCH, ElementType.ROOT].includes(item.type)) {
      result.push(item.raw);
    }
  });
  console.log(result);
  //todo 待测试
  //saveFlowDefineContent();
}

// async function saveFlowDefineContent(flowContent:string) {
//   const res = await flowDefineService.saveFlowContent({
//     id: route.params.flowDefinitionId as number,
//     flowContent:flowContent
//   });
//   if (res.success) {
//     ElMessage({ type: 'success', message: '保存成功' });
//   } else {
//     ElMessage({ type: 'error', message: res.errorMsg });
//   }
// }
</script>

<template>
  <div class="page-flow-design">
    <div class="flow-canvas" ref="flowCanvas">
      <ZoomTool :scale="scale" @change="onZoomToolChange" />
    </div>
    <LeftMenu />
    <AddNodeModal ref="addNodeModal"/>
    <EditNodeDrawer ref="editNodeModal" />
    <ConditionFilterModal ref="conditionFilterModal" />
    <el-button class="flow-submit" type="primary" @click="flowSubmit">保存</el-button>
  </div>
</template>

<style lang="less">
.layout-view .layout-router-view.page-flow-design {
  background-color: #fff;
}
.page-flow-design {
  position: relative;
  .flow-canvas {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;

    .flow-btn {
      cursor: pointer;
    }
    .flow-btn-edit,
    .flow-btn-delete {
      display: none;
    }
    .flow-node:hover {
      .flow-btn-edit,
      .flow-btn-delete {
        display: block;
      }
    }
  }
  .flow-submit {
    position: absolute;
    top: 20px;
    right: 20px;
  }
}
</style>
