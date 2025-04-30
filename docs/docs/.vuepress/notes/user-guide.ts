import { defineNoteConfig } from 'vuepress-theme-plume'

export const userGuide = defineNoteConfig({
    dir: 'guide',
    link: '/docs/guide/',
    sidebar: [
        {
            text: '什么是Juggle？',
            collapsed: true,
            prefix: 'introduce',
            items: ['introduce-index','concept'],
        },
        {
            text: '快速开始',
            collapsed: false,
            prefix: 'start',
            items: ['quick-start','start-with-docker','start-with-kubernetes'],
        },
        {
            text: '使用手册',
            collapsed: true,
            prefix: 'user',
            items: [
                'suite',
                'api',
                'flow-definition',
                {
                    text: '流程设计',
                    collapsed: true,
                    prefix: 'design',
                    items: ['nodes','variable']
                },
                {
                    text: '节点',
                    collapsed: true,
                    prefix: 'nodes',
                    items: ['method-node','condition-node','assign-node','code-node','mysql-node']
                },
                'flow',
                'flow-version',
                'object',
                'data-type-info',
                'example-api',
                'example-flow',
            ],
        },
        {
            text: 'OpenApi手册',
            collapsed: true,
            prefix: 'integration',
            items: [
                'open-api',
                {
                    text: '系统集成',
                    collapsed: true,
                    items: ['integration','java-juggle','spring-boot-juggle']
                },
            ],
        },
        {
            text: '运维手册',
            collapsed: true,
            prefix: 'operation',
            items: [
                {
                    text: '部署手册',
                    collapsed: true,
                    items: ['deployment-overview','deployment-standalone','deployment-cluster']
                },
                'system-configurations'
            ],
        },
        {
            text: '开源共建',
            collapsed: true,
            prefix: 'open',
            items: [
                'contributing-flow',
                'pull-request',
                'reporting-bug'
            ],
        },
        {
            text: '社区',
            collapsed: true,
            prefix: 'community',
            items: [
                'get-help',
                'donate'
            ],
        },
    ],
})