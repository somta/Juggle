<script lang="ts" setup>
import { ref, watch } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';

type ParamItem = {
  id?: number | null;
  paramKey: string;
  dataType: string;
  paramName: string;
  required: boolean;
};

const props = defineProps({
  modelValue: Array,
  showRequired: {
    type: Boolean,
    default: false,
  },
  addText: String,
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
  { name: '参数编码', prop: 'paramKey' },
  { name: '参数名称', prop: 'paramName' },
  { name: '数据类型', prop: 'dataType' },
  { name: '必填', prop: 'required' },
];

function addParam() {
  params.value.push({
    paramKey: '',
    paramName: '',
    dataType: 'String',
    required: false,
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
          <div class="param-setting-td" v-if="column.prop === 'paramKey'">{{ column.name }}</div>
          <div class="param-setting-td" v-if="column.prop === 'paramName'">{{ column.name }}</div>
          <div class="param-setting-td" v-if="column.prop === 'dataType'">{{ column.name }}</div>
          <div class="param-setting-td" v-if="showRequired && column.prop === 'required'">{{ column.name }}</div>
        </template>
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
            <DataTypeSelect v-model="param.dataType" type="basic" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="showRequired && column.prop === 'required'">
            <el-checkbox v-model="param.required" @change="onChange" />
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
