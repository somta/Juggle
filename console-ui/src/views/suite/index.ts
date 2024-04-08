import ApiList from './ApiList.vue';
import RouterNest from '@/views/RouterNest.vue';
import ApiDebug from "./ApiDebug.vue";
import SuiteList from "./SuiteList.vue";

export const SuiteRoutes = [
  {
    path: 'suite',
    name: 'suite',
    component: RouterNest,
    redirect: () => ({ name: 'suite-list' }),
    meta: { name: '套件' },
    children: [
      {
        path: 'list',
        name: 'suite-list',
        component: SuiteList,
        meta: { name: '套件列表' },
      },
      {
        path: 'api',
        name: 'api-list',
        component: ApiList,
        meta: { name: '接口列表' },
      },
      {
        path: 'debug/:apiId',
        name: 'api-debug',
        component: ApiDebug,
        meta: { name: '接口调试' },
      },
    ],
  },
];
