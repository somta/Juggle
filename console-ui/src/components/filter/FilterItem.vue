
<script lang="ts" setup>
import { Delete } from '@element-plus/icons-vue'
</script>

<template>
  <div class="filter-item">
    <div class="filter-item-field">
      <el-select placeholder="请选择"></el-select>
    </div>
    <div class="filter-item-method">
      <el-select placeholder="请选择"></el-select>
    </div>
    <div class="filter-item-value">
      <el-input placeholder="请输入内容"></el-input>
    </div>
    <div class="filter-item-action">
      <el-button :icon="Delete" link @click="$emit('delete')"></el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.filter-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  .filter-item-field {
    width: 120px;
    margin-right: 10px;
  }
  .filter-item-method {
    width: 120px;
    margin-right: 10px;
  }
  .filter-item-value {
    flex: 1;
    margin-right: 10px;
  }
  .filter-item-action {
    width: 24px;
  }
}
</style>