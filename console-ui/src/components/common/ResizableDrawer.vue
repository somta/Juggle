<script lang="ts" setup>
import { ref, watch } from 'vue';
import { useResizableDrawer, getDrawerSize, setDrawerSize } from '@/hooks/useResizableDrawer'; // 确保路径正确

const props = defineProps({
  drawerKey: String,
  size: {
    type: Number,
    default: 560,
  },
  title: {
    type: String,
    default: '',
  },
  modelValue: {
    type: Boolean,
    default: false,
  },
  direction: {
    type: String as () => 'ltr' | 'rtl' | 'ttb' | 'btt',
    default: 'rtl',
  },
});

const emit = defineEmits(['update:modelValue', 'closed']);

const drawerSize = ref(getDrawerSize(props.drawerKey, props.size));
const visible = ref(props.modelValue);

const {
  resizeBar,
  onMouseDown,
  onClosed,
  drawerClass,
  resizeBarClass,
} = useResizableDrawer(props, emit, drawerSize);

// 监听父组件的 v-model 更新
watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
});

// 监听 drawerSize 的变化并更新 localStorage
watch(drawerSize, (newSize) => {
  if (props.drawerKey) {
    setDrawerSize(props.drawerKey, newSize);
  }
});
</script>

<template>
  <el-drawer
      v-model="visible"
      :class="drawerClass"
      class="resizable-drawer"
      :size="drawerSize"
      :title="title"
      :direction="direction"
      @closed="onClosed"
  >
    <div :class="resizeBarClass" ref="resizeBar" @mousedown="onMouseDown"></div>
    <slot></slot>
  </el-drawer>
</template>

<style lang="less">
.el-drawer.resizable-drawer {
  transition: width, height 0s ease;
}

.resize-bar {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.1);
  z-index: 1;

  &.resize-bar-ltr,
  &.resize-bar-rtl {
    cursor: col-resize;
    width: 4px;
    height: 100%;
    top: 0;
  }

  &.resize-bar-ltr {
    right: 0;
  }

  &.resize-bar-rtl {
    left: 0;
  }

  &.resize-bar-ttb,
  &.resize-bar-btt {
    cursor: row-resize;
    height: 4px;
    width: 100%;
    left: 0;
  }

  &.resize-bar-ttb {
    bottom: 0;
  }

  &.resize-bar-btt {
    top: 0;
  }
}
</style>