<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { DataSourceTable, DataSourceDrawer } from './datasource';
import { dataSourceService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();

async function queryPage() {
  loading.value = true;
  const res = await dataSourceService.queryDataSourcePage({
    pageSize: pageSize.value,
    pageNum: pageNum.value,
  });
  if (res.success) {
    dataTotal.value = res.total;
    dataRows.value = res.result;
  }
  loading.value = false;
}

function onPageChange(page: number) {
  pageNum.value = page;
  queryPage();
}

// 初始加载
queryPage();

function openAdd() {
  formRef.value.open();
}

function openEdit(row: any) {
  formRef.value.open(row);
}

function openDelete(row: any) {
  ElMessageBox.confirm(`确定删除'${row.dataSourceName}'吗?,删除后将影响正在使用该数据源的数据节点`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteDataSourceItem(row);
    })
    .catch(() => {});
}

async function addDataSourceItem(row: any) {
  const res = await dataSourceService.addDataSource(row);
  if (res.result) {
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function editDataSourceItem(row: any) {
  const res = await dataSourceService.updateDataSource(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '编辑成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function deleteDataSourceItem(row: any) {
  const res = await dataSourceService.deleteDataSource(row.id);
  if (res.result) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function connectDataSource(row: any) {
  const res = await dataSourceService.connectDataSource(row.id);
  if (res.result) {
    ElMessage({ type: 'success', message: '连接成功' });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>
<template>
  <div class="page-interface-token">
    <el-container>
      <el-header class="page-header">
        <el-button :icon="Plus" type="primary" @click="openAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <DataSourceTable
          :dataRows="dataRows"
          :dataTotal="dataTotal"
          :pageNum="pageNum"
          :pageSize="pageSize"
          :loading="loading"
          @pageChange="onPageChange"
          @connect="connectDataSource"
          @edit="openEdit"
          @delete="openDelete"
        />
      </el-main>
    </el-container>
    <DataSourceDrawer ref="formRef" @add="addDataSourceItem" @edit="editDataSourceItem" />
  </div>
</template>
