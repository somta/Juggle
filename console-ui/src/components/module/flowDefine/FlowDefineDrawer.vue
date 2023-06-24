<template>
  <el-drawer v-model="flowDefineDrawer"
             title="新增流程">
    <div>
      <el-form>
        <el-form-item label="流程名称">
          <el-input/>
        </el-form-item>
        <el-form-item label="流程类型">
          <el-select
              placeholder="请选择流程类型"
          >
            <el-option label="同步" value="sync" />
            <el-option label="异步" value="async" />
          </el-select>
        </el-form-item>
        <el-form-item label="流程描述">
          <el-input type="textarea" />
        </el-form-item>
      </el-form>
      <div class="demo-drawer__footer">
        <el-button>取消</el-button>
        <el-button type="primary">确定</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import {nextTick, ref} from "vue";

const flowDefineDrawer = ref(false);

function open (item?: Record<string, any>) {
  //editItem.value = item;
  flowDefineDrawer.value = true;
}

defineExpose({ open });

</script>

<style scoped>

</style>