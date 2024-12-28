<script lang="ts" setup>
import { reactive, ref } from 'vue';
import DataTypeSelect from '@/components/form/DataTypeSelect.vue';
import { FormInstance, FormRules } from 'element-plus';
import { useFlowDataInject } from '../../hooks/flow-data';
const flowContext = useFlowDataInject();
type ParamItem = {
  envKey: string;
  envName: string;
  dataType: any;
  envType: number;
  id: number;
};
const emit = defineEmits(['add', 'edit']);
const formRef = ref<FormInstance>();
const form = reactive<ParamItem>({
  envKey: '',
  envName: '',
  dataType: null,
  envType: 3,
  id: 0,
});
const rules = reactive<FormRules>({
  envKey: [
    { required: true, message: '请输入变量编码', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9_]+$/, message: '请输入大小写字母或下划线', trigger: 'blur' },
    {
      validator: (_, value, callback) => {
        // 校验唯一性
        // 未改变时不校验
        if (value === _originalData.envKey) {
          callback();
          return;
        }
        const item = flowContext.data.value.flowVariables.find(item => item.envKey === value);
        if (item) {
          callback(new Error('变量编码已存在'));
          return;
        }
        callback();
      },
      trigger: 'blur',
    },
  ],
  envName: [{ required: true, message: '请输入变量名称', trigger: 'blur' }],
  dataType: [{ required: true, message: '请选择数据类型', trigger: 'blur' }],
});
const visible = ref(false);
const isEdit = ref(false);
const add = (maxId: number) => {
  isEdit.value = false;
  form.envKey = '';
  form.envName = '';
  form.dataType = null;
  form.envType = 3;
  form.id = maxId;
  _originalData = { ...form };
  visible.value = true;
};
let _originalData: ParamItem;
const edit = (data: ParamItem) => {
  isEdit.value = true;
  Object.assign(form, data);
  _originalData = { ...form };
  visible.value = true;
};
function onCancel() {
  visible.value = false;
}
async function onSubmit() {
  if (!formRef.value) return;
  const valid = await formRef.value?.validate(() => {});
  if (!valid) {
    return;
  }
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
  <el-dialog :title="isEdit ? '编辑变量' : '新增中间变量'" v-model="visible" append-to-body :width="400">
    <el-form labelPosition="top" ref="formRef" :model="form" :rules="rules">
      <el-form-item label="变量键" prop="envKey">
        <el-input v-model="form.envKey" placeholder="请输入" maxlength="30" />
      </el-form-item>
      <el-form-item label="变量名" prop="envName">
        <el-input v-model="form.envName" placeholder="请输入" maxlength="30" />
      </el-form-item>
      <el-form-item label="变量类型" prop="dataType">
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
