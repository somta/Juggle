<script lang="ts" setup>
import { Plus } from '@element-plus/icons-vue';
import FilterList from './FilterList.vue';
import { PropType } from 'vue';
const props = defineProps({
  value: {
    type: Array as PropType<any[]>,
    required: true,
  },
  sourceList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
  targetList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
});
const emit = defineEmits(['change']);
function onChange(item: any, index: number) {
  let result = [...props.value];
  result[index] = item;
  result = result.filter((item, index) => index === 0 || item.length > 0);
  if (result.length === 0) {
    result = [[]];
  }
  emit('change', result);
}
function onAdd() {
  const result = [...props.value];
  result.push([{}]);
  emit('change', result);
}
</script>

<template>
  <div class="filter-condition">
    <FilterList
      v-for="(list, index) in value"
      :key="index"
      :list="list"
      :sourceList="sourceList"
      :targetList="targetList"
      @change="onChange($event, index)"
    />
    <el-button @click="onAdd"
      ><el-icon><Plus /></el-icon>或条件</el-button
    >
  </div>
</template>
