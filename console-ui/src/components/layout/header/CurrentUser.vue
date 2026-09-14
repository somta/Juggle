<script setup lang="ts">
import { useRouter } from 'vue-router';
import { userService } from '@/service';
import { ElMessage, FormInstance, FormRules } from 'element-plus';
import CryptoJS from 'crypto-js';
import { reactive, ref } from 'vue';

const $router = useRouter();

const userName = ref('');
const userNameInitial = ref('');
const updatePwdDialogVisible = ref(false);
const aboutDialogVisible = ref(false);
const formRef = ref<FormInstance>();
const formValue = reactive({
  oldSecret: '',
  newSecret: '',
  repeatNewSecret: '',
});
const rules = reactive<FormRules>({
  oldSecret: [
    { required: true, message: '请输入旧密码', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9]+$/, message: '请输入大小写字母数字', trigger: 'blur' },
  ],
  newSecret: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9]+$/, message: '请输入大小写字母数字', trigger: 'blur' },
    { validator: validateNewSecret, trigger: 'blur' }
  ],
  repeatNewSecret: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { validator: validateRepeatNewSecret, trigger: 'blur' }
  ]
});

function validateNewSecret(rule: any, value: string, callback: any) {
  if (value.length < 6) {
    callback(new Error('新密码至少需要6位'));
  } else {
    callback();
  }
}

function validateRepeatNewSecret(rule: any, value: string, callback: any) {
  if (value !== formValue.newSecret) {
    callback(new Error('两次输入的新密码不一致'));
  } else {
    callback();
  }
}

userName.value = window.localStorage.getItem('Juggle-userName') as string;
userNameInitial.value = userName.value.charAt(0);

const productVersion = ref();

getProductInfo();

function openUpdatePwdDialog(){
  updatePwdDialogVisible.value = true;
}

function openAboutDialog(){
  aboutDialogVisible.value = true;
}

function aboutMe(){
  window.open("https://juggle.plus/about_me.html", '_blank');
}

function contactMe(){
  window.open("https://juggle.plus", '_blank');
}

async function onSubmit() {
  if (!formRef.value) return;
  const valid = await formRef.value?.validate(() => {});
  if (!valid) {
    return;
  }
  if (formValue.newSecret !== formValue.repeatNewSecret) {
    ElMessage({ type: 'error', message: '两次输入的新密码不一致' });
    return;
  }
  const param = {
    oldSecret: CryptoJS.MD5(formValue.oldSecret).toString(),
    newSecret: CryptoJS.MD5(formValue.newSecret).toString(),
  }
  const res = await userService.updateAccountSecret(param);
  if (res.success) {
    ElMessage({ type: 'success', message: '修改成功' });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
  updatePwdDialogVisible.value = false;

}

async function getProductInfo() {
  const res = await userService.getProductInfo();
  if(res.success){
    productVersion.value = res.result;
  }
}

async function logout() {
  const res = await userService.logout();
  if (res) {
    await $router.push('/login');
  } else {
    ElMessage.error('退出失败');
  }
}

function extractColorByName(name) {
  const temp = [];
  temp.push('#');
  for (let index = 0; index < name.length; index++) {
    temp.push(parseInt(name[index].charCodeAt(0), 10).toString(16));
  }
  return temp.slice(0, 5).join('').slice(0, 4);
}
</script>
<template>
  <el-dropdown class="app-current-userPO-dropdown">
    <div class="app-current-userPO">
      <el-avatar :size="32" :style="`background:${extractColorByName(userName)}`">{{ userNameInitial }}</el-avatar>
      <span class="current-userPO-name">{{ userName }}</span>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="openUpdatePwdDialog">修改密码</el-dropdown-item>
        <el-dropdown-item @click="openAboutDialog">关于我们</el-dropdown-item>
        <el-dropdown-item @click="logout" divided>退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>

  <el-dialog v-model="updatePwdDialogVisible" title="修改密码" width="500" center>
    <div class="form">
      <el-form ref="formRef" label-position="top" :model="formValue" :rules="rules">
        <el-form-item label="旧密码" prop="oldSecret">
          <el-input v-model="formValue.oldSecret" type="password" placeholder="请输入旧密码" maxlength="15" />
        </el-form-item>
        <el-form-item label="新密码" prop="newSecret">
          <el-input v-model="formValue.newSecret" type="password" placeholder="6-15个字符，包含数字和大小写字母" minlength="6" maxlength="15" />
        </el-form-item>
        <el-form-item label="重复新密码" prop="repeatNewSecret">
          <el-input v-model="formValue.repeatNewSecret" type="password" placeholder="6-15个字符，包含数字和大小写字母" minlength="6" maxlength="15" />
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="updatePwdDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="aboutDialogVisible" title="关于" width="500" center>
    <el-descriptions :column="1">
      <el-descriptions-item label="版本:">开源版</el-descriptions-item>
      <el-descriptions-item label="版本号:">{{productVersion}}</el-descriptions-item>
      <el-descriptions-item label="备注:"></el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" plain @click="aboutMe">关于我们</el-button>
        <el-button type="success" plain @click="contactMe">联系我们</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<style lang="less" scoped>
.app-current-userPO-dropdown {
  height: 100%;
  padding: 0 20px 0 0;
}
.app-current-userPO {
  height: 100%;
  display: flex;
  align-items: center;
  color: #fff;
  outline: none;

  .el-avatar {
    font-size: 22px;
    font-weight: bold;
  }

  .current-userPO-name {
    font-size: 15px;
    margin-left: 8px;
  }
}
</style>
