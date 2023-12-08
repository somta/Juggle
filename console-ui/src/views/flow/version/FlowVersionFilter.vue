<script lang="ts" setup>
import { reactive } from 'vue'
const emit = defineEmits(['search'])

const formValue = reactive({
  flowVersionStatus: '',
})

const onSubmit = () => {
  emit('search', formValue)
}

const onReset = () => {
  formValue.flowVersionStatus = ''
}
</script>

<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="流程状态">
      <el-select v-model="formValue.flowVersionStatus" placeholder="请选择流程类型">
        <el-option key="all" label="全部" value="" />
        <el-option key="ENABLE" label="启用" value="1" />
        <el-option key="DISABLED" label="禁用" value="0" />
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
