import { request, ResponsePageResult, type ResponseResult } from '@/service/base';
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

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
  return request.post(JUGGLE_API_PREFIX+`/datasource/add`, params);
}

export async function deleteDataSourceById(datasourceId: number): ResponseResult<boolean> {
  return request.delete(JUGGLE_API_PREFIX+`/datasource/delete/${datasourceId}`);
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
  return request.put(JUGGLE_API_PREFIX+`/datasource/update`, params);
}

export async function queryDataSourceInfo(dataSourceId: number): ResponsePageResult<any> {
  return request.get(JUGGLE_API_PREFIX+'/datasource/info/' + dataSourceId);
}

export async function dataSourceList(): ResponsePageResult {
  return request.get(JUGGLE_API_PREFIX+'/datasource/list');
}

export async function dataSourcePage(params: { pageNum: number; pageSize: number }): ResponsePageResult {
  return request.post(JUGGLE_API_PREFIX+'/datasource/page', params);
}

export async function connectDataSource(dataSourceId: number): ResponsePageResult<any> {
  return request.get(JUGGLE_API_PREFIX+'/datasource/connect/' + dataSourceId);
}
