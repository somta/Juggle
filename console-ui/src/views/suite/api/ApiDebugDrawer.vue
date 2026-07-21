<script setup lang="ts">
import {ref} from 'vue';
import { apiService } from '@/service';
import { ElMessage } from 'element-plus';
import {ApiInfo, DataType} from '@/typings';
import FilterValue from '@/components/filter/FilterValue.vue';
import { QuestionFilled } from '@element-plus/icons-vue';
import DataTypeDisplay from "@/components/common/DataTypeDisplay.vue";
import {isEmpty, safeTrim} from "@/utils/CommonUtil.ts";
import {computed} from "vue";
import VueJsonPretty from 'vue-json-pretty';
import ResizableDrawer from "@/components/common/ResizableDrawer.vue";

const loading = ref(false);
const apiDebugDrawerVisible = ref(false);
const activeNames = ref(['param']);
const apiId = ref<string>();
let flowResponseJson = ref();
const apiInfo = ref<ApiInfo>({
  id: null,
  suiteId: null,
  suiteCode: '',
  suiteFlag: null,
  apiCode: '',
  apiProtocol:'',
  apiUrl: '',
  apiName: '',
  apiDesc: '',
  apiRequestType: '',
  apiRequestContentType: '',
  interfaceName:'',
  methodName:'',
  groupName:'',
  namespace: '',
  responseName: '',
  apiHeaders: [],
  apiInputParams: [],
  apiOutputParams: [],
});

const suiteCodes = computed(() => [apiInfo.value.suiteCode]);

async function queryApiInfo() {
  const res = await apiService.queryApiInfo(apiId.value as string);
  if (res.success) {
    apiInfo.value = res.result;
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function sendApiDebug() {
  if (!validate()) {
    return;
  }
  loading.value = true;
  const params = {
    headerData: getHeaders(),
    inputParamData: getParams(),
  };
  const res = await apiService.debugApi(apiId.value as string, params);
  if (res.success) {
    flowResponseJson.value = res.result;
    activeNames.value.push("result");
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
  loading.value = false;
}

function validate() {
  const apiHeaders = apiInfo.value?.apiHeaders || [];
  const apiInputParams = apiInfo.value?.apiInputParams || [];
  const errors: string[] = [];
  apiHeaders.forEach((param: any) => {
    if (param.required && isEmpty(param.value)) {
      param.error = '必填字段不能为空';
      errors.push(param.paramKey);
    } else {
      param.error = '';
    }
  });
  apiInputParams.forEach((param: any) => {
    if (param.required && isEmpty(param.value)) {
      param.error = '必填字段不能为空';
      errors.push(param.paramKey);
    } else {
      param.error = '';
    }
  });
  return errors.length === 0;
}

function getHeaders() {
  const apiHeaders = apiInfo.value?.apiHeaders || [];
  const params: any = {};
  apiHeaders.forEach((param: any) => {
    if (!isEmpty(param.value)) {
      params[param.paramKey] = param.value;
    }
  });
  return params;
}

function getParams() {
  const apiInputParams = apiInfo.value?.apiInputParams || [];
  const params: any = {};
  apiInputParams.forEach((param: any) => {
    const dataType: DataType = param.dataType;
    if (!isEmpty(param.value)) {
      if (dataType.type === 'Object' || dataType.type === 'List') {
        params[param.paramKey] = param.value;
      }   else if(dataType.type === 'String') {
        params[param.paramKey] = safeTrim(param.value);
      } else {
        params[param.paramKey] = param.value;
      }
    }else{
      if (dataType.type === 'Boolean') {
        params[param.paramKey] = false;
      }
    }
  });
  return params;
}

function open(id:string) {
  apiId.value = id;
  queryApiInfo();
  apiDebugDrawerVisible.value = true;
}

defineExpose({ open });
</script>

<template>
  <ResizableDrawer v-model="apiDebugDrawerVisible" :size="500" title="接口调试" drawer-key="API_DEBUG" destroyOnClose>
    <div class="api-debug"
         v-loading="loading"
         element-loading-text="接口请求中">
      <div class="api-debug-content">
        <div class="api-url">
          <div class="name">接口地址</div>
          <el-text>{{ apiInfo.apiUrl }}</el-text>
        </div>
        <el-collapse expand-icon-position="left" v-model="activeNames" >
          <el-collapse-item title="请求头" name="header">
            <div class="param-item" v-for="param in apiInfo?.apiHeaders" :key="param.paramKey">
              <div class="param-code">
                <span v-if="param.required" class="required-mark">*</span>
                <span class="param-key"> {{ param.paramKey }} </span>
                <span class="param-data-type"> <DataTypeDisplay :dataType="param.dataType" :suite-codes="suiteCodes"/> </span>
              </div>
              <div class="param-name">
                {{ param.paramName }}
                <span class="param-desc-tip">
                  <el-tooltip v-if="param.paramDesc" effect="dark" placement="top" :content="param.paramDesc">
                    <el-icon style="color: var(--el-text-color-secondary);"><QuestionFilled /></el-icon>
                  </el-tooltip>
                </span>
              </div>
              <div class="param-value">
                <FilterValue v-model="param.value" :dataType="param.dataType" />
                <div class="error-message">{{ param.error || '' }}</div>
              </div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="请求参数" name="param">
            <div class="param-item" v-for="param in apiInfo?.apiInputParams" :key="param.paramKey">
              <div class="param-code">
                <span v-if="param.required" class="required-mark">*</span>
                <span class="param-key"> {{ param.paramKey }} </span>
                <span class="param-data-type"> <DataTypeDisplay :dataType="param.dataType" :suite-codes="suiteCodes"/> </span>
              </div>
              <div class="param-name">
                {{ param.paramName }}
                <span class="param-desc-tip">
                  <el-tooltip v-if="param.paramDesc" effect="dark" placement="top" :content="param.paramDesc">
                    <el-icon style="color: var(--el-text-color-secondary);"><QuestionFilled /></el-icon>
                  </el-tooltip>
                </span>
              </div>
              <div class="param-value">
                <FilterValue v-model="param.value" :dataType="param.dataType" />
                <div class="error-message">{{ param.error || '' }}</div>
              </div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="运行结果" name="result">
            <div v-if="flowResponseJson">
              <vue-json-pretty
                  :data="flowResponseJson"
                  :deep="3"
                  show-length
                  show-line-num
              />
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
      <div class="api-debug-footer">
        <span class="run-button" @click="sendApiDebug">
          <svg width="1em" height="1em" viewBox="0 0 25 24" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M19.5283 11.1341C20.1949 11.519 20.1949 12.4812 19.5283 12.8661L7.8313 19.6194C7.16463 20.0043 6.3313 19.5231 6.3313 18.7533L6.3313 5.24685C6.3313 4.47705 7.16463 3.99593 7.8313 4.38083L19.5283 11.1341Z"></path></svg>
          <span class="run-button-right">试运行</span>
        </span>
      </div>
    </div>
  </ResizableDrawer>
</template>

<style lang="less" scoped>
.api-debug {

  .api-url {
    font-size: 13px;
    margin-bottom: 10px;

    .name {
      margin-bottom: 4px;
    }
  }

  .api-debug-content {
    flex: 1;
    overflow-y: auto;
    margin-bottom: 16px;
  }

  .api-debug-footer {
    display: flex;
    justify-content: center;
    padding-top: 10px;

    .run-button{
      width: 100%;
      align-items: center;
      display: flex;
      justify-content: center;
      background-color: #00b42a;
      color: #fff;
      padding: 15px 16px;
      border-radius: 4px;
      transition: background-color 0.2s ease;
      height: 24px;
    }

    .run-button:hover {
      background-color: #00a329;
    }

    .run-button-right {
      margin-left: 6px;
      font-size: 14px;
    }
  }


  .param-item {
    margin-bottom: 15px;
    align-items: center;
    gap: 10px;

    .param-code {
      display: flex;
      align-items: center;
      gap: 5px;
      flex: 1;
    }

    .param-data-type {
      font-size: 10px;
      display: inline-block;
      background-color: #f5f7fa;
      padding: 2px 6px;
      border-radius: 4px;
      white-space: nowrap;
    }

    .param-key {
      font-size: 14px;
      font-weight: bold;
    }

    .param-name {
      font-weight: 400;
      display: flex;
      gap: 2px;
      
      .param-desc-tip {
        padding-top: 2px;
      }
    }

    .required-mark {
      color: #f56c6c;
    }

    .param-value {
      flex: 1;
    }

    .error-message {
      color: #f56c6c;
      font-size: 12px;
      margin-top: 4px;
    }
  }
}
</style>
