<script setup lang="ts">
import { FlowVersionTable } from './version';
import {flowVersionService} from "@/service";
import {reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {useRoute} from "vue-router";

const route = useRoute();
let paramsData = reactive({
  params: route.params
});
console.log(paramsData);

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);


const drawerRef = ref();
const filter = ref<{
  flowId: number;
}>({
  flowId : Number(paramsData.params.flowId)
});

// 初始加载
queryFlowVersionPage();

function onSearch (param: typeof filter.value) {
  filter.value = param;
  onPageChange(1);
}

async function queryFlowVersionPage () {
  loading.value = true;
  const res = await flowVersionService.queryFlowVersionPage({
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    ...filter.value,
  });
  if (res.success) {
    dataTotal.value = res.total;
    dataRows.value = res.result;
  }
  loading.value = false;
}

function onPageChange (page:number) {
  pageNum.value = page;
  queryFlowVersionPage();
}

function openUpdateFlowStatus (row: any) {
  ElMessageBox.confirm(
      `确定${row.flowStatus == 0 ? "启用" : "禁用"}'${row.flowName}'流程吗?`,
      '操作确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      },
  ).then(() => {
    updateFlowStatus(row);
  }).catch(() => {});
}

async function updateFlowStatus (row: any) {
  const res = await flowVersionService.updateFlowVersionStatus(row.id,row.flowStatus);
  if (res.success) {
    ElMessage({ type: 'success', message: '操作成功' });
    queryFlowVersionPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function openDelete (row: any) {
  ElMessageBox.confirm(
      `确定删除'${row.flowName}'流程吗?`,
      '操作确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      },
  ).then(() => {
    deleteFlowItem(row);
  }).catch(() => {});
}

async function deleteFlowItem (row: any) {
  const res = await flowVersionService.deleteFlowVersionById(row.id);
  if (res.success) {
    ElMessage({ type: 'success', message: '删除成功' });
    queryFlowVersionPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>

<template>
  <div class="page-flow">
    <el-container>
      <el-header class="page-header">
        <FlowFilter @search="onSearch" />
      </el-header>
      <el-main class="page-body">
        <FlowVersionTable
            :dataRows="dataRows"
            :dataTotal="dataTotal"
            :pageNum="pageNum"
            :pageSize="pageSize"
            :loading="loading"
            @flowStatusChange="openUpdateFlowStatus"
            @delete="openDelete"
        />
      </el-main>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
.page-flow {
  .page-header {
    height: auto;
    padding: 24px 16px;
  }

  .page-body {
    min-height: 0;
    overflow: auto;
  }
}
</style>