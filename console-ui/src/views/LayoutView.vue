<script setup lang="ts">
import { RouterView } from 'vue-router';
import LayoutHeader from '../components/layout/header/LayoutHeader.vue';
import LayoutAside from '../components/layout/aside/LayoutAside.vue';
</script>

<template>
  <div class="layout-view">
    <el-container style="height: 100%">
      <el-header style="padding: 0;"><LayoutHeader /></el-header>
      <el-container>
        <el-aside width="200px"><LayoutAside /></el-aside>
        <el-main><RouterView /></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
.layout-view {
  height: 100%;
}
</style>
