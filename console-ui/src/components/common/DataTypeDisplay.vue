<script setup lang="ts">
import {computed, ref} from "vue";
import {DataTypeMap} from "@/const";
import {DataType} from "@/typings";
import {commonService} from "@/service";

const props = defineProps({
  dataType: Object,
});

let objectDataTypeMap = ref<Record<string, string>>();

loadDataTypeList();

const displayName = computed(() => {
  const dataType = props.dataType as DataType;
  const displayName =  getDataTypeDisplayName(dataType);
  return displayName;
});

function getDataTypeDisplayName(dataType:DataType){
  let displayName;
  if('Object' == dataType.type){
    displayName = dataType.objectKey;
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

// todo 要只加载一次就好
async function loadDataTypeList() {
  const res = await commonService.dataType.getList();
  const dataTypeList = res || [];
  objectDataTypeMap = dataTypeList.filter(item => item.dataTypeClassify === 3).reduce((acc, item) => {
    if (item.objectKey) {
      acc[item.objectKey] = item.displayName;
    }
    return acc;
  }, {} as Record<string, string>);
  console.log('66',objectDataTypeMap)
  //console.log(dataTypeList.value);
}

</script>

<template>
<div>{{displayName}}</div>
</template>

<style scoped lang="less">

</style>