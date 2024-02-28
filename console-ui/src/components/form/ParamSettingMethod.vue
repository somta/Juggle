<script lang="ts" setup>
import { ref, watch } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';

enum valueType {
  VARIABLE = 'VARIABLE',
  INPUT_PARAM = 'INPUT_PARAM',
}

type DataTypeItem = {
  type: string;
  itemType: string;
  objectKey: string;
  objectStructure: string;
}

type ParamItem = {
  source: string;
  sourceDataType: DataTypeItem;
  sourceType: valueType;
  target: string;
  targetDataType: DataTypeItem;
  targetType: valueType;
};

const targetTypeList = [
  { value: valueType.INPUT_PARAM, label: '常量' },
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
    if (val !== params.value) {
      params.value = val;
    }
  }
);

const params = ref<ParamItem[]>([]);
const columns = [
  { name: '参数名称', prop: 'source' },
  { name: '数据类型', prop: 'sourceDataType' },
  { name: '必填', prop: 'required' },
  { name: '赋值方式', prop: 'targetType' },
  { name: '赋值', prop: 'target' },
];

function addParam() {
  params.value.push({
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
  emit('update:modelValue', params.value);
}

</script>

<template>
  <div class="param-setting">
    <div class="param-setting-head">
      <div class="param-setting-tr">
        <template v-for="column in columns" :key="column.prop">
          <template v-if="column.prop === 'required'">
            <div class="param-setting-td" v-if="showRequired">{{ column.name }}</div>
          </template>
          <div class="param-setting-td" v-else>{{ column.name }}</div>
        </template>
      </div>
    </div>
    <div class="param-setting-body">
      <div class="param-setting-tr" v-for="(param, rowIndex) in params" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="param-setting-td" v-if="column.prop === 'source'">
            <el-select v-model="param.source" size="small" @change="onChange">
              <el-option v-for="item in sourceList" :key="item.envKey" :value="item.envKey" :label="item.envName" />
            </el-select>
          </div>
          <div class="param-setting-td" v-if="column.prop === 'sourceDataType'">
            <DataTypeSelect v-model="param.sourceDataType" type="basic" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="showRequired && column.prop === 'required'">
            <el-checkbox v-model="param.required" @change="onChange" />
          </div>
          <div class="param-setting-td" v-if="column.prop === 'targetType'">
            <el-select v-model="param.targetType" size="small" @change="onChange">
              <el-option v-for="item in targetTypeList" :key="item.value" :value="item.value" :label="item.label" />
            </el-select>
          </div>
          <div class="param-setting-td" v-if="column.prop === 'target'">
            <el-select v-model="param.target" size="small" @change="onChange">
              <el-option v-for="item in targetList" :key="item.envKey" :value="item.envKey" :label="item.envName" />
            </el-select>
          </div>
        </template>
      </div>
    </div>
    <div class="param-setting-foot">
      <el-button size="small" type="info" @click="addParam">{{ addText || '新增入参'}}</el-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.param-setting {
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
