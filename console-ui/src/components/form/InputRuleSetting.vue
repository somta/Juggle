<script lang="ts" setup>
import { ref, watch, PropType } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';
import { valueType, RuleItem } from '@/typings';
import { Delete } from '@element-plus/icons-vue';
import { isDataTypeEqual } from '@/utils/dataType';
import FilterValue from '../filter/FilterValue.vue';

const targetTypeList = [
  { value: valueType.CONSTANT, label: '常量' },
  { value: valueType.VARIABLE, label: '变量' },
];

const props = defineProps({
  modelValue: Array as PropType<RuleItem[]>,
  showRequired: {
    type: Boolean,
    default: false,
  },
  showTargetType: {
    type: Boolean,
    default: true,
  },
  addText: String,
  sourceList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
  targetList: {
    type: Array as PropType<any[]>,
    default: () => [],
  },
  requiredKeys: {
    type: Array as PropType<string[]>,
    default: () => [],
  },
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
  { name: '赋值方式', prop: 'targetType' },
  { name: '赋值', prop: 'target' },
];

function addrule() {
  rules.value.push({
    source: '',
    sourceDataType: '',
    sourceType: valueType.VARIABLE,
    target: '',
    targetDataType: '',
    targetType: valueType.INPUT_PARAM,
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

function onSourceTypeChange (rowIndex: number) {
  rules.value[rowIndex].source = '';
  rules.value[rowIndex].sourceDataType = '';
  onChange();
}

function onSourceVarChange (rowIndex: number) {
  const source = rules.value[rowIndex].source;
  const param = props.sourceList.find((item) => item.envKey === source);
  rules.value[rowIndex].sourceDataType = param.dataType;
  onChange();
}

function getAvailableTarget (target: string) {
  //console.log(props);
  return props.sourceList.filter((item) => {
    // 已选参数也能选
    if (item.paramKey === target) {
      return item;
    }
    // 只能选未被选择的参数
    return !rules.value.map(item => item.target).includes(item.paramKey);
  });
}
function getAvailableSource (target: string, targetDataType: string) {
  //console.log(props.sourceList,target,targetDataType)
  return props.targetList.filter((item) => {
    // 不选取自己
    if (item.envKey === target) {
      return false;
    }
    // 只能选与自己类型一致的
    return isDataTypeEqual(item.dataType, targetDataType);
  });
}

function onTargetChange (rowIndex: number) {
  console.log("onTargetChange",rules.value[rowIndex]);
  const target = rules.value[rowIndex].target;
  const param = props.sourceList.find((item) => item.paramKey === target);
  rules.value[rowIndex].source = '';
  rules.value[rowIndex].targetDataType = param.dataType;
  rules.value[rowIndex].targetType = param.targetType;
  if (props.requiredKeys.includes(target)) {
    rules.value[rowIndex].required = true;
  }
}

function getDataTypeDisplayName(dataType: any){
  console.log(dataType)
}
</script>

<template>
  <div class="rule-setting">
    <div class="rule-setting-head">
      <div class="rule-setting-tr">
        <template v-for="column in columns" :key="column.prop">
          <template v-if="column.prop === 'targetType'">
            <div class="rule-setting-td" v-if="showTargetType">{{ column.name }}</div>
          </template>
          <div class="rule-setting-td" v-else>{{ column.name }}</div>
        </template>
        <div class="rule-setting-td delete-td"></div>
      </div>
    </div>
    <div class="rule-setting-body">
      <div class="rule-setting-tr" v-for="(rule, rowIndex) in rules" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="rule-setting-td" v-if="column.prop === 'source'">
            <div class="required"><span v-if="requiredKeys.includes(rule.target)">*</span></div>
            <el-select v-model="rule.target" :disabled="requiredKeys.includes(rule.target)" size="small" @change="onTargetChange(rowIndex)">
              <el-option v-for="item in getAvailableTarget(rule.target)" :key="item.paramKey" :value="item.paramKey" :label="item.paramName" :title="item.paramName" />
            </el-select>
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'sourceDataType'">
<!--            <template>
              {{ getDataTypeDisplayName(rule.targetDataType) }}
            </template>-->
            <DataTypeSelect v-model="rule.targetDataType" disabled type="basic" size="small" />
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'targetType' && showTargetType">
            <el-select v-model="rule.sourceType" size="small" @change="onSourceTypeChange(rowIndex)">
              <el-option v-for="item in targetTypeList" :key="item.value" :value="item.value" :label="item.label" />
            </el-select>
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'target'">
            <!-- 常量 -->
            <template v-if="rule.sourceType === valueType.CONSTANT">
              <FilterValue v-model="rule.source" :dataType="rule.targetDataType" size="small" :showNumberControls="false" />
            </template>
            <!-- 变量 -->
            <el-select v-else v-model="rule.source" size="small" @change="onSourceVarChange(rowIndex)">
              <el-option v-for="item in getAvailableSource(rule.target, rule.targetDataType)" :key="item.envKey" :value="item.envKey" :label="item.envName" :title="item.envName" />
            </el-select>
          </div>
        </template>
        <div class="rule-setting-td delete-td">
          <el-icon @click="removeRule(rowIndex)" v-if="!requiredKeys.includes(rule.target)"><Delete /></el-icon>
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
    .required {
      color: red;
      width: 10px;
    }
    &.delete-td {
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
