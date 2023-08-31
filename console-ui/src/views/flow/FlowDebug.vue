<script setup lang="ts">
import {reactive, ref} from "vue";
import {useRoute} from "vue-router";

const route=useRoute();
let data=reactive({
  params: route.params
});

console.log(data);
const select = ref('');
const flowUrl = ref('');



</script>

<template>
  <div class="page-flow-define">
    <el-form
        ref="formRef"
    >
      <el-form-item>
        <el-input
            v-model="flowUrl"
            placeholder="Please input"
            class="input-with-select"
        >
          <template #prepend>
            <el-select v-model="select" placeholder="Select" style="width: 115px">
              <el-option label="Get" value="Get" />
              <el-option label="Post" value="Post" />
            </el-select>
          </template>
          <template #append>
            <el-button>发送</el-button>
          </template>
        </el-input>
      </el-form-item>

    </el-form>
  </div>
</template>

<style lang="less" scoped>
.page-flow-define {
  .page-header {
    height: auto;
    padding: 24px 16px;
  }
}
</style>
