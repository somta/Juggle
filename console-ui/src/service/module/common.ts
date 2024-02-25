import { commonAPI } from '@/service/api';

export async function listDataType() {
  return commonAPI.listDataType();
}

export const getDataTypeList = (function () {
  let dataTypeList: any[] = [];
  return async function () {
    if (dataTypeList.length === 0) {
      const res = await listDataType();
      if (res.success) {
        dataTypeList = res.result;
      }
    }
    return dataTypeList;
  };
})();
