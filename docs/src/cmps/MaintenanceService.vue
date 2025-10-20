<script setup>

</script>

<template>
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
                  <label style="background: #d7e3f8;">基础服务包 <span title="我们将通过公共交流群、社区平台等方式为用户提供维保服务，响应时间不定。" class="tip"><svg viewBox="64 64 896 896" focusable="false" data-icon="question-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"></path><path d="M623.6 316.7C593.6 290.4 554 276 512 276s-81.6 14.5-111.6 40.7C369.2 344 352 380.7 352 420v7.6c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8V420c0-44.1 43.1-80 96-80s96 35.9 96 80c0 31.1-22 59.6-56.1 72.7-21.2 8.1-39.2 22.3-52.1 40.9-13.1 19-19.9 41.8-19.9 64.9V620c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-22.7a48.3 48.3 0 0130.9-44.8c59-22.7 97.1-74.7 97.1-132.5.1-39.3-17.1-76-48.3-103.3zM472 732a40 40 0 1080 0 40 40 0 10-80 0z"></path></svg></span></label>
                  <label style="background: linear-gradient(181deg, #fcf0e6, #f6caa6);">专业服务包</label>
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
</template>

<style scoped>
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