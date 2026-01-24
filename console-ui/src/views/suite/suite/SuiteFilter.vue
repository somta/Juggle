<script lang="ts" setup>
import { reactive } from 'vue';
const emit = defineEmits(['search']);

const formValue = reactive({
  suiteName: '',
  suiteFlag: null,
});

const onSubmit = () => {
  emit('search', formValue);
};

const onReset = () => {
  formValue.suiteName = '';
  formValue.suiteFlag = null;
};
</script>
<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="套件名称">
      <el-input v-model="formValue.suiteName" placeholder="请输入套件名称" />
    </el-form-item>
    <el-form-item label="套件类型">
      <el-select placeholder="请选择套件类型" v-model="formValue.suiteFlag" clearable style="width: 200px">
        <el-option label="个人套件" :value="2" />
        <el-option label="官方套件" :value="1" />
        <el-option label="内置套件" :value="0" />
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
