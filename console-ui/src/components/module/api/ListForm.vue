<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
import DomainSelect from '@/components/form/DomainSelect.vue';
import ParamSetting from './ParamSetting.vue';

const dialogVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive({
  domainId: null,
});
const rules = reactive<FormRules>({
  domainId: [
    { required: true, message: '请输入领域编码', trigger: 'blur' },
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
      formValue.domainId = item.domainId;
    }
  });
  
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑接口';
  }
  return '新增接口';
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
        <el-form-item label="领域" prop="domainId">
          <DomainSelect v-model="formValue.domainId" />
        </el-form-item>
        <el-form-item label="入参">
          <ParamSetting />
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