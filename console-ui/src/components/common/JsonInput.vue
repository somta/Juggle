<script setup lang="ts">
import {ref, watch, computed, onBeforeUnmount} from 'vue';
import type { PropType } from 'vue';
import { ElInput, ElButton, ElDialog } from 'element-plus';
import { Edit } from '@element-plus/icons-vue';
import * as monaco from 'monaco-editor';
import {DataType} from "@/typings";

const props = defineProps({
  dataType: Object as PropType<DataType>,
  modelValue: {
    type: Object,
    default: null
  }
});

const emit = defineEmits(['update:modelValue']);

const dialogVisible = ref(false);
let editor: monaco.editor.IStandaloneCodeEditor | null = null;
const editorContainer = ref<HTMLDivElement | null>(null);
let defaultJsonValue = {};

// 初始化默认JSON值
watch(
    () => [props.dataType, props.modelValue],
    ([newDataType, newModelValue]) => {
      if (newDataType) {
        // 处理 List类型
        if (newDataType.type === 'List') {
          // 根据传入的modelValue初始化或使用默认值
          if (newModelValue && Array.isArray(newModelValue)) {
            defaultJsonValue = [...newModelValue];
          } else {
            // 如果是对象列表，初始化属性
            if(newDataType.objectStructure){
              const defaultItem = getObjectJson(newDataType as DataType);
              defaultJsonValue = [defaultItem];
            }else{
              defaultJsonValue = [];
            }

          }
        }
        // 处理 Object 类型
        else if (newDataType.type === 'Object' && newDataType.objectStructure) {
          // 根据传入的modelValue初始化或使用默认值
          if (newModelValue && typeof newModelValue === 'object' && !Array.isArray(newModelValue)) {
            defaultJsonValue = { ...newModelValue };
          } else {
            // 初始化为空对象
            defaultJsonValue = getObjectJson(newDataType as DataType) ?? {};
          }
        }
      }
    },
    { immediate: true }
);

const inputValue = computed({
  get: () => {
    return props.modelValue ? JSON.stringify(props.modelValue, null, 2) : '';
  },
  set: (val) => {
    emit('update:modelValue', val);
  }
});

const openDialog = () => {
  dialogVisible.value = true;
  // 延迟初始化编辑器，确保DOM已渲染
  setTimeout(() => {
    initEditor();
  }, 0);
};


function getObjectJson(newDataType: DataType){
  const jsonValue: Record<string, any> = {};
  if(newDataType.objectStructure){
    newDataType.objectStructure.forEach(item => {
      switch (item.dataType.type) {
        case 'String':
          jsonValue[item.propKey] = "";
          break;
        case 'Boolean':
          jsonValue[item.propKey] = false;
          break;
        case 'Object':
          jsonValue[item.propKey] = getObjectJson(item.dataType);
          break;
        default:
          jsonValue[item.propKey] = null;
      }
    });
    return jsonValue;
  }

}


const initEditor = () => {
  if (editorContainer.value) {
    // 销毁现有的编辑器实例
    if (editor) {
      editor.dispose();
    }
    
    // 创建新的编辑器实例
    editor = monaco.editor.create(editorContainer.value, {
      value: props.modelValue ? JSON.stringify(props.modelValue, null, 2) : JSON.stringify(defaultJsonValue, null, 2),
      language: 'json',
      theme: 'vs',
      automaticLayout: true,
      minimap: {
        enabled: false
      },
      fontSize: 14,
      scrollBeyondLastLine: false,
      readOnly: false,
      glyphMargin: false,
      folding: false,
      lineNumbers: 'off',
      renderLineHighlight: 'none',
      overviewRulerLanes: 0,
      overviewRulerBorder: false,
      scrollbar: {
        vertical: 'auto',
        horizontal: 'auto'
      }
    });
  }
};

const closeDialog = () => {
  dialogVisible.value = false;
  // 销毁编辑器实例
  if (editor) {
    editor.dispose();
    editor = null;
  }
};

const saveAndCloseDialog = () => {
  // 获取编辑器中的值并发出更新事件
  if (editor) {
    const value = editor.getValue();
    try {
      const parsedValue = JSON.parse(value);
      emit('update:modelValue', parsedValue);
    } catch (e) {
      console.error('Invalid JSON:', e);
      // 如果不是有效的JSON，直接返回原始字符串
      emit('update:modelValue', value);
    }
  }
  closeDialog();
};

// 组件卸载前清理编辑器
onBeforeUnmount(() => {
  if (editor) {
    editor.dispose();
    editor = null;
  }
});
</script>

<template>
  <div style="width: 100%">
    <el-input v-model="inputValue" placeholder="请输入内容" style="width: 100%">
      <template #append>
        <el-button :icon="Edit" @click="openDialog" />
      </template>
    </el-input>
    <el-dialog title="编辑 JSON" v-model="dialogVisible" width="500px" @close="closeDialog">
      <div ref="editorContainer" style="height: 300px; border: 1px solid #ddd;"></div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="saveAndCloseDialog">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>



