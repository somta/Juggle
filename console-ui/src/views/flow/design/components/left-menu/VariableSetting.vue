<script lang="ts" setup>
import { shallowRef, computed, ref } from 'vue';
import { Edit, Delete, Search } from '@element-plus/icons-vue';
import { useFlowDataInject } from '../../hooks/flow-data';
import { cloneDeep } from 'lodash-es';
const flowContext = useFlowDataInject();
const searchValue = ref('');
const treeData = computed(() => {
  const flowVariables = flowContext.data.value.flowVariables.map(v => ({
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
const maxId = computed(() => {
  return Math.max(...flowContext.data.value.flowVariables.map(v => v.id), 0);
});

const paramSettingModal = shallowRef();

function onAddOpen() {
  paramSettingModal.value.add(maxId.value + 1);
}
function onEditOpen(data: any) {
  paramSettingModal.value.edit(data);
}
function onAdd(data: any) {
  console.log('开始增加', data);
  flowContext.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.id === data.id);
    if (index === -1) {
      console.log(draft.flowVariables);
      draft.flowVariables.push(cloneDeep(data));
    }
  });
}
function onEdit(data: any) {
  flowContext.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.id === data.id);
    if (index > -1) {
      draft.flowVariables.splice(index, 1, data);
    }
  });
}
function onDelete(data: any) {
  flowContext.update(draft => {
    const index = draft.flowVariables.findIndex(item => item.envKey === data.envKey);
    if (index > -1) {
      draft.flowVariables.splice(index, 1);
    }
  });
}

const treeRef = ref();

function searchVariable(value: any) {
  treeRef.value?.filter(value);
}

function filterNode(value: any, data: any) {
  if (!value) return true;
  return data.label.includes(value) || data.envKey.includes(value);
}
</script>

<template>
  <div class="flow-variable-setting">
    <div class="variable-head">
      <el-button type="primary" size="small" class="add-temp-variable" @click="onAddOpen">新增中间变量</el-button>
      <el-input
        v-model="searchValue"
        :prefix-icon="Search"
        placeholder="搜索变量"
        class="variable-search-input"
        size="small"
        @input="searchVariable"
      />
    </div>
    <div class="variable-body">
      <el-tree ref="treeRef" :data="treeData" node-key="envKey" default-expand-all :filter-node-method="filterNode">
        <template #default="{ node, data }">
          <span class="custom-tree-node">
            <span v-if="data.children" class="custom-tree-node-label">{{ node.label }}</span>
            <template v-else>
              <span class="custom-tree-node-label" :title="node.label">
                <span>{{ data.envKey }}</span>
                <span class="custom-tree-node-name">{{ node.label }}</span>
              </span>
              <span class="tree-node-action" v-if="data.envType === 3">
                <el-icon @click="onEditOpen(data)"><Edit /></el-icon>
                <el-icon @click="onDelete(data)" style="margin-left: 8px"><Delete /></el-icon>
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
