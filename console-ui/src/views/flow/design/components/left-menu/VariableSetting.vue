
<script lang="ts" setup>
import { shallowRef, computed } from 'vue';
import { Edit, Delete } from '@element-plus/icons-vue';
import ParamSettingModal from './ParamSettingModal.vue';
import { useFlowDataInject } from '../../hooks/flow-data';
const flowData = useFlowDataInject();
const treeData = computed(() => {
  const flowVariables = flowData.data.value.flowVariables.map(v => ({
    ...v,
    label: v.envName,
  }));
  return [
    {
      label: '入参变量',
      envKey: 'in',
      children: flowVariables.filter(v => v.envType === 1),
    },
    {
      label: '出参变量',
      envKey: 'out',
      children: flowVariables.filter(v => v.envType === 2),
    },
    {
      label: '中间变量',
      envKey: 'temp',
      children: flowVariables.filter(v => v.envType === 3),
    },
  ];
});

const paramSettingModal = shallowRef();

function onAddOpen () {
  paramSettingModal.value.open();
}
function onEditOpen (data: any) {
  paramSettingModal.value.open(data);
}
function onAdd (data: any) {
  flowData.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.envKey === data.envKey);
    if (index === -1) {
      draft.flowVariables.push(data);
    }
  });
}
function onEdit (data: any) {
  flowData.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.envKey === data.envKey);
    if (index > -1) {
      draft.flowVariables.splice(index, 1, data);
    }
  });
}
function onDelete(data: any) {
  flowData.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.envKey === data.envKey);
    if (index > -1) {
      draft.flowVariables.splice(index, 1);
    }
  });
}

</script>

<template>
  <div class="flow-variable-setting">
    <div class="variable-head">
      <el-button type="primary" size="small" class="add-temp-variable" @click="onAddOpen">新增中间变量</el-button>
      <el-input placeholder="搜索变量" class="variable-search-input" size="small"/>
    </div>
    <div class="variable-body">
      <el-tree :data="treeData" node-key="envKey" default-expand-all>
        <template #default="{ node, data }">
          <span class="custom-tree-node">
            <span v-if="data.children" class="custom-tree-node-label">{{ node.label }}</span>
            <template v-else>
              <span class="custom-tree-node-label" :title="node.label">
                <span>{{ data.envKey }}</span>
                <span class="custom-tree-node-name">{{ node.label }}</span>
              </span>
              <span class="tree-node-action" v-if="data.envType === 3">
                <el-icon @click="onEditOpen(data)"><Edit/></el-icon>
                <el-icon @click="onDelete(data)" style="margin-left: 8px"><Delete/></el-icon>
              </span>
            </template>
          </span>
        </template>
      </el-tree>
    </div>
    <ParamSettingModal ref="paramSettingModal" @edit="onEdit" @add="onAdd" />
  </div>
</template>

<style lang="less" scoped>
.flow-variable-setting {
  height: 100%;
  display: flex;
  flex-direction: column;
  .variable-head {
    display: flex;
    flex-direction: column;
    padding: 16px 12px 8px;
  }
  .variable-body {
    flex: 1;
    overflow: auto;
    min-height: 0;
  }
  .add-temp-variable {
    margin-bottom: 12px;
  }
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 13px;
    padding-right: 8px;
    min-width: 0;
  }
  .custom-tree-node-label {
    flex: 1;
    min-width: 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .custom-tree-node-name {
    font-size: 12px;
    color: #999;
    margin-left: 6px;
  }
  .tree-node-action {
    display: none;
  }
  .el-tree-node__content:hover .tree-node-action {
    display: flex;
  }
}
</style>
