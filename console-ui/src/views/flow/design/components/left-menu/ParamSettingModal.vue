
<script lang="ts" setup>
import { reactive, ref } from 'vue';
import DataTypeSelect from '@/components/form/DataTypeSelect.vue';
type ParamItem = {
  envKey: string;
  envName: string;
  dataType: string;
  envType: number;
  id: number;
};
const emit = defineEmits(['add', 'edit']);
const form = reactive<ParamItem>({
  envKey: '',
  envName: '',
  dataType: '',
  envType: 3,
  id: 0,
});
const visible = ref(false);
const isEdit = ref(false);
const add = (maxId: number) => {
  isEdit.value = false;
  form.envKey = '';
  form.envName = '';
  form.dataType = '';
  form.envType = 3;
  form.id = maxId;
  visible.value = true;
}
const edit = (data: ParamItem) => {
  isEdit.value = true;
  Object.assign(form, data);
  visible.value = true;
};
function onCancel () {
  visible.value = false;
}
function onSubmit () {
  visible.value = false;
  const result = { ...form };
  if (isEdit.value) {
    emit('edit', result);
  } else {
    emit('add', result);
  }
}
defineExpose({ add, edit });
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
        <el-input v-model="form.envKey" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量名">
        <el-input v-model="form.envName" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量类型">
        <DataTypeSelect v-model="form.dataType" jsonParse />
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
