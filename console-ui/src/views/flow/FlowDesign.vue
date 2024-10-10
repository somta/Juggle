<script setup lang="ts">
import { onMounted, ref, shallowRef } from 'vue';
import {
  ZoomTool,
  FlowRenderer,
  ElementType,
  AddNodeModal,
  EditNodeDrawer,
  LeftMenu,
  ConditionItem,
  FlowVariable,
  ConditionFilterModal,
  RawData,
} from './design';
import { flowDefineService } from '@/service';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import { addNode, deleteNode } from './design/operate';
import { useFlowDataProvide } from './design/hooks/flow-data';
import { DataNode, DataBranch } from './design/data';
import { rebuildCondition } from './design/data/generate';

const flowContext = useFlowDataProvide();
const route = useRoute();
const flowName = ref();
async function queryFlowDefineInfo() {
  const res = await flowDefineService.getDefineInfo(route.params.flowDefinitionId as unknown as number);
  if (res.success) {
    flowName.value = res.result.flowName;
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
const conditionFilterModalRef = shallowRef();
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
          addNode({ info, prev: d.data });
          flowRenderer.refresh();
        },
      });
    },
    onEdit: d => {
      console.log(d);
      if (d.data.type === ElementType.BRANCH) {
        const data = d.data as DataBranch;
        const parent = data.getParent();
        conditionFilterModalRef.value.open({
          data: parent!.raw,
          index: data.branchIndex,
          afterEdit: (val: ConditionItem) => {
            if (val) {
              flowContext.update(draft => {
                const parentRaw = draft.flowContent.find(item => item.key === parent.key);
                const branch = parentRaw?.conditions?.[data.branchIndex];
                if (branch) {
                  branch.conditionName = val.conditionName;
                  branch.conditionExpressions = val.conditionExpressions;
                }
              });
              flowRenderer.refresh();
            }
          },
        });
      } else if (d.data.type === ElementType.CONDITION) {
        editNodeModal.value.open({
          data: d.data.raw,
          afterEdit: (oldData: RawData) => {
            rebuildCondition(flowContext, d.data, oldData);
            flowRenderer.refresh();
          },
        });
      } else {
        editNodeModal.value.open({
          data: d.data.raw,
          afterEdit: () => {
            flowRenderer.refresh();
          },
        });
      }
    },
    onDelete: d => {
      console.log(d);
      deleteNode({ current: d.data });
      flowRenderer.refresh();
    },
  });
});

function flowSubmit() {
  const { flowContent, flowVariables } = flowContext.data.value;
  saveFlowDefineContent(JSON.stringify(flowContent), flowVariables);
}

async function saveFlowDefineContent(flowContent: string, flowVariables: FlowVariable[]) {
  const res = await flowDefineService.saveFlowContent({
    id: route.params.flowDefinitionId as unknown as number,
    flowContent: flowContent,
    flowVariables: flowVariables,
  });
  if (res.success) {
    ElMessage({ type: 'success', message: '保存成功' });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>

<template>
  <div class="page-flow-design">
    <div class="flow-header">
      <p>{{ flowName }}</p>
      <el-button class="flow-submit" type="primary" @click="flowSubmit">保存</el-button>
    </div>
    <div class="flow-canvas" ref="flowCanvas">
      <ZoomTool :scale="scale" @change="onZoomToolChange" />
    </div>
    <LeftMenu />
    <AddNodeModal ref="addNodeModal" />
    <EditNodeDrawer ref="editNodeModal" />
    <ConditionFilterModal ref="conditionFilterModalRef" />

  </div>
</template>

<style lang="less">
.layout-view .layout-router-view.page-flow-design {
  background-color: #fff;
  overflow: hidden !important;
}

.flow-header {
  border-bottom: 1px solid #dcdfe6;
  position: relative;
  height: 50px;
  align-items: center;
  display: flex;
  p {
    margin-left: 10px;
  }
  .flow-submit {
    position: absolute;
    right: 20px;
    top: 50%;
    transform: translate(-50%, -50%);
  }
}

.page-flow-design {
  .flow-canvas {
    width: 100%;
    height: calc(100% - 40px);
    overflow: hidden;
    position: absolute;
    font-size: 14px;

    .flow-btn {
      cursor: pointer;
      transition: opacity 0.2s;
      &:hover {
        opacity: 0.85;
      }
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

}
</style>
