<script lang="ts" setup>
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
</script>

<template>
  <el-table v-loading="loading" :data="dataRows" size="large" :header-cell-style="{ background: '#f0f0f0' }" style="width: 100%">
    <el-table-column prop="tokenDesc" label="令牌描述" />
    <el-table-column prop="createdAt" label="创建时间" width="180" />
    <el-table-column label="操作" width="180">
      <template #default="scope">
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
