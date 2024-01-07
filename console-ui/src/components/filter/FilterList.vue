
<script lang="ts" setup>
import { Plus } from '@element-plus/icons-vue'
import FilterItem from './FilterItem.vue';
import { PropType } from 'vue';
const props = defineProps({
  list: {
    type: Array as PropType<any[]>,
    required: true,
  },
});
const emit = defineEmits(['change']);
function onDelete (index: number) {
  const result = [...props.list];
  result.splice(index, 1);
  emit('change', result);
}
function onChange (item: any, index: number) {
  const result = [...props.list];
  result[index] = item;
  emit('change', result);
}
function onAdd () {
  const result = [...props.list];
  result.push({});
  emit('change', result);
}
</script>

<template>
  <div class="filter-list">
    <FilterItem
      v-for="item, index in list"
      :key="index"
      :item="item"
      @delete="onDelete(index)"
      @change="onChange($event, index)"
    />
    <el-button @click="onAdd"><el-icon><Plus /></el-icon>且条件</el-button>
  </div>
</template>

<style lang="less" scoped>
.filter-list {
  border: 1px solid #ebeef5;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}
</style>
