
<script lang="ts" setup>
import { shallowRef } from 'vue';
import { Edit, Delete } from '@element-plus/icons-vue';
import ParamSettingModal from './ParamSettingModal.vue';
const treeData = [
  {
    label: '入参变量',
    children: [
      { label: 'id' },
      { label: 'type' },
    ],
  },
  {
    label: '出参变量',
    children: [
      { label: 'nickName' },
      { label: 'userName' },
    ],
  },
  {
    label: '中间变量',
    children: [
      { label: 'env_id' },
      { label: 'env_name' },
    ],
  },
];
const paramSettingModal = shallowRef();

function onAdd () {
  paramSettingModal.value.open();
}
function onEdit(data: any) {
  paramSettingModal.value.open(data);
}
function onDelete(data: any) {
  console.log(data);
}

</script>

<template>
  <div class="flow-variable-setting">
    <div class="variable-head">
      <el-button type="primary" size="small" class="add-temp-variable" @click="onAdd">新增中间变量</el-button>
      <el-input placeholder="搜索变量" class="variable-search-input" size="small"/>
    </div>
    <div class="variable-body">
      <el-tree :data="treeData" default-expand-all>
        <template #default="{ node, data }">
          <span class="custom-tree-node">
            <span>{{ node.label }}</span>
            <span class="tree-node-action" v-if="!data.children || data.children.length === 0">
              <el-icon @click="onEdit(data)"><Edit/></el-icon>
              <el-icon @click="onDelete(data)" style="margin-left: 8px"><Delete/></el-icon>
            </span>
          </span>
        </template>
      </el-tree>
    </div>
    <ParamSettingModal ref="paramSettingModal" />
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
  }
  .tree-node-action {
    display: none;
  }
  .el-tree-node__content:hover .tree-node-action {
    display: flex;
  }
}
</style>
