<script setup lang="ts">
import { computed, nextTick, reactive, ref } from 'vue';
import {FormInstance, FormRules} from 'element-plus';
import {ObjectInfo, ObjectProperty} from '@/typings';
import ParamSetting from "@/components/form/ParamSetting.vue";
import {objectService} from "@/service";

const objectDrawerVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const objectFormValue = reactive<ObjectInfo>({
  id: null,
  objectKey: '',
  objectName: '',
  objectDesc: '',
  props: []
});

const rules = reactive<FormRules>({
  objectKey: [{ required: true, message: '请输入对象编码', trigger: 'blur' },{ pattern: /^[a-zA-Z0-9_]+$/, message: '请输入大小写字母、下划线和数字', trigger: 'blur' }],
  objectName: [{ required: true, message: '请输入对象名称', trigger: 'blur' }],
});

const emit = defineEmits(['add', 'edit']);

function onCancel() {
  objectDrawerVisible.value = false;
}

async function onSubmit() {
  if (!formRef.value) return;
  const valid = await formRef.value?.validate(() => {});
  if (!valid) {
    return;
  }

  objectDrawerVisible.value = false;
  if (editItem.value) {
    emit('edit', { ...editItem.value, ...objectFormValue });
  } else {
    emit('add', objectFormValue);
  }
}

function open(item?: Record<string, any>) {
  editItem.value = item;
  objectDrawerVisible.value = true;
  nextTick(async () => {
    if (formRef.value) {
      formRef.value?.resetFields();
    }
    if (item) {
      const res = await objectService.queryObjectInfo(item.id);
      if (res.success) {
        objectFormValue.id = res.result.id;
        objectFormValue.objectKey = res.result.objectKey;
        objectFormValue.objectName = res.result.objectName;
        objectFormValue.objectDesc = res.result.objectDesc;
        const propArray: ObjectProperty[] =res.result.props;
        if(Array.isArray(propArray) && propArray.length !== 0){
          const paramArray = propArray.map((item: ObjectProperty) => {
            return {
              paramKey: item.propKey,
              paramName: item.propName,
              dataType: item.dataType
            };
          });
          objectFormValue.props = paramArray as any;
        }
      }
    }
  });
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
  <el-drawer v-model="objectDrawerVisible" :title="title">
    <div>
      <el-form ref="formRef" label-position="top" :model="objectFormValue" :rules="rules">
        <el-form-item label="对象编码" prop="objectKey">
          <el-input v-model="objectFormValue.objectKey" />
        </el-form-item>
        <el-form-item label="对象名称" prop="objectName">
          <el-input v-model="objectFormValue.objectName" />
        </el-form-item>
        <el-form-item label="对象描述">
          <el-input type="textarea" v-model="objectFormValue.objectDesc" />
        </el-form-item>
        <el-form-item label="对象属性">
          <ParamSetting v-model="objectFormValue.props" addText="新增属性"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-drawer>
</template>
