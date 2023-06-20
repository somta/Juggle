import { createRouter, createWebHistory } from 'vue-router';
import LayoutView from '../views/LayoutView.vue';
import LoginView from '../views/LoginView.vue';
import HomeView from '../views/HomeView.vue';
import NotFound from '../views/NotFound.vue';
import { FlowRoutes } from '../views/flow';
import { ApiRoutes } from '../views/api';
import { CommonRoutes } from '../views/common';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      meta: { name: '登录' },
    },
    {
      path: '/',
      name: 'index',
      component: LayoutView,
      meta: { name: '首页' },
      children: [
        {
          path: '',
          name: 'home',
          component: HomeView,
          meta: { name: '首页' },
        },
        ...CommonRoutes,
        ...FlowRoutes,
        ...ApiRoutes,
        {
          path: '/:pathMatch(.*)*',
          name: 'notfound',
          component: NotFound,
          meta: { name: '页面不存在' },
        },
      ],
    },
  ],
});

export default router;
