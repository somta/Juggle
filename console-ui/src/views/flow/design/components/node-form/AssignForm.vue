<script lang="ts" setup>
import {computed, PropType, ref, watch} from 'vue';
import {ElementType, FlowVariable, FlowVariableType, RawData} from '../../types';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';
import { Delete } from '@element-plus/icons-vue';
import {useFlowDataInject} from "@/views/flow/design/hooks/flow-data.ts";
import {DataTypeItem, RuleItem, valueType} from "@/typings";
import DataTypeSelect from "@/components/form/DataTypeSelect.vue";
import FilterValue from "@/components/filter/FilterValue.vue";
import {isDataTypeEqual} from "@/utils/dataType.ts";

const flowContext = useFlowDataInject();

type AssignRawData = RawData & { assignRules: RuleItem[]; };

const assignTypeList = [
  { value: valueType.CONSTANT, label: '常量' },
  { value: valueType.VARIABLE, label: '变量' },
];

const columns = [
  { name: '变量名称', prop: 'source' },
  { name: '数据类型', prop: 'sourceDataType' },
  { name: '赋值方式', prop: 'targetType' },
  { name: '赋值', prop: 'target' },
];

function getDefaultData() {
  return {
    key: '',
    name: '',
    desc: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.ASSIGN,
    assignRules: [],
  };
}

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<AssignRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as AssignRawData);
watch(
  () => props.data,
  val => {
    if (val !== nodeData.value) {
      nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
    }
  },
  { immediate: true }
);

const targetEnvList = computed<FlowVariable[]>(() => {
  return  flowContext.data.value.flowVariables;
});

const sourceEnvList = computed<FlowVariable[]>(() => {
  const flowVariables = flowContext.data.value.flowVariables;
  return flowVariables.filter(item => [FlowVariableType.INPUT, FlowVariableType.TEMP].includes(item.envType));
});

function validate() {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
    return false;
  }
  return true;
}

function onSubmit() {
  if (!validate()) {
    return;
  }
  emit('update', cloneDeep(nodeData.value));
}
function onCancel() {
  emit('cancel');
}

function getAvailableTarget(target: string) {
  return targetEnvList.value.filter(item => {
    // 已选参数也能选
    if (item.envKey === target) {
      return item;
    }
    // 只能选未被选择的参数
    return !nodeData.value.assignRules.map(item => item.target).includes(item.envKey);
  });
}

function getAvailableSource(target: string, targetDataType: DataTypeItem) {
  return sourceEnvList.value.filter(item => {
    // 不选取自己
    if (item.envKey === target) {
      return false;
    }
    // 只能选与自己类型一致的
    return isDataTypeEqual(item.dataType, targetDataType);
  });
}

function onTargetEnvChange(rowIndex: number) {
  const target = nodeData.value.assignRules[rowIndex].target;
  const param = targetEnvList.value.find(item => item.envKey === target);
  nodeData.value.assignRules[rowIndex].source = '';
  nodeData.value.assignRules[rowIndex].sourceDataType = param?.dataType;
  nodeData.value.assignRules[rowIndex].targetDataType = param?.dataType;
}

function onAssignTypeChange(rowIndex: number) {
  nodeData.value.assignRules[rowIndex].source = '';
  //nodeData.value.assignRules[rowIndex].sourceDataType = null;
}

function onSourceEnvChange(rowIndex: number) {
  const source =  nodeData.value.assignRules[rowIndex].source;
  const param = sourceEnvList.value.find(item => item.envKey === source);
  nodeData.value.assignRules[rowIndex].sourceDataType = param?.dataType;
}

function addAssignRule() {
  if(nodeData.value.assignRules === null){
    nodeData.value.assignRules = [];
  }
  nodeData.value.assignRules.push({
    source: '',
    sourceDataType: null,
    sourceType: valueType.VARIABLE,
    target: '',
    targetDataType: null,
    targetType: valueType.VARIABLE,
  });
}

function removeRule(rowIndex: number) {
  nodeData.value.assignRules.splice(rowIndex, 1);
}

</script>

<template>
  <div class="node-method-form">
    <el-form label-position="top">
      <el-form-item label="节点编码">
        <span>{{ nodeData.key }}</span>
      </el-form-item>
      <el-form-item label="节点名称">
        <el-input v-model="nodeData.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="节点描述">
        <el-input v-model="nodeData.desc" placeholder="请输入" :rows="2" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="赋值规则">
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
            <div class="rule-setting-tr" v-for="(rule, rowIndex) in nodeData.assignRules" :key="rowIndex">
              <template v-for="column in columns" :key="column.prop">
                <div class="rule-setting-td" v-if="column.prop === 'source'">
                  <el-select v-model="rule.target" size="small" @change="onTargetEnvChange(rowIndex)">
                    <el-option
                        v-for="item in getAvailableTarget(rule.target)"
                        :key="item.envKey"
                        :value="item.envKey"
                        :label="item.envName"
                        :title="item.envName"
                    />
                  </el-select>
                </div>
                <div class="rule-setting-td" v-if="column.prop === 'sourceDataType'">
                  <DataTypeSelect v-model="rule.targetDataType" disabled size="small" />
                </div>
                <div class="rule-setting-td" v-if="column.prop === 'targetType'">
                  <el-select v-model="rule.sourceType" size="small" @change="onAssignTypeChange(rowIndex)">
                    <el-option v-for="item in assignTypeList" :key="item.value" :value="item.value" :label="item.label" />
                  </el-select>
                </div>
                <div class="rule-setting-td" v-if="column.prop === 'target'">
                  <!-- 常量 -->
                  <template v-if="rule.sourceType === valueType.CONSTANT">
                    <FilterValue v-model="rule.source" :dataType="rule.targetDataType" size="small" :showNumberControls="false" />
                  </template>
                  <!-- 变量 -->
                  <el-select v-else v-model="rule.source" size="small" @change="onSourceEnvChange(rowIndex)">
                    <el-option
                        v-for="item in getAvailableSource(rule.target, rule.targetDataType as DataTypeItem)"
                        :key="item.envKey"
                        :value="item.envKey"
                        :label="item.envName"
                        :title="item.envName"
                    />
                  </el-select>
                </div>
              </template>
              <div class="rule-setting-td delete-td">
                <el-icon @click="removeRule(rowIndex)"><Delete /></el-icon>
              </div>
            </div>
          </div>
          <div class="rule-setting-foot">
            <el-button size="small" type="info" @click="addAssignRule">新增赋值</el-button>
          </div>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
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
