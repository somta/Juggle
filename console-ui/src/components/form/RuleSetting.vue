<script lang="ts" setup>
import { ref, watch } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';
import { valueType, RuleItem } from '@/typings';

const targetTypeList = [
  { value: valueType.INPUT_rule, label: '常量' },
  { value: valueType.VARIABLE, label: '变量' },
];

const props = defineProps({
  modelValue: Array,
  showRequired: {
    type: Boolean,
    default: false,
  },
  addText: String,
  sourceList: Array,
  targetList: Array,
});
const emit = defineEmits(['update:modelValue']);

watch(
  () => props.modelValue,
  (val: any) => {
    if (val !== rules.value) {
      rules.value = val;
    }
  }
);

const rules = ref<RuleItem[]>([]);
const columns = [
  { name: '参数名称', prop: 'source' },
  { name: '数据类型', prop: 'sourceDataType' },
  { name: '必填', prop: 'required' },
  { name: '赋值方式', prop: 'targetType' },
  { name: '赋值', prop: 'target' },
];

function addrule() {
  rules.value.push({
    source: '',
    sourceDataType: '',
    sourceType: '',
    target: '',
    targetDataType: '',
    targetType: '',
  });
  onChange();
}

function onChange() {
  emit('update:modelValue', rules.value);
}

</script>

<template>
  <div class="rule-setting">
    <div class="rule-setting-head">
      <div class="rule-setting-tr">
        <template v-for="column in columns" :key="column.prop">
          <template v-if="column.prop === 'required'">
            <div class="rule-setting-td" v-if="showRequired">{{ column.name }}</div>
          </template>
          <div class="rule-setting-td" v-else>{{ column.name }}</div>
        </template>
      </div>
    </div>
    <div class="rule-setting-body">
      <div class="rule-setting-tr" v-for="(rule, rowIndex) in rules" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="rule-setting-td" v-if="column.prop === 'source'">
            <el-select v-model="rule.source" size="small" @change="onChange">
              <el-option v-for="item in sourceList" :key="item.envKey" :value="item.envKey" :label="item.envName" />
            </el-select>
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'sourceDataType'">
            <DataTypeSelect v-model="rule.sourceDataType" type="basic" @change="onChange" />
          </div>
          <div class="rule-setting-td" v-else-if="showRequired && column.prop === 'required'">
            <el-checkbox v-model="rule.required" @change="onChange" />
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'targetType'">
            <el-select v-model="rule.targetType" size="small" @change="onChange">
              <el-option v-for="item in targetTypeList" :key="item.value" :value="item.value" :label="item.label" />
            </el-select>
          </div>
          <div class="rule-setting-td" v-if="column.prop === 'target'">
            <el-select v-model="rule.target" size="small" @change="onChange">
              <el-option v-for="item in targetList" :key="item.envKey" :value="item.envKey" :label="item.envName" />
            </el-select>
          </div>
        </template>
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
  }
  &-foot {
    text-align: center;
    padding: 6px 0;
  }
}
</style>
