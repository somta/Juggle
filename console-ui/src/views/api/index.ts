import DomainList from './DomainList.vue';
import ApiList from './ApiList.vue';
import RouterNest from '@/views/RouterNest.vue';
import ApiDebug from "./ApiDebug.vue";

export const ApiRoutes = [
  {
    path: 'api',
    name: 'api',
    component: RouterNest,
    redirect: () => ({ name: 'api-domain' }),
    meta: { name: '接口' },
    children: [
      {
        path: 'domain',
        name: 'domain-api',
        component: DomainList,
        meta: { name: '领域' },
      },
      {
        path: 'list',
        name: 'api-api',
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
