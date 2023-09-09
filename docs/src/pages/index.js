import React from 'react';
import clsx from 'clsx';
import Layout from '@theme/Layout';
import Link from '@docusaurus/Link';
import CodeBlock from '@theme/CodeBlock';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import styles from './index.module.css';
import Hightlights from '../components/Highlights';

function Hero() {
    return (
        <header className={clsx('container', styles.heroBanner)}>
            <div className="row padding-horiz--md">
                <div className="col col--12">
                    <div className={clsx(styles.relative, 'row')}>
                        <div className="col">
                            <h1 className={styles.tagline}>
                                Juggle是一个可用于接口编排 ， 定制开发<br />  等场景的一套完整解决方案
                            </h1>
                            <h1 className={styles.tagline}>
                                <span>Juggle</span>是一个可用于<span>接口编排</span> ， <span>定制开发<br /> </span> 等场景的一套<span>完整</span>解决方案
                            </h1>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <h2>大幅提高开发效率，为企业降本增效.</h2>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <div className={styles.heroButtons}>
                                <Link to="docs/guide/quick-start" className={styles.getStarted}>Get Started</Link>
                                {/*<a href='https://gitee.com/Somta' className={styles.giteeStar}><img src='' alt='star'></img></a>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
}

function Contributor({ name, url, avatar }) {
    return (<div className={styles.contributor}>
        <a href={url} target='_blank'>
            <div alt={name} title={name}><img src={avatar} /></div>
        </a>
    </div>);
}


function Knife4jFramWork() {
    return (
        <section className={clsx(styles.try, 'container')}>
            <div className="col">
                <img src='/images/website/knife4j-framework2.png' />
            </div>
        </section>
    );
}


function Contributors() {
    const contributorDatas = require('../../static/json/contributors.json');

    return (
        <section className={clsx(styles.try, 'container')}>
            <div className="col">
                <div className={styles.contributorsHeader}>
                    <h1>Contributors</h1>
                    <div>Juggle的每一点发展离不开每一位Contributor的无私奉献～～！</div>
                </div>
                <div className={styles.contributos}>
                    {
                        contributorDatas.map((props, idx) => (
                            <Contributor key={idx} {...props} />
                        ))
                    }
                </div>
            </div>
        </section>
    );
}


export default function Home() {
    const SvgLogo = require('../../static/img/juggle-logo.svg').default;
    const { siteConfig } = useDocusaurusContext();
    return (
        <Layout
            title={`${siteConfig.title} · ${siteConfig.tagline}`}
            description={siteConfig.description}>
            <Hero />
            <Hightlights />
            {/*<Knife4jFramWork />*/}
            <Contributors />
            <div className="container">
                <div className="row">
                    <div className="col text--center padding-top--lg padding-bottom--xl">
                        <SvgLogo className={styles.bottomLogo} />
                    </div>
                </div>
            </div>
        </Layout>
    );
}
