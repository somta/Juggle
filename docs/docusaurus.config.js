const { externalLinkProcessor } = require('./tools/utils/externalLink');

module.exports = {
    title: 'Juggle',
    tagline: '一个可用于接口编排，第三方系统集成，定制开发等场景的一套完整解决方案',
    url: 'https://www.juggle.plus',
    baseUrl: '/',
    organizationName: 'somta',
    projectName: 'juggle-doc',
    scripts: ['/js/custom.js', '/js/baidu.js'],
    favicon: 'img/favicon.ico',
    i18n: {
        defaultLocale: 'zh-Hans',
        locales: ['zh-Hans'],
    },
    customFields: {
        markdownOptions: {
            html: true,
        },
        gaGtag: true,
        repoUrl: 'https://github.com/somta/Juggle',
    },
    onBrokenLinks: 'throw',
    onBrokenMarkdownLinks: 'throw',
    presets: ([
        [
            '@docusaurus/preset-classic',
            ({
                debug: true,
                docs: {
                    path: './docs',
                    showLastUpdateAuthor: true,
                    showLastUpdateTime: true,
                    sidebarPath: './sidebars.js',
                    rehypePlugins: [externalLinkProcessor],
                },
                blog: {
                    blogSidebarTitle: '最新文章',
                    showReadingTime: true,
                },
                theme: {
                    customCss: 'src/css/custom.css',
                },
            }),
        ],
    ]),
    plugins: [

    ],
    themeConfig:({
            docs: {
                versionPersistence: 'localStorage',
                sidebar: {
                    hideable: true,
                },
            },
            tableOfContents: {
                minHeadingLevel: 2,
                maxHeadingLevel: 5
            },
            navbar: {
                title: 'Juggle',
                logo: {
                    src: 'img/juggle-banner-light.png',
                    srcDark: 'img/juggle-banner-dark.png',
                },
                items: [
                    {
                        to: '/',
                        label: '首页',
                        position: 'left',
                    },
                    {
                        type: 'docsVersion',
                        to: 'docs/guide/start/quick-start',
                        label: '文档',
                        position: 'left',
                    },
                    {
                        to: '/blog',
                        label: '博客',
                        position: 'left'
                    },
                    {
                        to: 'docs/changelog',
                        label: '更新日志',
                        activeBasePath: '/docs/changelog',
                        position: 'left',
                        className: 'changelog',
                    },
                    {
                        to: 'docs/faq',
                        activeBasePath: '/docs/faq',
                        label: 'FAQ',
                        position: 'left',
                        className: 'changelog',
                    },
                    {
                        href: 'https://github.com/somta/Juggle',
                        label: 'GitHub',
                        title: 'View on GitHub',
                        position: 'right',
                        className: 'icon',
                    }
                ],
            },
            colorMode: {
                defaultMode: 'dark',
                disableSwitch: false,
                respectPrefersColorScheme: true,
            },
            prism: {
                defaultLanguage: 'typescript',
                theme: require('prism-react-renderer/themes/github'),
                darkTheme: require('prism-react-renderer/themes/dracula'),
                additionalLanguages: ['docker', 'log'],
            },
            metadata: [
                {name: 'keywords', content: '微服务编排, 流程引擎, 接口编排, 低代码, 集成平台, 定制开发'},
                {name: 'description', content: 'Juggle一个可用于微服务接口编排,定制开发,第三方系统集成等场景的一套完整解决方案,支持零码编排流程，复杂场景可以通过低码开发进行拓展，支持多种方式集成第三方系统，打破单一系统孤岛。'},
            ],
            footer: {
                links: [
                    {
                        title: '文档指南',
                        items: [
                            {
                                label: '文档',
                                to: 'docs/guide/start/quick-start',
                            },
                            {
                                label: '更新日志',
                                to: 'docs/changelog',
                            },
                        ],
                    }, {
                        title: '社区',
                        items: [
                            {
                                label: 'Bilibili',
                                href: 'https://space.bilibili.com/100381077',
                            },
                            {
                                label: '知乎',
                                href: 'https://www.zhihu.com/people/songhu-83',
                            }
                        ],
                    },
                    {
                        title: '关注公众号',
                        items: [
                            {
                                html: '<img src="/images/website/gzh_qrcode.jpg" width="150"/>'
                            }
                        ],
                    },
                    {
                        title: '更多',
                        items: [
                            {
                                label: 'Docusaurus',
                                href: 'https://docusaurus.io',
                            },
                            {
                                label: 'GitHub',
                                href: 'https://github.com/somta/Juggle',
                            },
                        ],
                    }
                ],
                copyright: `GPL 3.0 | Copyright © 2018-${new Date().getFullYear()}-明天的地平线 `
            },
            algolia: {
                appId: 'DADHMSXO5L',
                apiKey: '64fce270bcef566f454360776f0e7bec', // search only (public) API key
                indexName: 'juggle-index',
                algoliaOptions: {
                    facetFilters: ['version:VERSION'],
                },
            },
        }),
};
