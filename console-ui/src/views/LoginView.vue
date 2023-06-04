
<script setup lang="ts">
import LoginForm from '@/components/entry/LoginForm.vue';
</script>

<template>
  <div class="entry-view">
    <div class="entry-view-title">Juggle</div>
    <div class="entry-view-subtitle">一个微服务编排系统</div>
    <div class="entry-view-form">
      <LoginForm />
    </div>
  </div>
</template>

<style lang="less" scoped>
.entry-view {
  height: 100%;
  padding-top: 72px;
  background: #f0f2f5;

  .entry-view-title {
    font-size: 34px;
    font-family: Avenir,Helvetica Neue,Arial,Helvetica,sans-serif;
    font-weight: 600;
    position: relative;
    text-align: center;
  }

  .entry-view-subtitle {
    font-size: 14px;
    color: rgba(0,0,0,.45);
    margin-top: 12px;
    margin-bottom: 40px;
    text-align: center;
  }
  
  .entry-view-form {
    margin: 0 auto;
    width: 360px;
    min-height: 260px;
  }
}
</style>

