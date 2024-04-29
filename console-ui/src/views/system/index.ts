import RouterNest from '@/views/RouterNest.vue';
import TokenList from "@/views/system/TokenList.vue";
import DataSourceList from "@/views/system/DataSourceList.vue";

export const SystemRoutes = [
  {
    path: 'system',
    name: 'system',
    component: RouterNest,
    redirect: () => ({ name: 'api-token' }),
    meta: { name: '系统' },
    children: [
      {
        path: 'token',
        name: 'token-list',
        component: TokenList,
        meta: { name: '令牌' },
      },
      {
        path: 'datasource',
        name: 'datasource-list',
        component: DataSourceList,
        meta: { name: '数据源' },
      },
    ],
  },
];
