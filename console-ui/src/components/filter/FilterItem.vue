<script lang="ts" setup>
import {getDataTypeObject, getVariableDataType, isDataTypeEqual} from '@/utils/dataType';
import { Delete } from '@element-plus/icons-vue';
import { PropType, computed } from 'vue';
import { DataTypeOperatorMap, BaseDataType, OperatorNameMap, Operator } from './config';
import FilterValue from './FilterValue.vue';
import VariableSelect from "@/components/form/VariableSelect.vue";
import {FlowVariable} from "@/views/flow/design";

enum FilterAssignType {
  Constant = 'CONSTANT',
  Variable = 'VARIABLE',
}

const emit = defineEmits(['change', 'delete']);

const props = defineProps({
  item: {
    type: Object as PropType<any>,
    required: true,
  },
  sourceList: {
    type: Array as PropType<FlowVariable[]>,
    default: () => [],
  },
  targetList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
});

const sourceModel = computed({
  get: () => props.item.envKey,
  set: value => {
    /*const current = props.sourceList.find(item => item.envKey === value);*/
    const current = getVariableDataType(value,props.sourceList as FlowVariable[]);
    emit('change', { envKey: value, dataType: current.dataType });
  },
});

const operatorModel = computed({
  get: () => props.item.operator,
  set: value => {
    emit('change', { ...props.item, operator: value, assignType: FilterAssignType.Constant, value: null });
  },
});

const assignTypeModel = computed({
  get: () => props.item.assignType,
  set: value => {
    emit('change', { ...props.item, assignType: value, value: null });
  },
});

const targetModel = computed({
  get: () => props.item.value,
  set: value => {
    emit('change', { ...props.item, value: value });
  },
});

const isConstant = computed(() => {
  return props.item.assignType === FilterAssignType.Constant;
});

const isVariable = computed(() => {
  return props.item.assignType === FilterAssignType.Variable;
});

const operatorList = computed(() => {
  const dataType = getDataTypeObject(props.item.dataType)?.type as BaseDataType;
  const operators = DataTypeOperatorMap[dataType] || [];
  return operators.map(operator => {
    return {
      value: operator,
      label: OperatorNameMap[operator],
    };
  });
});

const isNoValueOperator = computed(() => {
  return !props.item.operator || [Operator.Empty, Operator.NotEmpty].includes(props.item.operator);
});

const filteredTargetList = computed(() => {
  return props.targetList.filter((item: any) => {
    // 不选取自己
    if (item.envKey === props.item.envKey) {
      return false;
    }
    // 只能选与自己类型一致的
    return isDataTypeEqual(item.dataType, props.item.dataType);
  });
});
</script>

<template>
  <div class="filter-item">
    <div class="filter-item-key">
<!--      <el-select placeholder="请选择" v-model="sourceModel">
        <el-option v-for="source in sourceList" :key="source.envKey" :value="source.envKey" :label="source.envName" />
      </el-select>-->
      <VariableSelect
          v-model="sourceModel"
          :options="sourceList"
      />
    </div>
    <div class="filter-item-operator">
      <el-select placeholder="请选择" v-model="operatorModel">
        <el-option v-for="operator in operatorList" :key="operator.value" :value="operator.value" :label="operator.label" />
      </el-select>
    </div>
    <div class="filter-item-assign-type">
      <template v-if="!isNoValueOperator">
        <el-select placeholder="请选择" v-model="assignTypeModel">
          <el-option :value="FilterAssignType.Constant" label="常量" />
          <el-option :value="FilterAssignType.Variable" label="变量" />
        </el-select>
      </template>
    </div>
    <div class="filter-item-value">
      <template v-if="!isNoValueOperator">
        <FilterValue v-if="isConstant" v-model="targetModel" :dataType="props.item.dataType" />
        <el-select v-else-if="isVariable" placeholder="请选择" v-model="targetModel">
          <el-option v-for="source in filteredTargetList" :key="source.envKey" :value="source.envKey" :label="source.envName" />
        </el-select>
      </template>
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
