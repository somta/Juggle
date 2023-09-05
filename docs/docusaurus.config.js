/* eslint-disable global-require,import/no-extraneous-dependencies */
const { externalLinkProcessor } = require('./tools/utils/externalLink');


/** @type {Partial<import('@docusaurus/types').DocusaurusConfig>} */
module.exports = {
    title: 'Knife4j',
    tagline: '集Swagger2及OpenAPI3为一体的增强解决方案.',
    url: 'https://doc.xiaominfo.com',
    baseUrl: '/',
    trailingSlash: false,
    organizationName: 'xiaoymin',
    projectName: 'swagger-bootstrap-ui',
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
        repoUrl: 'https://gitee.com/xiaoym/knife4j',
    },
    onBrokenLinks:
    /** @type {import('@docusaurus/types').ReportingSeverity} */ ('throw'),
    onBrokenMarkdownLinks:
    /** @type {import('@docusaurus/types').ReportingSeverity} */ ('throw'),
    presets: /** @type {import('@docusaurus/types').PresetConfig[]} */ ([
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
                theme: {
                    customCss: 'src/css/custom.css',
                },
            }),
        ],
    ]),
    plugins: [
        [
            'docusaurus-gtm-plugin',
            {
                id: 'GTM-TKBX678',
            },
        ],
    ],
    themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */ ({
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
                hideOnScroll: true,
                title: 'Juggle',
                logo: {
                    src: 'img/knife4j-light.svg',
                    srcDark: 'img/knife4j-dark.svg',
                },
                items: [
                    {
                        to: '/',
                        label: '首页',
                        position: 'left',
                    },
                    {
                        type: 'docsVersion',
                        to: 'docs/guide/quick-start',
                        label: '文档',
                        position: 'left',
                    },
                    {
                        to: 'docs/blog',
                        activeBasePath: '/docs/blog',
                        label: '博客',
                        position: 'left',
                        className: 'changelog',
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
                        href: 'https://github.com/xiaoymin/swagger-bootstrap-ui',
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
            metadata: [],
            footer: {
                links: [
                    {
                        title: '文档指南',
                        items: [
                            {
                                label: '文档',
                                to: 'docs/guide/quick-start',
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
                                label: 'Gitter',
                                href: 'https://gitter.im/knife4j/knife4j',
                            },
                            {
                                label: '开源中国',
                                href: 'https://www.oschina.net/question/tag/swagger-bootstrap-ui',
                            }
                        ],
                    },
                    {
                        title: '关注公众号',
                        items: [
                            {
                                html: '<img src="/images/website/qrcode.jpg" width="150"/>'
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
                copyright: `Apache License 2.0 | Copyright © 2018-${new Date().getFullYear()}-明天的地平线 `
            },
        //todo 这里要换成自己的
            algolia: {
                appId: '3CRIMRK623',
                apiKey: '69d77aed70b67e21c81c6b4a38d0cf1a', // search only (public) API key
                indexName: 'xiaominfo',
                algoliaOptions: {
                    facetFilters: ['version:VERSION'],
                },
            },
        }),
};
