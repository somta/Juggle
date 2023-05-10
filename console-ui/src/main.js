import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// 样式
import './assets/main.css'

const app = createApp(App)

app.use(router)

app.mount('#app')
