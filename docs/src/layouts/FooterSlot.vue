<script setup>
</script>

<template #footer-content>
    <div class="custom-footer">
      <div class="footer_links">

        <div class="footer_col">
          <div class="footer_title">文档指南</div>
          <ul class="footer_items">
            <li class="footer_item"><a class="footer_link_item" href="https://juggle.plus/docs/guide/start/quick-start/">文档</a></li>
            <li class="footer_item"><a class="footer_link_item" href="https://juggle.plus/changelog/introduce.html">更新日志</a></li>
          </ul>
        </div>

        <div class="footer_col">
          <div class="footer_title">社区</div>
          <ul class="footer_items">
            <li class="footer_item">
              <a href="https://space.bilibili.com/100381077" target="_blank" rel="noopener noreferrer" class="footer_link_item">Bilibili</a>
            </li>
            <li class="footer_item">
              <a href="https://www.zhihu.com/people/songhu-83" target="_blank" rel="noopener noreferrer" class="footer_link_item">知乎</a>
            </li>
            <li class="footer_item">
              <a href="https://blog.csdn.net/husong_?spm=1010.2135.3001.5343" target="_blank" rel="noopener noreferrer" class="footer_link_item">CSDN</a>
            </li>
          </ul>
        </div>

        <div class="footer_col">
          <div class="footer_title">关注公众号</div>
          <ul class="footer_items">
            <li class="footer_item">
              <img src="/images/gzh_qrcode.jpg" width="150" alt="公众号">
            </li>
          </ul>
        </div>

        <div class="footer_col">
          <div class="footer_title">更多</div>
          <ul class="footer_items">
            <li class="footer_item">
              <a href="https://github.com/somta" target="_blank" rel="noopener noreferrer" class="footer_link_item">GitHub</a>
            </li>
            <li class="footer_item"><a href="https://gitee.com/Somta" target="_blank" rel="noopener noreferrer" class="footer_link_item">Gitee</a></li>
          </ul>
        </div>
      </div>

      <div class="footer_copyright">
        <a href="https://beian.miit.gov.cn/" target="_blank">粤ICP备18059311号-3</a> | Copyright © 2018-2024
      </div>

    </div>
</template>

<style>
.vp-footer{
  padding: 0px !important;
  margin-top: 16px;
}

.vp-footer a{
  color: var(--vp-c-text-1) !important;
}

.custom-footer{
  margin: 0 auto;
  padding: 36px;
}

.footer_links{
  display: flex;
  flex-wrap: wrap;
  margin: 0 calc(1rem * -1);
}

.footer_col{
  flex: 1 0;
  margin-left: 0;
  padding: 0px 1rem;
}

.footer_title{
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}


.footer_item{
}

.footer_link_item{
  color: #f2f3fb;
  line-height: 2;
  text-decoration:none !important;
}

.footer_copyright{
  text-align: center;
  margin-top: 20px;
}

.footer_copyright a{
  text-decoration:none !important;
}

@media (max-width: 768px) {
  .footer_links {
    flex-direction: column;
  }

  .footer_col {
    flex: unset;
    margin-left: 0;
    margin-bottom: 1rem;
  }
}
</style>