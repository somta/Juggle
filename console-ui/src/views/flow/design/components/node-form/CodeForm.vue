
<script lang="ts" setup>
import { PropType, ref, watch } from 'vue';
import {ElementType, RawData} from '../../types';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';
import CodeEditor from "@/components/form/CodeEditor.vue";

type CodeRawData = RawData & { language: string; content:string };

function getDefaultData () {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.CODE,
    language: 'groovy',
    content: '',
  }
}
const codeEditRef = ref<InstanceType<typeof CodeEditor>>();

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<CodeRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as CodeRawData);
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
      <el-form-item label="Groovy代码">
        <CodeEditor ref="codeEditRef" v-model="nodeData.content" width="480px" height="200px" language="groovy" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="less" scoped>

</style>