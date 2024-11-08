<script lang="ts" setup>
defineProps({
  dataRows: {
    type: Array,
    default: [],
  },
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
  <el-table v-loading="loading" :data="dataRows" size="large" header-cell-class-name="table-header">
    <el-table-column prop="suiteCode" label="套件图像" width="100" >
      <template #default="scope">
        <img v-if="scope.row.suiteImage" :src="scope.row.suiteImage" class="suite-image" alt="suite image" />
        <img v-else class="suite-image" alt="default" src="">
      </template>
    </el-table-column>
    <el-table-column prop="suiteCode" label="套件编码" width="180" />
    <el-table-column prop="suiteName" label="套件名称" width="180" />
    <el-table-column prop="flowType" label="套件类型" width="140">
      <template #default="scope">
        <el-tag v-if="scope.row.suiteFlag == 0" type="info">内置</el-tag>
        <el-tag v-else-if="scope.row.suiteFlag == 1" type="success">官方</el-tag>
        <el-tag v-else type="primary">个人</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="suiteDesc" label="套件描述" show-overflow-tooltip />
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
.suite-image{
  width: 40px;
  height: 40px;
}

</style>
