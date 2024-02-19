import { commonAPI } from '@/service/api';

export async function listDataType() {
  return commonAPI.listDataType();
}
