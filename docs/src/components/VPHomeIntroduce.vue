<script setup lang="ts">
import type {PlumeHomeConfigBase, PlumeThemeHeroAction} from 'vuepress-theme-plume'
import { VPHomeBox,VPButton } from 'vuepress-theme-plume/client'
import { computed } from 'vue'
import {useData} from "vuepress-theme-plume/client";

interface ImageLink {
  image: string;
  link?: string;
}

const props = defineProps<PlumeHomeConfigBase & {
  backgroundImageMask?: number | {
    light?: number;
    dark?: number;
  };
  desc?: string
  actions: PlumeThemeHeroAction[];
  imageLinks: ImageLink[]
}>()

const { isDark } = useData<'home'>()

const mask = computed(() => {
  const mask = props.backgroundImageMask;
  if (typeof mask !== 'object'){
    return mask || 0
  }
  return (isDark.value ? mask.dark : mask.light) || 0
})

const actions = computed(() => props.actions ? props.actions : [])
const imageLinks = computed(() => props.imageLinks ? props.imageLinks : [])
</script>

<template>
  <VPHomeBox
      :type="type"
      :background-image="backgroundImage"
      :background-attachment="backgroundAttachment"
      :full="true"
  >
    <div class="vp-home-introduce">
      <div class="banner-mask" :style="{ opacity: mask }" />
      <div class="container">
        <div class="content">
          <h2 class="name">
            一个<span class="main">零码</span> , <span class="main">低码</span> , <span class="main">AI</span>的微服务<span class="function">接口编排</span> & <span class="function">系统集成</span>的强大编排工具平台
          </h2>

          <p v-if="desc" class="hero-text">
            {{ desc }}
          </p>

          <div v-if="actions.length" class="actions">
            <div v-for="action in actions" :key="action.link" class="action">
              <VPButton
                  tag="a"
                  size="medium"
                  :theme="action.theme"
                  :text="action.text"
                  :href="action.link"
              />
            </div>

            <div v-if="imageLinks.length" class="imageLink">
              <template v-for="imageLink in imageLinks" :key="imageLink.image" >
                <a :href="imageLink.link">
                  <img :src="imageLink.image" alt="star">
                </a>
              </template>
            </div>

          </div>

        </div>
      </div>
    </div>
  </VPHomeBox>
</template>

<style scoped>
.vp-home-introduce {
  position: relative;
  width: 100%;
  min-height: calc(100vh - var(--vp-nav-height));
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  transition: all var(--vp-t-color);
}

.vp-home-introduce .container {
  position: relative;
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  padding-top: 4rem;
  margin: 0 auto;
}

.vp-home-introduce .content {
  width: 100%;
  padding: 0 2rem;
}

.vp-home-introduce .content .name {
  font-size: 50px;
  font-weight: 600;
  line-height: 60px;
  color: var(--vp-c-text-1);
}

.vp-home-introduce .content .main{
  color: #fb980e;
}

.vp-home-introduce .content .function{
  color: #ef473a;
}

.vp-home-introduce .content .hero-text {
  width: 100%;
  max-width: 700px;
  margin-top: 1.5rem;
  font-size: 16px;
  font-weight: 500;
  color: var(--vp-c-text-hero-text);
  border-radius: 5px;
}

@media (min-width: 960px) {
  .vp-home-introduce .container {
    max-width: 768px;
    padding-top: 6rem;
  }

  .vp-home-introduce .content .name {
    font-size: 50px;
  }
}

@media (min-width: 1440px) {
  .vp-home-introduce .container {
    max-width: 1104px;
    padding-top: 8rem;
  }
}

.actions {
  display: flex;
  flex-wrap: wrap;
  padding-top: 24px;
  margin: -6px;
}

.action {
  flex-shrink: 0;
  padding: 6px;
}

.imageLink{
  display: flex;
  flex-wrap: wrap;
  padding-top: 18px;
  margin: -6px;
}

.imageLink a {
  margin-left: 10px;
}

.imageLink a img{
  height: 26px;
  line-height: 26px;
}

</style>