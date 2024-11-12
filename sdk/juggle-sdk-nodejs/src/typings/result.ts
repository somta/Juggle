export interface ResponseDataResult<T = any> {
    success: boolean;
    result: T;
    errorCode?: string;
    errorMsg?: string;
}