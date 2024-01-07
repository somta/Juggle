
<script lang="ts" setup>
import { PropType } from 'vue';
import { RawData } from '../../types';
import { Delete, Edit } from '@element-plus/icons-vue';
import ConditionFilterModal from '../ConditionFilterModal.vue';
import { shallowRef } from 'vue';
defineProps({
  data: {
    type: Object as PropType<RawData>,
    required: true,
  },
});
const conditionFilterModal = shallowRef();
function editCondtion () {
  conditionFilterModal.value.open();
}
</script>

<template>
  <div class="node-condition-form">
    <el-form label-position="top">
      <el-form-item label="节点描述">
        <el-input placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="条件表达式">
        <div class="condition-list">
          <div 
            class="condition-item"
            v-for="item, index in data.conditions"
            :key="index"
          >
            <div class="condition-head">
              <div class="condition-title">{{ item.conditionName }}</div>
              <div class="condition-action">
                <el-button link :icon="Edit" v-if="item.conditionType === 'CUSTOM'" @click="editCondtion"></el-button>
                <el-button link :icon="Delete" v-if="item.conditionType === 'CUSTOM' && data.conditions.length > 1"></el-button>
              </div>
            </div>
            <div class="condition-body" v-if="item.expression">{{ item.expression }}</div>
          </div>
        </div>
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
  }
}
</style>
