<script setup lang="ts">
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { TokenTable, TokenForm } from './token';
import { tokenService } from '@/service';
import { ElMessage, ElMessageBox } from 'element-plus';
import TokenSuccessForm from '@/views/system/token/TokenSuccessForm.vue';

const pageNum = ref(1);
const pageSize = ref(10);
const dataTotal = ref(0);
const dataRows = ref<Record<string, any>[]>([]);
const loading = ref(false);
const formRef = ref();
const successFormRef = ref();

async function queryPage() {
  loading.value = true;
  const res = await tokenService.queryTokenPage({
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
  ElMessageBox.confirm(`确定删除'${row.tokenDesc}'吗?,删除后将影响使用该令牌的系统`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteTokenItem(row);
    })
    .catch(() => {});
}

async function addTokenItem(row: any) {
  const res = await tokenService.addToken(row);
  if (res.result) {
    successFormRef.value.open(res.result);
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function editTokenItem(row: any) {
  const res = await tokenService.updateToken({
    id: row.id,
    tokenDesc: row.tokenDesc,
  });
  if (res.result) {
    ElMessage({ type: 'success', message: '编辑成功' });
    await queryPage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function deleteTokenItem(row: any) {
  const res = await tokenService.deleteToken(row.id);
  if (res.result) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryPage();
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
        <TokenTable
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
    <TokenForm ref="formRef" @add="addTokenItem" @edit="editTokenItem" />
    <TokenSuccessForm ref="successFormRef" />
  </div>
</template>

<style lang="less" scoped>
.page-interface-token {
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
