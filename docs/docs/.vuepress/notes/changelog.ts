import {ThemeDocCollection} from "vuepress-theme-plume";

export const changeLog = {
    type: 'doc',
    dir: 'changelog',
    title: '更新日志',
    sidebar: [
        {
            text: '1.x版本',
            collapsed: false,
            prefix: '1.x',
            items: "auto",
        }
    ],
} as ThemeDocCollection