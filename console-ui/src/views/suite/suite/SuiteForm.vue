<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
const dialogVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive({
  suiteCode: '',
  suiteName: '',
  suiteDesc: '',
});
const rules = reactive<FormRules>({
  suiteCode: [{ required: true, message: '请输入套件编码', trigger: 'blur' },{ pattern: /^[a-zA-Z_]+$/, message: '请输入大小写字母或下划线', trigger: 'blur' }],
  suiteName: [{ required: true, message: '请输入套件名称', trigger: 'blur' }],
});

const emit = defineEmits(['add', 'edit']);

function onCancel() {
  dialogVisible.value = false;
}

async function onSubmit() {
  if (!formRef.value) return;
  const valid = await formRef.value?.validate(() => {});
  if (!valid) {
    return;
  }

  dialogVisible.value = false;
  if (editItem.value) {
    emit('edit', { ...editItem.value, ...formValue });
  } else {
    emit('add', formValue);
  }
}

function open(item?: Record<string, any>) {
  editItem.value = item;
  dialogVisible.value = true;
  nextTick(() => {
    formRef.value?.resetFields();
    if (item) {
      formValue.suiteCode = item.suiteCode;
      formValue.suiteName = item.suiteName;
      formValue.suiteDesc = item.suiteDesc;
    }
  });
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑套件';
  }
  return '新增套件';
});

defineExpose({ open });
</script>
<template>
  <el-dialog v-model="dialogVisible" :title="title" width="400">
    <div class="form">
      <el-form ref="formRef" label-position="top" :model="formValue" :rules="rules">
        <el-form-item label="套件编码" prop="suiteCode">
          <el-input v-model="formValue.suiteCode" maxlength="20" />
        </el-form-item>
        <el-form-item label="套件名称" prop="suiteName">
          <el-input v-model="formValue.suiteName" maxlength="30" />
        </el-form-item>
        <el-form-item label="套件描述" prop="suiteDesc">
          <el-input v-model="formValue.suiteDesc" type="textarea" maxlength="120" />
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="onCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
