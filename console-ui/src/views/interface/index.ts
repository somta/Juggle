
import InterfaceDomain from './InterfaceDomain.vue';
import InterfaceList from './InterfaceList.vue';

export const InterfaceRoutes = [
  {
    path: 'interface/domain',
    name: 'interface-domain',
    component: InterfaceDomain,
  },
  {
    path: 'interface/list',
    name: 'interface-list',
    component: InterfaceList,
  },
];
