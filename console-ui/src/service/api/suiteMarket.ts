import { request, type ResponsePageResult, type ResponseResult } from '../base';


export async function querySuiteMarketList(): ResponseResult {
    return request.post('/v1/suite/market');
}

export async function querySuiteMarketDetail(suiteId:number): ResponseResult {
    return request.get('/v1/suite/market/info/' + suiteId);
}

export async function installSuiteMarket(suiteId:number): ResponseResult {
    return request.post('/v1/suite/market/install',{
        suiteId:suiteId
    });
}