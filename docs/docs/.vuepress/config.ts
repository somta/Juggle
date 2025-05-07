import { viteBundler } from '@vuepress/bundler-vite'
import { defineUserConfig } from 'vuepress'
import { plumeTheme } from 'vuepress-theme-plume'
import {zhNavbar} from "./navbar";
import {zhNotes} from "./notes";

export default defineUserConfig({
    lang: 'zh-CN',
    title: 'Juggle',
    description: 'juggle是一个可用于微服务接口编排，BFF层，第三方系统对接，私有化定制等场景的完整解决方案',
    head:[
        ['link', { rel: 'icon', href: '/images/favicon.ico' }],
        ['meta', { name: 'keywords', content: 'juggle,微服务接口编排,bff,定制化开发,流程编排,连接器,低代码,AI,零代码'}],
        [
            "script",
            {},
            `
              var _hmt = _hmt || [];
              (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?689d52441d8a61e2d50c86dd41503cca";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
              })();
            `
        ]
    ],
    bundler: viteBundler(),
    // 默认主题配置
    theme: plumeTheme({
        logo: '/images/logo.png',
        hostname: 'https://juggle.plus',
        // 导航栏配置
        navbar: zhNavbar,
        social: [
            { icon: 'github', link: 'https://github.com/somta' }
        ],
        notes: zhNotes,
        footer: { copyright: '<a href="https://beian.miit.gov.cn/" target="_blank">粤ICP备18059311号-3</a> | Copyright © 2018-present' },
    }),
})