<script setup lang="ts">
import { computed, nextTick, reactive, ref } from 'vue';
import { FormInstance } from 'element-plus';
import { FlowDefineInfo } from '@/typings';
import ParamSetting from "@/components/form/ParamSetting.vue";

const flowDefineDrawerVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive<FlowDefineInfo>({
  id: null,
  flowName: '',
  flowType: '',
  remark: '',
  flowInputParams: [],
  flowOutputParams: [],
});

function onCancel() {
  flowDefineDrawerVisible.value = false;
}

async function onSubmit() {
  /*if (!formRef.value) return;
  const valid = await formRef.value.validate(() => {});
  if (!valid) {
    return;
  }

  dialogVisible.value = false;
  if (editItem.value) {
    emit('edit', { ...editItem.value, ...formValue });
  } else {
    emit('add', formValue);
  }*/
}

function open(item?: Record<string, any>) {
  //editItem.value = item;
  flowDefineDrawerVisible.value = true;
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑对象';
  }
  return '新增对象';
});

defineExpose({ open });
</script>

<template>
  <el-drawer v-model="flowDefineDrawerVisible" :title="title">
    <div>
      <el-form ref="formRef">
        <el-form-item label="对象编码">
          <el-input />
        </el-form-item>
        <el-form-item label="对象名称">
          <el-input />
        </el-form-item>
        <el-form-item label="对象描述">
          <el-input type="textarea" />
        </el-form-item>
        <el-form-item label="对象属性">
          <ParamSetting />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-drawer>
</template>
