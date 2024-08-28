<script lang="ts" setup>
defineProps({
  dataRows: Array,
  pageNum: Number,
  pageSize: Number,
  dataTotal: Number,
  loading: Boolean,
});
const emit = defineEmits(['pageChange', 'edit', 'delete']);
import { useRouter } from 'vue-router';
const router = useRouter();

function deleteRow(row: any, index: number) {
  emit('delete', row, index);
}

function editRow(row: any) {
  emit('edit', row);
}
</script>

<template>
  <el-table v-loading="loading" :data="dataRows" size="large" header-cell-class-name="table-header">
    <el-table-column prop="objectKey" label="对象编码" width="200" />
    <el-table-column prop="objectName" label="对象名称" width="280" />
    <el-table-column prop="objectDesc" label="对象描述" width="520" />
    <el-table-column label="操作" width="250">
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

</style>
