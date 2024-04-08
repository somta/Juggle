<script lang="ts" setup>
import { reactive } from 'vue';
const emit = defineEmits(['search']);

const formValue = reactive({
  suiteName: '',
});

const onSubmit = () => {
  emit('search', formValue);
};

const onReset = () => {
  formValue.suiteName = '';
};
</script>
<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="套件名称">
      <el-input v-model="formValue.suiteName" placeholder="请输入套件名称" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
