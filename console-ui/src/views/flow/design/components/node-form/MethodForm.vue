
<script lang="ts" setup>
import DomainSelect from '@/components/form/DomainSelect.vue';
import ApiSelect from '@/components/form/ApiSelect.vue';
import ParamSetting from '@/components/form/ParamSetting.vue';
import { PropType, ref, watch, toRaw } from 'vue';
import { ElementType, RawData } from '../../types';
import { cloneDeep } from 'lodash-es';
import { apiService } from '@/service';

function getDefaultData () {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.METHOD,
    desc: '',
    method: {
      methodId: null,
      domainId: null,
      url: '',
      requestType: 'GET',
      requestContentType: 'application/json',
      headerFillRules: [],
      inputFillRules: [],
      outputFillRules: [],
    },
  }
}

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<RawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as RawData);
watch(() => props.data, val => {
  nodeData.value = cloneDeep(val);
}, { immediate: true });

function onDomainChange () {
  nodeData.value.method = {
    ...getDefaultData().method,
    domainId: nodeData.value.method.domainId,
  };
}

async function onApiChange (val: number) {
  const res = await apiService.queryApiInfo(val);
  if (res.result) {
    const method = res.result;
    nodeData.value.method.requestType = method.requestType;
    nodeData.value.method.requestContentType = method.requestContentType;
    nodeData.value.method.headerFillRules = method.apiHeaders;
    nodeData.value.method.inputFillRules = method.apiInputParams;
    nodeData.value.method.outputFillRules = method.apiOutputParams;
    nodeData.value.method.url = method.apiUrl;
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