import { datasourceAPI } from "@/service/api";

export async function addDataSource(tokenDesc:string) {
    return datasourceAPI.addDataSource(tokenDesc);
}

export async function deleteDataSource(dataSourceId:number) {
    return datasourceAPI.deleteDataSourceById(dataSourceId);
}

export async function updateDataSource(params: Parameters<typeof datasourceAPI.updateDataSourceById>[0]) {
    return datasourceAPI.updateDataSourceById(params);
}

export async function queryDataSourcePage(params: Parameters<typeof datasourceAPI.dataSourcePage>[0]) {
    return datasourceAPI.dataSourcePage(params);
}