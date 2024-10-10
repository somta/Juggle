import { createRouter, createWebHashHistory } from 'vue-router';
import LayoutView from '../views/LayoutView.vue';
import LoginView from '../views/LoginView.vue';
import HomeView from '../views/HomeView.vue';
import NotFound from '../views/NotFound.vue';
import { FlowRoutes } from '../views/flow';
import { CommonRoutes } from '../views/common';
import ObjectList from '@/views/object/ObjectList.vue';
import { SystemRoutes } from '@/views/system';
import { SuiteRoutes } from '@/views/suite';
import SuiteMarket from '@/views/market/SuiteMarket.vue';
import SuiteMarketDetail from '@/views/market/SuiteMarketDetail.vue';
import FlowDesign from "@/views/flow/FlowDesign.vue";

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
      path: '/design/:flowDefinitionId/:flowKey',
      name: 'flow-design',
      component: FlowDesign,
      meta: { name: '流程设计' },
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
        ...SuiteRoutes,
        {
          path: 'object/list',
          name: 'object-list',
          component: ObjectList,
          meta: { name: '对象' },
        },
        {
          path: 'market/suite',
          name: 'suite-market',
          component: SuiteMarket,
          meta: { name: '套件市场' },
        },
        {
          path: 'market/suite/detail/:suiteId',
          name: 'suite-market-detail',
          component: SuiteMarketDetail,
          meta: { name: '套件详情' },
        },
        ...SystemRoutes,
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
