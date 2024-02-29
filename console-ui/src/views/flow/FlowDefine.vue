<script setup lang="ts">
import { FlowDefineTable, FlowDefineDrawer, FlowDefineFilter } from './define';
import { flowDefineService, flowVersionService } from '@/service';
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';

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

const deployFormVisible = ref(false);
let deployForm = reactive({
  flowDefinitionId: '',
  flowName: '',
  flowDeployVersion: '',
  flowVersionRemark: '',
});

// 初始加载
queryFlowDefinePage();

function onSearch(param: typeof filter.value) {
  filter.value = param;
  onPageChange(1);
}

async function queryFlowDefinePage() {
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

function onPageChange(page: number) {
  pageNum.value = page;
  queryFlowDefinePage();
}

function openflowDefineAdd() {
  console.log('...');
  drawerRef.value.open();
}

async function addFlowDefineItem(row: any) {
  const res = await flowDefineService.addDefineInfo(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '新建成功' });
    await queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function updateFlowDefineItem(row: any) {
  const res = await flowDefineService.updateDefineInfo(row);
  if (res.result) {
    ElMessage({ type: 'success', message: '修改成功' });
    await queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function openDeployDialog(row: any) {
  deployForm.flowDefinitionId = row.id;
  deployForm.flowName = row.flowName;
  flowVersionService.getLatestDeployVersion(row.flowKey).then(res => {
    deployFormVisible.value = true;
    deployForm.flowDeployVersion = res.result as string;
  });
}

async function onSubmitDeploy() {
  await deployFlowDefine(deployForm.flowDefinitionId, deployForm.flowDeployVersion, deployForm.flowVersionRemark);
}

async function deployFlowDefine(flowDefinitionId: string, flowDeployVersion: string, flowVersionRemark: string) {
  const res = await flowDefineService.deployFlowDefine({
    flowDefinitionId: flowDefinitionId,
    flowDeployVersion: flowDeployVersion,
    flowVersionRemark: flowVersionRemark,
  });
  if (res.success) {
    ElMessage({ type: 'success', message: '部署成功' });
    deployFormVisible.value = false;
    await queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function openDelete(row: any) {
  ElMessageBox.confirm(`确定删除'${row.flowName}'流程吗?`, '操作确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      deleteFlowDefineItem(row);
    })
    .catch(() => {});
}

async function deleteFlowDefineItem(row: any) {
  const res = await flowDefineService.deleteFlowDefineById(row.id);
  if (res.success) {
    ElMessage({ type: 'success', message: '删除成功' });
    await queryFlowDefinePage();
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function openEdit(row: any) {
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
          @deploy="openDeployDialog"
          @edit="openEdit"
          @delete="openDelete"
        />
      </el-main>
    </el-container>
    <FlowDefineDrawer ref="drawerRef" @add="addFlowDefineItem" @edit="updateFlowDefineItem"/>

    <el-dialog v-model="deployFormVisible" :show-close="false" title="部署流程" width="400">
      <el-form :model="deployForm">
        <el-form-item label="流程名称">
          <el-input v-model="deployForm.flowName" disabled />
        </el-form-item>
        <el-form-item label="部署版本">
          <el-input v-model="deployForm.flowDeployVersion" disabled />
        </el-form-item>
        <el-form-item label="版本描述">
          <el-input type="textarea" v-model="deployForm.flowVersionRemark" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="deployFormVisible = false">取消</el-button>
          <el-button type="primary" @click="onSubmitDeploy">部署</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="less" scoped>
.page-flow-define {
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
