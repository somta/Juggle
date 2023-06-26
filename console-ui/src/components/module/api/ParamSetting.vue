
<script lang="ts" setup>
import { ref } from 'vue';
type ParamItem = {
  name: string;
  type: string;
  required?: boolean;
  method?: string;
  value?: string;
}
const params = ref<ParamItem[]>([
  { name: '参数', type: 'String', required: true, method: '=', value: '123' },
  { name: '参数', type: 'String', required: true, method: '=', value: '123' },
  { name: '参数', type: 'String', required: true, method: '=', value: '123' },
]);
const columns = [
  { name: '参数名称', prop: 'name' },
  { name: '数据类型', prop: 'type' },
  { name: '必填', prop: 'required' },
  { name: '赋值方式', prop: 'method' },
  { name: '赋值', prop: 'value' },
];

function addParam () {
  params.value.push({
    name: '',
    type: 'String',
    required: true,
    method: '',
    value: '',
  });
}
</script>

<template>
  <div class="param-setting">
    <div class="param-setting-head">
      <div class="param-setting-tr">
        <div class="param-setting-td" v-for="column in columns" :key="column.prop">{{ column.name }}</div>
      </div>
    </div>
    <div class="param-setting-body">
      <div class="param-setting-tr" v-for="param, rowIndex in params" :key="rowIndex">
        <div class="param-setting-td" v-for="column in columns" :key="column.prop">{{ param[column.prop] }}</div>
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
    height: 32px;
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
