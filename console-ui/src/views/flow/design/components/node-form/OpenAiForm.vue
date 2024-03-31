
<script lang="ts" setup>
import { PropType, ref, watch } from 'vue';
import { ElementType, RawData } from '../../types';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';

type OpenAiRawData = RawData & { product: string; apiKey:string; model:string; prompt:string;inputFillRule:any;outputFillRule:any};

function getDefaultData () {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.AI,
    product: '',
    apiKey: '',
    model: '',
    prompt: '',
    inputFillRule:{},
    outputFillRule:{}
  }
}

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<OpenAiRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as OpenAiRawData);
watch(() => props.data, val => {
  if (val !== nodeData.value) {
    nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
  }
}, { immediate: true });

function validate () {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
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
      <el-form-item label="产品">
        <el-select v-model="nodeData.product" placeholder="请选择AI产品">
          <el-option key="tongyi" label="通义千问" value="tongyi"/>
        </el-select>
      </el-form-item>
      <el-form-item label="模型">
        <el-select v-model="nodeData.model" placeholder="请选择大模型">
          <el-option key="qwen-turbo" label="qwen-turbo" value="qwen-turbo"/>
          <el-option key="qwen-plus" label="qwen-plus" value="qwen-plus"/>
          <el-option key="qwen-max" label="qwen-max" value="qwen-max"/>
          <el-option key="qwen-max-1201" label="qwen-max-1201" value="qwen-max-1201"/>
          <el-option key="qwen-max-longcontext" label="qwen-max-longcontext" value="qwen-max-longcontext"/>
        </el-select>
      </el-form-item>
      <el-form-item label="API-KEY">
        <el-input v-model="nodeData.apiKey" placeholder="请输入API-KEY"></el-input>
      </el-form-item>
      <el-form-item label="prompt">
        <el-input type="textarea" v-model="nodeData.prompt" placeholder="请输入系统prompt"></el-input>
      </el-form-item>
      <el-form-item label="输入">
        input 字符串  赋值方式选择   文本输入框/下拉框选字符串变量
      </el-form-item>
      <el-form-item label="输出">
        output 字符串  下拉框选字符串变量
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>