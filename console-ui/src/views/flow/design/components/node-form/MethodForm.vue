
<script lang="ts" setup>
import DomainSelect from '@/components/form/DomainSelect.vue';
import ParamSetting from '@/components/form/ParamSetting.vue';
import { PropType, computed } from 'vue';
import { RawData } from '../../types';

const props = defineProps({
  data: {
    type: Object as PropType<RawData>,
    required: true,
  },
});
console.log(props);

const desc = computed({
  get: () => props.data.desc,
  set: (val) => {
  },
});

function onSubmit() {
  console.log('submit');
}
function onCancel() {
  console.log('cancel');
}
</script>

<template>
  <div class="node-method-form">
    <el-form label-position="top">
      <el-form-item label="节点描述">
        <el-input placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="领域">
        <DomainSelect :auto="true" />
      </el-form-item>
      <el-form-item label="服务接口">
        <el-input placeholder="请输入"></el-input>
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