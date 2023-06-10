
<script lang="ts" setup>
defineProps({
  dataRows: Array,
  pageIndex: Number,
  pageSize: Number,
  dataTotal: Number,
  loading: Boolean,
});
const emit = defineEmits(['pageChange', 'edit', 'delete']);

function deleteRow (row: any, index: number) {
  emit('delete', row, index);
}

function editRow (row: any) {
  emit('edit', row);
}
</script>

<template>
  <el-table v-loading="loading" :data="dataRows" style="width: 100%">
    <el-table-column prop="code" label="领域编码" width="180" />
    <el-table-column prop="name" label="领域名称" width="180" />
    <el-table-column prop="description" label="领域描述" />
    <el-table-column prop="time" label="创建时间" width="180" />
    <el-table-column label="操作" width="180" >
      <template #default="scope">
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
      :currentPage="pageIndex"
      :pageSize="pageSize"
      background
      hideOnSinglePage
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
