// @ts-ignore
import { defineClientConfig } from 'vuepress/client'
// @ts-ignore
import VPHomeIntroduce from "../../src/components/VPHomeIntroduce.vue";
import VPTitle from "../../src/components/VPTitle.vue";

import './styles/index.css'
import './styles/custom.css'


export default defineClientConfig({
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