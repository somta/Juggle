
import DomainList from './DomainList.vue';
import ApiList from './ApiList.vue';
import RouterNest from '@/views/RouterNest.vue';

export const ApiRoutes = [
  {
    path: 'api',
    name: 'api',
    component: RouterNest,
    redirect: () => ({name: 'api-domain'}),
    meta: { name: '接口' },
    children: [
      {
        path: 'domain',
        name: 'domain-list',
        component: DomainList,
        meta: { name: '领域' },
      },
      {
        path: 'list',
        name: 'api-list',
        component: ApiList,
        meta: { name: '接口列表' },
      },
    ],
  },
];
