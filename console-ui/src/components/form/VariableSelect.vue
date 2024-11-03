<script lang="ts" setup>
import { computed, PropType } from 'vue';
import { DataTypeItem } from '@/typings/flowDesign';

const props = defineProps({
  modelValue: String,
  options: Array as PropType<Array<{
    envName: string;
    envKey: string;
    dataType: DataTypeItem,
  }>>,
  disabled: Boolean,
  size: {
    type: String as PropType<'large' | 'default' | 'small'>,
    default: 'default',
  },
});
const emit = defineEmits(['update:modelValue', 'change']);

const innerOptions = computed(() => {
  const result = (props.options || []).map(option => {
    return {
      label: option.envName,
      value: option.envKey,
      children: getOptionChildren(option.dataType),
    };
  });
  return result;
});

const hasObjectOption = computed(() => {
  return props.options?.some(item => item.dataType.type === 'Object');
});

type propItem = {
  propName: string;
  propKey: string;
  dataType: DataTypeItem;
}
function getOptionChildren (dataType: DataTypeItem, count: number = 0):
  Array<{ label: string, value: string, children?: any }> | undefined  {
  if (dataType.type === 'Object') {
    // 循环嵌套最多99层
    if (count > 99) {
      return;
    }
    const list = (dataType.objectStructure || []) as propItem[];
    return list.map(item => {
      return {
        label: item.propName,
        value: item.propKey,
        children: getOptionChildren(item.dataType, count + 1),
      };
    });
  }
}

function stringToArray (val: string) {
  if (val && typeof val === 'string') {
    return val.split('.');
  }
  return [];
}

function arrayToString (val: string[]) {
  if (val && val.length > 0) {
    return val.join('.');
  }
  return null;
}

const innerValue = computed(() => {
  let val: any = props.modelValue;
  let result: string[] = [];
  if (!val) {
    return result;
  }
  result = stringToArray(val);
  return result;
});

const handleChange = (val: any) => {
  const result = arrayToString(val);
  emit('update:modelValue', result);
  emit('change', result);
};

function handleSimpleChange (val: string) {
  emit('update:modelValue', val);
  emit('change', val);
}

const cascaderProps = {
  expandTrigger: 'hover' as const,
  checkStrictly: true,
};

</script>

<template>
  <el-cascader
    v-if="hasObjectOption"
    :modelValue="innerValue"
    :options="innerOptions"
    :props="cascaderProps"
    separator="."
    :disabled="disabled"
    :showAllLevels="true"
    :size="size"
    style="width: 100%"
    @change="handleChange"
  />
  <el-select v-else :modelValue="modelValue" size="small" @change="handleSimpleChange">
    <el-option
        v-for="item in innerOptions"
        :key="item.value"
        :value="item.value"
        :label="item.label"
        :title="item.label"
    >
      <span style="float: left">{{ item.value }}</span>
      <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;margin-left: 5px;">
        {{ item.label }}
      </span>
    </el-option>
  </el-select>
</template>

<style lang="less" scoped></style>
