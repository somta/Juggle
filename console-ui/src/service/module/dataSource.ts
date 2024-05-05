import { datasourceAPI } from "@/service/api";
import {ResponsePageResult} from "@/service/base";

export interface DataSource {
    id: number | null;
    dataSourceName: string,
    dataSourceType: string,
    dataSourceDesc: string,
    address: string,
    port: string,
    userName: string,
    password: string,
    databaseName: string,
    connectExtInfo: string | number,
    minPoolSize: number,
    maxPoolSize: number,
    queryTimeout: number,
}

export async function addDataSource(tokenDesc:string) {
    return datasourceAPI.addDataSource(tokenDesc);
}

export async function deleteDataSource(dataSourceId:number) {
    return datasourceAPI.deleteDataSourceById(dataSourceId);
}

export async function updateDataSource(params: Parameters<typeof datasourceAPI.updateDataSourceById>[0]) {
    return datasourceAPI.updateDataSourceById(params);
}

export async function queryDataSourceInfo(dataSourceId: number): ResponsePageResult<DataSource> {
    return datasourceAPI.queryDataSourceInfo(dataSourceId);
}

export async function queryDataSourcePage(params: Parameters<typeof datasourceAPI.dataSourcePage>[0]) {
    return datasourceAPI.dataSourcePage(params);
}