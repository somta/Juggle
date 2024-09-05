import { orderAPI} from '@/service/api'

export async function createOrder(params: Parameters<typeof orderAPI.createOrder>[0]) {
  return orderAPI.createOrder(params);
}

export async function getOrderPayStatus(orderNo: string) {
  return orderAPI.getOrderPayStatus(orderNo);
}


