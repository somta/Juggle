import { request, type ResponseResult } from '../base';

export async function queryTemplateMarketClassifyList(): ResponseResult {
  return request.post('/v1/template/market/classify');
}

export async function queryTemplateMarketList(params: {
  pageNum:number;
  pageSize:number;
  templateName: string;
  templateClassifyId: number|null;
}): ResponseResult {
  return request.post('/v1/template/market',params);
}

export async function queryTemplateMarketDetail(templateId: number): ResponseResult {
  return request.get('/v1/template/market/info/' + templateId);
}

export async function useTemplateMarket(templateId: number,bill?:string): ResponseResult {
  return request.post('/v1/template/market/use', {
    templateId: templateId,
    bill: bill
  });
}