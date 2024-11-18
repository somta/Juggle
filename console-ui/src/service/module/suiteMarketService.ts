import {suiteMarketAPI} from '@/service/api';
import { ResponseResult } from '@/service/base';
import {SuiteMarketInfo} from '@/typings';

export async function querySuiteMarketClassifyList() {
  return suiteMarketAPI.querySuiteMarketClassifyList();
}

export async function querySuiteMarketList(params: Parameters<typeof suiteMarketAPI.querySuiteMarketList>[0]) {
  return suiteMarketAPI.querySuiteMarketList(params);
}

export async function querySuiteMarketDetail(suiteId: number): ResponseResult<SuiteMarketInfo> {
  return suiteMarketAPI.querySuiteMarketDetail(suiteId);
}

export async function installSuiteMarket(suiteId: number, bill?:string) {
  return suiteMarketAPI.installSuiteMarket(suiteId,bill);
}
