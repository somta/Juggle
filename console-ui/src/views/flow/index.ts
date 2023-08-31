
import FlowDefine from './FlowDefine.vue';
import FlowList from './FlowList.vue';
import FlowDebug from './FlowDebug.vue';
import RouterNest from '@/views/RouterNest.vue';

export const FlowRoutes = [
  {
    path: 'flow',
    name: 'flow',
    component: RouterNest,
    redirect: () => ({name: 'flow-define'}),
    meta: { name: '流程' },
    children: [
      {
        path: 'define',
        name: 'flow-define',
        component: FlowDefine,
        meta: { name: '流程定义' },
      },
      {
        path: 'list',
        name: 'flow-list',
        component: FlowList,
        meta: { name: '流程列表' },
      },
      {
        path: 'debug/:flowKey',
        name: 'flow-debug',
        component: FlowDebug,
        meta: { name: '流程调试' },
      },
    ],
  },
];
