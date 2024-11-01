import {templateMarketAPI} from '@/service/api';
import { ResponseResult } from '@/service/base';
import {SuiteMarketInfo} from '@/typings';

export async function queryTemplateMarketClassifyList() {
  return templateMarketAPI.queryTemplateMarketClassifyList();
}

export async function queryTemplateMarketList(params: Parameters<typeof templateMarketAPI.queryTemplateMarketList>[0]) {
  return templateMarketAPI.queryTemplateMarketList(params);
}

export async function queryTemplateMarketDetail(templateId: number): ResponseResult<SuiteMarketInfo> {
  return templateMarketAPI.queryTemplateMarketDetail(templateId);
}

export async function useTemplateMarket(templateId: number, bill?:string) {
  return templateMarketAPI.useTemplateMarket(templateId,bill);
}

export async function queryRecommendTemplateList(templateId: number){
  return templateMarketAPI.queryRecommendTemplateList(templateId);
}
