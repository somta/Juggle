package net.somta.juggle.core.utils;

import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.http.HttpClientFactory;
import net.somta.juggle.core.http.IHttpClient;
import net.somta.juggle.core.http.Request;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {


    @Test
    public void getFromRequestTest(){
        IHttpClient formHttpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM);
        HttpClient httpClient = new HttpClient();
        //发送请求内容类型为application/x-www-form-urlencoded的GET请求
        //httpClient.sendRequest();


        //发送请求内容类型为application/json的GET请求


    }

    @Test
    public void getJsonRequestTest(){
        IHttpClient formHttpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM);
        HttpClient httpClient = new HttpClient();
        //发送请求内容类型为application/x-www-form-urlencoded的GET请求
        //httpClient.sendRequest();


        //发送请求内容类型为application/json的GET请求


    }

    // https://www.cnblogs.com/kiko2014551511/p/11609853.html
    @Test
    public void postFormRequestTest(){
        //todo 待补充
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        Request request = new Request(RequestTypeEnum.POST,"http://127.0.0.1:8686/example/user/login");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("userName","juggle");
        requestParam.put("password","123456");
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        System.out.println(rst);

    }


    @Test
    public void postJsonRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        Request request = new Request(RequestTypeEnum.POST,"http://127.0.0.1:8686/example/user/login");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("userName","juggle");
        requestParam.put("password","123456");
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        System.out.println(rst);

    }

}
