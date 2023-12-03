<script lang="ts" setup>
import { useRouter } from 'vue-router';
const router = useRouter();
defineProps({
  dataRows: Array,
  pageNum: Number,
  pageSize: Number,
  dataTotal: Number,
  loading: Boolean,
});
const emit = defineEmits(['pageChange','deploy', 'edit', 'delete']);

function deployFlow (row: any) {
  emit('deploy', row);
}
function deleteRow (row: any, index: number) {
  emit('delete', row, index);
}

function editRow (row: any) {
  emit('edit', row);
}
function goDebugPage(flowDefinitionId: number, flowKey: string) {
  router.push({
    name:'flow-debug',
    params:{
      flowDefinitionId: flowDefinitionId,
      flowKey: flowKey,
    },
  });
}
function goDesignPage(flowDefinitionId: number, flowKey: string) {
  router.push({
    name:'flow-design',
    params:{
      flowDefinitionId: flowDefinitionId,
      flowKey: flowKey,
    },
  });
}

</script>

<template>
  <el-table v-loading="loading" :data="dataRows" style="width: 100%">
    <el-table-column prop="flowKey" label="流程编码" width="180" />
    <el-table-column prop="flowName" label="流程名称" width="180" />
    <el-table-column prop="flowType" label="流程类型" width="140" />
    <el-table-column prop="createdAt" label="创建时间" width="140" />
    <el-table-column prop="remark" label="流程描述" width="300" />
    <el-table-column label="操作" width="250" >
      <template #default="scope">
        <el-button link type="primary" size="small" @click="goDesignPage(scope.row.id,scope.row.flowKey)">
          流程
        </el-button>
        <el-button link type="primary" size="small" @click="goDebugPage(scope.row.id,scope.row.flowKey)">
          调试
        </el-button>
        <el-button link type="primary" size="small" @click.prevent="deployFlow(scope.row)">
          部署
        </el-button>
        <el-button link type="primary" size="small" @click.prevent="editRow(scope.row)">
          编辑
        </el-button>
        <el-button link type="primary" size="small" @click.prevent="deleteRow(scope.row, scope.$index)">
          删除
        </el-button>
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
