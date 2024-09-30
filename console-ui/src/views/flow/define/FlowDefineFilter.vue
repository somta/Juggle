<script lang="ts" setup>
import { reactive } from 'vue';
const emit = defineEmits(['search']);

const formValue = reactive({
  flowName: '',
  flowType: '',
});

const onSubmit = () => {
  emit('search', formValue);
};

const onReset = () => {
  formValue.flowName = '';
  formValue.flowType = '';
};
</script>

<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="流程名称">
      <el-input v-model="formValue.flowName" placeholder="请输入流程名称" />
    </el-form-item>
    <el-form-item label="流程类型" style="width: 250px">
      <el-select v-model="formValue.flowType" placeholder="请选择流程类型">
        <el-option key="all" label="全部" value="" />
        <el-option key="sync" label="同步" value="sync" />
        <el-option key="async" label="异步" value="async" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
