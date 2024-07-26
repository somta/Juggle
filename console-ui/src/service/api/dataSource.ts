import { request, ResponsePageResult, type ResponseResult } from '@/service/base';

export async function addDataSource(params: {
  dataSourceName: string;
  dataSourceType: string;
  dataSourceDesc: string;
  address: string;
  port: string;
  userName: string;
  password: string;
  databaseName: string;
  connectExtInfo: string;
  minPoolSize: number;
  maxPoolSize: number;
  queryTimeout: number;
}): ResponseResult<string> {
  return request.post(`/v1/datasource/add`, params);
}

export async function deleteDataSourceById(datasourceId: number): ResponseResult<boolean> {
  return request.delete(`/v1/datasource/delete/${datasourceId}`);
}

export async function updateDataSourceById(params: {
  id: number;
  dataSourceName: string;
  dataSourceType: string;
  dataSourceDesc: string;
  address: string;
  port: string;
  userName: string;
  password: string;
  databaseName: string;
  connectExtInfo: string;
  minPoolSize: number;
  maxPoolSize: number;
  queryTimeout: number;
}): ResponseResult<boolean> {
  return request.put(`/v1/datasource/update`, params);
}

export async function queryDataSourceInfo(dataSourceId: number): ResponsePageResult<any> {
  return request.get('/v1/datasource/info/' + dataSourceId);
}

export async function dataSourceList(): ResponsePageResult {
  return request.get('/v1/datasource/list');
}

export async function dataSourcePage(params: { pageNum: number; pageSize: number }): ResponsePageResult {
  return request.post('/v1/datasource/page', params);
}

export async function connectDataSource(dataSourceId: number): ResponsePageResult<any> {
  return request.get('/v1/datasource/connect/' + dataSourceId);
}
