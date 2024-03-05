<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import {flowDefineService, flowVersionService} from '@/service';
import { ElMessage } from 'element-plus';
import CodeEditor from "@/components/form/CodeEditor.vue";
import {FlowDefineInfo} from "@/typings";

const route = useRoute();
let paramsData = reactive({
  params: route.params,
});

const codeEditRef = ref<InstanceType<typeof CodeEditor>>();


const requestTabActiveName = ref('inputParam');
const responseTabActiveName = ref('result');

const debugUrl = ref('');
let flowResponseJson = ref(`{
          "flowInstanceId": "sync_MurlbkKxc6",
          "status": "FINISH",
          "data": {
          "userName": "张三"
          }`);
const flowDefine = ref<FlowDefineInfo>();

queryFlowDefineInfo();

async function queryFlowDefineInfo() {
  const res = await flowDefineService.getDefineInfo(paramsData.params.flowDefinitionId as number);
  if (res.success) {
    // 填充到面板
    //debugUrl.value = window.location.origin + '/v1/flow/definition/debug/'+ paramsData.params.flowKey;
    debugUrl.value = 'http://127.0.0.1:8686/v1/flow/definition/debug/' + paramsData.params.flowKey;
    flowDefine.value = res.result;
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

let timerId;
async function sendFlowDebug() {
  //todo 这里参数要从流程入参中获取
  const res = await flowDefineService.debugFlow(paramsData.params.flowKey as string, {});
  if (res.success) {
    if(flowDefine.value?.flowType === "sync"){
      flowResponseJson.value = String(res.result);
    }else{
      console.log("123")
      timerId = setInterval(getAsyncFlowResult, 1000);
    }
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function getAsyncFlowResult(flowInstanceId: string) {
  const res = await flowVersionService.getAsyncFlowResult(flowInstanceId);
  if (res.success) {
    if(res.result){
      flowResponseJson.value = String(res.result);
      clearInterval(timerId);
    }
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

</script>

<template>
  <div class="flow-debug">
    <el-row>
      <el-col :span="20">
        <el-input v-model="debugUrl"/>
      </el-col>
      <el-col :span="4">
        <el-button @click="sendFlowDebug">发送</el-button>
        <el-button >重置</el-button>
      </el-col>
    </el-row>
    <el-tabs v-model="requestTabActiveName">
      <el-tab-pane label="请求参数" name="inputParam">
        <template v-for="param in flowDefine?.flowInputParams" :key="param.paramKey">
          <span>{{param?.paramName}} : </span>
          <el-input v-model="param.paramKey" style="width: 240px"/>
        </template>
      </el-tab-pane>
    </el-tabs>

    <el-tabs v-model="responseTabActiveName">
      <el-tab-pane label="响应内容" name="result">
        <el-text line-clamp="2">
          <CodeEditor ref="codeEditRef" v-model="flowResponseJson" width="1000px" height="200px" language="json" />
        </el-text>
      </el-tab-pane>
      <el-tab-pane label="响应头" name="result">

      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style lang="less" scoped>
.flow-debug {
  padding: 24px 40px;
}
</style>
