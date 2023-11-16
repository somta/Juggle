package net.somta.juggle.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author husong
 */
public class HttpClient {

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper = new ObjectMapper();

    public HttpClient() {
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * 发送请求
     * @param request
     * @return
     */
    public Map<String,Object> sendRequest(Request request) {
        /*HttpRequestBase uriRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            uriRequest = new HttpGet(request.getRequestUrl());
        }else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            uriRequest = new HttpDelete(request.getRequestUrl());
        } else {
            HttpEntityEnclosingRequestBase r = null;
            if (request.getRequestType() == RequestTypeEnum.PUT) {
                r = new HttpPut(request.getRequestUrl());
            } else {
                r = new HttpPost(request.getRequestUrl());
            }
            if (request.getReqBody() != null) {
                r.setEntity(new ByteArrayEntity(request.getReqBody()));
            }
            uriRequest = r;
        }*/

        // TODO 处理header
        /*HttpHeaders headers = request.getReqHeaders();
        for (Map.Entry<String, String> e : headers.entries()) {
            uriRequest.addHeader(e.getKey(), e.getValue());
        }*/

        Map<String,Object> resultMap = new HashMap<>(8);
      /*  try {
            // 发送同步请求  https://blog.csdn.net/weixin_32265569/article/details/108606783
            CloseableHttpResponse httpResponse = httpClient.execute(uriRequest);
            System.out.println(httpResponse.getStatusLine());

            HttpEntity entity = httpResponse.getEntity();
            // 获取响应体的字符串
            String result = EntityUtils.toString(entity);
            // do something useful with the response body
            // and ensure it is fully consumed
            try {
                resultMap = objectMapper.readValue(result, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            EntityUtils.consume(entity);
            System.out.println(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return resultMap;
    }

    /**
     * 请求实体
     */
    public static class Request {

        /**
         * 请求url,带域名的完整地址
         */
        private String requestUrl;
        /**
         * 请求方法类型 GET POST PUT DELETE
         */
        private RequestTypeEnum requestType;
        /**
         * 请求头
         */
       // private HttpHeaders requestHeaders;
        /**
         * 请求体
         */
        private byte[] reqBody;

        public Request(RequestTypeEnum requestType, String requestUrl) {
            if (requestUrl == null) {
                throw new IllegalArgumentException("reqMethod,reqHeaders,reqBody should not be null");
            }
            this.requestType = requestType;
            this.requestUrl = requestUrl;
        }

        public String getRequestUrl() {
            return requestUrl;
        }

        public RequestTypeEnum getRequestType() {
            return requestType;
        }

       /* public HttpHeaders getRequestHeaders() {
            return requestHeaders;
        }*/

        public void setReqBody(byte[] reqBody) {
            this.reqBody = reqBody;
        }

        public byte[] getReqBody() {
            return reqBody;
        }



    }

}
