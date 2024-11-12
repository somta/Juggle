<script lang="ts" setup>
import {computed, onMounted, onUnmounted, ref, watch} from 'vue';

const props = defineProps({
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

const resizeBar = ref<HTMLElement | null>(null);
const drawerSize = ref(props.size);
const visible = ref(props.modelValue);

let startX = 0;
let startY = 0;
let initialSize = 0;

function onMouseMove(e: MouseEvent) {
  if (props.direction === 'ltr' || props.direction === 'rtl') {
    const deltaX = props.direction === 'ltr' ?  e.clientX - startX: startX - e.clientX ;
    drawerSize.value = Math.max(200, initialSize + deltaX);
  } else if (props.direction === 'ttb' || props.direction === 'btt') {
    const deltaY = props.direction === 'ttb' ? e.clientY - startY : startY - e.clientY;
    drawerSize.value = Math.max(200, initialSize + deltaY);
  }
}

function onMouseUp() {
  document.removeEventListener('mousemove', onMouseMove);
  document.removeEventListener('mouseup', onMouseUp);
}

function onMouseDown(e: MouseEvent) {
  e.preventDefault();
  startX = e.clientX;
  startY = e.clientY;
  initialSize = drawerSize.value;
  document.addEventListener('mousemove', onMouseMove);
  document.addEventListener('mouseup', onMouseUp);
}

onMounted(() => {
});

onUnmounted(() => {
  document.removeEventListener('mousemove', onMouseMove);
  document.removeEventListener('mouseup', onMouseUp);
});

function onClosed() {
  emit('closed');
  emit('update:modelValue', false);
}

// 监听父组件的 v-model 更新
watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
});

const drawerClass = computed(() => {
  return `resize-drawer-${props.direction}`;
});

const resizeBarClass = computed(() => {
  return `resize-bar resize-bar-${props.direction}`;
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