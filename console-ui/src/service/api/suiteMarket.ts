import { request, type ResponseResult } from '../base';

export async function querySuiteMarketClassifyList(): ResponseResult {
  return request.post('/v1/suite/market/classify');
}

export async function querySuiteMarketList(params: {
  pageNum:number;
  pageSize:number;
  suiteName: string;
  suiteClassifyId: number|null;
}): ResponseResult {
  return request.post('/v1/suite/market',params);
}

export async function querySuiteMarketDetail(suiteId: number): ResponseResult {
  return request.get('/v1/suite/market/info/' + suiteId);
}

export async function installSuiteMarket(suiteId: number,bill?:string): ResponseResult {
  return request.post('/v1/suite/market/install', {
    suiteId: suiteId,
    bill: bill
  });
}
