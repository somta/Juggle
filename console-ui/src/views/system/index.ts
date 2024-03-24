import RouterNest from '@/views/RouterNest.vue';
import TokenList from "@/views/system/TokenList.vue";

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
        name: 'token-api',
        component: TokenList,
        meta: { name: '令牌' },
      },
    ],
  },
];
