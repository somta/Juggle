<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import {DataTypeMap} from "@/const";
import {DataType} from "@/typings";
import {commonService} from "@/service";

const props = defineProps<{
  dataType: DataType
}>();

let objectDataTypeMap = ref<Record<string, string>>({});

onMounted(() => {
  loadDataTypeList();
});

const displayName = computed(() => {
  const dataType = props.dataType as DataType;
  return getDataTypeDisplayName(dataType);
});

function getDataTypeDisplayName(dataType:DataType){
  let displayName;
  if('Object' == dataType.type){
    displayName = objectDataTypeMap.value[dataType.objectKey];
  } else if('List' == dataType.type){
    const start = '列表<';
    let itemDisplayName;
    if(dataType.itemType){
      itemDisplayName = getDataTypeDisplayName({
        type: dataType.itemType,
        objectKey: dataType.objectKey,
        objectStructure: dataType.objectStructure
      });
    }
    displayName = start + itemDisplayName +">";
  } else {
    displayName = DataTypeMap[dataType.type];
  }
  return displayName;
}

async function loadDataTypeList() {
  const res = await commonService.dataType.getList();
  const dataTypeList = res || [];
  objectDataTypeMap.value = dataTypeList.filter(item => item.dataTypeClassify === 3).reduce((acc, item) => {
    if (item.objectKey) {
      acc[item.objectKey] = item.displayName;
    }
    return acc;
  }, {} as Record<string, string>);
}

</script>

<template>
<div :title="displayName" class="dataTypeName">{{displayName}}</div>
</template>

<style scoped lang="less">

</style>