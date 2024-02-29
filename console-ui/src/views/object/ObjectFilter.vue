<script lang="ts" setup>
import { reactive } from 'vue';
const emit = defineEmits(['search']);

const objectFilterFormValue = reactive({
  objectName: '',
});

const onSubmit = () => {
  emit('search', objectFilterFormValue);
};

const onReset = () => {
  objectFilterFormValue.objectName = '';
};
</script>

<template>
  <el-form :inline="true" :model="objectFilterFormValue">
    <el-form-item label="对象名称">
      <el-input v-model="objectFilterFormValue.objectName" placeholder="请输入对象名称" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
