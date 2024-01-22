<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import {objectService} from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';
import ObjectFilter from "@/views/object/ObjectFilter.vue";
import ObjectTable from "@/views/object/ObjectTable.vue";
import ObjectDrawer from "@/views/object/ObjectDrawer.vue";

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();
const filter = ref<{
  objectName?: string;
}>({});

async function queryObjectPage() {
  loading.value = true;
  const params = {
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    ...filter.value,
  };
  const res = await objectService.queryObjectPage(params);
  if (res.success) {
    dataTotal.value = res.total;
    dataRows.value = res.result;
  }
  loading.value = false;
}

function onPageChange(page: number) {
  pageNum.value = page;
  queryObjectPage();
}

function onSearch(val: typeof filter.value) {
  filter.value = val;
  onPageChange(1);
}

// 初始加载
queryObjectPage();

function openObjectAdd() {
  formRef.value.open();
}

function openEdit(row: any) {
  formRef.value.open(row);
}

function openDelete(row: any) {
  ElMessageBox.confirm(`确定删除'${row.objectName}'吗?`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteItem(row);
    })
    .catch(() => {});
}

/*async function addItem(row: any) {
  const res = await apiService.listAdd(row);
  if (res.success) {
    ElMessage({ type: 'success', message: '新建成功' });
    queryObjectPage();
  } else {
    ElMessage({ type: 'error', message: '新建失败' });
  }
}*/

/*async function editItem(row: any) {
  const res = await apiService.listUpdate(row);
  if (res.success) {
    ElMessage({ type: 'success', message: '编辑成功' });
    queryObjectPage();
  } else {
    ElMessage({ type: 'error', message: '编辑失败' });
  }
}*/

async function deleteItem(row: any) {
  const res = await objectService.deleteObject(row.id);
  if (res.success) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryObjectPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>
<template>
  <div class="page-object-list">
    <el-container>
      <el-header class="page-header">
        <ObjectFilter @search="onSearch" />
        <el-button :icon="Plus" type="primary" @click="openObjectAdd">新建</el-button>
      </el-header>
      <el-main class="page-body">
        <ObjectTable
          :dataRows="dataRows"
          :dataTotal="dataTotal"
          :pageNum="pageNum"
          :pageSize="pageSize"
          :loading="loading"
          @pageChange="onPageChange"
          @delete="openDelete"
        />
      </el-main>
    </el-container>
    <ObjectDrawer ref="formRef" />
  </div>
</template>

<style lang="less" scoped>
.page-object-list {
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
