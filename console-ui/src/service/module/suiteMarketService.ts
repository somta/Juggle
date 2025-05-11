import {suiteMarketAPI} from '@/service/api';
import { ResponseResult } from '@/service/base';
import {SuiteMarketInfo} from '@/typings';

export async function querySuiteMarketClassifyList() {
  const res = await suiteMarketAPI.querySuiteMarketClassifyList();
  if (res.success) {
    const suiteClassifyFilter = {
      title: "套件分类",
      key: "suiteClassify",
      options : [{label: "全部",value: ""},
        ...res.result.map(item => ({
          value: item.id,
          label: item.classifyName
        }))]
    }
    return suiteClassifyFilter;
  } else {
    return null;
  }
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
