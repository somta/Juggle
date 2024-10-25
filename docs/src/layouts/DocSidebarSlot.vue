<script setup>
</script>

<template #sidebar-nav-before>
  <div class="sidebar_donate">
    <div class="sidebar_sponsor">
      <span>虚位以待</span>
<!--      <a target="_blank" href="https://juggle.plus">
        <img src="/images/sponsor/juggle.png" alt="">
      </a>-->
    </div>
    <div class="sidebar_sponsor_submit">
      <a href="/donate.html">成为赞助商</a>
    </div>
    <div>
    </div></div>
</template>

<style>
.sidebar_donate {
  text-align: center;
}
.sidebar_donate {
  text-align: center;
}

.sidebar_sponsor {
  height: 60px;
  /*border: 1px dashed gray;*/
  line-height: 60px;
}

.sidebar_sponsor:hover {
  cursor: pointer;
}

.sidebar_sponsor_submit {
  margin-top: 5px;
  border-bottom: 2px solid #878282;
}

.sidebar_sponsor_submit a {
  font-size: 16px;
  color: red;
  font-weight: bold;
}
</style>