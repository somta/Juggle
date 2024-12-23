---
pageLayout: home
backToTop: false
externalLink: false
config:
  -
    type: introduce
    desc: 大幅提高开发效率，为企业降本增效.
    actions:
      -
        theme: brand
        text: 快速开始
        link: https://juggle.plus/docs/guide/start/quick-start/
      -
        theme: alt
        text: 在线演示
        link: https://demo.juggle.plus/#/login
    imageLinks:
      -
        image: https://gitee.com/Somta/Juggle/badge/star.svg
        link: https://gitee.com/Somta/Juggle
      -
        image: https://img.shields.io/github/stars/somta/Juggle
        link: https://github.com/somta/Juggle
  -
    type: features
    features:
      -
        title: 特性
        icon: 📖
        details: 国内首个微服务编排框架，提供了丰富的功能特性，适配国内应用场景，开箱即用，非常方便
      -
        title: 性能
        icon: 🚀
        details: 渲染与执行为一体的极简设计，内存占用少，全协程设计，运行速度快
      -
        title: 安全
        icon: twemoji:locked-with-key
        details: 通过多个机构安全扫描验证，安全部署，放心使用
  -
    type: image-text
    title: 功能特性
    description: 强大的功能，一切皆可编排，满足用户需求场景。
    image: /images/function.svg
    list:
      -
        title: 多版本流程
        description: 流程多版本管理，天然支持流程灰度能力。
      -
        title: 丰富的数据类型
        description: 支持字符串，布尔，整数，小数，日期，时间，列表，对象等数据结构，满足绝大数数据定义场景。
      -
        title: 丰富的节点
        description: 内置方法节点，判断节点，代码节点，赋值节点，MySql节点等多种节点，能灵活设计流程。
      -
        title: 支持多种脚本
        description: 支持Groovy,JavaScript等多种脚本语言来增强流程。
      -
        title: 丰富的套件&模板
        description: 套件/模板市场拥有几十个常见系统的官方套件（如：通义千问，钉钉机器人，QQ邮箱，阿里云短信）和官方模板等，开箱即用，大大降低流程设计的复杂度
      -
        title: 全信创支持
        description: 全信创支持，支持MySql，达梦，TiDB，OceanBase等数据库
  -
    type: text-image
    title: 应用场景
    description: 适用于企业的多种业务场景，大幅提高开发效率，为企业降本增效。
    image: /images/scene.svg
    list:
      -
        title: 快速构建新产品
        description: 在已有基础服务的基础上，通过Juggle进行微服务接口编排快速搭建一个新产品。
      -
        title: 多系统互联
        description: 通过Juggle直接将企业内多个系统打通，无需任何代码开发，直接界面编排，小白也能干拉。
      -
        title: 替代传统BFF层
        description: 业界常使用Nodejs做一层适配对前端提供新的接口（简称：BFF层），可以通过Juggle的界面编排流程来替换BFF层。
      -
        title: 定制开发
        description: 产品私有化部署时，会与甲方进行各种接口对接，通过Juggle编排定制化接口，避免对标准代码的污染。
  -
    type: image-text
    title: 社区生态
    description: 丰富的生态，活跃的社区，为使用者保驾护航。
    image: /images/ecology.svg
    list:
      -
        title: 客户实践
        description: 在证券，银行，旅游等多个互联网行业公司落地，有丰富的落地经验。
      -
        title: 版本更新
        description: Juggle每1-2月会更新发布一个新的版本，持续更新有价值的能力。
      -
        title: 详细的文档
        description: Juggle提供了非常完善的使用手册，运维手册，部署手册等，上手门槛极低。
      -
        title: 丰富的视频讲解
        description: 在B站,微信视频号等平台，提供丰富的使用技巧和最佳实践视频，帮助用户更快上手。
  -
    type: custom
---
<script setup>
import Swiper from 'vuepress-theme-plume/features/Swiper.vue';
const customerList = [
  { link: '/customer/hstong.png',href:'https://www.hstong.com',alt: '华盛通'},
  { link: '/customer/pingankeji.png',href:'https://tech.pingan.com',alt: '平安科技'},
  { link: '/customer/swsc.png',href:'https://www.swsc.com.cn',alt: '西南证券'},
  { link: '/customer/xinyucores.png',href:'https://www.xinyucores.com',alt: '成都新昱科技有限公司'},
  { link: '/customer/scooper.png',href:'https://www.scooper.com.cn',alt: '杭州叙简科技股份有限公司'},
];
</script>


<Title content="客户案例"></Title>
<Swiper
:items="customerList"
mode="carousel"
:height="95"
:slides-per-view="3"
:space-between="20"
:speed="5500"
:pauseOnMouseEnter="true"
/>




