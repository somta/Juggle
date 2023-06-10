
import InterfaceDomain from './InterfaceDomain.vue';
import InterfaceList from './InterfaceList.vue';
import RouterNest from '@/views/RouterNest.vue';

export const InterfaceRoutes = [
  {
    path: 'interface',
    name: 'interface',
    component: RouterNest,
    redirect: () => ({name: 'interface-domain'}),
    meta: { name: '接口' },
    children: [
      {
        path: 'domain',
        name: 'interface-domain',
        component: InterfaceDomain,
        meta: { name: '领域' },
      },
      {
        path: 'list',
        name: 'interface-list',
        component: InterfaceList,
        meta: { name: '接口列表' },
      },
    ],
  },
];
