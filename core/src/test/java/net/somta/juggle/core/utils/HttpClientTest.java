package net.somta.juggle.core.utils;

import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.http.HttpClientFactory;
import net.somta.juggle.core.http.IHttpClient;
import net.somta.juggle.core.http.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {


    ///////////////////////////////////////////////////////请求内容类型为application/json///////////////////////////////////////////////////////
    @Test
    public void getByJsonRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        Request request = new Request(RequestTypeEnum.GET,"http://127.0.0.1:8686/example/goods/getGoodsInfo");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("goodsName","鞋");
        requestParam.put("goodsInventory",6);
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resGoodsName = (String) rst.get("goodsName");
        Assertions.assertEquals(resGoodsName, "鞋");
    }

    @Test
    public void postByJsonRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        Request request = new Request(RequestTypeEnum.GET,"http://127.0.0.1:8686/example/goods/releaseGoods");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("goodsName","鞋");
        requestParam.put("goodsInventory",6);
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resGoodsName = (String) rst.get("goodsName");
        Assertions.assertEquals(resGoodsName, "鞋");

    }


    ///////////////////////////////////////////////////////请求内容类型为application/x-www-form-urlencoded///////////////////////////////////////////////////////
    @Test
    public void getByFromRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
        //GET单参数接口
        Request request = new Request(RequestTypeEnum.GET,"http://127.0.0.1:8686/example/order/queryOrderByNo");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("orderNo","NO123");
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resOrderNo = (String) rst.get("orderNo");
        Assertions.assertEquals(resOrderNo, "NO123");

        //GET对象参数接口
        Request request2 = new Request(RequestTypeEnum.GET,"http://127.0.0.1:8686/example/order/queryUserOrderList");
        Map<String,Object> requestParam2 = new HashMap<>(8);
        requestParam2.put("userId",1);
        request2.setRequestParams(requestParam2);
        Map<String,Object> rst2 = httpClient.sendRequest(request2);
        Integer resUserId = (Integer) rst2.get("userId");
        Assertions.assertEquals(resUserId, 1);


        //todo get 的path请求
    }

    @Test
    public void postByFormRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
        Request request = new Request(RequestTypeEnum.POST,"http://127.0.0.1:8686/example/order/placeOrder");
        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("orderNo","NO123");
        requestParam.put("orderName","测试订单");
        requestParam.put("userId",1);
        request.setRequestParams(requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resOrderNo = (String) rst.get("orderNo");
        Assertions.assertEquals(resOrderNo, "NO123");

    }


}
