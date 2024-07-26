<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { SuiteFilter, SuiteTable, SuiteForm } from './suite';
import { suiteService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();
const filter = ref<{ suiteName?: string }>({});

async function querySuitePage() {
  loading.value = true;
  const res = await suiteService.querySuitePage({
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

function onPageChange(page: number) {
  pageNum.value = page;
  querySuitePage();
}

function onSearch(val: typeof filter.value) {
  filter.value = val;
  onPageChange(1);
}

// 初始加载
querySuitePage();

function openAdd() {
  formRef.value.open();
}

function openEdit(row: any) {
  formRef.value.open(row);
}

function openDelete(row: any) {
  ElMessageBox.confirm(`确定删除'${row.suiteName}'套件吗?`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteSuiteItem(row);
    })
    .catch(() => {});
}

async function addSuiteItem(row: any) {
  const res = await suiteService.addSuite(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '新建成功' });
    await querySuitePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function editSuiteItem(row: any) {
  const res = await suiteService.updateSuite(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '编辑成功' });
    await querySuitePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function deleteSuiteItem(row: any) {
  const res = await suiteService.deleteSuite(row.id);
  if (res.result) {
    ElMessage({ type: 'success', message: '删除成功' });
    await querySuitePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>
<template>
  <div class="page-interface-suite">
    <el-container>
      <el-header class="page-header">
        <SuiteFilter @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <SuiteTable
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
    <SuiteForm ref="formRef" @add="addSuiteItem" @edit="editSuiteItem" />
  </div>
</template>

<style lang="less" scoped>
.page-interface-suite {
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
