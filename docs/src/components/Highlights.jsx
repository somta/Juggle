import React from 'react';
import clsx from 'clsx';
import styles from './Highlights.module.css';

const FeatureList = [
    {
        title: '基础特性',
        Svg: require('../../static/img/features/important.svg').default,
        description: (
            <>
                <ul>
                    <li>基于领域的接口管理</li>
                    <li>支持多种部署形态</li>
                </ul>
            </>
        ),
    },
    {
        title: '增强扩展',
        Svg: require('../../static/img/features/extend.svg').default,
        description: (
            <>
                <ul>
                    <li>流程的自由化配置</li>
                    <li>丰富的模板</li>
                    <li>基于Swagger,OpenApi规范的接口自动注入starter</li>
                </ul>
            </>
        ),
    },
    {
        title: '应用场景',
        Svg: require('../../static/img/features/spring.svg').default,
        description: (
            <>
                <ul>
                    <li>适用于微服务的接口编排</li>
                    <li>适用于写适配聚合层（BFF层）的代码</li>
                    <li>适用于私有化定制开发，避免污染标准代码</li>
                </ul>
            </>
        ),
    },
    {
        title: '社区生态',
        Svg: require('../../static/img/features/contributors.svg').default,
        description: (
            <>
                <ul>
                    <li>详细的文档</li>
                </ul>
            </>
        ),
    },
    {
        title: '云原生',
        Svg: require('../../static/img/features/kubernetes2.svg').default,
        description: (
            <>
                <ul>
                    <li>提供基于K8S+Docker的云原生的聚合OpenAPI文档的解决方案</li>
                </ul>
            </>
        ),
    }
];

function Feature({ Svg, title, description }) {
    return (
        <div className={clsx('col col--6')}>
            <div className="padding-horiz--md padding-bottom--md">
                <div className={styles.featureLine}>
                    <div className={styles.featureIcon}>
                        {Svg ? <Svg alt={title} /> : null}
                    </div>
                    <h3>{title}</h3>
                </div>
                <div>{description}</div>
            </div>
        </div>
    );
}

export default function Highlights() {
    const Svg = require('../../static/img/features/gradient.svg').default;
    return (
        <section className={styles.features}>
            {<Svg />}
            <div className="container">
                <div className="row">
                    {FeatureList.map((props, idx) => (
                        <Feature key={idx} {...props} />
                    ))}
                </div>
            </div>
        </section>
    );
}
