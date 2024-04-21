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
const emit = defineEmits(['pageChange', 'edit', 'delete']);

function deleteRow(row: any, index: number) {
  emit('delete', row, index);
}

function editRow(row: any) {
  emit('edit', row);
}

function goApiDebugPage(apiId: number) {
  router.push({
    name: 'api-debug',
    params: {
      apiId: apiId
    },
  });
}
</script>
<template>
  <el-table v-loading="loading" :data="dataRows" :header-cell-style="{background:'#f0f0f0'}" style="width: 100%">
    <el-table-column prop="apiName" label="接口名称" width="160" />
    <el-table-column prop="apiUrl" label="接口地址" width="280" />
    <el-table-column prop="suiteName" label="套件" width="160" />
    <el-table-column prop="apiRequestType" label="请求类型" width="80" />
    <el-table-column prop="apiDesc" label="接口描述" min-width="80" show-overflow-tooltip />
    <el-table-column prop="createdAt" label="创建时间" width="120" />
    <el-table-column fixed="right" label="操作" width="140">
      <template #default="scope">
        <el-button link type="primary" size="small" @click.prevent="goApiDebugPage(scope.row.id)"> 调试 </el-button>
        <el-button link type="primary" size="small" @click.prevent="editRow(scope.row)"> 编辑 </el-button>
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
