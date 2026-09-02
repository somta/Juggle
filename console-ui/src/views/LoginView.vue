<script setup lang="ts">
import LoginForm from '@/components/entry/LoginForm.vue';
import banImg from '@/assets/images/juggle_ban.png';
</script>

<template>
  <div class="login-page">
    <div class="login-left">
      <img class="login-banner" :src="banImg" alt="Juggle - 零码+低码+AI的微服务编排引擎" />
    </div>
    <div class="login-right">
      <div class="login-card">
        <LoginForm />
      </div>
      <!--      <div class="login-footer">© 2026 Juggle · 灵码科技</div>-->
    </div>
  </div>
</template>

<style lang="less" scoped>
.login-page {
  display: flex;
  height: 100%;
  width: 100%;
  min-height: 0;
  overflow: hidden;
  background: #efecfb;
}

.login-left {
  flex: 1.35;
  min-width: 0;
  min-height: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 48px 48px 48px 48px;
  box-sizing: border-box;

  .login-banner {
    max-width: 100%;
    max-height: 100%;
    width: auto;
    height: auto;
    display: block;
  }
}

.login-right {
  flex: 1;
  min-width: 0;
  min-height: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px;
  position: relative;
}

.login-card {
  width: 100%;
  max-width: 440px;
  background: #fff;
  border-radius: 16px;
  padding: 44px 40px 40px;
  box-shadow: 0 4px 24px rgba(108, 99, 190, 0.10);
}

.login-footer {
  margin-top: 28px;
  font-size: 12px;
  color: #9a91a8;
  letter-spacing: 0.3px;
}

@media (max-width: 900px) {
  .login-left {
    display: none;
  }
}
</style>
