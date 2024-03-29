
<script setup lang="ts">
import { ref } from 'vue';
import { ElementType } from '../types';
const visible = ref(false);
type NodeInfo = { name: string; elementType: ElementType  };
let openParams: { afterSelect: (info: NodeInfo) => void };
function open (params: typeof openParams) {
  visible.value = true;
  openParams = params;
}

const flowNodes = [
  {
    name: '方法节点',
    type: ElementType.METHOD,
    icon: 'Ⓐ',
  },
  {
    name: '判断节点',
    type: ElementType.CONDITION,
    icon: 'Ⓒ',
  },
  {
    name: '自定义代码',
    type: ElementType.CODE,
    icon: '</>',
  },
];

function addNode (item: { name: string; type: ElementType }) {
  const info = {
    name: item.name,
    elementType: item.type,
  };
  if (typeof openParams.afterSelect === 'function') {
    openParams.afterSelect(info);
  }
  visible.value = false;
}

defineExpose({ open });
</script>

<template>
  <el-dialog
    v-model="visible"
    title=""
    class="design-add-node-modal"
    :width="480"
    align-center
  >
    <el-tabs type="border-card">
      <el-tab-pane label="流程节点">
        <div class="node-types">
          <div class="node-type"
            v-for="item in flowNodes"
            :key="item.type"
            @click="addNode(item)"
          >
            <span>{{ item.icon }}</span><span>{{ item.name }}</span>
          </div>
        </div>
      </el-tab-pane>
<!--      <el-tab-pane label="调用节点">
      </el-tab-pane>-->
    </el-tabs>
  </el-dialog>
</template>

<style lang="less">
.design-add-node-modal {
  .el-dialog__header {
    padding: 0;
    width: 100%;
    position: absolute;
    z-index: 1;
  }
  .el-dialog__headerbtn {
    height: 40px;
    width: 40px;
    top: 0;
  }
  .el-dialog__body {
    padding: 0;
  }
  .el-tab-pane {
    height: 320px;
  }
  .node-types {
    padding: 8px 0;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .node-type {
    line-height: 32px;
    margin-bottom: 8px;
    font-size: 14px;
    cursor: pointer;
  }
}
</style>
