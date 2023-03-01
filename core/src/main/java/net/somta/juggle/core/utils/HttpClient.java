package net.somta.juggle.core.utils;

import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class HttpClient {

    private CloseableHttpClient httpClient;

    public HttpClient() {
        this.httpClient = HttpClients.createDefault();
        //this.httpClient.start();
    }

    public void sendRequest(Request request) {
        HttpRequestBase uriRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            uriRequest = new HttpGet(request.getReqUrl());
        }else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            uriRequest = new HttpDelete(request.getReqUrl());
        } else {
            HttpEntityEnclosingRequestBase r = null;
            if (request.getRequestType() == RequestTypeEnum.PUT) {
                r = new HttpPut(request.getReqUrl());
            } else {
                r = new HttpPost(request.getReqUrl());
            }
            if (request.getReqBody() != null) {
                r.setEntity(new ByteArrayEntity(request.getReqBody()));
            }
            uriRequest = r;
        }

        // TODO 处理header
        /*HttpHeaders headers = request.getReqHeaders();
        for (Map.Entry<String, String> e : headers.entries()) {
            uriRequest.addHeader(e.getKey(), e.getValue());
        }*/

        try {
            // 发送同步请求  https://blog.csdn.net/weixin_32265569/article/details/108606783
            CloseableHttpResponse httpResponse = httpClient.execute(uriRequest);
            System.out.println(httpResponse.getStatusLine());

            HttpEntity entity = httpResponse.getEntity();
            // 获取响应体的字符串
            String rst = EntityUtils.toString(entity);
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
            System.out.println(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求实体
     */
    public static class Request {

        public final static String HTTP_SCHEME = "http";

        /**
         * 请求URI,带域名的完整地址
         */
        private String reqUrl;
        /**
         * 请求方法类型 GET POST
         */
        private RequestTypeEnum requestType;
        /**
         * 请求头
         */
        private HttpHeaders reqHeaders;
        /**
         * 请求体
         */
        private byte[] reqBody;

        public Request(String reqUrl) {
            if (reqUrl == null) {
                throw new IllegalArgumentException("reqMethod,reqHeaders,reqBody should not be null");
            }
            this.reqUrl = reqUrl;
        }

        public String getReqUrl() {
            return reqUrl;
        }

        public RequestTypeEnum getRequestType() {
            return requestType;
        }

        public void setRequestType(RequestTypeEnum requestType) {
            this.requestType = requestType;
        }

        public HttpHeaders getReqHeaders() {
            return reqHeaders;
        }

        public void setReqBody(byte[] reqBody) {
            this.reqBody = reqBody;
        }

        public byte[] getReqBody() {
            return reqBody;
        }



    }

}
