<script setup lang="ts">

import {ref} from "vue";

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
      <div class="product-version-diff">
        <div class="product-version-diff-title">
          <h3>版本功能对比</h3>
        </div>
        <div class="table-info">
          <div class="table-left">
            <div class="item-classify">
              <div class="table-left-header">模块</div>
              <div class="item-type" style="height: 750px">流程</div>
              <div class="item-type-list">
                <ul class="table-item-list">
                  <li><span class="item-name">流程定义管理</span></li>
                  <li><span class="item-name">支持的流程类型</span></li>
                  <li><span class="item-name">流程设计</span></li>
                  <li><span class="item-name">流程调试</span></li>
                  <li><span class="item-name">流程检测 <span title="支持自动检测流程，并一键修复流程设计中的问题" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span></li>
                  <li><span class="item-name">流程节点</span></li>
                  <li><span class="item-name">多版本流程</span></li>
                  <li><span class="item-name">流程历史版本</span></li>
                  <li><span class="item-name">节点数据Mock</span></li>
                  <li><span class="item-name">支持的脚本语言</span></li>
                  <li><span class="item-name">流程导入导出</span></li>
                  <li><span class="item-name">流程日志</span></li>
                  <li><span class="item-name">企业流程模板</span></li>
                  <li><span class="item-name">流程令牌</span></li>
                  <li><span class="item-name">多语言SDK集成</span></li>
                </ul>
              </div>
            </div>
            <div class="item-classify">
              <div class="item-type" style="height: 300px">套件</div>
              <div class="item-type-list" style="height: 300px">
                <ul class="table-item-list">
                  <li><span class="item-name">套件管理</span></li>
                  <li><span class="item-name">接口管理</span></li>
                  <li><span class="item-name">对象管理</span></li>
                  <li><span class="item-name">接口调试</span></li>
                  <li><span class="item-name">支持的接口协议</span></li>
                  <li><span class="item-name">套件导入/导出</span></li>
                </ul>
              </div>
            </div>
            <div class="item-classify">
              <div class="item-type" style="height: 100px">市场</div>
              <div class="item-type-list" style="height: 100px">
                <ul class="table-item-list">
                  <li><span class="item-name">套件市场</span></li>
                  <li><span class="item-name">模板市场</span></li>
                </ul>
              </div>
            </div>
            <div class="item-classify">
              <div class="item-type" style="height: 100px">设置</div>
              <div class="item-type-list" style="height: 100px">
                <ul class="table-item-list">
                  <li><span class="item-name">数据源</span></li>
                  <li><span class="item-name">静态变量</span></li>
                </ul>
              </div>
            </div>
            <div class="item-classify">
              <div class="item-type" style="height: 100px">系统</div>
              <div class="item-type-list" style="height: 100px">
                <ul class="table-item-list">
                  <li><span class="item-name">用户管理</span></li>
                  <li><span class="item-name">角色&权限</span></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="table-right">
            <ul class="option-list">
              <li>
                <span class="table-con">
                  <label style="background: #d7e3f8;">开源版本</label>
                  <label style="background-image: url(/images/price-bg.jpg);background-size: cover;">企业版本</label>
                </span>
              </li>
            </ul>
            <ul class="table-content-list">
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>方法节点/判断节点/赋值节点/mysql节点/代码节点</label>
                  <label>方法节点/判断节点/赋值节点/数据库节点/代码节点/并行节点</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>Groovy/JavaScript</label>
                  <label>Groovy/JavaScript/Python/Java</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>全局令牌</label>
                  <label>应用级别细粒度令牌</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>Http</label>
                  <label>Http/Dubbo/WebService</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>MySql数据源</label>
                  <label>多种数据源</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <!-- 维保服务订阅 -->
      <div class="service-container">
        <div class="service-container-title">
            <h3>维保服务订阅</h3>
        </div>
        <div class="table-info">
          <div class="table-left">
            <div class="item-classify">
              <div class="table-left-header">服务项目</div>
              <div class="item-type">工单系统</div>
              <div class="item-type-list">
                <ul class="table-item-list">
                  <li><span class="item-name">公共交流群支持</span></li>
                  <li><span class="item-name">远程故障排除协助</span></li>
                  <li><span class="item-name">技术支持专群</span></li>
                  <li><span class="item-name">专属客户经理</span></li>
                  <li><span class="item-name">服务支持时间</span></li>
                  <li><span class="item-name">服务响应时间</span></li>
                  <li><span class="item-name">紧急响应支持</span></li>
                  <li><span class="item-name">紧急响应时间</span></li>
                </ul>
              </div>
            </div>
            <div class="item-classify">
              <div class="item-type" style="height: 400px">增值服务</div>
              <div class="item-type-list">
                <ul class="table-item-list">
                  <li><span class="item-name">线上专题培训 <span title="通过远程会议，进行系统使用讲解" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span></li>
                  <li><span class="item-name">原厂协助升级</span></li>
                  <li><span class="item-name">远程迁移服务</span></li>
                  <li><span class="item-name">业务问题处理指导</span></li>
                  <li><span class="item-name">远程技术支持</span></li>
                  <li><span class="item-name">最新API文档讲解</span>
                  </li>
                  <li><span class="item-name">二开Demo教学讲解 <span title="针对项目源码结构，新增套件进行讲解（仅限购买源码的用户）" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
                  </li>
                  <li><span class="item-name">权限系统整合咨询 <span title="登录，注册，权限等功能，提供系统源码讲解和对接咨询（仅限购买源码的用户）" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="table-right">
            <ul class="option-list">
              <li>
                <span class="table-con">
                  <label style="background: #d7e3f8;">基础维保 <span title="我们将通过公共交流群、社区平台等方式为用户提供维保服务，响应时间不定。" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></label>
                  <label style="background: linear-gradient(181deg, #fcf0e6, #f6caa6);">专业维保</label>
                  <label style="background-image: url(/images/price-bg.jpg);background-size: cover;">黄金服务包</label>
                </span>
              </li>
            </ul>
            <ul class="table-content-list">

              <li>
                <span class="table-con">
                  <label>
                    <img alt="" src="/images/choose.png" decoding="async" loading="lazy" style="color:transparent">
                  </label>
                  <label>
                    <img alt="" src="/images/choose.png" decoding="async" loading="lazy" style="color:transparent">
                  </label>
                  <label>
                    <img alt="" src="/images/choose.png" decoding="async" loading="lazy" style="color:transparent">
                  </label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy" style="color:transparent"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"style="color:transparent"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"style="color:transparent"></label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy" style="color:transparent"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>- -</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"style="color:transparent"></label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>不定时</label>
                  <label>5 × 8</label>
                  <label>7 × 8</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>不定时</label>
                  <label>6小时</label>
                  <label>4小时</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>3次</label>
                  <label>12次</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>90分钟</label>
                  <label>90分钟</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>1次</label>
                  <label>3次</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>1次</label>
                  <label>3次</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                <label>- -</label>
                <label>1次</label>
                <label>3次</label>
              </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>支持</label>
                  <label>支持</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1人天</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1次</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1次</label>
                </span>
              </li>
              <li>
                <span class="table-con">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1次</label>
                </span>
              </li>
            </ul>
          </div>
        </div>
      </div>

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


.product-version-diff {
  width: 100%;
  height: 1350px;
  max-width: 1220px;
  margin: auto;
  .product-version-diff-title {
    margin: 70px auto 26px;
    text-align: center;
  }
  label{
    width: 50% !important;
  }
}


.service-container {
  width: 100%;
  max-width: 1220px;
  margin: auto;
  .service-container-title {
    margin: 70px auto 26px;
    text-align: center;
  }
}

.table-info {
  box-sizing: border-box;
  border-radius: 8px 8px 0 0;

  .table-left {
    width: 350px;
    float: left;
    border-bottom: 1px solid #e5e9f0;
    border-left: 1px solid #e5e9f0;
    border-right: 1px solid #e5e9f0;
    box-sizing: border-box;

    .item-classify {
      float: left;

      .table-left-header {
        height: 60px;
        line-height: 60px;
        background: #e6eaf2;
        border-radius: 8px 8px 0 0;
        font-size: 16px;
        font-weight: 500;
        color: #363b52;
        padding-left: 22px;
        text-align: center;
      }

      .item-type {
        width: 123px;
        height: 400px;
        writing-mode: tb-rl;
        text-align: center;
        line-height: 123px;
        float: left;
        border-bottom: 1px solid #e5e9f0;
      }

      .item-type-list {
        width: calc(100% - 123px);
        height: 400px;
        float: left;
        .table-item-list {
          list-style: none;
          padding: 0;
          margin: 0;
          li {
            //background: #f5f7fa;
            margin-top: 0px;
            border-top: 1px solid #e5e9f0;
            border-left: 1px solid #e5e9f0;
            box-sizing: border-box;
            list-style: none;
            height: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
          }
          .item-name {
            display: block;
            width: 350px;
            padding-left: 22px;
          }

        }
      }
    }
  }

  .table-right {
    width: calc(100% - 350px);
    border-bottom: 1px solid #e5e9f0;
    border-right: 1px solid #e5e9f0;
    box-sizing: border-box;
    float: right;

    .option-list {
      list-style: none;
      padding: 0;
      margin: 0;
      text-align: center;
      li {
        height: 60px;
        border-top: 0;
        margin-top: 0px;
        list-style: none;
        display: flex;
        justify-content: space-between;
        align-items: center;
        box-sizing: border-box;

        .table-con {
          display: flex;
          width: 100%;;
          justify-content: space-around;

          label {
            width: 34%;
            font-size: 16px;
            font-weight: 500;
            color: #363b52;
            line-height: 60px;
            background: #e6eaf2;
            border-radius: 8px 8px 0 0;
          }
        }
      }
    }

    .table-content-list{
      list-style: none;
      padding: 0;
      margin: 0;
      li {
        list-style: none;
        height: 50px;
        margin-top: 0px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: 1px solid #e5e9f0;
        box-sizing: border-box;
        .table-con {
          display: flex;
          width: 100%;
          justify-content: space-around;
          label {
            display: block;
            width: 34%;
            text-align: center;
            img {
              width: 20px;
              height: 20px;
            }
          }
        }
      }
    }
  }

  .tip svg{
    display: inline-block;
    vertical-align: -.125em;
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