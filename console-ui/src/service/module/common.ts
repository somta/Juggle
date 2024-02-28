import { commonAPI } from '@/service/api';

export async function listDataType() {
  return commonAPI.listDataType();
}

export const getDataTypeList = (function () {
  let dataTypeList: any[] = [];
  let pedding: any = null;
  return async function () {
    if (dataTypeList.length === 0) {
      pedding = pedding || listDataType();
      const res = await pedding;
      if (res.success) {
        pedding = null;
        dataTypeList = res.result;
      }
    }
    return dataTypeList;
  };
})();
