
<script lang="ts" setup>
import { ref, computed } from 'vue';
type MenuItem = {
  label: string;
  value: string;
  children?: MenuItem[];
};
function addPrefix (item: MenuItem, prefix = '') {
  if (prefix) {
    item.value = prefix + '-' + item.value;
  }
  if (item.children && item.children.length > 0) {
    item.children = item.children.map(child => addPrefix(child, item.value));
  }
  return item;
}

function getBasicTypeList(prefix: string = '') {
  return [
    {
      label: '字符串',
      value: 'String',
    },
    {
      label: '数字',
      value: 'Number',
    },
    {
      label: '布尔',
      value: 'Boolean',
    },
  ].map((item) => {
    return addPrefix(item, prefix);
  });
}
function getListTypeList() {
  return [
    {
      label: '基础',
      value: 'Basic',
      children: getBasicTypeList(),
    },
    {
      label: '结构',
      value: 'Struct',
      children: getStructTypeList(),
    },
  ];
}
function getStructTypeList() {
  return [
    {
      label: '暂不支持',
      value: 'NotSupport',
    },
  ]
}

function getBasicType(prefix: string = '') {
  return addPrefix({
    label: '基础',
    value: 'Basic',
    children: getBasicTypeList(),
  }, prefix);
}
function getListType(prefix: string = '') {
  return addPrefix({
    label: '列表',
    value: 'List',
    children: getListTypeList(),
  }, prefix);
}
function getStructType(prefix: string = '') {
  return addPrefix({
    label: '结构',
    value: 'Struct',
    children: getStructTypeList(),
  }, prefix);
}
const allTypes = [
  getBasicType(),
  getListType(),
  getStructType(),
  ...getBasicTypeList(),
  ...getListTypeList(),
  ...getStructTypeList(),
];
const allTypesMap = allTypes.reduce((map, item) => {
  map[item.value] = item.label;
  return map;
}, {} as Record<string, string>);

const dataTypeList = ref([
  getBasicType(),
  getListType(),
  getStructType(),
]);
const currentKey = ref('');
function onSelect (val: string) {
  currentKey.value = val;
}
const selectedName = computed(() => {
  const val = currentKey.value;
  if (!val || val.includes('NotSupport')) {
    return '';
  }
  return val.split('-').map(key => allTypesMap[key]).join(' - ');
})

</script>

<template>
  <el-menu
    mode="horizontal"
    :ellipsis="false"
    class="data-type-select"
    popper-class="data-type-select-popper"
    :default-active="currentKey"
    @select="onSelect"
  >
    <el-sub-menu index="1">
      <template #title>
        <span v-if="selectedName">{{ selectedName }}</span>
        <span v-else class="data-type-select-placeholder">请选择</span>
      </template>
      <el-sub-menu :index="item.value" v-for="item in dataTypeList" :key="item.value" :popper-offset="1">
        <template #title>{{ item.label }}</template>
        <template v-for="subItem in item.children" :key="subItem.value">
          <el-sub-menu :index="subItem.value" v-if="subItem.children" :popper-offset="1">
            <template #title>{{ subItem.label }}</template>
            <template v-for="subSubItem in subItem.children" :key="subSubItem.value">
              <el-menu-item :index="subSubItem.value">{{ subSubItem.label }}</el-menu-item>
            </template>
          </el-sub-menu>
          <el-menu-item v-else :index="subItem.value">{{ subItem.label }}</el-menu-item>
        </template>
      </el-sub-menu>
    </el-sub-menu>
  </el-menu>
</template>

<style lang="less">
.data-type-select.el-menu.el-menu--horizontal {
  height: 32px;
  border: none;
  box-shadow: 0 0 0 1px var(--el-input-border-color,var(--el-border-color)) inset;
  border-radius: var(--el-input-border-radius,var(--el-border-radius-base));
  &>.el-sub-menu.is-active .el-sub-menu__title {
    border: none;
    color: var(--el-text-color-primary);
  }
  .el-sub-menu__title {
    height: 32px;
    line-height: 32px;
    padding: 0 32px 0 12px;
  }
  .el-sub-menu .el-sub-menu__icon-arrow {
    right: 8px;
  }
  .data-type-select-placeholder {
    color: #999;
  }
}
.data-type-select-popper {
  .el-menu--popup {
    min-width: 100px;
  }
}
</style>
