import { createRouter, createWebHistory } from 'vue-router';
import LayoutView from '../views/LayoutView.vue';
import HomeView from '../views/HomeView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
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
      ],
    },
  ],
});

export default router;
