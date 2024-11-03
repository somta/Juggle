<script lang="ts" setup>
import { getDataTypeObject } from '@/utils/dataType';
import { computed } from 'vue';
const props = defineProps({
  modelValue: {
    type: [String, Number, Boolean, Array, Object],
    default: '',
  },
  dataType: {
    type: String,
    default: '',
  },
  size: {
    type: String,
    default: 'default',
  },
  showNumberControls: {
    type: Boolean,
    default: true,
  },
});
const emit = defineEmits(['update:modelValue']);

const innerValue = computed({
  get: () => {
    if (currentType.value === 'Integer' || currentType.value === 'Double') {
      if (props.modelValue === '') {
        return null;
      }
      return Number(props.modelValue);
    }
    return props.modelValue;
  },
  set: value => {
    emit('update:modelValue', value);
  },
});

const currentType = computed(() => {
  return getDataTypeObject(props.dataType)?.type;
});
</script>

<template>
  <div class="filter-value">
    <el-input-number
      v-if="currentType === 'Integer'"
      v-model="innerValue"
      :controls="showNumberControls"
      controls-position="right"
      max="100000000"
      :precision="0"
      :size="size"
      placeholder="请输入"
    />
    <el-input-number
      v-else-if="currentType === 'Double'"
      v-model="innerValue"
      :controls="showNumberControls"
      controls-position="right"
      :precision="2"
      :size="size"
      placeholder="请输入"
    />
    <el-date-picker
        v-else-if="currentType === 'Date'"
        v-model="innerValue"
        :size="size"
        type="date"
        value-format="YYYY-MM-DD" />
    <el-date-picker
        v-else-if="currentType === 'Time'"
        v-model="innerValue"
        :size="size"
        type="datetime"
        value-format="YYYY-MM-DD HH:mm:ss"/>
    <el-switch
      v-else-if="currentType === 'Boolean'"
      v-model="innerValue"
      inline-prompt
      active-text="是"
      inactive-text="否"
      :size="size"
      :width="48"
    />
    <el-input v-else v-model="innerValue" :size="size" placeholder="请输入" />
  </div>
</template>

<style lang="less" scoped>
.filter-value {
  display: flex;
  :deep(.el-input-number) {
    width: 100%;
    .el-input__inner {
      text-align: left;
    }
  }
  :deep(.el-date-editor){
    width: 100%;
  }
}
</style>
