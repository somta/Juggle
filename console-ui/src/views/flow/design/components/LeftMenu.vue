
<script lang="ts" setup>
import IconFlow from '@/components/icons/IconFlow.vue';
import { CaretLeft } from '@element-plus/icons-vue';
import { ref } from 'vue';
const activeItem = ref('');
const menuItems = [
  {
    key: 'flow',
    icon: IconFlow,
    name: '流程',
  },
  {
    key: 'variable',
    icon: '[x]',
    name: '变量',
  }
];
function switchItem(key: string) {
  activeItem.value = key;
}
const treeData = [
  {
    label: '入参变量',
    children: [
      { label: 'id' },
      { label: 'type' },
    ],
  },
  {
    label: '出参变量',
    children: [
      { label: 'nickName' },
      { label: 'userName' },
    ],
  },
  {
    label: '中间变量',
    children: [
      { label: 'env_id' },
      { label: 'env_name' },
    ],
  },
];
</script>

<template>
  <div class="flow-design-left-menu">
    <div class="left-menu-panel" :class="{ active: activeItem}">
      <div class="" v-if="activeItem === 'variable'">
        <el-button type="primary" size="small">新增中间变量</el-button>
        <el-tree :data="treeData">
          <template #default="{ node }">
            <span class="custom-tree-node">
              <span>{{ node.label }}</span>
              <span>
                <a>+</a>
                <a style="margin-left: 8px">x</a>
              </span>
            </span>
          </template>
        </el-tree>
      </div>
      <div class="left-menu-close" @click="switchItem('')" v-if="activeItem">
        <el-icon><CaretLeft /></el-icon>
      </div>
    </div>
    <div class="left-menu">
      <div class="left-menu-list">
        <div class="left-menu-item"
          v-for="item in menuItems"
          :key="item.key"
          :class="{ active: activeItem === item.key }"
          @click="switchItem(item.key)"
        >
          <div class="left-menu-icon" v-if="typeof item.icon === 'string'">{{ item.icon }}</div>
          <component v-else :is="item.icon"></component>
          <div class="left-menu-name">{{ item.name }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.flow-design-left-menu {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  .left-menu {
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 60px;
    border: solid 1px var(--el-menu-border-color);
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
  
    .left-menu-list {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      gap: 4px;
      align-items: center;
      justify-content: center;
      background-color: #fff;
    }
  
    .left-menu-item {
      position: relative;
      width: 48px;
      height: 48px;
      border-radius: 4px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      transition: all 0.3s;
      &::after {
        content: "";
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        left: 0;
        width: 2px;
        height: 24px;
        background-color: #409eff;
        opacity: 0;
        transition: opacity 0.3s;
      }
      &:hover, &.active {
        background-color: #f0f2f5;
      }
      &.active::after {
        opacity: 1;
      }
    }
    .left-menu-icon {
      font-size: 16px;
      line-height: 14px;
      margin-bottom: 4px;
    }
    .left-menu-name {
      font-size: 12px;
    }
  }
  .left-menu-panel {
    position: absolute;
    top: 0;
    left: 0;
    transform: translateX(-140px);
    width: 200px;
    height: 100%;
    border: 1px solid var(--el-menu-border-color);
    border-left: none;
    background-color: #fff;
    transition: transform ease 0.2s;

    &.active {
      transform: translateX(60px);
    }

    .left-menu-close {
      position: absolute;
      top: 50%;
      right: -20px;
      background-color: #fff;
      border: 1px solid var(--el-menu-border-color);
      border-top-right-radius: 18px;
      border-bottom-right-radius: 18px;
      border-left: none;
      transform: translateY(-50%);
      width: 20px;
      height: 60px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 16px;
      color: #666;
      cursor: pointer;
    }

    .custom-tree-node {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 14px;
      padding-right: 8px;
    }
  }
}
</style>
