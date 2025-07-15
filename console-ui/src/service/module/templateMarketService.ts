import {templateMarketAPI} from '@/service/api';
import { ResponseResult } from '@/service/base';
import { TemplateMarketInfo} from '@/typings';

export async function queryTemplateMarketClassifyList() {
  const res = await templateMarketAPI.queryTemplateMarketClassifyList();
  if (res.success) {
    const suiteClassifyFilter = {
      title: "模板分类",
      key: "templateClassify",
      options : [{label: "全部",value: null},
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

export async function queryTemplateMarketList(params: Parameters<typeof templateMarketAPI.queryTemplateMarketList>[0]) {
  return templateMarketAPI.queryTemplateMarketList(params);
}

export async function queryTemplateMarketDetail(templateId: number): ResponseResult<TemplateMarketInfo> {
  return templateMarketAPI.queryTemplateMarketDetail(templateId);
}

export async function useTemplateMarket(templateId: number, bill?:string) {
  return templateMarketAPI.useTemplateMarket(templateId,bill);
}

export async function queryRecommendTemplateList(templateId: number){
  return templateMarketAPI.queryRecommendTemplateList(templateId);
}
