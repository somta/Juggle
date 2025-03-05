<script setup lang="ts">

import {ref} from "vue";

const selectedPriceTab = ref<string>("yearly");

// 定义一个函数用于处理点击事件，并设置selectedPriceTab的值为当前点击的价格选项ID
function showServiceDetail(tabId: string) {
  selectedPriceTab.value = tabId;
}

</script>

<template>
    <div class="vp-price">

      <!-- 价格分类 -->
      <div class="price_tab-box">

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

         <!-- 按年订阅 -->
        <div v-if="selectedPriceTab === 'yearly'" class="service-detail-item">
            <div class="service-detail-div">
              <div class="service-detail-header">
                <h4>按年订阅</h4>
                <p class="detail-desc">私有化部署，终身免费升级</p>
                <button type="button" class="contact-us">
                  <span>联系我们</span>
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
                <span class="content-title">系统文档</span><span class="content-desc">官网使用手册</span>
              </div>
            </div>
        </div>

        <!-- 终生授权 -->
        <div v-else-if="selectedPriceTab === 'lifetime'" class="service-detail-item">
            <div class="service-detail-div">
              <div class="service-detail-header">
                <h4>终生授权</h4>
                <p class="detail-desc">私有化部署，终身免费升级</p>
                <button type="button" class="contact-us">
                  <span>联系我们</span>
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
                <h4>源代码</h4>
                <p class="detail-desc">私有化部署，终身免费升级</p>
                <button type="button" class="contact-us">
                  <span>联系我们</span>
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
      <div class="maintain_maintain-container__5nbIS">
        <div class="maintain_tabs-maintain-product__5Ya7c">
          <div class="maintain_maintain-title___XvfV">
            <h3>维保服务订阅</h3>
          </div>
        </div>
        <div class="table-info">
          <div class="table-left">
            <div class="maintain_gong-dan__2riB2">
              <div class="maintain_header__XdF9r">服务项目</div>
              <div class="maintain_total__9MRKX">工单系统</div>
              <div class="maintain_list__lT_Gz">
                <ul class="maintain_maintain-list__T1mQP">
                  <li><span class="maintain_title-name__K5wf2">公共交流群支持</span></li>
                  <li><span class="maintain_title-name__K5wf2">远程故障排除协助</span></li>
                  <li><span class="maintain_title-name__K5wf2">技术支持专群</span></li>
                  <li><span class="maintain_title-name__K5wf2">专属客户经理</span></li>
                  <li><span class="maintain_title-name__K5wf2">服务支持时间</span></li>
                  <li><span class="maintain_title-name__K5wf2">服务响应时间</span></li>
                  <li><span class="maintain_title-name__K5wf2">紧急响应支持</span></li>
                  <li><span class="maintain_title-name__K5wf2">紧急响应时间</span></li>
                </ul>
              </div>
            </div>
            <div class="maintain_gong-dan__2riB2">
              <div class="maintain_total__9MRKX" style="height: 400px">增值服务</div>
              <div class="maintain_list__lT_Gz">
                <ul class="maintain_maintain-list__T1mQP">
                  <li><span class="maintain_title-name__K5wf2">线上专题培训</span></li>
                  <li><span class="maintain_title-name__K5wf2">原厂协助升级</span></li>
                  <li><span class="maintain_title-name__K5wf2">远程迁移服务</span></li>
                  <li><span class="maintain_title-name__K5wf2">业务问题处理指导</span></li>
                  <li><span class="maintain_title-name__K5wf2">远程技术支持</span></li>
                  <li><span class="maintain_title-name__K5wf2">最新API文档讲解</span>
                  </li>
                  <li><span class="maintain_title-name__K5wf2">二开Demo教学讲解</span>
                  </li>
                  <li><span class="maintain_title-name__K5wf2">权限系统整合咨询</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="table-right">
            <ul class="maintain_maintain-title___XvfV">
              <li>
                <span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk">
                  <label class="maintain_shequ__aw7tz">基础维保</label>
                  <label class="maintain_jichu__QJaIV">专业维保</label>
                  <label class="maintain_huangjin__zplGe">黄金服务包</label>
                </span>
              </li>
            </ul>
            <ul class="maintain_maintain-list__T1mQP">

              <li>
                <span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk">
                  <label>
                    <img alt="" src="/images/choose.png" width="40" height="40" decoding="async" class="maintain_choose__IScUV" loading="lazy" style="color:transparent">
                  </label>
                  <label>
                  <img alt="" src="/images/choose.png" width="40" height="40" decoding="async" class="maintain_choose__IScUV" loading="lazy" style="color:transparent">
                  </label>
                  <label>
                    <img alt="" src="/images/choose.png" width="40" height="40" decoding="async" class="maintain_choose__IScUV" loading="lazy" style="color:transparent">
                  </label>
                </span>
              </li>

              <li>
                <span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label><img
                  alt=""
                  src="/images/choose.png"
                  width="40" height="40" decoding="async" data-nimg="1" class="maintain_choose__IScUV" loading="lazy"
                  style="color:transparent"></label><label><img alt=""
                                                                src="/images/choose.png"
                                                                width="40" height="40" decoding="async" data-nimg="1"
                                                                class="maintain_choose__IScUV" loading="lazy"
                                                                style="color:transparent"></label></span></li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label><img
                  alt=""
                  src="/images/choose.png"
                  width="40" height="40" decoding="async" data-nimg="1" class="maintain_choose__IScUV" loading="lazy"
                  style="color:transparent"></label><label><img alt=""
                                                                src="/images/choose.png"
                                                                width="40" height="40" decoding="async" data-nimg="1"
                                                                class="maintain_choose__IScUV" loading="lazy"
                                                                style="color:transparent"></label></span></li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>- -</label><label><img
                  alt=""
                  src="/images/choose.png"
                  width="40" height="40" decoding="async" data-nimg="1" class="maintain_choose__IScUV" loading="lazy"
                  style="color:transparent"></label></span></li>
              <li><span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>不定时</label><label>5 × 8</label><label>7 × 8</label></span>
              </li>
              <li><span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>不定时</label><label>6小时</label><label>4小时</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>3次</label><label>12次</label></span>
              </li>
              <li><span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>90分钟</label><label>90分钟</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>1次</label><label>3次</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>1次</label><label>3次</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>1次</label><label>3次</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>支持</label><label>支持</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>- -</label><label>1人天</label></span>
              </li>
              <li><span
                  class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk"><label>- -</label><label>- -</label><label>1次</label></span>
              </li>
              <li>
                <span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1次</label>
                </span>
              </li>
              <li>
                <span class="maintain_maintain-con__oCJAa maintain_maintain-con-2__AQxEk">
                  <label>- -</label>
                  <label>- -</label>
                  <label>1次</label>
                </span>
              </li>
            </ul>
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
  width: 800px;
  margin: 50px auto auto;
}

.price-tab {
  width: 32%;
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

.maintain_maintain-container__5nbIS {
  width: 100%;
  max-width: 1220px;
  margin: auto;

  .maintain_tabs-maintain-product__5Ya7c {
    margin: 70px auto 26px;
    text-align: center;
  }

  .table-info {
    box-sizing: border-box;
    border-radius: 8px 8px 0 0;

    .table-left {
      width: 350px;
      float: left;
      border-bottom: 1px solid #e5e9f0;
      border-right: 1px solid #e5e9f0;
      box-sizing: border-box;

      .maintain_gong-dan__2riB2 {
        background: #f5f7fa;
        float: left;

        .maintain_header__XdF9r {
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

        .maintain_total__9MRKX {
          width: 123px;
          height: 400px;
          writing-mode: tb-rl;
          text-align: center;
          line-height: 123px;
          float: left;
          border-bottom: 1px solid #e5e9f0;
        }

        .maintain_list__lT_Gz {
          width: calc(100% - 123px);
          height: 400px;
          float: left;
          .maintain_maintain-list__T1mQP {
            list-style: none;
            padding: 0;
            margin: 0;
            li {
              background: #f5f7fa;
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
            .maintain_title-name__K5wf2 {
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
      box-sizing: border-box;
      float: right;

      .maintain_maintain-title___XvfV {
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

          .maintain_maintain-con__oCJAa {
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

      .maintain_maintain-list__T1mQP{
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
          .maintain_maintain-con__oCJAa {
            display: flex;
            width: 100%;
            justify-content: space-around;
            label {
              display: block;
              width: 34%;
              text-align: center;
              .maintain_choose__IScUV {
                width: 20px;
                height: 20px;
              }
            }
          }
        }
      }
    }

  }

}

</style>