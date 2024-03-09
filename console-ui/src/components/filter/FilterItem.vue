
<script lang="ts" setup>
import { Delete } from '@element-plus/icons-vue'
import { PropType, computed } from 'vue';
import { DataTypeOperatorMap, BaseDataType, OperatorNameMap } from './config';
import FilterValue from './FilterValue.vue';

enum FilterAssignType {
  Constant = 'constant',
  Variable = 'variable',
}

const emit = defineEmits(['change', 'delete']);

const props = defineProps({
  item: {
    type: Object as PropType<any>,
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

const sourceModel = computed({
  get: () => props.item.envKey,
  set: (value) => {
    const current = props.sourceList.find((item) => item.envKey === value);
    emit('change', { envKey: value, dataType: JSON.parse(current.dataType) })
  }
});

const operatorModel = computed({
  get: () => props.item.operator,
  set: (value) => {
    emit('change', { ...props.item, operator: value, assignType: FilterAssignType.Constant, value: null })
  }
});

const assignTypeModel = computed({
  get: () => props.item.assignType,
  set: (value) => {
    emit('change', { ...props.item, assignType: value, value: null })
  }
});

const targetModel = computed({
  get: () => props.item.target,
  set: (value) => {
    emit('change', { ...props.item, value: value })
  }
});

const isConstant = computed(() => {
  return props.item.assignType === FilterAssignType.Constant;
});

const operatorList = computed(() => {
  const dataType = props.item.dataType?.type as BaseDataType;
  const operators = DataTypeOperatorMap[dataType] || [];
  return operators.map((operator) => {
    return {
      value: operator,
      label: OperatorNameMap[operator],
    };
  });
});
</script>

<template>
  <div class="filter-item">
    <div class="filter-item-key">
      <el-select placeholder="请选择" v-model="sourceModel">
        <el-option v-for="source in sourceList" :key="source.envKey" :value="source.envKey" :label="source.envName" />
      </el-select>
    </div>
    <div class="filter-item-operator">
      <el-select placeholder="请选择" v-model="operatorModel">
        <el-option v-for="operator in operatorList" :key="operator.value" :value="operator.value" :label="operator.label" />
      </el-select>
    </div>
    <div class="filter-item-assign-type">
      <el-select placeholder="请选择" v-model="assignTypeModel">
        <el-option :value="FilterAssignType.Constant" label="常量" />
        <el-option :value="FilterAssignType.Variable" label="变量" />
      </el-select>
    </div>
    <div class="filter-item-value">
      <filter-value v-if="isConstant" v-model="targetModel" :dataType="props.item.dataType" />
      <el-select v-else placeholder="请选择" v-model="targetModel">
        <el-option v-for="source in targetList" :key="source.envKey" :value="source.envKey" :label="source.envName" />
      </el-select>
    </div>
    <div class="filter-item-action">
      <el-button :icon="Delete" link @click="$emit('delete')"></el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.filter-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  .filter-item-key {
    width: 120px;
    margin-right: 10px;
  }
  .filter-item-operator {
    width: 120px;
    margin-right: 10px;
  }
  .filter-item-assign-type {
    width: 90px;
    margin-right: 10px;
  }
  .filter-item-value {
    flex: 1;
    margin-right: 10px;
  }
  .filter-item-action {
    width: 24px;
  }
}
</style>
