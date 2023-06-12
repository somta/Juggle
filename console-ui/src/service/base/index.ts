
import axios from 'axios';

function createRequestInstance () {
  // 创建实例
  const instance = axios.create({
    baseURL: '',
    timeout: 1000,
    headers: {},
  });
  
  // 请求拦截器
  instance.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    return Promise.reject(error);
  });
  
  
  // 响应拦截器
  instance.interceptors.response.use(function (response) {
    return response;
  }, function (error) {
    return Promise.reject(error);
  });

  return instance;
}

// 实例
export const request = createRequestInstance();


// 返回结果 - 通用
export type ResponseResult<T = any> = Promise<{
  success: boolean;
  result: T;
  errorCode?: string;
  errorMsg?: string;
}>

// 返回结果 - 分页
export type ResponsePageResult<T = Record<string, any>> = ResponseResult<{
  rows: T[];
  total: number;
}>
