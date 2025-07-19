import { request, type ResponseResult } from '../base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function queryTemplateMarketClassifyList(): ResponseResult {
  return request.post(JUGGLE_API_PREFIX+'/template/market/classify');
}

export async function queryTemplateMarketList(params: {
  pageNum:number;
  pageSize:number;
  templateName: string;
  templateClassifyId: number|null;
  priceStatus: number|null;
}): ResponseResult {
  return request.post(JUGGLE_API_PREFIX+'/template/market',params);
}

export async function queryTemplateMarketDetail(templateId: number): ResponseResult {
  return request.get(JUGGLE_API_PREFIX+'/template/market/info/' + templateId);
}

export async function useTemplateMarket(templateId: number,bill?:string): ResponseResult {
  return request.post(JUGGLE_API_PREFIX+'/template/market/use', {
    templateId: templateId,
    bill: bill
  });
}

export function queryRecommendTemplateList(templateId: number): ResponseResult  {
  return request.get(JUGGLE_API_PREFIX+'/template/market/recommend/'+templateId);
}
