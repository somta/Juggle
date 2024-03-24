import { tokenAPI } from "@/service/api";

export async function addToken(tokenDesc:string) {
    return tokenAPI.addToken(tokenDesc);
}

export async function deleteToken(tokenId:number) {
    return tokenAPI.deleteTokenById(tokenId);
}

export async function updateToken(params: Parameters<typeof tokenAPI.updateTokenById>[0]) {
    return tokenAPI.updateTokenById(params);
}

export async function queryTokenPage(params: Parameters<typeof tokenAPI.tokenPage>[0]) {
    return tokenAPI.tokenPage(params);
}