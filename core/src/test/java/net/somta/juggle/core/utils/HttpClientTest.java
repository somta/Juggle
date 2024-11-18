package net.somta.juggle.core.utils;

import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.enums.ParameterPositionEnum;
import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.http.HttpClientFactory;
import net.somta.juggle.core.http.IHttpClient;
import net.somta.juggle.core.http.Request;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.InputParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientTest {


    ///////////////////////////////////////////////////////请求内容类型为application/json///////////////////////////////////////////////////////

    /**
     * JSON GET
     */
    //@Test
    public void getByJsonRequestTest(){
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        List<InputParameter> inputParamSchema = new ArrayList<>();
        InputParameter inputParameter1 = new InputParameter();
        inputParameter1.setKey("goodsName");
        inputParameter1.setName("goodsName");
        inputParameter1.setDataType(new DataType(DataTypeEnum.String));
        inputParameter1.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter1);

        InputParameter inputParameter2 = new InputParameter();
        inputParameter2.setKey("goodsInventory");
        inputParameter2.setName("goodsInventory");
        inputParameter2.setDataType(new DataType(DataTypeEnum.Integer));
        inputParameter2.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter2);

        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("goodsName","鞋");
        requestParam.put("goodsInventory",6);

        Request request = new Request(RequestTypeEnum.GET,inputParamSchema);
        request.initRequest("4a6054620efab47bbd69507b4de044c9","http://127.0.0.1:9127/example/goods/getGoodsInfo",null,requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resGoodsName = (String) rst.get("goodsName");
        Assertions.assertEquals(resGoodsName, "鞋");
    }

    //@Test
    public void postByJsonRequestTest(){

        List<InputParameter> inputParamSchema = new ArrayList<>();
        InputParameter inputParameter1 = new InputParameter();
        inputParameter1.setKey("goodsName");
        inputParameter1.setName("goodsName");
        inputParameter1.setDataType(new DataType(DataTypeEnum.String));
        inputParameter1.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter1);

        InputParameter inputParameter2 = new InputParameter();
        inputParameter2.setKey("goodsInventory");
        inputParameter2.setName("goodsInventory");
        inputParameter2.setDataType(new DataType(DataTypeEnum.Integer));
        inputParameter2.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter2);

        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("goodsName","鞋");
        requestParam.put("goodsInventory",6);

        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_JSON);
        Request request = new Request(RequestTypeEnum.GET,inputParamSchema);
        request.setRetryCount(3);
        request.initRequest("86eaa1477f519274bb05fc90b1e76e83","http://127.0.0.1:9127/example/goods/releaseGoods",null,requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resGoodsName = (String) rst.get("goodsName");
        Assertions.assertEquals(resGoodsName, "鞋");

    }


    ///////////////////////////////////////////////////////请求内容类型为application/x-www-form-urlencoded///////////////////////////////////////////////////////
   //@Test
   public void getByFromRequestTest(){
        //GET单参数接口
        List<InputParameter> inputParamSchema = new ArrayList<>();
        InputParameter inputParameter1 = new InputParameter();
        inputParameter1.setKey("orderNo");
        inputParameter1.setName("orderNo");
        inputParameter1.setDataType(new DataType(DataTypeEnum.String));
        inputParameter1.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter1);

        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("orderNo","NO123");

        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
        Request request = new Request(RequestTypeEnum.GET,inputParamSchema);

        request.initRequest("ad1ecc2a3dbd189f3fa478f30755a200","http://127.0.0.1:9127/example/order/getOrderByNo",null,requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resOrderNo = (String) rst.get("orderNo");
        Assertions.assertEquals(resOrderNo, "NO123");

        //////////////////////GET 对象参数接口//////////////////////////////////////
       List<InputParameter> inputParamSchema2 = new ArrayList<>();
       InputParameter inputParameter2 = new InputParameter();
       inputParameter2.setKey("orderNo");
       inputParameter2.setName("orderNo");
       inputParameter2.setDataType(new DataType(DataTypeEnum.String));
       inputParameter2.setPosition(ParameterPositionEnum.QUERY.getCode());
       inputParamSchema2.add(inputParameter2);

       Map<String,Object> requestParam2 = new HashMap<>(8);
       requestParam2.put("userId",1);

       IHttpClient httpClient2 = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
       Request request2 = new Request(RequestTypeEnum.GET,inputParamSchema2);

       request2.initRequest("0004812ec25f31c3f25596681997978d","http://127.0.0.1:9127/example/order/getUserOrderList",null,requestParam2);
       Map<String,Object> rst2 = httpClient2.sendRequest(request2);
       Integer resUserId = (Integer) rst2.get("userId");
       Assertions.assertEquals(resUserId, 1);
    }

    /**
     * Get请求，path参数接口
     */
    //@Test
    public void getPathByFromRequestTest(){
        List<InputParameter> inputParamSchema2 = new ArrayList<>();
        InputParameter inputParameter2 = new InputParameter();
        inputParameter2.setKey("userId");
        inputParameter2.setName("用户ID");
        inputParameter2.setDataType(new DataType(DataTypeEnum.Integer));
        inputParameter2.setPosition(ParameterPositionEnum.PATH.getCode());
        inputParamSchema2.add(inputParameter2);

        Map<String,Object> requestParam2 = new HashMap<>(8);
        requestParam2.put("userId",1);

        IHttpClient httpClient2 = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
        Request request2 = new Request(RequestTypeEnum.GET,inputParamSchema2);

        request2.initRequest("0004812ec25f31c3f25596681997978d","http://127.0.0.1:9127/example/order/getOrderListByUserId/{userId}",null,requestParam2);
        Map<String,Object> rst2 = httpClient2.sendRequest(request2);
        Integer resUserId = (Integer) rst2.get("userId");
        Assertions.assertEquals(resUserId, 1);
    }

    //@Test
    public void postByFormRequestTest(){
        List<InputParameter> inputParamSchema = new ArrayList<>();
        InputParameter inputParameter1 = new InputParameter();
        inputParameter1.setKey("orderNo");
        inputParameter1.setName("订单号");
        inputParameter1.setDataType(new DataType(DataTypeEnum.String));
        inputParameter1.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter1);
        InputParameter inputParameter2 = new InputParameter();
        inputParameter2.setKey("orderName");
        inputParameter2.setName("订单名称");
        inputParameter2.setDataType(new DataType(DataTypeEnum.String));
        inputParameter2.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter2);
        InputParameter inputParameter3 = new InputParameter();
        inputParameter3.setKey("userId");
        inputParameter3.setName("用户ID");
        inputParameter3.setDataType(new DataType(DataTypeEnum.Integer));
        inputParameter3.setPosition(ParameterPositionEnum.QUERY.getCode());
        inputParamSchema.add(inputParameter3);


        Map<String,Object> requestParam = new HashMap<>(8);
        requestParam.put("orderNo","NO123");
        requestParam.put("orderName","测试订单");
        requestParam.put("userId",1);

        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED);
        Request request = new Request(RequestTypeEnum.POST,inputParamSchema);
        request.initRequest("a141ec5b270a87fe4a42a2ea1300cf0d","http://127.0.0.1:9127/example/order/placeOrder",null,requestParam);
        Map<String,Object> rst = httpClient.sendRequest(request);
        String resOrderNo = (String) rst.get("orderNo");
        Assertions.assertEquals(resOrderNo, "NO123");

    }


}
