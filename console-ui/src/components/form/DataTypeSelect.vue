
<script lang="ts" setup>
import { ref, computed } from 'vue';
import { commonService } from '@/service';

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
}

const dataTypeList = ref<DataTypeItem[]>([]);

async function loadData () {
  const res = await commonService.listDataType();
  if (res.success) {
    dataTypeList.value = res.result;
  }
}

loadData();

const value = ref([]);

const props = {
  expandTrigger: 'hover' as const,
}

const options = computed(() => {
  const basicTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Basic);
  const objectTypeList = dataTypeList.value.filter(item => item.dataTypeClassify === DataTypeEnum.Object);
  const basicNode = {
    value: 'baisc',
    label: '基础类型',
    children: basicTypeList.map(item => ({ value: item.id, label: item.displayName })),
  }
  const objectNode = {
    value: 'object',
    label: '对象类型',
    children: objectTypeList.map(item => ({ value: item.id, label: item.displayName })),
  };
  const listNode = {
    value: 'list',
    label: '列表类型',
    children: [basicNode, objectNode],
  };
  return [
    basicNode,
    listNode,
    objectNode,
  ];
});

const handleChange = (value: any) => {
  console.log(value)
}
</script>

<template>
  <el-cascader
    v-model="value"
    :options="options"
    :props="props"
    @change="handleChange"
  />
</template>

<style lang="less" scoped>
</style>
