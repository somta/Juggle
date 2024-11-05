<script lang="ts" setup>
import { computed, PropType } from 'vue';
import {isDataTypeMatch} from "@/utils/dataType.ts";
import {DataType} from "@/typings";
const props = defineProps({
  modelValue: String,
  options: Array as PropType<Array<{
    envName: string;
    envKey: string;
    dataType: DataType,
  }>>,
  filterDataType: Object as PropType<DataType>,
  size: {
    type: String as PropType<'large' | 'default' | 'small'>,
    default: 'default',
  },
});
const emit = defineEmits(['update:modelValue', 'change']);

const innerOptions = computed(() => {
  const result = (props.options || []).map(option => {
    const newItem = {
      label: option.envName,
      value: option.envKey,
    };
    if(handleShowObject(option.dataType, newItem.value)){
      return {
        ...newItem,
        children: getOptionChildren(option.dataType, newItem.value),
      };
    }
    return null;
  }).filter(item => item !== null);
  return result;
});

const hasObjectOption = computed(() => {
  return props.options?.some(item => item.dataType.type === 'Object');
});

type propItem = {
  propName: string;
  propKey: string;
  dataType: DataType;
}

function handleShowObject(dataType: DataType, preValue: string){
  if(dataType.type == 'List' && props.filterDataType?.type == 'List'
  && dataType.itemType != props.filterDataType.itemType){
    return false;
  }
  if(dataType.type !== 'Object'){
    return true;
  }
  const propList = getOptionChildren(dataType,preValue);
  if(props.filterDataType){
    if(props.filterDataType.type == "Object"){
      return props.filterDataType.type == dataType.type;
    }else{
      if(propList?.length == 0){
        return false;
      }
    }
  }
  return true;
}

function getOptionChildren (dataType: DataType, preValue: string, count: number = 0):
  Array<{ label: string, value: string, children?: any }> | undefined  {
  if (dataType.type === 'Object') {
    // 循环嵌套最多99层
    if (count > 99) {
      return;
    }
    let list = (dataType.objectStructure || []) as propItem[];
    if(props.filterDataType){
      list = list.filter(item => isDataTypeMatch(item.dataType,props.filterDataType as DataType));
    }
    return list.map(item => {
      const newItem = {
        label: item.propName,
        value: preValue + '.' + item.propKey,
      };
      return {
        ...newItem,
        children: getOptionChildren(item.dataType, newItem.value, count + 1),
      };
    });
  }
}

const handleChange = (val: any) => {
  emit('update:modelValue', val);
  emit('change', val);
};

function getLabel (val: string) {
  if (!val) return '';
  const arr = val.split('.');
  let key = arr.shift();
  let parent = innerOptions?.value.find(item => item?.value === key);
  let result = parent?.label;
  while (parent && arr.length > 0) {
    key = key + '.' + arr.shift();
    parent = parent?.children?.find(item => item.value === key);
    if (parent) {
      result += '.' + parent.label;
    }
  }
  return result;
}

</script>

<template>
  <el-tree-select
    v-if="hasObjectOption"
    :modelValue="modelValue"
    :data="innerOptions"
    check-strictly
    :render-after-expand="false"
    :size="size"
    @change="handleChange"
  >
    <template #label="{ value }">
      <span>{{ getLabel(value) }} </span>
    </template>
  </el-tree-select>
  <el-select v-else :modelValue="modelValue" :size="size" @change="handleChange">
    <el-option
        v-for="item in innerOptions"
        :key="item?.value"
        :value="item?.value"
        :label="item?.label"
        :title="item?.label"
    >
      <span style="float: left">{{ item.value }}</span>
      <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;margin-left: 5px;">
        {{ item.label }}
      </span>
    </el-option>
  </el-select>
</template>

<style lang="less" scoped></style>
