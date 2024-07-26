<script setup lang="ts">
import { ref } from 'vue';
import { ElementType } from '../types';
import IconMethod from '@/components/icons/IconMethod.vue';
import IconCondition from '@/components/icons/IconCondition.vue';
import IconCode from '@/components/icons/IconCode.vue';
import IconMysql from '@/components/icons/IconMysql.vue';
const visible = ref(false);
type NodeInfo = { name: string; elementType: ElementType };
let openParams: { afterSelect: (info: NodeInfo) => void };
function open(params: typeof openParams) {
  visible.value = true;
  openParams = params;
}

const containerRef = ref<HTMLElement | null>(null);

const handleClick = (e: MouseEvent) => {
  e.preventDefault();
};

const flowNodes = [
  {
    name: '方法节点',
    type: ElementType.METHOD,
    icon: IconMethod,
  },
  {
    name: '判断节点',
    type: ElementType.CONDITION,
    icon: IconCondition,
  },
  {
    name: '代码节点',
    type: ElementType.CODE,
    icon: IconCode,
  },
];

const flowDataNodes = [
  {
    name: 'MySql节点',
    type: ElementType.MYSQL,
    icon: IconMysql,
  },
];

const flowOtherNodes = [
  {
    name: 'OpenAi',
    type: ElementType.AI,
    icon: '⦾',
  },
];

function addNode(item: { name: string; type: ElementType }) {
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
  <el-dialog v-model="visible" title="" class="design-add-node-modal" :width="480" :show-close="false" align-center>
    <el-anchor :offset="70" :container="containerRef" direction="horizontal" @click="handleClick">
      <el-anchor-link href="#baseNodes" title="基础节点" />
      <el-anchor-link href="#dataNodes" title="数据节点" />
    </el-anchor>
    <el-row>
      <el-col>
        <div ref="containerRef" style="height: 300px; overflow-y: auto">
          <div id="baseNodes" class="node-types">
            <div class="node-type-name">基础节点</div>
            <div class="node-type" v-for="item in flowNodes" :key="item.type" @click="addNode(item)">
              <span
                ><el-icon :size="25"><component :is="item.icon"></component></el-icon
              ></span>
              <span class="node-text">{{ item.name }}</span>
            </div>
          </div>

          <div id="dataNodes" class="node-types">
            <div class="node-type-name">数据节点</div>
            <div class="node-type" v-for="item in flowDataNodes" :key="item.type" @click="addNode(item)">
              <span
                ><el-icon :size="25"><component :is="item.icon"></component></el-icon
              ></span>
              <span class="node-text">{{ item.name }}</span>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
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
  .el-anchor__link {
    font-size: 15px;
  }
  .node-types {
    padding: 8px 0;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .node-type-name {
    font-size: 18px;
    margin-bottom: 10px;
  }
  .node-type {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 448px;
    margin-bottom: 8px;
    font-size: 14px;
    cursor: pointer;
    border: 1px solid #f0f2f5;
  }
  .node-text {
    margin: 10px 5px;
  }
}
</style>
