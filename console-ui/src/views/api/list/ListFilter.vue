<script lang="ts" setup>
import { reactive } from 'vue';
import DomainSelect from '@/components/form/DomainSelect.vue';
const emit = defineEmits(['search']);

const formValue = reactive({
  domainId: null,
  apiName: '',
  apiUrl: '',
});

const onSubmit = () => {
  emit('search', formValue);
};

const onReset = () => {
  formValue.domainId = null;
  formValue.apiName = '';
  formValue.apiUrl = '';
};
</script>
<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="领域" style="width: 200px;">
      <DomainSelect v-model="formValue.domainId" />
    </el-form-item>
    <el-form-item label="接口名称">
      <el-input v-model="formValue.apiName" placeholder="请输入接口名称" />
    </el-form-item>
    <el-form-item label="接口地址">
      <el-input v-model="formValue.apiUrl" placeholder="请输入接口地址" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
