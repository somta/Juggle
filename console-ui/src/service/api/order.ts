import { request, type ResponseResult } from '@/service/base';
import {CreateOrder} from "@/typings";
import {JUGGLE_API_PREFIX} from "@/const/application.ts";

export async function createOrder(params: {
  orderName: string;
  orderType: number;
  goodsId: number;
}): ResponseResult<CreateOrder> {
  return request.post(JUGGLE_API_PREFIX+`/order/createOrder`, params);
}

export async function getOrderPayStatus(orderNo: string): ResponseResult<string> {
  return request.get(JUGGLE_API_PREFIX+`/order/getOrderPayStatus/${orderNo}`);
}


