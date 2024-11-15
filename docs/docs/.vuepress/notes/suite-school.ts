import { defineNoteConfig } from 'vuepress-theme-plume'

export const suiteSchool = defineNoteConfig({
    dir: 'suite',
    link: '/suite/',
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
            items: ['qq-email','caiyun-weather'],
        }
    ],
})