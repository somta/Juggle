
<script lang="ts" setup>
import { computed } from 'vue';
const props = defineProps({
  modelValue: {
    type: [String, Number, Boolean, Array, Object],
    default: ''
  },
  dataType: {
    type: Object,
    default: () => ({ type: 'String' }),
  },
})
const emit = defineEmits(['update:modelValue']);

const innerValue = computed({
  get: () => props.modelValue,
  set: (value) => {
    emit('update:modelValue', value)
  }
})

const currentType = computed(() => props.dataType.type);
</script>

<template>
  <div class="filter-value">
    <el-input-number v-if="currentType === 'Integer'" v-model="innerValue" :controls="false" placeholder="请输入" />
    <el-input-number v-else-if="currentType === 'Double'" v-model="innerValue" :controls="false" placeholder="请输入" />
    <el-time-picker v-else-if="currentType === 'Date'" v-model="innerValue" />
    <el-checkbox v-else-if="currentType === 'Boolean'" v-model="innerValue" />
    <el-input v-else v-model="innerValue" placeholder="请输入" />
  </div>
</template>