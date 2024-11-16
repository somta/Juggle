<template>
  <div ref="monacoEditorRef" :style="monacoEditorStyle"></div>
</template>
<script setup lang="ts">
import {onMounted, computed, watch} from 'vue';
import { useCodeEditor } from '@/hooks/useCodeEditor.ts';

const props = defineProps({
  modelValue: String,
  width:  {
    type: String,
    default: '100%'
  },
  height: {
    type: String,
    default: '100%'
  },
  language: {
    type: String,
    default: 'javascript'
  },
  editorOption:  {
    type: Object,
    default: () => ({})
  },
});

const emits = defineEmits<{
  (e: 'blue'): void;
  (e: 'update:modelValue', val: string): void;
}>();

const monacoEditorStyle = computed(() => {
  return {
    width: props.width,
    height: props.height,
  };
});

const { monacoEditorRef, createEditor, updateVal, updateOptions, getEditor } = useCodeEditor(props.language);

onMounted(() => {
  const monacoEditor = createEditor(props.editorOption);
  updateMonacoVal(props.modelValue);
  monacoEditor?.onDidChangeModelContent(() => {
    emits('update:modelValue', monacoEditor!.getValue());
  });
  monacoEditor?.onDidBlurEditorText(() => {
    emits('blue');
  });
});

watch(
  () => props.modelValue,
  () => {
    updateMonacoVal(props.modelValue);
  }
);

function updateMonacoVal(val: Object) {
  if (val !== getEditor()?.getValue()) {
    updateVal(val);
  }
}

defineExpose({ updateOptions });
</script>
<style lang="less" scoped></style>
