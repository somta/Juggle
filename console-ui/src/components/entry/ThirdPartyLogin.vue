<script setup lang="ts">

</script>

<template>
  <div class="third-login">
    <div class="title"><span>-----------------</span> 一键登录体验 <span>------------------</span></div>
    <div class="auth_app">
      <a href="https://demo.juggle.plus/open/v1/gitee/auth">
        <img src="https://demo.juggle.plus/suite/gitee.svg" alt="gitee">
      </a>
      <a href="https://demo.juggle.plus/open/v1/github/auth">
        <img src="https://demo.juggle.plus/suite/github.svg" alt="github">
      </a>
    </div>
  </div>
</template>

<style scoped lang="less">

.title{
  text-align: center;
  color: #409eff;
  margin-bottom: 8px;
}

.auth_app{
  text-align: center;
  a {
    text-decoration: none;
    margin: 0px 10px;
  }
  img{
    width: 45px;
  }
}

</style>