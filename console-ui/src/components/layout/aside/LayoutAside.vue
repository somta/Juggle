<script lang="ts" setup>
import { Coin, ShoppingBag } from '@element-plus/icons-vue';
import IconFlow from '@/components/icons/IconFlow.vue';
import IconInterface from '@/components/icons/IconInterface.vue';
import IconSetting from "@/components/icons/IconSetting.vue";
</script>

<template>
  <div class="layout-aside">
    <el-menu :default-active="$route.fullPath" :unique-opened="true" class="el-menu-vertical-demo" style="height: 100%" router>
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
      <el-menu-item index="/suite/list">
        <el-icon><IconInterface /></el-icon>
        <span>套件</span>
      </el-menu-item>
      <el-menu-item index="/object/list">
        <el-icon><Coin /></el-icon>
        <span>对象</span>
      </el-menu-item>
      <el-sub-menu index="4">
        <template #title>
          <el-icon><ShoppingBag /></el-icon>
          <span>市场</span>
        </template>
        <el-menu-item index="/market/suite">套件市场</el-menu-item>
        <el-menu-item index="/market/template">模板市场</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <el-icon><IconSetting/></el-icon>
          <span>设置</span>
        </template>
        <el-menu-item index="/system/token">令牌</el-menu-item>
        <el-menu-item index="/system/datasource">数据源</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<style lang="less" scoped>
.layout-aside {
  height: 100%;
}
.el-menu-item.is-active {
  background: #ecf5ff !important;
  border-right: 2px solid #409eff;
}
</style>
