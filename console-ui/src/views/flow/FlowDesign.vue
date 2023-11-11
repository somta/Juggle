<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue';
import ZoomTool from './design/ZoomTool.vue';
import { FlowRenderer } from './design/renderer';
import { flowDefineService } from '@/service';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';

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

onMounted(async () => {
  await queryFlowDefineInfo();
  let content = [];
  try {
    content = JSON.parse(flowData.flowContent);
  } catch (error) {
    ElMessage({type: 'error', message: '流程定义内容解析失败'});
    return;
  }
  flowRenderer = new FlowRenderer(flowCanvas.value, {
    datas: content,
    onZoom: (event: any) => {
      scale.value = event.transform.k;
    },
  });
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

    .flow-node {
    }

    .flow-add-btn {
      cursor: pointer;
    }
  }
}
</style>