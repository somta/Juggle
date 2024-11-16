# juggle-sdk-nodejs

Node 环境 juggle-client sdk

## 安装

```sh
# npm
npm install juggle-client
# or yarn
yarn add juggle-client
# or pnpm
pnpm i juggle-client
```

## 使用

```js
// 引入 pkg
const JuggleClient = require('juggle-client');

// 配置信息
const serverAddr = 'https://demo.juggle.plus';
const accessToken = 'eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcyOTAwODYzOTc5MH0=';

// 实例初始化
const juggleClient = new JuggleClient({
  accessToken,
  serverAddr,
});

async function r() {
  const res1 = await juggleClient.triggerFlow('v1', 'sync_example', {
    userName: 'juggle',
    password: '123456',
    deposit: 1000,
  });
  console.log(res1);

  const res2 = await juggleClient.getAsyncFlowResult('222');
  console.log(res2);
}

r();
```


