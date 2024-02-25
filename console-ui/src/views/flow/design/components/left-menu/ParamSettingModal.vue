
<script lang="ts" setup>
import { reactive, ref, computed } from 'vue';
import DataTypeSelect from '@/components/form/DataTypeSelect.vue';
type ParamItem = {
  evnKey: string;
  envName: string;
  dataType: string;
  envType: number;
};
const emit = defineEmits(['add', 'edit']);
const form = reactive<ParamItem>({
  envKey: '',
  envName: '',
  dataType: '',
  envType: 3,
});
const visible = ref(false);
const isEdit = ref(false);
const open = (data?: ParamItem) => {
  if (data) {
    isEdit.value = true;
    Object.assign(form, data);
  } else {
    isEdit.value = false;
    form.envKey = '';
    form.envName = '';
    form.dataType = '';
    form.envType = 3;
  }
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
        <el-input v-model="form.envKey" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量名">
        <el-input v-model="form.envName" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="变量类型">
        <DataTypeSelect v-model="form.dataType" />
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
