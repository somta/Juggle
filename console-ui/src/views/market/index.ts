import RouterNest from '@/views/RouterNest.vue';
import SuiteMarket from "@/views/market/SuiteMarket.vue";
import SuiteMarketDetail from "@/views/market/SuiteMarketDetail.vue";
import TemplateMarket from "@/views/market/TemplateMarket.vue";
import TemplateMarketDetail from "@/views/market/TemplateMarketDetail.vue";

export const MarketRoutes = [
  {
    path: 'market',
    name: 'market',
    component: RouterNest,
    redirect: () => ({ name: 'suite-market' }),
    meta: { name: '市场' },
    children: [
      {
        path: 'suite',
        name: 'suite-market',
        component: SuiteMarket,
        meta: { name: '套件市场' },
      },
      {
        path: 'suite/detail/:suiteId',
        name: 'suite-market-detail',
        component: SuiteMarketDetail,
        meta: { name: '套件详情' },
      },
      {
        path: 'template',
        name: 'template-market',
        component: TemplateMarket,
        meta: { name: '模板市场' },
      },
      {
        path: 'template/detail/:templateId',
        name: 'template-market-detail',
        component: TemplateMarketDetail,
        meta: { name: '模板详情' },
      },
    ],
  },
];
