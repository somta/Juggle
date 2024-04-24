<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
import {ElMessage} from "element-plus";
import { Plus } from '@element-plus/icons-vue'
const dialogVisible = ref(false);
const formRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
const formValue = reactive({
  suiteImage: '',
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

const imageUrl = ref('')
function beforeSuiteImageUpload(file){
  const isJPG = file.type === 'image/jpeg';
  const isPNG = file.type === 'image/png';
  const isLt30K = file.size / 1024 < 230
  if (!isJPG && !isPNG) {
    ElMessage.error('只能上传 JPG/PNG 格式的图片');
    return false;
  }
  if (!isLt30K) {
    ElMessage.error('图片大小不能超过30KB')
    return false
  }
  convertImageToBase64(file);
  return false
}

function convertImageToBase64(file) {
  console.log('2222')
  const reader = new FileReader();
  reader.onload = () => {
    const base64Data = reader.result.split(',')[1]; // 获取 base64 编码的图片数据
    console.log(base64Data); // 在控制台输出 Base64 字符串
//todo 套件的base64的图像放到suiteImage上传给后端
    // 在这里可以将 base64Data 用于其他操作，比如显示图片预览等
  };
  reader.readAsDataURL(file.raw);
}

defineExpose({ open });
</script>
<template>
  <el-dialog v-model="dialogVisible" :title="title" width="400">
    <div class="form">
      <el-form ref="formRef" label-position="top" :model="formValue" :rules="rules">
<!--        <el-form-item label="套件图像">
          <el-upload
              class="suite-image-uploader"
              :show-file-list="false"
              :before-upload="beforeSuiteImageUpload"
              :auto-upload="false"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>-->
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

<style lang="less" scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}

.suite-image-uploader{
  width: 60px;
  height: 60px;
  display: block;
  border: 1px #8c939d solid;

}

.suite-image-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.suite-image-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 60px;
  height: 60px;
  text-align: center;
}
</style>
