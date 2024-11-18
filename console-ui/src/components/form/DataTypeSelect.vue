<script lang="ts" setup>
import { ref, computed, PropType } from 'vue';
import { commonService } from '@/service';
import {DataTypeEnum, DataTypeInfo} from "@/typings";

const props = defineProps({
  modelValue: Object,
  type: String,
  disabled: Boolean,
  size: {
    type: String as PropType<'large' | 'default' | 'small'>,
    default: 'default',
  },
});
const emit = defineEmits(['update:modelValue', 'change']);

const dataTypeList = ref<DataTypeInfo[]>([]);

async function loadData() {
  const res = await commonService.dataType.getList();
  dataTypeList.value = res || [];
}

loadData();

const cascaderProps = {
  expandTrigger: 'hover' as const,
};

const options = computed(() => {
  const basicTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Basic);
  const objectTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Object);
  const basicNode = {
    value: 'Basic',
    label: '基础类型',
    children: basicTypeList.map(item => ({ value: item.type, label: item.displayName })),
  };
  const objectNode = {
    value: 'Object',
    label: '对象类型',
    children: objectTypeList.map(item => ({ value: item.objectKey, label: item.displayName })),
  };
  const listNode = {
    value: 'List',
    label: '列表类型',
    children: [basicNode, objectNode].map(node => {
      return {
        ...node,
        children: node.children.map(child => {
          return {
            ...child,
            label: `[ ${child.label} ]`,
          };
        }),
      };
    }),
  };
  return [basicNode, listNode, objectNode];
});

function typeToArray(type: string, dataType: any): string[] {
  const item = dataTypeList.value.find(item => item.type === type);
  if (!item) {
    return [];
  }
  if (item.dataTypeClassify === DataTypeEnum.Basic) {
    return ['Basic', type];
  } else if (item.dataTypeClassify === DataTypeEnum.Object) {
    return ['Object', dataType.objectKey];
  } else if (item.dataTypeClassify === DataTypeEnum.List) {
    return ['List', ...typeToArray(dataType.itemType, dataType)];
  }
  return [];
}

function arrayToType(arr: string[]) {
  if (!Array.isArray(arr) || arr.length === 0) {
    return null;
  }
  const result = {
    type: '',
    itemType: null as string,
    objectKey: null as string,
    objectStructure: null as unknown,
  };
  if (arr[0] === 'Basic') {
    result.type = arr[1];
  } else if (arr[0] === 'Object') {
    result.type = 'Object';
    result.objectKey = arr[1];
  } else if (arr[0] === 'List') {
    result.type = 'List';
    if (arr[1] === 'Basic') {
      result.itemType = arr[2];
    } else if (arr[1] === 'Object') {
      result.itemType = 'Object';
      result.objectKey = arr[2];
    }
  }
  if (result.objectKey) {
    const item = dataTypeList.value.find(item => item.objectKey === result.objectKey);
    if (item) {
      result.objectStructure = item.objectStructure;
    }
  }
  return result;
}

const modelValueObj = computed(() => {
  let val: any = null;
  if (props.modelValue) {
    val = { ...(props.modelValue as object) };
  }
  return val;
});

const innerValue = computed(() => {
  let val: any = modelValueObj.value;
  let result: string[] = [];
  if (!val) {
    return result;
  }
  result = typeToArray(val.type, val);
  return result;
});

const innerBasicValue = computed(() => {
  if (innerValue.value[0] === 'Basic') {
    return innerValue.value[1];
  }
  return null;
});

const handleChange = (val: any) => {
  if (!Array.isArray(val)) {
    emit('update:modelValue', null);
    emit('change', null);
    return;
  }
  const result = arrayToType(val);
  emit('update:modelValue', result);
  emit('change', result);
};

const handleBasicChange = (val: any) => {
  if (!val) {
    emit('update:modelValue', null);
    emit('change', null);
    return;
  }
  let result: any = {
    type: val,
    itemType: null,
    objectKey: null,
    objectStructure: null,
  };
  emit('update:modelValue', result);
  emit('change', result);
};
</script>

<template>
  <el-select :size="size" v-if="type === 'basic'" :modelValue="innerBasicValue" :disabled="disabled" @change="handleBasicChange">
    <el-option v-for="item in options[0]?.children" :key="item.value" :label="item.label" :value="item.value" />
  </el-select>
  <el-cascader
    v-else
    :modelValue="innerValue"
    :options="options"
    :props="cascaderProps"
    :disabled="disabled"
    :show-all-levels="false"
    :size="size"
    style="width: 100%"
    @change="handleChange"
  />
</template>

<style lang="less" scoped></style>
