const JuggleClient = require('juggle-client');

const serverAddr = 'https://demo.juggle.plus';
const accessToken = 'eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcyOTAwODYzOTc5MH0=';

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
