<script setup lang="ts">
import { useRouter } from 'vue-router';
import { userService } from '@/service';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

const $router = useRouter();

const userName = ref('');
const userNameInitial = ref('');
userName.value = window.localStorage.getItem('Juggle-userName');
userNameInitial.value = userName.value.charAt(0);

async function logout() {
  const res = await userService.logout();
  if (res) {
    await $router.push('/login');
  } else {
    ElMessage.error('退出失败');
  }
}

function extractColorByName(name) {
  const temp = [];
  temp.push('#');
  for (let index = 0; index < name.length; index++) {
    temp.push(parseInt(name[index].charCodeAt(0), 10).toString(16));
  }
  return temp.slice(0, 5).join('').slice(0, 4);
}
</script>
<template>
  <el-dropdown class="app-current-userPO-dropdown">
    <div class="app-current-userPO">
      <el-avatar :size="32" :style="`background:${extractColorByName(userName)}`">{{ userNameInitial }}</el-avatar>
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
  padding: 0 20px 0 0;
}
.app-current-userPO {
  height: 100%;
  display: flex;
  align-items: center;
  color: #fff;
  outline: none;

  .el-avatar {
    font-size: 22px;
    font-weight: bold;
  }

  .current-userPO-name {
    font-size: 15px;
    margin-left: 8px;
  }
}
</style>
