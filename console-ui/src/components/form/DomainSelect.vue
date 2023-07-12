<script lang="ts" setup>
import { ref, watch } from 'vue';
import { apiService } from '@/service';

const props = defineProps(['modelValue']);
const emit = defineEmits(['update:modelValue']);

watch(() => props.modelValue, (val) => {
  console.log(val, 'sss');
});

const domainList = ref<Array<{ value: string; label: string; }>>([]);
const domainLoading = ref(false);
let domainLoaded = false;

async function onVisibleChange (val: boolean) {
  if (domainLoaded) {
    return;
  }
  if (val) {
    domainLoading.value = true; 
    const res = await apiService.domainQuery({ pageNum: 1, pageSize: 999 });
    if (res.success) {
      domainList.value = res.result.map(item => ({ label: item.domainName, value: item.id }));
    }
    domainLoaded = true;
    domainLoading.value = false; 
  }
}

function onChange (val: number) {
  emit('update:modelValue', val);
}

</script>
<template>
  <el-select
    :modelValue="props.modelValue"
    placeholder="请选择领域"
    filterable
    @visibleChange="onVisibleChange"
    @change="onChange"
  >
    <template v-slot:empty>
      <div class="select-option-empty" v-loading="domainLoading">
        <span v-if="!domainLoading">无数据</span>
      </div>
    </template>
    <el-option
      v-for="item in domainList"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    />
  </el-select>
</template>
<style lang="less">
.select-option-empty {
  height: 120px;
  align-items: center;
  justify-content: center;
}
</style>