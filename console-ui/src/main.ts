
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import './assets/base.css';
import 'element-plus/dist/index.css';
import { checkToken } from './utils/user';

const app = createApp(App);

app.use(ElementPlus);
app.use(router);

async function startup () {
  // 检查登录状态
  const isLogin = await checkToken();
  if (!isLogin) {
    router.push({ name: 'login' });
  }

  app.mount('#app');
}

startup();
