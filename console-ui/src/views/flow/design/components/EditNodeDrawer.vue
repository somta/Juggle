
<script lang="ts" setup>
import { ref, shallowRef } from 'vue';
import { RawData } from '../types';
import { getNodeForm } from './node-form';
import { useFlowDataInject } from '../hooks/flow-data';
const flowContext = useFlowDataInject();
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

function onUpdate (val: RawData) {
  flowContext.update(draft => {
    const index = draft.flowContent.findIndex(item => item.key === val.key);
    if (index > -1) {
      Object.assign(draft.flowContent[index], val);
    }
  });
  visible.value = false;
  openParams.afterEdit();
}
defineExpose({ open });
</script>

<template>
  <el-drawer v-model="visible" :size="480" :title="currentData?.name">
    <component :is="currentNodeForm" :data="currentData" @update="onUpdate" @cancel="visible = false" />
  </el-drawer>
</template>
