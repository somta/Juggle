<script setup lang="ts">
import { FlowDefineTable,FlowDefineDrawer,FlowDefineFilter } from '@/components/module/flowDefine';
import {flowDefineService} from "@/service";
import {ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {Plus} from "@element-plus/icons-vue";

const searchParam = ref<Record<string, any>>({});
const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const drawerRef = ref();

// 初始加载
queryFlowDefinePage();

function onSearch (param: Record<string, any>) {
  console.log(searchParam);
  //todo 这里是一个对象的时候要怎么传递？
  searchParam.value = param;
  onPageChange(1);
}

async function queryFlowDefinePage () {
  loading.value = true;
  const res = await flowDefineService.queryFlowDefinePage({
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    flowName: searchParam.value.flowName
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
  if (res.result) {
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
  <div class="page-interface-domain">
    <el-container class="page-interface-domain">
      <el-header class="page-header">
        <FlowDefineFilter :keywords="searchParam" @search="onSearch" />
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