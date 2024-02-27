<script lang="ts" setup>
import { ref, watch, PropType } from 'vue';
import DataTypeSelect from './DataTypeSelect.vue';

const props = defineProps({
  modelValue: Array,
  columns: {
    type: Array as PropType<Array<{
      prop: string;
      name: string;
      type: 'String' | 'Number' | 'Boolean' | 'DataType' | 'Select';
      options?: Array<{ value: string; label: string }>;
    }>>,
    default: () => [],
  },
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

const params = ref<any[]>([]);

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
          <div class="param-setting-td">{{ column.name }}</div>
        </template>
      </div>
    </div>
    <div class="param-setting-body">
      <div class="param-setting-tr" v-for="(param, rowIndex) in params" :key="rowIndex">
        <template v-for="column in columns" :key="column.prop">
          <div class="param-setting-td" v-if="column.type === 'String'">
            <el-input v-model="param[column.prop]" size="small" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="column.type === 'Number'">
            <el-input-number v-model="param[column.prop]" size="small" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="column.type === 'DataType'">
            <DataTypeSelect v-model="param[column.prop]" type="basic" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="column.type === 'Boolean'">
            <el-checkbox v-model="param[column.prop]" @change="onChange" />
          </div>
          <div class="param-setting-td" v-else-if="column.type === 'Select'">
            <el-select v-model="param[column.prop]" @change="onChange">
              <el-option v-for="option in column.options" :key="option.value" :value="option.value" :label="option.label" />
            </el-select>
          </div>
        </template>
      </div>
    </div>
    <div class="param-setting-foot">
      <el-button size="small" type="info" @click="addParam">新增入参</el-button>
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
