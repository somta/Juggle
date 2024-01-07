
<script lang="ts" setup>
import { ref, shallowRef } from 'vue';
import { RawData } from '../types';
import { getNodeForm } from './node-form';
const visible = ref(false);
let openParams: {
  data: RawData;
  afterEdit: () => void;
};
function open (params: typeof openParams) {
  visible.value = true;
  openParams = params;
  currentNodeForm.value = getNodeForm(params.data.elementType);
  currentData.value = params.data;
}
const currentNodeForm = shallowRef();
const currentData = ref<RawData>();
defineExpose({ open });
</script>

<template>
  <el-drawer v-model="visible" :size="480" :title="currentData?.name">
    <component :is="currentNodeForm" :data="currentData" />
  </el-drawer>
</template>
