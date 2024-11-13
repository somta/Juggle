<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';
import { dataSourceService } from '@/service';
import { DataSource } from '@/service/module/dataSource.ts';
import ResizableDrawer from "@/components/common/ResizableDrawer.vue";
import {DrawerKey} from "@/components/common/types.ts";

const dialogVisible = ref(false);
const dataSourceFormRef = ref<FormInstance>();
const editItem = ref<Record<string, any>>();
function getDefault() {
  return {
    id: null,
    dataSourceName: '',
    dataSourceType: '',
    dataSourceDesc: '',
    address: '',
    port: '',
    userName: '',
    password: '',
    databaseName: '',
    connectExtInfo: '',
    minPoolSize: 5,
    maxPoolSize: 5,
    queryTimeout: 30,
  };
}
const formValue = reactive<DataSource>(getDefault());
const rules = reactive<FormRules>({
  dataSourceName: [{ required: true, message: '请输入数据源名称', trigger: 'blur' }],
  dataSourceType: [{ required: true, message: '请选择数据源类型', trigger: 'blur' }],
  address: [{ required: true, message: '请输入连接地址', trigger: 'blur' }],
  port: [{ required: true, message: '请输入连接端口', trigger: 'blur' }],
  userName: [{ required: true, message: '请输入连接账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入连接密码', trigger: 'blur' }],
  databaseName: [{ required: true, message: '请输入连接数据库名', trigger: 'blur' }],
});

const emit = defineEmits(['add', 'edit']);

function onCancel() {
  dialogVisible.value = false;
}

async function onSubmit() {
  if (!dataSourceFormRef.value) return;
  const valid = await dataSourceFormRef.value?.validate(() => {});
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
    dataSourceFormRef.value?.resetFields();
    if (item) {
      const res = await dataSourceService.queryDataSourceInfo(item.id);
      if (res.success) {
        formValue.id = res.result.id;
        formValue.dataSourceName = res.result.dataSourceName;
        formValue.dataSourceType = res.result.dataSourceType;
        formValue.dataSourceDesc = res.result.dataSourceDesc;
        formValue.address = res.result.address;
        formValue.port = res.result.port;
        formValue.userName = res.result.userName;
        formValue.password = res.result.password;
        formValue.databaseName = res.result.databaseName;
        formValue.connectExtInfo = res.result.connectExtInfo;
        formValue.minPoolSize = res.result.minPoolSize;
        formValue.maxPoolSize = res.result.maxPoolSize;
        formValue.queryTimeout = res.result.queryTimeout;
      }
    } else {
      // 清空
      Object.assign(formValue, getDefault());
    }
  });
}

const title = computed(() => {
  if (editItem.value) {
    return '编辑数据源';
  }
  return '新增数据源';
});

defineExpose({ open });
</script>
<template>
  <ResizableDrawer v-model="dialogVisible" :size="480" :title="title" destroyOnClose :drawer-key="DrawerKey.DATA_SOURCE_DRAWER">
    <div class="form">
      <el-form ref="dataSourceFormRef" label-position="top" :model="formValue" :rules="rules">
        <el-form-item label="数据源名称" prop="dataSourceName">
          <el-input v-model="formValue.dataSourceName" maxlength="30" />
        </el-form-item>
        <el-form-item label="数据源类型" prop="dataSourceType">
          <el-select v-model="formValue.dataSourceType">
            <el-option value="MySql" key="MySql">MySql</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="dataSourceDesc">
          <el-input v-model="formValue.dataSourceDesc" type="textarea" :rows="2" maxlength="120" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="18">
            <el-form-item label="连接地址" prop="address">
              <el-input v-model="formValue.address" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="端口" prop="port">
              <el-input v-model="formValue.port" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="账号" prop="userName">
              <el-input v-model="formValue.userName" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="formValue.password" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="数据库名称" prop="databaseName">
          <el-input v-model="formValue.databaseName" />
        </el-form-item>
        <el-form-item label="连接拓展信息" prop="connectExtInfo">
          <el-input v-model="formValue.connectExtInfo" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="最小连接数" prop="minPoolSize">
              <el-input-number v-model="formValue.minPoolSize" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大连接数" prop="maxPoolSize">
              <el-input-number v-model="formValue.maxPoolSize" class="mx-4" controls-position="right" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="查询超时时间（秒）" prop="queryTimeout">
          <el-input v-model="formValue.queryTimeout" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </ResizableDrawer>
</template>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
