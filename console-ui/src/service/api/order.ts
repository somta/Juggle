import { request, type ResponseResult } from '@/service/base';
import {CreateOrder} from "@/typings";

export async function createOrder(params: {
  orderName: string;
  orderType: number;
  goodsId: number;
}): ResponseResult<CreateOrder> {
  return request.post(`/v1/order/createOrder`, params);
}

export async function getOrderPayStatus(orderNo: string): ResponseResult<string> {
  return request.get(`/v1/order/getOrderPayStatus/${orderNo}`);
}


