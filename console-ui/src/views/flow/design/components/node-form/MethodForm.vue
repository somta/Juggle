
<script lang="ts" setup>
import DomainSelect from '@/components/form/DomainSelect.vue';
import ApiSelect from '@/components/form/ApiSelect.vue';
import ParamSetting from '@/components/form/ParamSetting.vue';
import { PropType, ref, watch, toRaw } from 'vue';
import { ElementType, RawData, MethodInfo } from '../../types';
import { cloneDeep } from 'lodash-es';
import { apiService } from '@/service';

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
  nodeData.value = cloneDeep(Object.assign(getDefaultData(), val));
}, { immediate: true });

function onDomainChange () {
  nodeData.value.method = {
    ...getDefaultData().method,
    domainId: nodeData.value.method?.domainId as number,
  };
}

async function onApiChange (val: number) {
  const res = await apiService.queryApiInfo(val);
  if (res.result) {
    const val = res.result;
    const method = nodeData.value.method;
    method.requestType = val.apiRequestType;
    method.requestContentType = val.apiRequestContentType;
    method.headerFillRules = val.apiHeaders;
    method.inputFillRules = val.apiInputParams;
    method.outputFillRules = val.apiOutputParams;
    method.url = val.apiUrl;
  }
}

function onSubmit() {
  emit('update', toRaw(nodeData.value));
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
        <ParamSetting />
      </el-form-item>
      <el-form-item label="入参赋值">
        <ParamSetting />
      </el-form-item>
      <el-form-item label="出参赋值">
        <ParamSetting />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>