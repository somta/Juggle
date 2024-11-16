<script setup lang="ts">
import { ref } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { userService } from '@/service';
const router = useRouter();

const userName = ref('');
const password = ref('');
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
    await router.push({name: 'flow'});
  } else {
    ElMessage.error(result.errorMsg || '登录失败');
  }
  loading.value = false;
}
</script>

<template>
  <div class="login-form">
    <div class="login-form-item">
      <el-input v-model="userName" type="text" placeholder="用户名" size="large" :prefix-icon="User" @keyup.enter="submit" />
    </div>
    <div class="login-form-item">
      <el-input v-model="password" type="password" placeholder="密码" show-password size="large" :prefix-icon="Lock" @keyup.enter="submit" />
    </div>
    <div class="login-form-item">
      <el-button type="primary" size="large" :style="{ width: '100%', marginTop: '24px' }" :loading="loading" @click="submit"> 登录 </el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.login-form {
  margin-top: 60px;
  .login-form-item {
    margin-bottom: 24px;
  }
}
</style>
