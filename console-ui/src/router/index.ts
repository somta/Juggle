import { createRouter, createWebHashHistory } from 'vue-router';
import LayoutView from '../views/LayoutView.vue';
import LoginView from '../views/LoginView.vue';
import HomeView from '../views/HomeView.vue';
import NotFound from '../views/NotFound.vue';
import { FlowRoutes } from '../views/flow';
import { ApiRoutes } from '../views/api';
import { CommonRoutes } from '../views/common';
import ObjectList from "@/views/object/ObjectList.vue";

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
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
      redirect: () => ({ name: 'flow-define' }),
      meta: { name: '首页' },
      children: [
        /*{
          path: '',
          name: 'home',
          component: HomeView,
          meta: { name: '首页' },
        },*/
        ...CommonRoutes,
        ...FlowRoutes,
        ...ApiRoutes,
        {
          path: 'object/list',
          name: 'object-list',
          component: ObjectList,
          meta: { name: '对象' },
        },
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
