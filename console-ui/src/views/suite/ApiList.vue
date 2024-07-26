<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { ListFilter, ListTable, ListForm } from './api';
import { apiService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();
const filter = ref<{
  suiteId?: number;
  apiName?: string;
  apiUrl?: string;
}>({});

async function queryPage() {
  loading.value = true;
  const params = {
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    ...filter.value,
  };
  const res = await apiService.listQuery(params);
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

function onSearch(val: typeof filter.value) {
  filter.value = val;
  onPageChange(1);
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
  ElMessageBox.confirm(`确定删除'${row.apiName}'吗?`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteApiItem(row);
    })
    .catch(() => {});
}

async function addApiItem(row: any) {
  const paramArray = row.apiHeaders;
  if (Array.isArray(paramArray) && paramArray.length !== 0) {
    const headerArray = paramArray.map((item: any) => {
      return {
        headerKey: item.paramKey,
        headerName: item.paramName,
        dataType: item.dataType,
        required: item.required,
      };
    });
    row.apiHeaders = headerArray;
  }
  const res = await apiService.listAdd(row);
  if (res.success) {
    ElMessage({ type: 'success', message: '新建成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function editApiItem(row: any) {
  const paramArray = row.apiHeaders;
  if (Array.isArray(paramArray) && paramArray.length !== 0) {
    const headerArray = paramArray.map((item: any) => {
      return {
        headerKey: item.paramKey,
        headerName: item.paramName,
        dataType: item.dataType,
        required: item.required,
      };
    });
    row.apiHeaders = headerArray;
  }
  const res = await apiService.listUpdate(row);
  if (res.success) {
    ElMessage({ type: 'success', message: '编辑成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function deleteApiItem(row: any) {
  const res = await apiService.listDelete(row.id);
  if (res.success) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>
<template>
  <div class="page-interface-list">
    <el-container>
      <el-header class="page-header">
        <ListFilter @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <ListTable
          :dataRows="dataRows"
          :dataTotal="dataTotal"
          :pageNum="pageNum"
          :pageSize="pageSize"
          :loading="loading"
          @pageChange="onPageChange"
          @edit="openEdit"
          @delete="openDelete"
        />
      </el-main>
    </el-container>
    <ListForm ref="formRef" @add="addApiItem" @edit="editApiItem" />
  </div>
</template>

<style lang="less" scoped>
.page-interface-list {
  .page-header {
    height: auto;
    padding: 24px 16px 0px 16px;
  }

  .page-body {
    min-height: 0;
    overflow: auto;
  }
}
</style>
