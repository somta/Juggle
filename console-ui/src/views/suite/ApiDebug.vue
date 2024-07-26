<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import { apiService } from '@/service';
import { ElMessage } from 'element-plus';
import CodeEditor from '@/components/form/CodeEditor.vue';
import { ApiInfo, DataTypeItem, InputParams } from '@/typings';
import FilterValue from '@/components/filter/FilterValue.vue';
import DataTypeSelect from '@/components/form/DataTypeSelect.vue';

const route = useRoute();
let paramsData = reactive({
  params: route.params,
});

const codeEditRef = ref<InstanceType<typeof CodeEditor>>();

let flowResponseJson = ref('');
const apiInfo = ref<ApiInfo>({
  id: null,
  suiteId: null,
  apiUrl: '',
  apiName: '',
  apiDesc: '',
  apiRequestType: '',
  apiRequestContentType: '',
  apiHeaders: [],
  apiInputParams: [],
  apiOutputParams: [],
});

const responseHeaderData = ref([]);

queryApiInfo();

async function queryApiInfo() {
  const res = await apiService.queryApiInfo(paramsData.params.apiId as number);
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
  const params = {
    headerData: getHeaders(),
    inputParamData: getParams(),
  };
  const res = await apiService.debugApi(paramsData.params.apiId as number, params);
  if (res.success) {
    flowResponseJson.value = JSON.stringify(res.result);
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
  responseHeaderData.value = Object.entries(res.response?.headers).map(([key, value]) => {
    return {
      headerKey: key,
      headerValue: value,
    };
  });
}

function isEmpty(val: any) {
  return val === undefined || val === null || val === '';
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
    if (!isEmpty(param.value)) {
      const dataType: DataTypeItem = param.dataType;
      if (dataType.type === 'Object' || dataType.type === 'List') {
        params[param.paramKey] = JSON.parse(param.value);
      } else {
        params[param.paramKey] = param.value;
      }
      console.log(param);
    }
  });
  return params;
}

function resetParams() {
  apiInfo.value?.apiHeaders.forEach((param: any) => {
    param.value = '';
    param.error = '';
  });
  apiInfo.value?.apiInputParams.forEach((param: any) => {
    param.value = '';
    param.error = '';
  });
}
</script>

<template>
  <div class="flow-debug">
    <el-row :gutter="16">
      <el-col :span="20">
        <el-input v-model="apiInfo.apiUrl">
          <template #prepend>
            <el-select v-model="apiInfo.apiRequestType" disabled style="width: 115px">
              <el-option label="GET" value="GET" />
              <el-option label="POST" value="POST" />
              <el-option label="PUT" value="PUT" />
              <el-option label="DELETE" value="DELETE" />
            </el-select>
          </template>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="sendApiDebug">发送</el-button>
        <el-button @click="resetParams">重置</el-button>
      </el-col>
    </el-row>
    <el-tabs model-value="inputParam">
      <el-tab-pane label="请求头" name="headerParam">
        <div class="input-param-head">
          <div class="input-param-tr">
            <div class="input-param-td"></div>
            <div class="input-param-td">参数编码</div>
            <div class="input-param-td">参数名称</div>
            <div class="input-param-td">参数类型</div>
            <div class="input-param-td td-value">参数值</div>
          </div>
        </div>
        <div class="input-param-body">
          <div class="input-param-tr" v-for="header in apiInfo?.apiHeaders as InputParams[]" :key="header.paramKey">
            <div class="input-param-td">
              <template v-if="header.required">*</template>
            </div>
            <div class="input-param-td" :title="header.paramKey">{{ header.paramKey }}</div>
            <div class="input-param-td" :title="header.paramName">{{ header.paramName }}</div>
            <div class="input-param-td">
              <DataTypeSelect :modelValue="header.dataType" disabled />
            </div>
            <div class="input-param-td td-value">
              <FilterValue v-model="header.value" :dataType="header.dataType" />
            </div>
            <div class="input-param-td td-error">{{ header.error || '' }}</div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="请求参数" name="inputParam">
        <div class="input-param-head">
          <div class="input-param-tr">
            <div class="input-param-td"></div>
            <div class="input-param-td">参数编码</div>
            <div class="input-param-td">参数名称</div>
            <div class="input-param-td">参数类型</div>
            <div class="input-param-td td-value">参数值</div>
          </div>
        </div>
        <div class="input-param-body">
          <div class="input-param-tr" v-for="param in apiInfo?.apiInputParams" :key="param.paramKey">
            <div class="input-param-td">
              <template v-if="param.required">*</template>
            </div>
            <div class="input-param-td" :title="param.paramKey">{{ param.paramKey }}</div>
            <div class="input-param-td" :title="param.paramName">{{ param.paramName }}</div>
            <div class="input-param-td">
              <DataTypeSelect :modelValue="param.dataType" disabled />
            </div>
            <div class="input-param-td td-value">
              <FilterValue v-model="param.value" :dataType="param.dataType" />
            </div>
            <div class="input-param-td td-error">{{ param.error || '' }}</div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>

    <el-tabs model-value="result">
      <el-tab-pane label="响应内容" name="result">
        <el-text line-clamp="2">
          <CodeEditor ref="codeEditRef" v-model="flowResponseJson" width="1000px" height="200px" language="json" />
        </el-text>
      </el-tab-pane>
      <el-tab-pane label="响应头" name="responseHeader">
        <el-table :data="responseHeaderData" style="width: 100%">
          <el-table-column prop="headerKey" label="响应头" width="350" />
          <el-table-column prop="headerValue" label="值" />
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style lang="less" scoped>
.flow-debug {
  padding: 24px 40px;

  .input-param-body {
    color: #666;
  }
  .input-param-tr {
    display: flex;
    font-size: 14px;
    line-height: 28px;
    margin-bottom: 8px;
  }
  .input-param-td {
    margin-right: 12px;
    width: 120px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    &:first-child {
      margin: 0;
      width: 12px;
      color: red;
    }
    &.td-value {
      width: 240px;
    }
    &.td-error {
      color: red;
    }
  }
}
</style>
