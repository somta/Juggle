
<script lang="ts" setup>
import { PropType, ref, watch, shallowRef } from 'vue';
import { RawData, ConditionItem, ElementType } from '../../types';
import { Delete, Edit, Top, Bottom } from '@element-plus/icons-vue';
import ConditionFilterModal from '../ConditionFilterModal.vue';
import { cloneDeep } from 'lodash-es';
import { ElMessage } from 'element-plus';

type ConditionRawData = RawData & { conditions: ConditionItem[] };

const emit = defineEmits(['update', 'cancel']);
const props = defineProps({
  data: {
    type: Object as PropType<ConditionRawData>,
    required: true,
  },
});
const conditionFilterModal = shallowRef();

function getDefaultData () {
  return {
    key: '',
    name: '',
    outgoings: [],
    incomings: [],
    elementType: ElementType.CONDITION,
    desc: '',
    conditions: [],
  }
}

const nodeData = ref(getDefaultData() as ConditionRawData);
watch(() => props.data, val => {
  if (val !== nodeData.value) {
    nodeData.value = Object.assign(getDefaultData(), cloneDeep(val));
  }
  // 确保else在最后
  const elseItemIndex = nodeData.value.conditions?.findIndex(item => item.conditionType === 'DEFAULT');
  const elseItem = nodeData.value.conditions?.[elseItemIndex];
  if (elseItemIndex > -1 && elseItemIndex !== nodeData.value.conditions.length - 1) {
    nodeData.value.conditions.splice(elseItemIndex, 1);
    nodeData.value.conditions.push(elseItem);
  }
}, { immediate: true });

function validate () {
  if (!nodeData.value.name) {
    ElMessage.error('节点名称不能为空');
    return false;
  }
  return true;
}

function edit (index: number) {
  conditionFilterModal.value.open({
    data: nodeData.value,
    index: index,
    afterEdit: (val: ConditionItem) => {
      if (val) {
        if (!val.outgoing) {
          val.outgoing = props.data.outgoings[0];
        }
        nodeData.value.conditions.splice(index, 1, val);
      }
    }
  });
}

function sortUp (index: number) {
  const current = nodeData.value.conditions[index];
  if (index === 0) {
    return;
  }
  nodeData.value.conditions.splice(index, 1);
  nodeData.value.conditions.splice(index - 1, 0, current);
}

function sortDown (index: number) {
  if (index === nodeData.value.conditions.length - 1) {
    return;
  }
  const current = nodeData.value.conditions[index];
  nodeData.value.conditions.splice(index, 1);
  nodeData.value.conditions.splice(index + 1, 0, current);
}

function remove (index: number) {
  nodeData.value.conditions.splice(index, 1);
}

function addCondition () {
  conditionFilterModal.value.open({
    data: nodeData.value,
    afterEdit: (val: ConditionItem) => {
      if (val) {
        if (!val.outgoing) {
          val.outgoing = props.data.outgoings[0];
        }
        const conditionSize = nodeData.value.conditions.length;
        nodeData.value.conditions.splice(conditionSize - 1, 0, val);
      }
    }
  });
}

function onSubmit() {
  if (!validate()) {
    return;
  }
  emit('update', cloneDeep(nodeData.value));
}
function onCancel() {
  emit('cancel');
}
</script>

<template>
  <div class="node-condition-form">
    <el-form label-position="top">
      <el-form-item label="节点编码">
        <span>{{ nodeData.key }}</span>
      </el-form-item>
      <el-form-item label="节点名称">
        <el-input v-model="nodeData.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="节点描述">
        <el-input v-model="nodeData.desc" placeholder="请输入" :rows="2" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="条件表达式">
        <div class="condition-list">
          <div 
            class="condition-item"
            v-for="item, index in nodeData.conditions"
            :key="index"
          >
            <div class="condition-head">
              <div class="condition-title">{{ item.conditionName }}</div>
              <div class="condition-action">
                <template v-if="item.conditionType === 'CUSTOM'">
                  <el-button link :icon="Top" v-if="index > 0" @click="sortUp(index)" title="升序"></el-button>
                  <el-button link :icon="Bottom" v-if="index < nodeData.conditions.length - 2" @click="sortDown(index)" title="降序"></el-button>
                  <el-button link :icon="Edit" @click="edit(index)" title="编辑"></el-button>
                  <el-button link :icon="Delete" @click="remove(index)" title="删除"></el-button>
                </template>
              </div>
            </div>
            <div class="condition-body" v-if="item.expression">{{ item.expression }}</div>
          </div>
          <div class="condition-add">
            <el-button size="small" type="info" @click="addCondition">新增分支</el-button>
          </div>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
    <ConditionFilterModal ref="conditionFilterModal" />
  </div>
</template>

<style lang="less" scoped>
.node-condition-form {
  .condition-list {
    width: 100%;
    .condition-item {
      padding: 5px 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      margin-bottom: 10px;
    }
    .condition-head {
      display: flex;
      justify-content: space-between;
      align-items: center;
      .condition-title {
        font-size: 14px;
      }
      .condition-action {
        .el-button {
          padding: 0;
          width: 30px;
          height: 30px;
          line-height: 30px;
          border-radius: 50%;
          font-size: 14px;
        }
        .el-button+.el-button {
          margin-left: 4px;
        }
      }
    }
    .condition-body {
      border-top: 1px solid #ccc;
      margin-top: 4px;
    }
    .condition-add {
      text-align: center;
    }
  }
}
</style>
