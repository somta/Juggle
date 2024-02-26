
<script lang="ts" setup>
import DomainSelect from '@/components/form/DomainSelect.vue';
import ParamSetting from '@/components/form/ParamSetting.vue';
import { PropType, ref, watch } from 'vue';
import { RawData } from '../../types';
import { cloneDeep } from 'lodash-es';

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<RawData>,
    required: true,
  },
});

const nodeData = ref<RawData>({});
watch(() => props.data, val => {
  nodeData.value = cloneDeep(val);
}, { immediate: true });

function onSubmit() {
  emit('update', nodeData.value);
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
        <!-- <DomainSelect v-model="nodeData.domain" :auto="true" /> -->
      </el-form-item>
      <el-form-item label="服务接口">
        <!-- <el-input placeholder="请输入"></el-input> -->
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