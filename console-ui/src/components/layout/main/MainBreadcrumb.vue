<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const $router = useRouter();
const matched = ref($router.currentRoute.value.matched);
$router.afterEach(to => {
  matched.value = to.matched;
});
</script>
<template>
  <el-breadcrumb separator="/">
    <template v-for="item in matched" :key="item.path">
      <el-breadcrumb-item :to="{ path: item.path }" v-if="item.name !== 'home'">
        {{ item.meta.name }}
      </el-breadcrumb-item>
    </template>
  </el-breadcrumb>
</template>