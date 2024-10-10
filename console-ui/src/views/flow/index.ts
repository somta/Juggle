import FlowDefineList from './FlowDefineList.vue';
import FlowList from './FlowList.vue';
import FlowVersionList from './FlowVersionList.vue';
import FlowDebug from './FlowDebug.vue';
import FlowDesign from './FlowDesign.vue';
import RouterNest from '@/views/RouterNest.vue';

export const FlowRoutes = [
  {
    path: 'flow',
    name: 'flow',
    component: RouterNest,
    redirect: () => ({ name: 'flow-define' }),
    meta: { name: '流程' },
    children: [
      {
        path: 'define',
        name: 'flow-define',
        component: FlowDefineList,
        meta: { name: '流程定义' },
      },
      {
        path: 'list',
        name: 'flow-api',
        component: FlowList,
        meta: { name: '流程列表' },
      },
      {
        path: 'version/:flowId',
        name: 'flow-version',
        component: FlowVersionList,
        meta: { name: '流程版本列表' },
      },
      {
        path: 'debug/:flowDefinitionId/:flowKey',
        name: 'flow-debug',
        component: FlowDebug,
        meta: { name: '流程调试' },
      },
    ],
  },
];
