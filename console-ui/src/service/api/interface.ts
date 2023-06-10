
import { request, type ResponsePageResult, type ResponseResult } from '../base';

export async function queryDomain (params: {
  pageSize: number;
  pageIndex: number;
  keywords: string;
}): ResponsePageResult {
  // return request.post('/abc', params);

  // 模拟
  await new Promise(resolve => setTimeout(() => { resolve(true); }, 500));
  if (params.keywords) {
    return {
      data: {
        rows: Array(6).fill(1).map((_, index) => {
          const keywords = params.keywords + '-' + index;
          return {
            code: '编码' + keywords,
            name: '名称' + keywords,
            description: '描述' + keywords,
            time: '2020-12-12',
          };
        }),
        total: 6,
      },
    };
  } else {
    return {
      data: {
        rows: Array(params.pageSize).fill(1).map((_, index) => {
          const key = params.pageIndex + '-' + index;
          return {
            code: '编码' + key,
            name: '名称' + key,
            description: '描述' + key,
            time: '2020-12-12',
          };
        }),
        total: 100,
      },
    };
  }

}

export async function addDomain (params: {
  code: string;
  name: string;
  description: string;
}): ResponseResult<boolean> {
  console.log(params);

  // 模拟
  await new Promise(resolve => setTimeout(() => { resolve(true); }, 500));
  return { data: true};
}

export async function editDomain (params: {
  code: string;
  name: string;
  description: string;
}): ResponseResult<boolean> {
  console.log(params);

  // 模拟
  await new Promise(resolve => setTimeout(() => { resolve(true); }, 500));
  return { data: true};
}

export async function deleteDomain (params: string): ResponseResult<boolean> {
  console.log(params);

  // 模拟
  await new Promise(resolve => setTimeout(() => { resolve(true); }, 500));
  return { data: true};
}
