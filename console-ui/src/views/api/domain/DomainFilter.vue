<script lang="ts" setup>
import { reactive } from 'vue'
const emit = defineEmits(['search'])

const formValue = reactive({
  domainName: '',
})

const onSubmit = () => {
  emit('search', formValue)
}

const onReset = () => {
  formValue.domainName = ''
}
</script>
<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="领域名称">
      <el-input v-model="formValue.domainName" placeholder="请输入领域名称" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
