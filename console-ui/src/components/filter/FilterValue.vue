
<script lang="ts" setup>
import { getDataTypeObject } from '@/utils/dataType';
import { computed } from 'vue';
const props = defineProps({
  modelValue: {
    type: [String, Number, Boolean, Array, Object],
    default: ''
  },
  dataType: {
    type: String,
    default: '',
  },
})
const emit = defineEmits(['update:modelValue']);

const innerValue = computed({
  get: () => props.modelValue,
  set: (value) => {
    emit('update:modelValue', value)
  }
})

const currentType = computed(() => {
  return getDataTypeObject(props.dataType)?.type;
});
</script>

<template>
  <div class="filter-value">
    <el-input-number v-if="currentType === 'Integer'" v-model="innerValue" controls-position="right" max="100000000" :precision="0" placeholder="请输入" />
    <el-input-number v-else-if="currentType === 'Double'" v-model="innerValue" controls-position="right" :precision="2" placeholder="请输入" />
    <el-date-picker v-else-if="currentType === 'Date'" v-model="innerValue" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" />
    <el-switch v-else-if="currentType === 'Boolean'" v-model="innerValue" inline-prompt active-text="是" inactive-text="否" :width="48" />
    <el-input v-else v-model="innerValue" placeholder="请输入" />
  </div>
</template>