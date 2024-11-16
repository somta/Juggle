import { defineNoteConfig } from 'vuepress-theme-plume'

export const changeLog = defineNoteConfig({
    dir: 'changelog',
    link: '/changelog/',
    sidebar: [
        'changelog-index',
        {
            text: '1.x版本',
            collapsed: false,
            prefix: '1.x',
            items: "auto",
        }
    ],
})