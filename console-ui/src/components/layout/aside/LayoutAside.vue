<script lang="ts" setup>
import { HomeFilled, Setting } from '@element-plus/icons-vue';
import IconFlow from '@/components/icons/IconFlow.vue';
import IconInterface from '@/components/icons/IconInterface.vue';
</script>

<template>
  <div class="layout-aside">
    <el-menu :default-active="$route.fullPath" class="el-menu-vertical-demo" style="height: 100%" router>
      <!--      <el-menu-item index="/">
        <el-icon><HomeFilled /></el-icon>
        <span>首页</span>
      </el-menu-item>-->
      <el-sub-menu index="2">
        <template #title>
          <el-icon><IconFlow /></el-icon>
          <span>流程</span>
        </template>
        <el-menu-item index="/flow/define">流程定义</el-menu-item>
        <el-menu-item index="/flow/list">流程列表</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><IconInterface /></el-icon>
          <span>接口</span>
        </template>
        <el-menu-item index="/api/domain">领域</el-menu-item>
        <el-menu-item index="/api/list">接口列表</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/setting">
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
