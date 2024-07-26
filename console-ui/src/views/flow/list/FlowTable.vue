<script lang="ts" setup>
defineProps({
  dataRows: Array,
  pageNum: Number,
  pageSize: Number,
  dataTotal: Number,
  loading: Boolean,
});
const emit = defineEmits(['pageChange', 'flowStatusChange', 'delete']);
import { useRouter } from 'vue-router';
const router = useRouter();

function deleteRow(row: any, index: number) {
  emit('delete', row, index);
}

function goFlowVersionListPage(flowId: number) {
  router.push({
    name: 'flow-version',
    params: {
      flowId: flowId,
    },
  });
}
</script>

<template>
  <el-table v-loading="loading" :data="dataRows" size="large" :header-cell-style="{ background: '#f0f0f0' }" style="width: 100%">
    <el-table-column prop="flowKey" label="流程编码" width="180" />
    <el-table-column prop="flowName" label="流程名称" width="220" />
    <el-table-column prop="flowType" label="流程类型" width="140">
      <template #default="scope">
        <el-tag v-if="scope.row.flowType == 'sync'" type="success">同步</el-tag>
        <el-tag v-else type="warning">异步</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="remark" label="流程描述" width="480" show-overflow-tooltip />
    <el-table-column label="操作" width="250">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="goFlowVersionListPage(scope.row.id)"> 版本列表 </el-button>
        <el-button link type="primary" size="small" @click.prevent="deleteRow(scope.row, scope.$index)"> 删除 </el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="table-pagination">
    <el-pagination
      :currentPage="pageNum"
      :pageSize="pageSize"
      background
      layout="total, prev, pager, next"
      :total="dataTotal"
      @currentChange="(val: number) => $emit('pageChange', val)"
    />
  </div>
</template>

<style lang="less" scoped>
.table-pagination {
  padding: 12px 0;
  display: flex;
  flex-direction: row-reverse;
}
</style>
