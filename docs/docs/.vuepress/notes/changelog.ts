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
            items: [
                '2024-03-22-juggle-1.0.0-issue',
                '2024-4-06-juggle-1.1.0-issue',
                '2024-04-24-juggle-1.1.1-issue',
                '2024-05-30-juggle-1.2.0-issue',
                '2024-07-30-juggle-1.2.1-issue',
                '2024-09-27-juggle-1.2.2-issue',
                '2024-11-27-juggle-1.2.3-issue',
                '2025-1-27-juggle-1.3.0-issue',
                '2025-4-27-juggle-1.3.1-issue',
                '2025-7-19-juggle-1.3.2-issue',
                '2025-9-27-juggle-1.4.0-issue',
                '2025-12-27-juggle-1.5.0-issue',
                '2026-03-27-juggle-1.6.0-issue',
            ],
        }
    ],
} as ThemeDocCollection