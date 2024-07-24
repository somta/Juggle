<script setup lang="ts">
import { Plus, Minus } from '@element-plus/icons-vue';
import { computed } from 'vue';
const emit = defineEmits(['change']);

const props = defineProps({
  scale: {
    type: Number,
    default: 1,
  },
});

const step = 0.1; // 缩放因子 每次10%

const percent = computed(() => {
  return `${Math.round(props.scale * 100)}%`;
});

function plus() {
  emit('change', props.scale + step);
}

function minus() {
  emit('change', props.scale - step);
}
</script>

<template>
  <div class="zoom-tool">
    <el-icon @click="minus"><Minus /></el-icon>
    <span>{{ percent }}</span>
    <el-icon @click="plus"><Plus /></el-icon>
  </div>
</template>

<style lang="less" scoped>
.zoom-tool {
  width: 112px;
  height: 28px;
  padding: 0 6px;
  background-color: #fff;
  box-shadow: 0 0 8px 0 #e0e5f0;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  font-size: 14px;
  user-select: none;

  position: absolute;
  right: 16px;
  bottom: 16px;

  .el-icon {
    cursor: pointer;
  }
}
</style>
