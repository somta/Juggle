<script lang="ts" setup>
import { ref } from 'vue';
import { suiteService } from '@/service';

const props = defineProps(['modelValue', 'auto']);
const emit = defineEmits(['update:modelValue', 'change']);

const suiteList = ref<Array<{ value: string; label: string }>>([]);
const suiteLoading = ref(false);
let suiteLoaded = false;

if (props.auto) {
  loadData();
}

async function loadData() {
  suiteLoading.value = true;
  const res = await suiteService.querySuiteList();
  if (res.success) {
    suiteList.value = res.result.map((item: any) => ({ label: item.suiteName, value: item.id }));
  }
  suiteLoaded = true;
  suiteLoading.value = false;
}

function onVisibleChange(val: boolean) {
  if (suiteLoaded) {
    return;
  }
  if (val) {
    loadData();
  }
}

function onChange(val: number) {
  emit('update:modelValue', val);
  emit('change', val);
}
</script>
<template>
  <el-select
    :modelValue="props.modelValue"
    placeholder="请选择套件"
    style="width: 100%"
    filterable
    @visibleChange="onVisibleChange"
    @change="onChange"
  >
    <template v-slot:empty>
      <div class="select-option-empty" v-loading="suiteLoading">
        <span v-if="!suiteLoading">无数据</span>
      </div>
    </template>
    <el-option v-for="item in suiteList" :key="item.value" :label="item.label" :value="item.value" />
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
