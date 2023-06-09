<script setup lang="ts">
import AppLogo from './AppLogo.vue';
import CurrentUser from './CurrentUser.vue';
</script>
<template>
  <div class="layout-header">
    <div class="header-left">
      <AppLogo />
    </div>
    <div class="header-right">
      <CurrentUser />
    </div>
  </div>
</template>
<style lang="less" scoped>
.layout-header {
  background-color: rgb(0, 21, 41);
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: stretch;
  color: #fff;
}
</style>
