<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
const dialogVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive({
  domainCode: '',
  domainName: '',
  domainDesc: '',
});
const rules = reactive<FormRules>({
  domainCode: [
    { required: true, message: '请输入领域编码', trigger: 'blur' },
  ],
  domainName: [
    { required: true, message: '请输入领域名称', trigger: 'blur' },
  ],
});

const emit = defineEmits(['add', 'edit']);

function onCancel () {
  dialogVisible.value = false;
}

async function onSubmit () {

  if (!formRef.value) return;
  const valid = await formRef.value.validate(() => {});
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

function open (item?: Record<string, any>) {
  editItem.value = item;
  dialogVisible.value = true;
  nextTick(() => {
    if (formRef.value) {
      formRef.value.resetFields();
    }
    if (item) {
      formValue.domainCode = item.domainCode;
      formValue.domainName = item.domainName;
      formValue.domainDesc = item.domainDesc;
    }
  });
  
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑领域';
  }
  return '新增领域';
});

defineExpose({ open });

</script>
<template>
  <el-dialog
    v-model="dialogVisible"
    :title="title"
    width="400"
  >
    <div class="form">
      <el-form
        ref="formRef"
        label-position="top"
        :model="formValue"
        :rules="rules"
      >
        <el-form-item label="领域代码" prop="domainCode">
          <el-input v-model="formValue.domainCode" />
        </el-form-item>
        <el-form-item label="领域名称" prop="domainName">
          <el-input v-model="formValue.domainName" />
        </el-form-item>
        <el-form-item label="领域描述" prop="domainDesc">
          <el-input v-model="formValue.domainDesc" />
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