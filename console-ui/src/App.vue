<script setup lang="ts">
import { RouterView } from 'vue-router';
// @ts-ignore
import zhCn from 'element-plus/dist/locale/zh-cn.mjs';
</script>

<template>
  <el-config-provider :locale="zhCn">
    <RouterView />
  </el-config-provider>
</template>

