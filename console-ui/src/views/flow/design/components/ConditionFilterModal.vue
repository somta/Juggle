
<script lang="ts" setup>
import { ref } from 'vue';
import FilterGroup from '@/components/filter/FilterGroup.vue';
const visible = ref(false);
const filters = ref([[]]);
function open () {
  visible.value = true;
}
function onChange (value: any) {
  filters.value = value;
}
function onCancel () {
  visible.value = false;
}
function onSubmit () {
  visible.value = false;
}
defineExpose({ open });
</script>

<template>
  <el-dialog
    title="设置分支条件"
    :width="480"
    v-model="visible"
    class="condition-filter-modal"
  >
    <div class="condition-name-label">分支名称</div>
    <el-input class="condition-name-input" placeholder="请输入" />
    <FilterGroup :value="filters" @change="onChange" />

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
