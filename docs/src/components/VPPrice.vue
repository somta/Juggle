<script setup lang="ts">

import {ref} from "vue";
import MaintenanceService from "../cmps/MaintenanceService.vue";
import ProductDiff from "../cmps/ProductDiff.vue";

const selectedPriceTab = ref<string>("yearly");
const modelVisible = ref(false);

// 定义一个函数用于处理点击事件，并设置selectedPriceTab的值为当前点击的价格选项ID
function showServiceDetail(tabId: string) {
  selectedPriceTab.value = tabId;
}

function showModel() {
  modelVisible.value = true;
}

function hideModel() {
  modelVisible.value = false;
}

</script>

<template>
    <div class="vp-price">

      <!-- 价格分类 -->
      <div class="price_tab-box">

        <div class="price-tab" @click="showServiceDetail('open')">
          <div class="price-title">免费使用</div>
          <ul class="price-desc">
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
             </span>
              开箱即用 | 快速尝试
            </li>
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              无需购买 | 立刻使用
            </li>
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
                <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              自助升级 | 无需二开
            </li>
          </ul>
        </div>

        <div class="price-tab" @click="showServiceDetail('yearly')">
          <div class="price-title">按年订阅</div>
          <ul class="price-desc">
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
             </span>
              开箱即用 | 快速尝试
            </li>
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              按需购买 | 价格优惠
            </li>
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
                <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              免费升级 | 无需二开
            </li>
          </ul>
        </div>

        <div class="price-tab" @click="showServiceDetail('lifetime')">
          <div class="price-title">终身授权</div>
          <ul class="price-desc">
            <li>
              <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg></span>一次付费 | 终身买断</li>
            <li>
            <span role="img" aria-label="check-circle" class="anticon anticon-check-circle">
              <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg></span>前端源码 | 多买多送</li><li><span role="img" aria-label="check-circle" class="anticon anticon-check-circle"><svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
            </span>
            免费升级 | 部分二开
          </li>
          </ul>
        </div>

        <div class="price-tab price_choose__XPhuP" @click="showServiceDetail('sourcecode')">
          <div class="price-title">源代码</div>
          <ul class="price-desc">
            <li>
              <span role="img" class="anticon">
                <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              一次付费 | 全套源码
            </li>
            <li>
              <span role="img" class="anticon">
                <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              源码讲解 | 二开服务
            </li>
            <li>
              <span role="img" class="anticon">
                <svg viewBox="64 64 896 896" focusable="false" data-icon="check-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path></svg>
              </span>
              源码更新 | 全量二开
            </li>
          </ul>
        </div>

      </div>

      <!-- 服务清单 -->
      <div class="service-detail">
        <div>
          <h3>服务清单</h3>
        </div>

        <!-- 开源版本 -->
        <div v-if="selectedPriceTab === 'open'" class="service-detail-item">
          <div class="service-detail-div">
            <div class="service-detail-header">
              <h4>开源版本-免费使用</h4>
              <p class="detail-desc">开源免费，开箱即用</p>
              <button type="button" class="contact-us">
                <span @click="showModel">联系我们</span>
              </button>
            </div>

            <div class="service-content-item">
              <span class="content-title">部署方式</span><span class="content-desc">私有化部署</span>
            </div>
            <div class="service-content-item">
              <span class="content-title">授权时长</span><span class="content-desc">永久</span>
            </div>
            <div class="service-content-item">
              <span class="content-title">多买优惠</span><span class="content-desc">无</span>
            </div>
            <div class="service-content-item">
              <span class="content-title">定制开发</span>
              <span class="content-desc">支持 <span title="定制化开发的内容将以人天额外计费" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
            </div>
            <div class="service-content-item">
              <span class="content-title">系统源码</span><span class="content-desc">无</span>
            </div>
            <div class="service-content-item">
              <span class="content-title">系统文档</span><span class="content-desc">官网使用手册</span>
            </div>
          </div>
        </div>

        <!-- 按年订阅 -->
        <div v-if="selectedPriceTab === 'yearly'" class="service-detail-item">
            <div class="service-detail-div">
              <div class="service-detail-header">
                <h4>企业版本-按年订阅</h4>
                <p class="detail-desc">按年付费，成本低廉</p>
                <button type="button" class="contact-us">
                  <span @click="showModel">联系我们</span>
                </button>
              </div>

              <div class="service-content-item">
                <span class="content-title">部署方式</span><span class="content-desc">私有化部署</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">授权时长</span><span class="content-desc">按年</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">多买优惠</span><span class="content-desc">多年优惠</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">定制开发</span>
                <span class="content-desc">支持 <span title="定制化开发的内容将以人天额外计费" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统源码</span><span class="content-desc">无</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统文档</span><span class="content-desc">产品使用说明书</span>
              </div>
            </div>
        </div>

        <!-- 终生授权 -->
        <div v-else-if="selectedPriceTab === 'lifetime'" class="service-detail-item">
            <div class="service-detail-div">
              <div class="service-detail-header">
                <h4>企业版本-终生授权</h4>
                <p class="detail-desc">一次付费，终身使用</p>
                <button type="button" class="contact-us">
                  <span @click="showModel">联系我们</span>
                </button>
              </div>

              <div class="service-content-item">
                <span class="content-title">部署方式</span><span class="content-desc">私有化部署</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">授权时长</span><span class="content-desc">永久</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">多买优惠</span><span class="content-desc">多套优惠</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">定制开发</span>
                <span class="content-desc">支持 <span title="定制化开发的内容将以人天额外计费" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统源码</span><span class="content-desc">前端源码</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">源码指导</span><span class="content-desc">无</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统文档</span><span class="content-desc">产品使用说明书</span>
              </div>
            </div>
        </div>

        <!-- 源代码 -->
        <div v-else-if="selectedPriceTab === 'sourcecode'" class="service-detail-item">
            <div class="service-detail-div">
              <div class="service-detail-header">
                <h4>企业版本-源代码</h4>
                <p class="detail-desc">源码在手，二开无忧</p>
                <button type="button" class="contact-us">
                  <span @click="showModel">联系我们</span>
                </button>
              </div>

              <div class="service-content-item">
                <span class="content-title">部署方式</span><span class="content-desc">私有化部署</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">授权时长</span><span class="content-desc">永久</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">多买优惠</span><span class="content-desc">无</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">定制开发</span>
                <span class="content-desc">支持 <span title="定制化开发的内容将以人天额外计费" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统源码</span><span class="content-desc">前后端源码</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">源码指导</span><span class="content-desc">提供源码解答服务</span>
              </div>
              <div class="service-content-item">
                <span class="content-title">系统文档</span><span class="content-desc">产品使用说明书</span>
              </div>
            </div>
        </div>
      </div>

      <!-- 版本功能对比 -->
      <ProductDiff/>
      <!-- 维保服务订阅 -->
      <MaintenanceService/>

      <!-- 弹框 -->
      <div v-if="modelVisible" class="modal">
        <div class="modal-content">
          <span class="close" @click="hideModel">&times;</span>
          <div>
            <div>添加官方微信,了解更多详情，备注"Juggle"</div>
            <img src="/images/wx.png" alt="">
          </div>
        </div>
      </div>

    </div>
</template>

<style scoped>

.vp-price{
  justify-content: center;
}

.price_tab-box {
  display: flex;
  justify-content: space-between;
  width: 1000px;
  margin: 50px auto auto;
}

.price-tab {
  width: 23%;
  background: #fff;
  box-shadow: 0 0 14px rgba(82, 112, 237, .322);
  border-radius: 8px;
  cursor: pointer;
  position: relative;

  .price-title {
    padding: 17px 0 18px;
    font-size: 18px;
    font-weight: 600;
    color: #333;
    background: #f5f7ff;
    border-radius: 8px 8px 0 0;
    line-height: 25px;
    text-align: center;
  }

  .price-desc {
    color: rgba(60, 60, 67);
    padding-bottom: 15px;
  }

  ul {
    list-style: none;
    margin: 0;
    padding: 0;
  }

  li {
    margin-top: 15px;
    text-align: left;
    text-indent: 28px;

    span {
      color: #2ec62e;
      opacity: 1;
      margin-right: 10px;
      font-size: 14px;
    }

    .anticon {
      display: inline-block;
      font-style: normal;
      line-height: 0;
      text-align: center;
      text-transform: none;
      vertical-align: -.125em;
      text-rendering: optimizelegibility;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }

  }
}

.service-detail {
  text-align: center;
  justify-content: space-between;
  width: 800px;
  margin: 50px auto auto;

  .service-detail-item {
    width: 400px;
    margin: auto;
    justify-content: space-between;
    .service-detail-div {
      width: 368px;
      height: 580px;
      background: url("/images/price-bg.jpg") no-repeat;
      background-size: 100% 170px;
      position: relative;

      border-radius: 8px 8px 0 0;
      text-align: center;
      color: #363b52;
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, .06);

      .service-detail-header{
        height: 167px;

        h4 {
          color: #583100;
          margin-bottom: 0;
          padding-top: 22px;
          margin-top: 0;
          font-size: 20px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 500;
          line-height: 28px;
          text-shadow: 0 8px 20px rgba(0, 0, 0, .06);
        }

        .detail-desc {
          color: #a28140;
        }

        .contact-us{
          width: 142px;
          height: 40px;
          border-radius: 20px;
          font-size: 18px;
          font-weight: bold;
          margin-left: 12px;
          border: 0;
          background: linear-gradient(180deg, #f8d98c, #e1aa58);
          box-shadow: 0 8px 20px 0 rgba(0, 0, 0, .06), 0 6px 10px 0 rgba(239, 173, 47, .4), inset 0 -1px 0 0 rgba(255, 207, 46, .5);
        }
      }

      .service-content-item {
        line-height: 36px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        margin-top: 5px;
        padding-top: 12px;
        .content-title {
          margin-left: 24px;
          color: #999;
        }
        .content-desc {
          color: var(--vp-c-text-1);
          margin-left: 24px;
          .tip svg{
            display: inline-block;
            vertical-align: -.125em;
          }
        }
      }
    }
  }
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;

  .modal-content {
    color: #333333;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    position: relative;
  }
  .close {
    position: absolute;
    color: #1b1b1f;
    top: 10px;
    right: 15px;
    font-size: 20px;
    cursor: pointer;
  }
}


</style>