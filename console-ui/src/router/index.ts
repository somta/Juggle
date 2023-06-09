import { createRouter, createWebHistory } from 'vue-router';
import LayoutView from '../views/LayoutView.vue';
import LoginView from '../views/LoginView.vue';
import HomeView from '../views/HomeView.vue';
import NotFound from '../views/NotFound.vue';
import { FlowRoutes } from '../views/flow';
import { InterfaceRoutes } from '../views/interface';
import { CommonRoutes } from '../views/common';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/',
      name: 'index',
      component: LayoutView,
      children: [
        {
          path: '',
          name: 'home',
          component: HomeView,
        },
        ...CommonRoutes,
        ...FlowRoutes,
        ...InterfaceRoutes,
        {
          path: '/:pathMatch(.*)*',
          name: 'notfound',
          component: NotFound,
        },
      ],
    },
  ],
});

export default router;
