
<script lang="ts" setup>
import { ref, computed } from 'vue';
import { commonService } from '@/service';

const props = defineProps(['modelValue']);
const emit = defineEmits(['update:modelValue', 'change']);

enum DataTypeEnum {
  Basic = 1,
  List = 2,
  Object = 3,
}

type DataTypeItem = {
  id: number;
  dataTypeClassify: DataTypeEnum;
  type: string;
  displayName: string;
  objectKey: string;
  objectStructure: string;
}

const dataTypeList = ref<DataTypeItem[]>([]);

async function loadData () {
  const res = await commonService.getDataTypeList();
  dataTypeList.value = res || [];
}

loadData();

const cascaderProps = {
  expandTrigger: 'hover' as const,
}

const options = computed(() => {
  const basicTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Basic);
  const objectTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Object);
  const basicNode = {
    value: 'Basic',
    label: '基础类型',
    children: basicTypeList.map(item => ({ value: item.type, label: item.displayName })),
  }
  const objectNode = {
    value: 'Object',
    label: '对象类型',
    children: objectTypeList.map(item => ({ value: item.objectKey, label: item.displayName })),
  };
  const listNode = {
    value: 'List',
    label: '列表类型',
    children: [basicNode, objectNode],
  };
  return [
    basicNode,
    listNode,
    objectNode,
  ];
});

function typeToArray (type: string, dataType: any): string[] {
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

function arrayToType (arr: string[]) {
  if (!Array.isArray(arr) || arr.length === 0) {
    return null;
  }
  const result = {
    type: '',
    itemType: '',
    objectKey: null as unknown as string,
    objectStructure: null as unknown as string,
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
    try {
      val = JSON.parse(props.modelValue);
    } catch (error) {
      console.error(error);
    }
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

const handleChange = (val: any) => {
  if (!Array.isArray(val)) {
    emit('update:modelValue', null);
    emit('change', null);
    return;
  }
  const result = arrayToType(val);
  const resultJson = JSON.stringify(result);
  emit('update:modelValue', resultJson);
  emit('change', resultJson);
}
</script>

<template>
  <el-cascader
    :modelValue="innerValue"
    :options="options"
    :props="cascaderProps"
    @change="handleChange"
  />
</template>

<style lang="less" scoped>
</style>
