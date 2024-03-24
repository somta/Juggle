<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
const dialogVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive({
  tokenDesc: '',
});
const rules = reactive<FormRules>({
  tokenDesc: [{ required: true, message: '请输入令牌描述', trigger: 'blur' }],
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
      formValue.tokenDesc = item.tokenDesc;
    }
  });
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑令牌';
  }
  return '新增令牌';
});

defineExpose({ open });
</script>
<template>
  <el-dialog v-model="dialogVisible" :title="title" width="400">
    <div class="form">
      <el-form ref="formRef" label-position="top" :model="formValue" :rules="rules">
        <el-form-item label="令牌描述" prop="tokenDesc">
          <el-input v-model="formValue.tokenDesc" maxlength="120" />
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
