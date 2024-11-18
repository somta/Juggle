import {h} from "vue";
// @ts-ignore
import { defineClientConfig } from 'vuepress/client'
import {Layout} from "vuepress-theme-plume/client";
// @ts-ignore
import VPHomeIntroduce from "../../src/components/VPHomeIntroduce.vue";
import VPTitle from "../../src/components/VPTitle.vue";
import DocSidebarSlot from "../../src/layouts/DocSidebarSlot.vue";
import FooterSlot from "../../src/layouts/FooterSlot.vue";

import './styles/index.css'
import './styles/custom.css'

export default defineClientConfig({
    layouts:{
        Layout: () => h(Layout, null, {
            'sidebar-nav-before': () => h(DocSidebarSlot),
            'footer-content': () => h(FooterSlot),
        })
    },
    enhance({ app,router  }) {
        app.component('introduce', VPHomeIntroduce)
        app.component('Title', VPTitle)

        router.beforeEach((to, from, next) => {
            console.log("切换路由", to.fullPath, from.fullPath);

            //触发百度的pv统计
            if (typeof _hmt != "undefined") {
                if (to.path) {
                    _hmt.push(["_trackPageview", to.fullPath]);
                    console.log("上报百度统计", to.fullPath);
                }
            }
            // continue
            next();
        });
    },
})