<script setup lang="ts">
const diamonds = new Array(36).fill(1).map((_, index) => {
  const size = 30 + 4 * index;
  const top = (1.15 - random()) * window.innerHeight;
  const left = Math.random() * window.innerWidth;
  return {
    index,
    style: {
      width: size + 'px',
      height: size + 'px',
      top: top + 'px',
      left: left + 'px',
      background: randomColor(),
    },
  };
});
function random() {
  return Math.random() * Math.random();
}
function randomColor () {
  // R在128~255之间
  const r = Math.floor(Math.random() * 128) + 128;
  const g = Math.floor(Math.random() * 128) + 128;
  const b = Math.floor(Math.random() * 128) + 128;

  // 将RGB分量转换成16进制表示的字符串
  const hexR = r.toString(16).padStart(2, '0');
  const hexG = g.toString(16).padStart(2, '0');
  const hexB = b.toString(16).padStart(2, '0');

  // 拼接成完整的颜色代码（加上井号#开头）
  var colorCode = '#' + hexR + hexG + hexB;
  return colorCode;
}
</script>
<template>
  <div class="entry-bg">
    <div class="diamond" v-for="item in diamonds" :style="item.style" :key="item.index"></div>
  </div>
</template>
<style lang="less" scoped>
.entry-bg {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  overflow: hidden;
  pointer-events: none;
  background: #f0f2f5;
  .diamond {
    position: absolute;
    background: #fff;
    transform: rotate(45deg);
    box-shadow: var(--el-box-shadow-dark);
  }
}
</style>
