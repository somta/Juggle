<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { DomainFilter, DomainTable, DomainForm } from '@/components/module/interface';
import { interfaceService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';

const pageIndex = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const keywords = ref('');
const loading = ref(false);
const formRef = ref();

async function queryPage () {
  loading.value = true;
  const res = await interfaceService.queryDomain({
    pageSize: pageSize.value,
    pageIndex: pageIndex.value,
    keywords: keywords.value,
  });
  if (res.data) {
    dataTotal.value = res.data.total;
    dataRows.value = res.data.rows;
  }
  loading.value = false;
}

function onPageChange (page: number) {
  pageIndex.value = page;
  queryPage();
}

function onSearch (val: string) {
  keywords.value = val;
  onPageChange(1);
}

// 初始加载
queryPage();

function openAdd () {
  formRef.value.open();
}

function openEdit (row: any) {
  formRef.value.open(row);
}

function openDelete (row: any) {
  ElMessageBox.confirm(
    `确定删除'${row.name}'吗?`,
    '操作确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    },
  ).then(() => {
    deleteItem(row);
  }).catch(() => {});
}

async function addItem (row: any) {
  const res = await interfaceService.addDomain(row);
  if (res.data) {
    ElMessage({ type: 'success', message: '新建成功' });
    queryPage();
  } else {
    ElMessage({ type: 'error', message: '新建失败' });
  }
}

async function editItem (row: any) {
  const res = await interfaceService.editDomain(row);
  if (res.data) {
    ElMessage({ type: 'success', message: '编辑成功' });
    queryPage();
  } else {
    ElMessage({ type: 'error', message: '编辑失败' });
  }
}

async function deleteItem (row: any) {
  const res = await interfaceService.deleteDomain(row.code);
  if (res.data) {
    ElMessage({ type: 'success', message: '删除成功' });
    queryPage();
  } else {
    ElMessage({ type: 'error', message: '删除失败' });
  }
}

</script>
<template>
  <div class="page-interface-domain">
    <el-container class="page-interface-domain">
      <el-header class="page-header">
        <DomainFilter :keywords="keywords" @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <DomainTable
          :dataRows="dataRows"
          :dataTotal="dataTotal"
          :pageIndex="pageIndex"
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
    padding: 24px 16px;
  }

  .page-body {
    min-height: 0;
    overflow: auto;
  }
}
</style>
