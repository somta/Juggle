package net.somta.juggle.example.dto;

/**
 * @author husong
 */
public class BaseResponse<T> {
    private Integer code;
    private String errorMsg;
    private T data;

    public static BaseResponse setSuccessResponse(Object data){
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(data);
        return response;
    }

    public static BaseResponse setErrorResponse(String errorMsg){
        BaseResponse response = new BaseResponse();
        response.setCode(-1);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
