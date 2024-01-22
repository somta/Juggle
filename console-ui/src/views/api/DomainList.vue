<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { DomainFilter, DomainTable, DomainForm } from './domain';
import { apiService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();
const filter = ref<{ domainName?: string }>({});

async function queryPage() {
  loading.value = true;
  const res = await apiService.domainQuery({
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    ...filter.value,
  });
  if (res.success) {
    dataTotal.value = res.total;
    console.log(dataTotal.value);
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
  ElMessageBox.confirm(`确定删除'${row.domainName}'吗?`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteItem(row);
    })
    .catch(() => {});
}

async function addItem(row: any) {
  const res = await apiService.domainAdd(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '新建成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: '新建失败' });
  }
}

async function editItem(row: any) {
  const res = await apiService.domainUpdate(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '编辑成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: '编辑失败' });
  }
}

async function deleteItem(row: any) {
  const res = await apiService.domainDelete(row.id);
  if (res.result) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>
<template>
  <div class="page-interface-domain">
    <el-container>
      <el-header class="page-header">
        <DomainFilter @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <DomainTable
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
    <DomainForm ref="formRef" @add="addItem" @edit="editItem" />
  </div>
</template>

<style lang="less" scoped>
.page-interface-domain {
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
