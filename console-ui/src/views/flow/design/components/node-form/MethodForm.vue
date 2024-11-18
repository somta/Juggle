<script lang="ts" setup>
import ApiSelect from '@/components/form/ApiSelect.vue';
import OutputRuleSetting from '@/components/form/OutputRuleSetting.vue';
import InputRuleSetting from '@/components/form/InputRuleSetting.vue';
import {computed, PropType, ref, watch} from 'vue';
import {ElementType, FlowVariableType, MethodInfo, RawData} from '../../types';
import {apiService, suiteService} from '@/service';
import {InputParams, valueType} from '@/typings';
import {useFlowDataInject} from '../../hooks/flow-data';
import {cloneDeep} from 'lodash-es';
import {ElMessage} from 'element-plus';

const flowContext = useFlowDataInject();

type MethodRawData = RawData & { method: MethodInfo };

function getDefaultData() {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.METHOD,
    desc: '',
    method: {
      methodCode: null as string,
      suiteCode: null as string,
      url: '',
      requestType: '',
      requestContentType: '',
      inputParamSchemas: [],
      headerFillRules: [],
      inputFillRules: [],
      outputFillRules: [],
    },
  };
}

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<MethodRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as MethodRawData);
const suiteList = ref([]);
const suiteLoading = ref(false);
let suiteLoaded = false;

watch(
  () => props.data,
  val => {
    if (val !== nodeData.value) {
      nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
      if (nodeData.value.method.methodCode) {
        initApiSourceList(nodeData.value.method.methodCode);
      }
    }
  },
  { immediate: true }
);

querySuiteList();



function paramToRule(param: { dataType: string; paramKey: string; paramName: string; required?: boolean }, sourceType: valueType) {
  return {
    source: '',
    sourceDataType: null,
    sourceType: valueType.VARIABLE,
    target: param.paramKey,
    targetDataType: param.dataType,
    targetType: sourceType,
    required: true,
  };
}

async function initApiSourceList(apiCode: string) {
  const res = await apiService.queryApiInfoByCode(apiCode);
  if (res.result) {
    const result = res.result;
    headerSourceList.value = result.apiHeaders.map(item => ({ ...item, targetType: valueType.HEADER }));
    inputSourceList.value = result.apiInputParams.map(item => ({ ...item, targetType: valueType.INPUT_PARAM }));
    outputSourceList.value = result.apiOutputParams.map(item => ({ ...item, sourceType: valueType.OUTPUT_PARAM }));
    // 默认必填 - 头参
    const headerRequired = result.apiHeaders.filter(item => item.required);
    headerRequiredKeys.value = headerRequired.map(item => item.paramKey);
    // 默认必填 - 入参
    const inputRequired = result.apiInputParams.filter(item => item.required);
    inputRequiredKeys.value = inputRequired.map(item => item.paramKey);
  }
}

function onSuiteChange(suiteCode:string) {
  nodeData.value.method = {
    ...getDefaultData().method,
    suiteCode: suiteCode,
  };
}

async function onApiChange(apiCode: string) {
  console.log("apiCode=",apiCode);
  const res = await apiService.queryApiInfoByCode(apiCode);
  if (res.result) {
    const result = res.result;
    const method = nodeData.value.method;
    method.requestType = result.apiRequestType;
    method.requestContentType = result.apiRequestContentType;
    method.inputParamSchemas = result.apiInputParams.map((item: InputParams) => {
      return {
        key: item.paramKey,
        name: item.paramName,
        dataType: item.dataType,
        position: item.paramPosition,
      };
    });
    headerSourceList.value = result.apiHeaders.map(item => ({ ...item, targetType: valueType.HEADER }));
    inputSourceList.value = result.apiInputParams.map(item => ({ ...item, targetType: valueType.INPUT_PARAM }));
    outputSourceList.value = result.apiOutputParams.map(item => ({ ...item, sourceType: valueType.OUTPUT_PARAM }));
    // 默认必填 - 头参
    const headerRequired = result.apiHeaders.filter(item => item.required);
    method.headerFillRules = headerRequired.map(item => paramToRule(item, valueType.HEADER));
    headerRequiredKeys.value = headerRequired.map(item => item.paramKey);

    // 默认必填 - 入参
    const inputRequired = result.apiInputParams.filter(item => item.required);
    method.inputFillRules = inputRequired.map(item => paramToRule(item, valueType.INPUT_PARAM));
    inputRequiredKeys.value = inputRequired.map(item => item.paramKey);

    method.outputFillRules = [];
    method.url = result.apiUrl;
  }
}

// 参数
const headerSourceList = ref<any[]>([]);
const inputSourceList = ref<any[]>([]);
const outputSourceList = ref<any[]>([]);
// 必填，不可删除
const headerRequiredKeys = ref<string[]>([]);
const inputRequiredKeys = ref<string[]>([]);

const inputTargetList = computed(() => {
  const flowVariables = flowContext.data.value.flowVariables;
  return flowVariables.filter(item => [FlowVariableType.INPUT, FlowVariableType.TEMP].includes(item.envType));
});

const outputTargetList = computed(() => {
  const flowVariables = flowContext.data.value.flowVariables;
  return flowVariables.filter(item => [FlowVariableType.OUTPUT, FlowVariableType.TEMP].includes(item.envType));
});

function validateParam(param: any) {
  if (!param.source) {
    return false;
  }
  if (!param.target) {
    return false;
  }
  return true;
}

function validate() {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
    return false;
  }
  const method = nodeData.value.method;
  if (!method.methodCode) {
    ElMessage.error('服务接口不能为空');
    return false;
  }
  if (method.headerFillRules.length > 0 && !method.headerFillRules.every(validateParam)) {
    ElMessage.error('请求头赋值不完整');
    return false;
  }
  if (method.inputFillRules.length > 0 && !method.inputFillRules.every(validateParam)) {
    ElMessage.error('入参赋值不完整');
    return false;
  }
  if (method.outputFillRules.length > 0 && !method.outputFillRules.every(validateParam)) {
    ElMessage.error('出参赋值不完整');
    return false;
  }
  return true;
}

function onSubmit() {
  if (!validate()) {
    return;
  }
  emit('update', cloneDeep(nodeData.value));
}
function onCancel() {
  emit('cancel');
}

async function querySuiteList() {
  suiteLoading.value = true;
  const res = await suiteService.querySuiteList();
  if (res.success) {
    suiteList.value = res.result;
  }
  suiteLoaded = true;
  suiteLoading.value = false;
}
</script>

<template>
  <div class="node-method-form">
    <el-form label-position="top">
      <el-form-item label="节点编码">
        <span>{{ nodeData.key }}</span>
      </el-form-item>
      <el-form-item label="节点名称" required>
        <el-input v-model="nodeData.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="节点描述">
        <el-input v-model="nodeData.desc" placeholder="请输入" :rows="2" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="套件" required>
<!--        <SuiteSelect v-model="nodeData.method.suiteId" :auto="true" @change="onSuiteChange" />-->
        <el-select
            :modelValue="nodeData.method.suiteCode"
            placeholder="请选择套件"
            style="width: 100%"
            filterable
            @change="onSuiteChange"
        >
          <template v-slot:empty>
            <div class="select-option-empty" v-loading="suiteLoading">
              <span v-if="!suiteLoading">无数据</span>
            </div>
          </template>
          <el-option v-for="item in suiteList" :key="item.suiteCode" :label="item.suiteName" :value="item.suiteCode" />
        </el-select>
      </el-form-item>
      <el-form-item label="服务接口" required>
        <ApiSelect v-model="nodeData.method.methodCode" :suiteCode="nodeData.method.suiteCode" @change="onApiChange" />
      </el-form-item>
      <el-form-item label="请求头赋值">
        <InputRuleSetting
          v-model="nodeData.method.headerFillRules"
          addText="新增请求头赋值"
          showRequired
          :sourceList="headerSourceList"
          :targetList="inputTargetList"
          :requiredKeys="headerRequiredKeys"
        />
      </el-form-item>
      <el-form-item label="入参赋值">
        <InputRuleSetting
          v-model="nodeData.method.inputFillRules"
          addText="新增入参赋值"
          showRequired
          :sourceList="inputSourceList"
          :targetList="inputTargetList"
          :requiredKeys="inputRequiredKeys"
        />
      </el-form-item>
      <el-form-item label="出参赋值">
        <OutputRuleSetting
          v-model="nodeData.method.outputFillRules"
          addText="新增出参赋值"
          :sourceList="outputSourceList"
          :targetList="outputTargetList"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
