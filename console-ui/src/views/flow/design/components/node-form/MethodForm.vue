
<script lang="ts" setup>
import DomainSelect from '@/components/form/DomainSelect.vue';
import ApiSelect from '@/components/form/ApiSelect.vue';
import RuleSetting from '@/components/form/RuleSetting.vue';
import { PropType, ref, watch, computed } from 'vue';
import { ElementType, RawData, MethodInfo, FlowVariableType } from '../../types';
import { apiService } from '@/service';
import { valueType } from '@/typings';
import { useFlowDataInject } from '../../hooks/flow-data';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';

const flowContext = useFlowDataInject();

type MethodRawData = RawData & { method: MethodInfo };

function getDefaultData () {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.METHOD,
    desc: '',
    method: {
      methodId: null as unknown as number,
      domainId: null as unknown as number,
      url: '',
      requestType: '',
      requestContentType: '',
      headerFillRules: [],
      inputFillRules: [],
      outputFillRules: [],
    },
  }
}

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<MethodRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as MethodRawData);
watch(() => props.data, val => {
  if (val !== nodeData.value) {
    nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
    if (nodeData.value.method.methodId) {
      initApiSourceList(nodeData.value.method.methodId);
    }
  }
}, { immediate: true });

function onDomainChange () {
  nodeData.value.method = {
    ...getDefaultData().method,
    domainId: nodeData.value.method?.domainId as number,
  };
}

function paramToRule (param: { dataType: string; paramKey: string; paramName: string; required?: boolean }) {
  const result = {
    source: param.paramKey,
    sourceDataType: param.dataType,
    sourceType: valueType.VARIABLE,
    target: '',
    targetDataType: null,
    targetType: valueType.VARIABLE,
    required: true,
  }
/*  try {
    result.sourceDataType = JSON.parse(result.sourceDataType);
  } catch (error) {
    console.error(error);
  }*/
  return result;
}

async function initApiSourceList (val: number) {
  const res = await apiService.queryApiInfo(val);
  if (res.result) {
    const result = res.result;
    headerSourceList.value = result.apiHeaders;
    inputSourceList.value = result.apiInputParams;
    outputSourceList.value = result.apiOutputParams;
    // 默认必填 - 头参
    const headerRequired = result.apiHeaders.filter(item => item.required);
    headerRequiredKeys.value = headerRequired.map(item => item.paramKey);
    // 默认必填 - 入参
    const inputRequired = result.apiInputParams.filter(item => item.required);
    inputRequiredKeys.value = inputRequired.map(item => item.paramKey);
  }
}

async function onApiChange (val: number) {
  const res = await apiService.queryApiInfo(val);
  if (res.result) {
    const result = res.result;
    const method = nodeData.value.method;
    method.requestType = result.apiRequestType;
    method.requestContentType = result.apiRequestContentType;
    headerSourceList.value = result.apiHeaders;
    inputSourceList.value = result.apiInputParams;
    outputSourceList.value = result.apiOutputParams;

    // 默认必填 - 头参
    const headerRequired = result.apiHeaders.filter(item => item.required);
    method.headerFillRules = headerRequired.map(paramToRule);
    headerRequiredKeys.value = headerRequired.map(item => item.paramKey);
    
    // 默认必填 - 入参
    const inputRequired = result.apiInputParams.filter(item => item.required);
    method.inputFillRules = inputRequired.map(paramToRule);
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
  return flowVariables.filter(item => [
    FlowVariableType.INPUT,
    FlowVariableType.TEMP,
  ].includes(item.envType));
});

const outputTargetList = computed(() => {
  const flowVariables = flowContext.data.value.flowVariables;
  return flowVariables.filter(item => [
    FlowVariableType.OUTPUT,
    FlowVariableType.TEMP,
  ].includes(item.envType))
});

function validateParam (param: any) {
  if (!param.source) {
    return false;
  }
  if (!param.target) {
    return false;
  }
  return true;
}

function validate () {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
    return false;
  }
  const method = nodeData.value.method;
  if (!method.methodId) {
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
</script>

<template>
  <div class="node-method-form">
    <el-form label-position="top">
      <el-form-item label="节点编码">
        <span>{{ nodeData.key }}</span>
      </el-form-item>
      <el-form-item label="节点名称">
        <el-input v-model="nodeData.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="节点描述">
        <el-input v-model="nodeData.desc" placeholder="请输入" :rows="2" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="领域">
        <DomainSelect v-model="nodeData.method.domainId" :auto="true" @change="onDomainChange" />
      </el-form-item>
      <el-form-item label="服务接口">
        <ApiSelect v-model="nodeData.method.methodId" :domainId="nodeData.method.domainId" @change="onApiChange" />
      </el-form-item>
      <el-form-item label="请求头赋值">
        <RuleSetting
          v-model="nodeData.method.headerFillRules"
          addText="新增请求头赋值"
          showRequired
          :sourceList="headerSourceList"
          :targetList="inputTargetList"
          :requiredKeys="headerRequiredKeys"
        />
      </el-form-item>
      <el-form-item label="入参赋值">
        <RuleSetting
          v-model="nodeData.method.inputFillRules"
          addText="新增入参赋值"
          showRequired
          :sourceList="inputSourceList"
          :targetList="inputTargetList"
          :requiredKeys="inputRequiredKeys"
        />
      </el-form-item>
      <el-form-item label="出参赋值">
        <RuleSetting
          v-model="nodeData.method.outputFillRules"
          addText="新增出参赋值"
          :sourceList="outputSourceList"
          :targetList="outputTargetList"
          :showTargetType="false"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>