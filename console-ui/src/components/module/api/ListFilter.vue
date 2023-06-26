<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { apiService } from '@/service';
const emit = defineEmits(['search']);

const formValue = reactive({
  domainId: null,
  apiName: '',
  apiUrl: '',
});

const onSubmit = () => {
  emit('search', formValue);
};

const onReset = () => {
  formValue.domainId = null;
  formValue.apiName = '';
  formValue.apiUrl = '';
};

const domainList = ref<Array<{ value: string; label: string; }>>([]);
const domainLoading = ref(false);
let domainLoaded = false;

async function onVisibleChange (val: boolean) {
  if (domainLoaded) {
    return;
  }
  if (val) {
    domainLoading.value = true; 
    const res = await apiService.domainQuery({ pageNum: 1, pageSize: 999 });
    if (res.success) {
      domainList.value = res.result.map(item => ({ label: item.domainName, value: item.id }));
    }
    domainLoaded = true;
    domainLoading.value = false; 
  }
}

</script>
<template>
  <el-form :inline="true" :model="formValue">
    <el-form-item label="领域">
      <el-select
        v-model="formValue.domainId"
        placeholder="请选择领域"
        filterable
        @visibleChange="onVisibleChange"
      >
        <template v-slot:empty>
          <div class="select-option-empty" v-loading="domainLoading">
            <span v-if="!domainLoading">无数据</span>
          </div>
        </template>
        <el-option
          v-for="item in domainList"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="接口名称">
      <el-input v-model="formValue.apiName" placeholder="请输入接口名称" />
    </el-form-item>
    <el-form-item label="接口地址">
      <el-input v-model="formValue.apiUrl" placeholder="请输入接口地址" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button @click="onReset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<style lang="less">
.select-option-empty {
  height: 120px;
  align-items: center;
  justify-content: center;
}
</style>