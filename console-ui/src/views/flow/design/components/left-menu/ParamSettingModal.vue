
<script lang="ts" setup>
import { reactive, ref, computed } from 'vue';
import DataTypeSelect from '@/components/form/DataTypeSelect.vue';
type ParamItem = {
  paramKey: string;
  paramName: string;
  dataType: string;
};
const form = reactive<ParamItem>({
  paramKey: '',
  paramName: '',
  dataType: '',
});
const visible = ref(false);
const open = (data?: ParamItem) => {
  if (data) {
    Object.assign(form, data);
  } else {
    form.paramKey = '';
    form.paramName = '';
    form.dataType = '';
  }
  visible.value = true;
};
const isEdit = computed(() => {
  return !!form.paramKey;
});
function onCancel () {
  visible.value = false;
}
function onSubmit () {
}
defineExpose({ open });
</script>

<template>
  <el-dialog
    :title="isEdit ? '编辑变量' : '新增中间变量'"
    v-model="visible"
    append-to-body
    :width="480"
  >
    <el-form
      labelPosition="top"
    >
      <el-form-item label="变量键">
        <el-input v-model="form.paramKey" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量名">
        <el-input v-model="form.paramName" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量类型">
        <DataTypeSelect />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="onCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
