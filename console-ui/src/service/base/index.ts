import axios, { AxiosError, type AxiosResponse } from 'axios';
import { userService } from '../module';

function createRequestInstance() {
  // 创建实例
  const instance = axios.create({
    baseURL: '',
    timeout: 10000,
    headers: {},
  });

  // 请求拦截器
  instance.interceptors.request.use(
    function (config) {
      // 设置用户鉴权
      const auth = userService.getAuth();
      if (auth) {
        config.headers.Authorization = auth;
      }
      return config;
    },
    function (error) {
      return Promise.reject(error);
    }
  );

  // 响应拦截器
  instance.interceptors.response.use(
    function (response) {
      // 返回data，并将response保存在data备用
      return { ...response.data, response };
    },
    function (error) {
      // 错误时，将error保存在data备用
      const result = {
        success: false,
        errorCode: '',
        errorMsg: '',
        error,
      };
      if (error.response) {
        Object.assign(result, error.response.data);
        if (error.response.status === 401) {
          window.localStorage.clear();
          window.location.href = '/#/login';
        }
      }
      if (!result.errorCode) {
        result.errorCode = error.code;
      }
      if (!result.errorMsg) {
        result.errorMsg = error.message;
      }
      return result;
    }
  );

  return instance;
}

// 实例
export const request = createRequestInstance();

// 定义结果
export interface CommonResult<T = any> {
  success: boolean;
  result: T;
  errorCode?: string;
  errorMsg?: string;
  error?: AxiosError;
  response?: AxiosResponse;
}

// 返回结果 - 通用
export type ResponseResult<T = any> = Promise<CommonResult<T>>;

// 返回结果 - 分页
export type ResponsePageResult<T = Array<Record<string, any>>> = Promise<CommonResult<T> & { total: number }>;
