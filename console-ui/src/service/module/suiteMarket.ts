import { suiteMarketAPI } from '@/service/api';
import {ResponseResult} from "@/service/base";
import {SuiteMarket} from "@/typings";

export async function querySuiteMarketList() {
  return suiteMarketAPI.querySuiteMarketList();
}

export async function querySuiteMarketDetail(suiteId:number):ResponseResult<SuiteMarket> {
  return suiteMarketAPI.querySuiteMarketDetail(suiteId);
}

export async function installSuiteMarket(suiteId: number) {
  return suiteMarketAPI.installSuiteMarket(suiteId);
}

