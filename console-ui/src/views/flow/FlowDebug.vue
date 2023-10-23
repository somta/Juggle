<script setup lang="ts">
import {reactive, ref} from "vue";
import {useRoute} from "vue-router";
import {flowDefineService} from "@/service";
import {ElMessage} from "element-plus";

const route=useRoute();
let paramsData=reactive({
  params: route.params
});

// todo 这样要从后端查询这个流程对应的入参，然后动态渲染
const debugForm = reactive({
  name: '',
  region: '',
  type: '',
});

const requestTabActiveName = ref('inputParam');
const responseTabActiveName = ref('result');

const debugUrl = ref('');
//debugUrl.value = window.location.origin + '/v1/flow/definition/debug/'+ paramsData.params.flowKey;
debugUrl.value = 'http://127.0.0.1:8686/v1/flow/definition/debug/'+ paramsData.params.flowKey;

queryFlowDefineInfo();

async function queryFlowDefineInfo() {
  const res = await flowDefineService.getDefineInfo(<number>paramsData.params.flowDefinitionId);
  if (res.success) {
    // 填充到面板
  } else {
    ElMessage({type: 'error', message: res.errorMsg});
  }
}

async function sendFlowDebug() {
  //todo 这里参数要从流程入参中获取
  const res = await flowDefineService.debugFlow(<string>paramsData.params.flowKey,{});
  if (res.success) {
    //todo 显示到界面上
    ElMessage({ type: 'success', message: '调试成功' });
  } else {
    ElMessage({type: 'error', message: res.errorMsg});
  }
}

</script>

<template>
  <div class="flow-debug">
    <el-input v-model="debugUrl">
      <template #append>
        <el-button @click="sendFlowDebug">发送</el-button>
      </template>
    </el-input>
    <el-tabs v-model="requestTabActiveName">
      <el-tab-pane label="请求参数" name="inputParam">
        <el-form
            label-width="100px"
            :model="debugForm"
            style="max-width: 460px"
        >
          <el-form-item label="Name">
            <el-input v-model="debugForm.name" />
          </el-form-item>
          <el-form-item label="Activity zone">
            <el-input v-model="debugForm.region" />
          </el-form-item>
          <el-form-item label="Activity form">
            <el-input v-model="debugForm.type" />
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>

    <el-tabs v-model="responseTabActiveName">
      <el-tab-pane label="响应内容" name="result">
        <el-text line-clamp="2">
          {
          "flowInstanceId": "sync_MurlbkKxc6",
          "status": "FINISH",
          "data": {
          "userName": "张三"
          }
        </el-text>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<style lang="less" scoped>
.flow-debug {
  padding: 24px 40px;
}
</style>
