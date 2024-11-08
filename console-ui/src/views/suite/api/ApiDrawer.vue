<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
import SuiteSelect from '@/components/form/SuiteSelect.vue';
import ParamSetting from '@/components/form/ParamSetting.vue';
import { apiService } from '@/service';
import type { ApiInfo } from '@/typings';
import { ApiRequestContentTypeMap, ApiRequestTypeMap } from '@/const';

const ApiRequestTypes = Object.keys(ApiRequestTypeMap);
const ApiRequestContentTypes = Object.keys(ApiRequestContentTypeMap);

const dialogVisible = ref(false);
const apiFormRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const suitFlag = ref();
function getDefault() {
  return {
    id: null,
    suiteId: null,
    apiCode: '',
    apiName: '',
    apiDesc: '',
    apiUrl: '',
    apiRequestType: '',
    apiRequestContentType: '',
    apiHeaders: [],
    apiInputParams: [],
    apiOutputParams: [],
  };
}
const formValue = reactive<ApiInfo>(getDefault());
const rules = reactive<FormRules>({
  suiteId: [{ required: true, message: '请选择套件', trigger: 'blur' }],
  apiName: [{ required: true, message: '请输入接口名称', trigger: 'blur' }],
  apiUrl: [
    { required: true, message: '请输入接口地址', trigger: 'blur' },
    { type: 'url', message: '请输入正确的接口地址 如: http://127.0.0.1/getUser', trigger: ['blur', 'change'] },
  ],
  apiRequestType: [{ required: true, message: '请选择请求类型', trigger: 'blur' }],
  apiRequestContentType: [{ required: true, message: '请选择请求内容类型', trigger: 'blur' }],
});

const emit = defineEmits(['add', 'edit']);

function onCancel() {
  dialogVisible.value = false;
}

async function onSubmit() {
  if (!apiFormRef.value) return;
  const valid = await apiFormRef.value?.validate(() => {});
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
  nextTick(async () => {
    apiFormRef.value?.resetFields();
    if (item) {
      const res = await apiService.queryApiInfo(item.id);
      if (res.success) {
        formValue.id = res.result.id;
        formValue.suiteId = res.result.suiteId;
        formValue.apiCode = res.result.apiCode;
        formValue.apiName = res.result.apiName;
        formValue.apiDesc = res.result.apiDesc;
        formValue.apiUrl = res.result.apiUrl;
        formValue.apiRequestType = res.result.apiRequestType;
        formValue.apiRequestContentType = res.result.apiRequestContentType;
        formValue.apiHeaders = res.result.apiHeaders;
        formValue.apiInputParams = res.result.apiInputParams;
        formValue.apiOutputParams = res.result.apiOutputParams;
        suitFlag.value = res.result.suiteFlag;
      }
    } else {
      // 清空
      Object.assign(formValue, getDefault());
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
  <el-drawer v-model="dialogVisible" :size="600" :title="title" destroyOnClose>
    <div class="form">
      <el-form ref="apiFormRef" label-position="top" :model="formValue" :rules="rules">
        <el-form-item label="套件" prop="suiteId">
          <SuiteSelect v-model="formValue.suiteId" :auto="true" />
        </el-form-item>
        <el-form-item label="接口名称" prop="apiName">
          <el-input v-model="formValue.apiName" maxlength="30" />
        </el-form-item>
        <el-form-item label="接口地址" prop="apiUrl">
          <el-input v-model="formValue.apiUrl" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="请求类型" prop="apiRequestType">
              <el-select v-model="formValue.apiRequestType">
                <el-option v-for="op in ApiRequestTypes" :value="op" :key="op">{{ op }}</el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="18">
            <el-form-item label="请求内容类型" prop="apiRequestContentType">
              <el-select v-model="formValue.apiRequestContentType" style="width: 100%">
                <el-option v-for="op in ApiRequestContentTypes" :value="op" :key="op">{{ op }}</el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="接口描述" prop="apiDesc">
          <el-input v-model="formValue.apiDesc" type="textarea" :rows="2" maxlength="120" />
        </el-form-item>
        <el-form-item label="请求头">
          <ParamSetting v-model="formValue.apiHeaders" addText="新增请求头" dataTypeClassify="basic" showRequired />
        </el-form-item>
        <el-form-item label="入参">
          <ParamSetting v-model="formValue.apiInputParams" addText="新增入参" showParamPosition showRequired />
        </el-form-item>
        <el-form-item label="出参">
          <ParamSetting v-model="formValue.apiOutputParams" addText="新增出参" />
        </el-form-item>
        <el-form-item v-if="suitFlag !== 1">
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-drawer>
</template>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
