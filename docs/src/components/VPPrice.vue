<script setup lang="ts">

import {ref} from "vue";

const selectedPriceTab = ref<string>("yearly");

// 定义一个函数用于处理点击事件，并设置selectedPriceTab的值为当前点击的价格选项ID
function showServiceDetail(tabId: string) {
  if (selectedPriceTab.value === tabId) {
    // 如果点击的是已选中的选项，则取消选择
    selectedPriceTab.value = null;
  } else {
    selectedPriceTab.value = tabId;
  }
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


              <div class="service-content-item" style="justify-content: flex-start; padding-top: 12px;">
                <span class="content-title">版本选择</span>
                <span class="content-desc">尊享版</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;">
                <span class="content-title">授权时长</span><span class="content-desc">永久</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">前端源码</span><span class="content-desc">监控系统</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">多买优惠</span><span class="content-desc">无</span></div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">项目数量</span><span class="content-desc">50 个</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;">
                <span class="content-title">数 据 库</span><span class="content-desc">&nbsp;Mysql + Clickhouse</span>
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



              <div class="service-content-item" style="justify-content: flex-start; padding-top: 12px;">
                <span class="content-title">版本选择</span>
                <span class="content-desc">单机版</span>
                <span class="content-desc ">尊享版</span><span
                  class="content-desc ">至尊VIP</span>
              </div>

              <div class="service-content-item">
                <span class="content-title">授权时长</span><span class="content-desc">永久</span>
              </div>


              <div class="service-content-item"><span
                  class="content-title">产品选择</span><span class="content-desc">
                <div class="ant-checkbox-group"><label
                    class="ant-checkbox-wrapper ant-checkbox-wrapper-checked ant-checkbox-group-item"><span
                    class="ant-checkbox ant-checkbox-checked"><input type="checkbox" class="ant-checkbox-input"
                                                                     value="monitor" checked=""><span
                    class="ant-checkbox-inner"></span></span><span>监控系统</span></label><label
                    class="ant-checkbox-wrapper ant-checkbox-group-item"><span class="ant-checkbox"><input type="checkbox"
                                                                                                           class="ant-checkbox-input"
                                                                                                           value="event"><span
                    class="ant-checkbox-inner"></span></span><span>埋点系统</span></label></div></span></div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">前端源码</span><span class="content-desc">监控系统</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">多买优惠</span><span class="content-desc">无</span></div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">项目数量</span><span class="content-desc">50 个</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;">
                <span class="content-title">数 据 库</span><span class="content-desc">&nbsp;Mysql + Clickhouse</span>
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

              <div class="service-content-item" style="justify-content: flex-start; padding-top: 12px;">
                <span class="content-title">版本选择</span>
                <span class="content-desc">单机版</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">授权时长</span><span class="content-desc">永久</span>
              </div>
              <div class="service-content-item"><span
                  class="content-title">产品选择</span><span class="content-desc">
                <div class="ant-checkbox-group"><label
                    class="ant-checkbox-wrapper ant-checkbox-wrapper-checked ant-checkbox-group-item"><span
                    class="ant-checkbox ant-checkbox-checked"><input type="checkbox" class="ant-checkbox-input"
                                                                     value="monitor" checked=""><span
                    class="ant-checkbox-inner"></span></span><span>监控系统</span></label><label
                    class="ant-checkbox-wrapper ant-checkbox-group-item"><span class="ant-checkbox"><input type="checkbox"
                                                                                                           class="ant-checkbox-input"
                                                                                                           value="event"><span
                    class="ant-checkbox-inner"></span></span><span>埋点系统</span></label></div></span></div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">前端源码</span><span class="content-desc">监控系统</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">多买优惠</span><span class="content-desc">无</span></div>
              <div class="service-content-item" style="justify-content: flex-start;"><span
                  class="content-title">项目数量</span><span class="content-desc">50 个</span>
              </div>
              <div class="service-content-item" style="justify-content: flex-start;">
                <span class="content-title">数 据 库</span><span class="content-desc">&nbsp;Mysql + Clickhouse</span>
              </div>
            </div>
        </div>
      </div>

      <!-- 版本功能对比 -->
      <div class="productDiff_product-diff-container__vt6BV">
        <div class="productDiff_tabs-maintain-product__HFYwK">
            <div class="productDiff_maintain-title__xZvBp">
<!--              <img alt="" src="/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fleft.16d25880.png&amp;w=640&amp;q=75" width="240" height="28" decoding="async" data-nimg="1" loading="lazy" style="color: transparent;">-->
              <h3>
<!--                <img alt="" src="/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fproduct-icon.43ba1193.png&amp;w=256&amp;q=75" width="80" height="80" decoding="async" data-nimg="1" loading="lazy" style="color: transparent;">-->
                产品功能对比
              </h3>
<!--              <img alt="" src="/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fleft.16d25880.png&amp;w=640&amp;q=75" width="240" height="28" decoding="async" data-nimg="1" loading="lazy" style="color: transparent;">-->
            </div>
        </div>
        <div class="productDiff_maintain-table__rSMUB">
          <ul class="productDiff_maintain-title__xZvBp">
            <li><span class="productDiff_title-name__md8Xm">版本对比项</span>
              <span class="productDiff_maintain-con__kaXk7">
                <label>开源版本</label>
                <label style="color: rgb(255, 118, 38);" >企业版本</label>
              </span>
            </li>
          </ul>

          <ul class="productDiff_maintain-list__G6ijE">
            <li>
              <span class="productDiff_title-name__md8Xm">产品终身升级</span>
              <span class="productDiff_maintain-con__kaXk7">
                <label>
                  <img alt="" src="/images/choose.png" decoding="async" class="productDiff_choose__QKqo7" loading="lazy" style="color: transparent;">
                </label>
                <label>
                  <img alt="" src="/images/choose.png" decoding="async" class="productDiff_choose__QKqo7" loading="lazy" style="color: transparent;">
                </label>
              </span>
            </li>

            <li>
              <span class="productDiff_title-name__md8Xm">支持日活量</span>
              <span class="productDiff_maintain-con__kaXk7">
                <label>百万级</label>
                <label>亿级</label>
              </span>
            </li>
            <li>
              <span class="productDiff_title-name__md8Xm">功能更完善
                <span class="tip anticon anticon-question-circle productDiff_question__ZXLch">
                  <svg viewBox="64 64 896 896" focusable="false" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg>
                </span>
              </span>
              <span class="productDiff_maintain-con__kaXk7">
                <label>- -</label>
                <label>
                  <img alt="" src="/images/choose.png" width="40" height="40" decoding="async" data-nimg="1" class="productDiff_choose__QKqo7"loading="lazy" style="color: transparent;"></label>
              </span>
            </li>

          </ul>
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
  width: 1540px;
  margin: auto;
  text-align: center;

  .service-detail-item {
    width: 400px;
    margin: auto;
    justify-content: space-between;
    .service-detail-div {
      width: 368px;
      height: 742px;
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
        }
      }
    }
  }
}

.productDiff_product-diff-container__vt6BV {
  width: 100%;
  max-width: 1220px;
  margin: auto;

  .productDiff_tabs-maintain-product__HFYwK {
    margin: 70px auto 26px;
    text-align: center;
  }

  .productDiff_maintain-table__rSMUB {
    border-left: 1px solid #e5e9f0;
    border-bottom: 1px solid #e5e9f0;
    border-right: 1px solid #e5e9f0;
    box-sizing: border-box;
    border-radius: 8px 8px 0 0;

    .productDiff_maintain-title__xZvBp {
      list-style: none;
      padding: 0;
      margin: 0;
      background-color: #fff;
      li {
        list-style: none;
        height: 60px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: 0;
        box-sizing: border-box;
      }
      .productDiff_title-name__md8Xm {
        display: block;
        width: 350px;
        padding-left: 22px;
        height: 60px;
        background: #e6eaf2;
        border-radius: 8px 8px 0 0;
        font-size: 16px;
        font-weight: 500;
        color: #363b52;
        line-height: 60px;

        .tip{
          font-size: 16px;
          margin-left: 6px;
          color: #828594;
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
      .productDiff_maintain-con__kaXk7 {
        display: flex;
        width: calc(100% - 350px);
        justify-content: space-around;

        label {
          display: block;
          width: 50%;
          text-align: center;
          font-size: 16px;
          font-weight: 500;
          color: #363b52;
          line-height: 60px;
          background: #e6eaf2;
          border-radius: 8px 8px 0 0;
        }

        .productDiff_choose__QKqo7 {
          width: 20px;
          height: 20px;
        }
      }
    }


    .productDiff_maintain-list__G6ijE {
      li {
        list-style: none;
        height: 50px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: 1px solid #e5e9f0;
        box-sizing: border-box;
      }
      .productDiff_title-name__md8Xm {
        display: block;
        width: 350px;
        padding-left: 22px;
      }
      .productDiff_maintain-con__kaXk7 {
        display: flex;
        width: calc(100% - 350px);
        justify-content: space-around;

        label {
          display: block;
          width: 50%;
          text-align: center;
        }
      }
    }
  }

}

</style>