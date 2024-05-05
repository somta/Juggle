import {request, ResponsePageResult, type ResponseResult} from '@/service/base';

export async function addDataSource(tokenDesc:string): ResponseResult<string> {
  return request.post(`/v1/datasource/add`,tokenDesc);
}

export async function deleteDataSourceById(tokenId: number): ResponseResult<boolean> {
  return request.delete(`/v1/datasource/delete/${tokenId}`);
}

export async function updateDataSourceById(params: {
  id: number;
  tokenDesc:string;
}): ResponseResult<boolean> {
  return request.put(`/v1/datasource/update`,params);
}

export async function queryDataSourceInfo(dataSourceId: number): ResponsePageResult {
  return request.get('/v1/datasource/info/' + dataSourceId);
}

export async function dataSourcePage(params: { pageNum: number; pageSize: number;}): ResponsePageResult {
  return request.post('/v1/datasource/page', params);
}
