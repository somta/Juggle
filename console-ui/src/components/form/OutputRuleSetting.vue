<script lang="ts" setup>
import { ref, watch, PropType } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';
import {valueType, RuleItem, DataTypeItem} from '@/typings';
import { Delete } from '@element-plus/icons-vue';
import { isDataTypeEqual } from '@/utils/dataType';

const props = defineProps({
  modelValue: Array as PropType<RuleItem[]>,
  addText: String,
  sourceList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
  targetList: {
    type: Array as PropType<any[]>,
    default: () => [],
  }
});
const emit = defineEmits(['update:modelValue']);
const rules = ref<RuleItem[]>([...(props.modelValue || [])]);

watch(
  () => props.modelValue,
  (val: any) => {
    if (val !== rules.value) {
      rules.value = [...val];
    }
  }
);

const columns = [
  { name: '参数名称', prop: 'source' },
  { name: '数据类型', prop: 'sourceDataType' },
  { name: '赋值', prop: 'target' },
];

function addrule() {
  rules.value.push({
    source: '',
    sourceDataType: null,
    sourceType: valueType.INPUT_PARAM,
    target: '',
    targetDataType: null,
    targetType: valueType.VARIABLE,
    required: false,
  });
  onChange();
}

function removeRule (rowIndex: number) {
  rules.value.splice(rowIndex, 1);
  onChange();
}

function onChange() {
  emit('update:modelValue', rules.value);
}

function onTargetVarChange (rowIndex: number) {
  const target = rules.value[rowIndex].target;
  const param = props.targetList.find((item) => item.envKey === target);
  rules.value[rowIndex].targetDataType = param.dataType;
  onChange();
}

function getAvailableSource (source: string) {
  return props.sourceList.filter((item) => {
    // 已选参数也能选
    if (item.paramKey === source) {
      return item;
    }
    // 只能选未被选择的参数
    return !rules.value.map(item => item.source).includes(item.paramKey);
  });
}
function getAvailableTarget (source: string, sourceDataType: DataTypeItem) {
  return props.targetList.filter((item) => {
    // 不选取自己
    if (item.envKey === source) {
      return false;
    }
    // 只能选与自己类型一致的
    return isDataTypeEqual(item.dataType, sourceDataType);
  });
}

function onSourceChange (rowIndex: number) {
  const source = rules.value[rowIndex].source;
  const param = props.sourceList.find((item) => item.paramKey === source);
  rules.value[rowIndex].target = '';
  rules.value[rowIndex].sourceDataType = param.dataType;
  rules.value[rowIndex].sourceType = param.sourceType;
}
</script>

<template>
  <div class="rule-setting">
    <div class="rule-setting-head">
      <div class="rule-setting-tr">
        <template v-for="column in columns" :key="column.prop">
          <div class="rule-setting-td">{{ column.name }}</div>
        </template>
        <div class="rule-setting-td delete-td"></div>
      </div>
    </div>
    <div class="rule-setting-body">
      <div class="rule-setting-tr" v-for="(rule, rowIndex) in rules" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="rule-setting-td" v-if="column.prop === 'source'">
            <el-select v-model="rule.source" size="small" @change="onSourceChange(rowIndex)">
              <el-option v-for="item in getAvailableSource(rule.source)" :key="item.paramKey" :value="item.paramKey" :label="item.paramName" :title="item.paramName" />
            </el-select>
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'sourceDataType'">
            <DataTypeSelect v-model="rule.sourceDataType" disabled size="small" />
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'target'">
            <!-- 变量 -->
            <el-select v-model="rule.target" size="small" @change="onTargetVarChange(rowIndex)">
              <el-option v-for="item in getAvailableTarget(rule.source, rule.sourceDataType)" :key="item.envKey" :value="item.envKey" :label="item.envName" :title="item.envName" />
            </el-select>
          </div>
        </template>
        <div class="rule-setting-td delete-td">
          <el-icon @click="removeRule(rowIndex)"><Delete /></el-icon>
        </div>
      </div>
    </div>
    <div class="rule-setting-foot">
      <el-button size="small" type="info" @click="addrule">{{ addText || '新增入参'}}</el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.rule-setting {
  width: 100%;
  &-head {
    background-color: #f2f2f2;
    padding: 0 1px;
  }
  &-body {
    border-left: 1px solid #f2f2f2;
    border-right: 1px solid #f2f2f2;
  }
  &-tr {
    display: flex;
    border-bottom: 1px solid #f2f2f2;
    height: 36px;
  }
  &-td {
    flex: 1;
    min-width: 0;
    padding: 0 6px;
    display: flex;
    align-items: center;
    &.delete-td, &.required-td {
      width: 40px;
      flex: none;
      justify-content: center;
    }
    &.delete-td {
      width: 20px;
      margin-right: 10px;
      & > .el-icon {
        cursor: pointer;
        color: #999;
      }
    }
  }
  &-foot {
    text-align: center;
    padding: 6px 0;
  }
}
</style>
