<script setup lang="ts">
import { ref } from 'vue';
import { useClipboard } from '@vueuse/core';
import {ElMessage} from "element-plus";

const { copy, isSupported } = useClipboard();
const tokenSuccessVisible = ref(false);
const tokenValue = ref('')

function copyToken () {
  if (!isSupported) {
    ElMessage({ type: 'error', message: '当前浏览器不知道复制API' });
    return;
  }
  copy(tokenValue.value);
  ElMessage({ type: 'success', message: '复制成功' });
}

function open(value:string){
  tokenSuccessVisible.value = true;
  tokenValue.value = value;
}

function close(){
  tokenSuccessVisible.value = false;
}

defineExpose({ open });
</script>
<template>
  <el-dialog v-model="tokenSuccessVisible" title="令牌生成提示" :close-on-click-modal="false" width="400">
    <el-result
        icon="success"
        title="令牌生成成功"
    >
    </el-result>
    <el-input v-model="tokenValue">
      <template #append>
        <el-button @click.prevent="copyToken">复制</el-button>
      </template>
    </el-input>
    <div class="token-tips">
      <el-text class="mx-1" type="danger">本页面关闭后，平台将不在显示私人令牌，请妥善保管。</el-text>
    </div>
    <template #footer>
        <el-button type="primary" @click.prevent="close">确认并关闭</el-button>
    </template>
  </el-dialog>
</template>

<style scoped>
.token-tips{
  margin-top: 10px;
}
</style>
