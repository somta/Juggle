<script lang="ts" setup>
import { ref, watch, PropType } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';
import { Delete } from '@element-plus/icons-vue';
import {DataTypeItem} from "@/typings";

type ParamItem = {
  id?: number | null;
  paramKey: string;
  dataType: DataTypeItem;
  paramName: string;
  required: boolean;
};

const props = defineProps({
  modelValue: Array as PropType<ParamItem[]>,
  showRequired: {
    type: Boolean,
    default: false,
  },
  dataTypeClassify: String,
  addText: String,
});
const emit = defineEmits(['update:modelValue']);
const params = ref<ParamItem[]>([...(props.modelValue || [])]);

watch(
  () => props.modelValue,
  (val: any) => {
    if (val !== params.value) {
      params.value = [...val];
    }
  }
);

const columns = [
  { name: '参数编码', prop: 'paramKey' },
  { name: '参数名称', prop: 'paramName' },
  { name: '数据类型', prop: 'dataType' },
  { name: '必填', prop: 'required' },
];

function addParam() {
  params.value.push({
    paramKey: '',
    paramName: '',
    dataType: { type: 'String', itemType: null, objectKey: null, objectStructure: null  },
    required: false,
  });
  onChange();
}

function removeParam (rowIndex: number) {
  params.value.splice(rowIndex, 1);
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
          <div class="param-setting-td" v-if="column.prop === 'paramKey'">{{ column.name }}</div>
          <div class="param-setting-td" v-if="column.prop === 'paramName'">{{ column.name }}</div>
          <div class="param-setting-td" v-if="column.prop === 'dataType'">{{ column.name }}</div>
          <div class="param-setting-td required-td" v-if="showRequired && column.prop === 'required'">{{ column.name }}</div>
        </template>
        <div class="param-setting-td delete-td"></div>
      </div>
    </div>
    <div class="param-setting-body">
      <div class="param-setting-tr" v-for="(param, rowIndex) in params" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="param-setting-td" v-if="column.prop === 'paramKey'">
            <el-input v-model="param.paramKey" size="small" @change="onChange" />
          </div>
          <div class="param-setting-td" v-if="column.prop === 'paramName'">
            <el-input v-model="param.paramName" size="small" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="column.prop === 'dataType'">
            <DataTypeSelect v-model="param.dataType" :type="dataTypeClassify" size="small" @change="onChange" />
          </div>
          <div class="param-setting-td required-td" v-else-if="showRequired && column.prop === 'required'">
            <el-checkbox v-model="param.required" @change="onChange" />
          </div>
        </template>
        <div class="param-setting-td delete-td">
          <el-icon @click="removeParam(rowIndex)"><Delete /></el-icon>
        </div>
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
