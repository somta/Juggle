<script setup lang="ts">
import { FlowDefineTable, FlowDefineDrawer, FlowDefineFilter } from './define';
import {flowDefineService} from "@/service";
import {ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {Plus} from "@element-plus/icons-vue";

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const drawerRef = ref();
const filter = ref<{
  flowName?: string;
  flowType?: string;
}>({});

// 初始加载
queryFlowDefinePage();

function onSearch (param: typeof filter.value) {
  filter.value = param;
  onPageChange(1);
}

async function queryFlowDefinePage () {
  loading.value = true;
  const res = await flowDefineService.queryFlowDefinePage({
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
  queryFlowDefinePage();
}


function openflowDefineAdd () {
  console.log("...");
  drawerRef.value.open();
}

async function addFlowDefineItem (row: any) {
  /*const res = await interfaceService.domainAdd(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '新建成功' });
    queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: '新建失败' });
  }*/
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
    deleteFlowDefineItem(row);
  }).catch(() => {});
}

async function deleteFlowDefineItem (row: any) {
  const res = await flowDefineService.deleteFlowDefineById(row.id);
  if (res.success) {
    ElMessage({ type: 'success', message: '删除成功' });
    queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function openEdit (row: any) {
  drawerRef.value.open(row);
}
</script>

<template>
  <div class="page-flow-define">
    <el-container>
      <el-header class="page-header">
        <FlowDefineFilter @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openflowDefineAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <FlowDefineTable
            :dataRows="dataRows"
            :dataTotal="dataTotal"
            :pageNum="pageNum"
            :pageSize="pageSize"
            :loading="loading"
            @edit="openEdit"
            @delete="openDelete"
        />
      </el-main>
    </el-container>
    <FlowDefineDrawer ref="drawerRef" @add="addFlowDefineItem" />
  </div>
</template>

<style lang="less" scoped>
.page-flow-define {
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
