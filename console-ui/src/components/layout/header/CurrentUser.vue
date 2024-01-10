<script setup lang="ts">
import { useRouter } from 'vue-router';
import { userService } from '@/service';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
const $router = useRouter();

const userName = ref('');
userName.value = window.localStorage.getItem('Juggle-userName');

async function logout() {
  const res = await userService.logout();
  if (res) {
    await $router.push('/login');
  } else {
    ElMessage.error('退出失败');
  }
}
</script>
<template>
  <el-dropdown class="app-current-userPO-dropdown">
    <div class="app-current-userPO">
      <el-avatar :size="32" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <span class="current-userPO-name">{{ userName }}</span>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="$router.push('/userPO')">用户信息</el-dropdown-item>
        <el-dropdown-item @click="$router.push('/about')">关于我们</el-dropdown-item>
        <el-dropdown-item @click="logout" divided>退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>
<style lang="less" scoped>
.app-current-userPO-dropdown {
  height: 100%;
  padding: 0 20px 0 10px;
}
.app-current-userPO {
  height: 100%;
  display: flex;
  align-items: center;
  color: #fff;
  outline: none;

  .current-userPO-name {
    font-size: 15px;
    margin-left: 8px;
  }
}
</style>
