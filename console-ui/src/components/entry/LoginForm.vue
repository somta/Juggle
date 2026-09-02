<script setup lang="ts">
import { ref } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { userService } from '@/service';

const REMEMBER_KEY = 'juggle-remember-account';

const router = useRouter();

const userName = ref(localStorage.getItem(REMEMBER_KEY) || '');
const password = ref('');
const rememberMe = ref(!!localStorage.getItem(REMEMBER_KEY));
const loading = ref(false);

async function submit() {
  if (!userName.value || !password.value) {
    ElMessage.error('用户名或密码为空');
    return;
  }
  loading.value = true;
  const result = await userService.login({
    userName: userName.value,
    password: password.value,
  });
  if (result.success) {
    if (rememberMe.value) {
      localStorage.setItem(REMEMBER_KEY, userName.value);
    } else {
      localStorage.removeItem(REMEMBER_KEY);
    }
    await router.push({name: 'flow'});
  } else {
    ElMessage.error(result.errorMsg || '登录失败');
  }
  loading.value = false;
}
</script>

<template>
  <div class="login-form">
    <div class="login-form-header">
      <h1 class="form-title">登录</h1>
      <p class="form-subtitle">欢迎登录 Juggle 微服务编排引擎</p>
    </div>

    <div class="login-form-body">
      <div class="form-item">
        <label class="form-label">账号</label>
        <el-input
          v-model="userName"
          type="text"
          placeholder="请输入账号"
          size="large"
          :prefix-icon="User"
          @keyup.enter="submit"
        />
      </div>

      <div class="form-item">
        <label class="form-label">密码</label>
        <el-input
          v-model="password"
          type="password"
          placeholder="请输入密码"
          show-password
          size="large"
          :prefix-icon="Lock"
          @keyup.enter="submit"
        />
      </div>

      <div class="form-options">
        <el-checkbox v-model="rememberMe">记住账号</el-checkbox>
      </div>

      <el-button
        type="primary"
        size="large"
        :style="{ width: '100%' }"
        :loading="loading"
        @click="submit"
        class="login-btn"
      >
        登录
      </el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.login-form {
  display: flex;
  flex-direction: column;
}

.login-form-header {
  margin-bottom: 28px;

  .form-title {
    font-size: 26px;
    font-weight: 700;
    color: #1e1b2e;
    margin: 0;
    letter-spacing: -0.5px;
  }

  .form-subtitle {
    margin: 8px 0 0;
    font-size: 13px;
    color: #9a91a8;
  }
}

.login-form-body {
  width: 100%;

  .form-item {
    margin-bottom: 18px;
  }

  .form-label {
    display: block;
    margin-bottom: 6px;
    font-size: 13px;
    color: #5a5270;
  }

  :deep(.el-input__inner) {
    height: 44px;
    line-height: 44px;
    font-size: 14px;
    color: #1e1b2e;
  }

  :deep(.el-input__prefix) {
    color: #b8afcc;

    svg {
      width: 16px;
      height: 16px;
    }
  }

  .form-options {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 2px 0 22px;

    :deep(.el-checkbox__label) {
      font-size: 13px;
      color: #5a5270;
    }

    :deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
      background-color: #7c3aed;
      border-color: #7c3aed;
    }

    :deep(.el-checkbox__input:hover .el-checkbox__inner),
    :deep(.el-checkbox__input.is-focus .el-checkbox__inner) {
      border-color: #7c3aed;
    }

    :deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
      color: #7c3aed;
    }
  }

  .login-btn {
    height: 44px;
    border-radius: 8px;
    font-size: 15px;
    font-weight: 600;
    background: linear-gradient(135deg, #7c3aed 0%, #6d28d9 100%);
    border: none;
    transition: all 0.2s ease;

    &:hover,
    &:focus {
      background: linear-gradient(135deg, #6d28d9 0%, #5b21b6 100%);
      box-shadow: 0 4px 14px rgba(124, 58, 237, 0.35);
    }

    &:active {
      transform: translateY(1px);
    }
  }
}
</style>
