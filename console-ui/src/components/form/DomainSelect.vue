<script lang="ts" setup>
import { ref } from 'vue';
import { apiService } from '@/service';

const props = defineProps(['modelValue', 'auto']);
const emit = defineEmits(['update:modelValue']);

const domainList = ref<Array<{ value: string; label: string }>>([]);
const domainLoading = ref(false);
let domainLoaded = false;

if (props.auto) {
  loadData();
}

async function loadData() {
  domainLoading.value = true;
  const res = await apiService.domainList();
  if (res.success) {
    domainList.value = res.result.map((item: any) => ({ label: item.domainName, value: item.id }));
  }
  domainLoaded = true;
  domainLoading.value = false;
}

function onVisibleChange(val: boolean) {
  if (domainLoaded) {
    return;
  }
  if (val) {
    loadData();
  }
}

function onChange(val: number) {
  emit('update:modelValue', val);
}
</script>
<template>
  <el-select :modelValue="props.modelValue" placeholder="请选择领域" style="width: 100%;" filterable @visibleChange="onVisibleChange" @change="onChange">
    <template v-slot:empty>
      <div class="select-option-empty" v-loading="domainLoading">
        <span v-if="!domainLoading">无数据</span>
      </div>
    </template>
    <el-option v-for="item in domainList" :key="item.value" :label="item.label" :value="item.value" />
  </el-select>
</template>
<style lang="less">
.select-option-empty {
  height: 120px;
  align-items: center;
  justify-content: center;
}
</style>
