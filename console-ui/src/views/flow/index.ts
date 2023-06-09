
import FlowDefine from './FlowDefine.vue';
import FlowList from './FlowList.vue';

export const FlowRoutes = [
  {
    path: 'flow/define',
    name: 'flow-define',
    component: FlowDefine,
  },
  {
    path: 'flow/list',
    name: 'flow-list',
    component: FlowList,
  },
];
