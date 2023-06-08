<script lang="ts" setup>
import { HomeFilled, Menu, Setting } from '@element-plus/icons-vue';
</script>

<template>
  <div class="layout-aside">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      style="height: 100%"
      @open="handleOpen"
      @close="handleClose"
    >
      <el-menu-item index="1">
        <el-icon><HomeFilled /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><Menu /></el-icon>
          <span>流程</span>
        </template>
        <el-menu-item index="2-1">流程定义</el-menu-item>
        <el-menu-item index="2-2">流程列表</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><Menu /></el-icon>
          <span>接口</span>
        </template>
        <el-menu-item index="3-1">领域</el-menu-item>
        <el-menu-item index="3-2">接口列表</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="4">
        <el-icon><Setting /></el-icon>
        <span>系统</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style lang="less" scoped>
.layout-aside {
  height: 100%;
}
</style>
