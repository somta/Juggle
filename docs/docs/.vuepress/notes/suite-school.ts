import {ThemeDocCollection} from "vuepress-theme-plume";

export const suiteSchool = {
    type: 'doc',
    dir: 'suite',
    title: '套件学堂',
    sidebar: [
        {
            text: '内置套件',
            collapsed: false,
            prefix: 'internal',
            items: "auto",
        },
        {
            text: '第三方套件',
            collapsed: false,
            prefix: 'third',
            items: [
                'qq-email',
                'caiyun-weather',
                'siliconflow',
                'lanyun'
            ],
        }
    ],
} as ThemeDocCollection