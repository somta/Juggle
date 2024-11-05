<script lang="ts" setup>
import { computed, PropType, ref, watch } from 'vue';
import { ElementType, FlowVariableType, RawData } from '../../types';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';
import CodeEditor from '@/components/common/CodeEditor.vue';
import { dataSourceService } from '@/service';
import { useFlowDataInject } from '@/views/flow/design/hooks/flow-data.ts';

const flowContext = useFlowDataInject();
type MySqlRawData = RawData & { dataSourceId: number; operationType: string; sql: string; variableKey: string };

function getDefaultData() {
  return {
    key: '',
    name: '',
    desc: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.MYSQL,
    dataSourceId: null,
    operationType: '',
    sql: '',
    variableKey: '',
  };
}
const dataSourceList = ref<Array<{ value: number; label: string }>>([]);
const codeEditRef = ref<InstanceType<typeof CodeEditor>>();

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<MySqlRawData>,
    required: true,
  },
});

const nodeData = ref(getDefaultData() as MySqlRawData);
watch(
  () => props.data,
  val => {
    if (val !== nodeData.value) {
      nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
    }
  },
  { immediate: true }
);

const outputVariableList = computed(() => {
  const flowVariables = flowContext.data.value.flowVariables;
  return flowVariables.filter(item => item.envType !== FlowVariableType.INPUT).map((item: any) => ({ label: item.envName, value: item.envKey }));
});

loadDataSourceData();
function validate() {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
    return false;
  }
  if (!nodeData.value.dataSourceId) {
    ElMessage.error('数据源不能为空');
    return false;
  }
  if (!nodeData.value.operationType) {
    ElMessage.error('操作类型不能为空');
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

async function loadDataSourceData() {
  const res = await dataSourceService.queryDataSourceList();
  if (res.success) {
    dataSourceList.value = res.result.map((item: any) => ({ label: item.dataSourceName, value: item.id }));
  }
}
</script>

<template>
  <div class="node-method-form">
    <el-form label-position="top">
      <el-form-item label="节点编码">
        <span>{{ nodeData.key }}</span>
      </el-form-item>
      <el-form-item label="节点名称" required>
        <el-input v-model="nodeData.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="节点描述">
        <el-input v-model="nodeData.desc" placeholder="请输入" :rows="2" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="数据源" required>
        <el-select v-model="nodeData.dataSourceId" placeholder="请选择数据源">
          <el-option v-for="item in dataSourceList" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="操作类型" required>
        <el-select v-model="nodeData.operationType" placeholder="请选择操作类型">
          <el-option key="CHANGE" label="更改(增/删/改)" value="CHANGE" />
          <el-option key="QUERY" label="查询" value="QUERY" />
        </el-select>
      </el-form-item>
      <el-form-item label="SQL语句">
        <CodeEditor ref="codeEditRef" v-model="nodeData.sql" width="480px" height="200px" language="sql" />
      </el-form-item>
      <el-form-item v-if="nodeData.operationType == 'QUERY'" label="结果输出">
        <el-select v-model="nodeData.variableKey" placeholder="请选择变量">
          <el-option v-for="item in outputVariableList" :key="item.value" :label="item.label" :value="item.value" >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;margin-left: 5px;">{{ item.label }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="less" scoped></style>
