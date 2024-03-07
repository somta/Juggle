
<script lang="ts" setup>
import { Delete } from '@element-plus/icons-vue'
import { PropType, computed } from 'vue';
import { DataTypeOperatorMap, BaseDataType, OperatorNameMap } from './config';

enum FilterAssignType {
  Constant = 'constant',
  Variable = 'variable',
}

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

const isConstant = computed(() => {
  return props.item.assignType === FilterAssignType.Constant;
});

const operatorList = computed(() => {
  const dataType = props.item.dataType as BaseDataType;
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
      <el-select placeholder="请选择">
        <el-option v-for="source in sourceList" :key="source.envKey" :value="source.envKey" :label="source.envName" />
      </el-select>
    </div>
    <div class="filter-item-operator">
      <el-select placeholder="请选择">
        <el-option v-for="operator in operatorList" :key="operator.value" :value="operator.value" :label="operator.label" />
      </el-select>
    </div>
    <div class="filter-item-assign-type">
      <el-select placeholder="请选择">
        <el-option :value="FilterAssignType.Constant" label="常量" />
        <el-option :value="FilterAssignType.Variable" label="变量" />
      </el-select>
    </div>
    <div class="filter-item-value">
      <el-input v-if="isConstant" placeholder="请输入内容"></el-input>
      <el-select placeholder="请选择">
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
