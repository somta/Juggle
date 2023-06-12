<script setup lang="ts">
import { useRouter } from 'vue-router';
import { userService } from '@/service';
import { ElMessage } from 'element-plus';
const $router = useRouter();
async function logout () {
  const res = await userService.logout();
  if (res) {
    $router.push('/login');
  } else {
    ElMessage.error('退出失败');
  }
}
</script>
<template>
  <el-dropdown class="app-current-user-dropdown">
    <div class="app-current-user">
      <el-avatar :size="32" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <span class="current-user-name">admin</span>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="$router.push('/user')">用户信息</el-dropdown-item>
        <el-dropdown-item @click="$router.push('/about')">关于我们</el-dropdown-item>
        <el-dropdown-item @click="logout" divided>退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>
<style lang="less" scoped>
.app-current-user-dropdown {
  height: 100%;
  padding: 0 20px;
}
.app-current-user {
  height: 100%;
  display: flex;
  align-items: center;
  color: #fff;
  outline: none;

  .current-user-name {
    font-size: 14px;
    margin-left: 8px;
  }
}
</style>
