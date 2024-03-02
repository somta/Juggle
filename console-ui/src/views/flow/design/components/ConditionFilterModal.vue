
<script lang="ts" setup>
import { ref } from 'vue';
import FilterGroup from '@/components/filter/FilterGroup.vue';
import { RawData, ConditionItem } from '../types';
import { cloneDeep } from 'lodash-es';
type ConditionRawData = RawData & { conditions: ConditionItem[] };
const visible = ref(false);
function getDefault () {
  return {
    conditionName: '',
    conditionType: 'CUSTOM',
    expression: '',
    outgoing: '',
    conditionExpressions: [],
  } as ConditionItem;
}
const condition = ref(getDefault());
let openParams: {
  data: ConditionRawData;
  index?: number;
  afterEdit: (val: ConditionItem) => void;
};
function open (params: typeof openParams) {
  openParams = params;
  if (typeof params.index === 'number') {
    condition.value = cloneDeep(params.data.conditions[params.index]);
  } else {
    condition.value = getDefault();
  }
  visible.value = true;
}
function onChange (value: any) {
  condition.value.conditionExpressions = value;
}
function onCancel () {
  visible.value = false;
}
function onSubmit () {
  visible.value = false;
  if (typeof openParams.afterEdit === 'function') {
    openParams.afterEdit(cloneDeep(condition.value));
  }
}
defineExpose({ open });
</script>

<template>
  <el-dialog
    title="设置分支条件"
    :width="640"
    v-model="visible"
    class="condition-filter-modal"
  >
    <div class="condition-name-label">分支名称</div>
    <el-input v-model="condition.conditionName" class="condition-name-input" placeholder="请输入" />
    <FilterGroup :value="condition.conditionExpressions" @change="onChange" />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="onCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style lang="less">
.condition-filter-modal {
  .el-dialog__body {
    padding-top: 10px;
  }
  .condition-name-label {
    margin-bottom: 10px;
  }
  .condition-name-input {
    margin-bottom: 10px;
  }
}
</style>
