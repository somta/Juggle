import { viteBundler } from '@vuepress/bundler-vite'
import { defineUserConfig } from 'vuepress'
import { plumeTheme } from 'vuepress-theme-plume'
import {zhNavbar} from "./navbar";
import {zhNotes} from "./notes";

export default defineUserConfig({
    lang: 'zh-CN',
    title: 'Juggle',
    description: '以终为始，方得始终',
    head:[
        ['link', { rel: 'icon', href: '/images/favicon.ico' }],
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