<script setup lang="ts">
const props = defineProps<{
  content?: string
  image?: string
}>()
</script>

<template>
    <div class="vp-title">
      <div v-if="content" class="content">{{ content }}</div>
      <img v-if="image" src="https://me.yicode.tech/common-title-bg.png" alt="">
    </div>
</template>

<style scoped>
.vp-title{
  display: flex;
  justify-content: center;
  margin-top: 50px;
  margin-bottom: 30px;

  .content {
    font-size: 30px;
    font-weight: 600;
  }

  img {
    height: 54px;
  }
}
</style>