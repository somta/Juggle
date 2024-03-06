<script setup lang="ts">
import { computed, nextTick, reactive, ref } from 'vue';
import {FormInstance, FormRules} from 'element-plus';
import {FlowDefineInfo} from '@/typings';
import ParamSetting from "@/components/form/ParamSetting.vue";
import {flowDefineService} from "@/service";

const flowDefineDrawerVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const flowDefineFormValue = reactive<FlowDefineInfo>(getDefaultFlowDefine());

function getDefaultFlowDefine() {
  return {
    id: null,
    flowName: '',
    flowType: '',
    remark: '',
    flowInputParams: [],
    flowOutputParams: [],
  }
}

const rules = reactive<FormRules>({
  flowName: [{ required: true, message: '请输入流程名称', trigger: 'blur' }],
  flowType: [{ required: true, message: '请选择流程类型', trigger: 'blur' }],
});

const emit = defineEmits(['add', 'edit']);

function onCancel() {
  flowDefineDrawerVisible.value = false;
}

async function onSubmit() {
  if (!formRef.value) return;
  const valid = await formRef.value?.validate(() => {});
  if (!valid) {
    return;
  }

  flowDefineDrawerVisible.value = false;
  if (editItem.value) {
    emit('edit', { ...editItem.value, ...flowDefineFormValue });
  } else {
    emit('add', flowDefineFormValue);
  }
}

function open(item?: Record<string, any>) {
  editItem.value = item;
  flowDefineDrawerVisible.value = true;
  nextTick(async () => {
    formRef.value?.resetFields();
    if (item) {
      const res = await flowDefineService.getDefineInfo(item.id);
      if (res.success) {
        flowDefineFormValue.id = res.result.id;
        flowDefineFormValue.flowName = res.result.flowName;
        flowDefineFormValue.flowType = res.result.flowType;
        flowDefineFormValue.remark = res.result.remark;
        flowDefineFormValue.flowInputParams = res.result.flowInputParams;
        flowDefineFormValue.flowOutputParams = res.result.flowOutputParams;
      }
    }else {
      Object.assign(flowDefineFormValue, getDefaultFlowDefine());
    }
  });
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑流程定义';
  }
  return '新增流程定义';
});

defineExpose({ open });
</script>

<template>
  <el-drawer v-model="flowDefineDrawerVisible" :title="title" destroyOnClose>
    <div>
      <el-form ref="formRef" label-position="top" :model="flowDefineFormValue" :rules="rules">
        <el-form-item label="流程名称" prop="flowName">
          <el-input v-model="flowDefineFormValue.flowName" maxlength="30" />
        </el-form-item>
        <el-form-item label="流程类型" prop="flowType">
          <el-select placeholder="请选择流程类型" v-model="flowDefineFormValue.flowType">
            <el-option label="同步" value="sync" />
            <el-option label="异步" value="async" />
          </el-select>
        </el-form-item>
        <el-form-item label="流程描述">
          <el-input type="textarea" v-model="flowDefineFormValue.remark" maxlength="120"/>
        </el-form-item>
        <el-form-item label="流程入参">
          <ParamSetting v-model="flowDefineFormValue.flowInputParams" addText="新增入参" showRequired/>
        </el-form-item>
        <el-form-item label="流程出参">
          <ParamSetting v-model="flowDefineFormValue.flowOutputParams" addText="新增出参" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-drawer>
</template>

<style scoped></style>
