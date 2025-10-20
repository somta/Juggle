<script setup>

</script>

<template>
  <div class="product-version-diff">
    <div class="product-version-diff-title">
      <h3>版本功能对比</h3>
    </div>
    <div class="table-info">
      <div class="table-left">
        <div class="item-classify">
          <div class="table-left-header">模块</div>
          <div class="item-type" style="height: 650px">流程</div>
          <div class="item-type-list">
            <ul class="table-item-list">
              <li><span class="item-name">流程定义管理</span></li>
              <li><span class="item-name">支持的流程类型</span></li>
              <li><span class="item-name">流程设计</span></li>
              <li><span class="item-name">流程调试</span></li>
              <li><span class="item-name">多版本流程</span></li>
              <li><span class="item-name">流程检测 <span title="支持自动检测流程，并一键修复流程设计中的问题" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span></li>
              <li><span class="item-name">流程历史版本</span></li>
              <li><span class="item-name">节点数据Mock</span></li>
              <li><span class="item-name">流程导入导出</span></li>
              <li><span class="item-name">流程日志</span></li>
              <li><span class="item-name">企业流程模板</span></li>
              <li><span class="item-name">流程令牌</span></li>
              <li><span class="item-name">多语言SDK集成</span></li>
            </ul>
          </div>
        </div>
        <div class="item-classify">
          <div class="item-type" style="height: 400px">流程节点</div>
          <div class="item-type-list" style="height: 400px">
            <ul class="table-item-list">
              <li><span class="item-name">方法节点</span></li>
              <li><span class="item-name">判断节点</span></li>
              <li><span class="item-name">赋值节点</span></li>
              <li><span class="item-name">代码节点</span></li>
              <li><span class="item-name">数据库节点</span></li>
              <li><span class="item-name">并行节点 <span title="支持添加多个接口，多线程执行调用，性能更快" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span></li>
              <li><span class="item-name">调用节点 <span title="支持通过该节点调用子流程，最大情况复用流程" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></span></li>
              <li><span class="item-name">缓存节点</span></li>
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
                  <label>Groovy/JavaScript</label>
                  <label>Groovy/JavaScript/Python/Java</label>
                </span>
          </li>
          <li>
                <span class="table-con">
                  <label>MySQL</label>
                  <label>MySQL/PostgreSQL/Oracle/Doris等近十款数据库</label>
                </span>
          </li>
          <li>
                <span class="table-con">
                  <label>--</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
          </li>
          <li>
                <span class="table-con">
                  <label>--</label>
                  <label><img alt="" src="/images/choose.png" decoding="async" loading="lazy"></label>
                </span>
          </li>
          <li>
                <span class="table-con">
                  <label>--</label>
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
</template>

<style scoped>
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
</style>