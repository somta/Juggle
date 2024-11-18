<script lang="ts" setup>
import { ref, watch } from 'vue';
import { apiService } from '@/service';

const props = defineProps(['modelValue', 'suiteCode']);
const emit = defineEmits(['update:modelValue', 'change']);

const apiList = ref([]);
const apiLoading = ref(false);

async function loadData() {
  apiLoading.value = true;
  const res = await apiService.getApiListBySuiteCode(props.suiteCode);
  if (res.success) {
    apiList.value = res.result;
  }
  apiLoading.value = false;
}

watch(
  () => props.suiteCode,
  (val: number) => {
    if (val) {
      loadData();
    }
  },
  { immediate: true }
);

function onChange(apiCode: string) {
  emit('update:modelValue', apiCode);
  emit('change', apiCode);
}
</script>
<template>
  <el-select :modelValue="props.modelValue" placeholder="请选择接口" style="width: 100%" filterable @change="onChange">
    <template v-slot:empty>
      <div class="select-option-empty" v-loading="apiLoading">
        <span v-if="!apiLoading">无数据</span>
      </div>
    </template>
    <el-option v-for="item in apiList" :key="item.apiCode" :label="item.apiName" :value="item.apiCode" />
  </el-select>
</template>
<style lang="less">
.select-option-empty {
  display: flex;
  height: 120px;
  align-items: center;
  justify-content: center;
}
</style>
