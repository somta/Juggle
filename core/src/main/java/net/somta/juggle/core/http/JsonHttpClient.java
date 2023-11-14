package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;

import java.util.Map;

/**
 * @author husong
 */
public class JsonHttpClient extends AbstractHttpClient{
    @Override
    public Map<String, Object> sendRequest(Request request) {
        HttpRequestBase httpRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            httpRequest = new HttpGet(request.getRequestUrl());
        }else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            httpRequest = new HttpDelete(request.getRequestUrl());
        } else {
            HttpEntityEnclosingRequestBase r = null;
            if (request.getRequestType() == RequestTypeEnum.PUT) {
                r = new HttpPut(request.getRequestUrl());
            } else {
                r = new HttpPost(request.getRequestUrl());
            }
            if (request.getRequestParams() != null) {
                String jsonParam = JsonSerializeHelper.serialize(request.getRequestParams());
                StringEntity stringEntity = new StringEntity(jsonParam,AbstractHttpClient.UTF8_ENCODE);
                stringEntity.setContentEncoding(AbstractHttpClient.UTF8_ENCODE);
                stringEntity.setContentType("application/json");
                r.setEntity(stringEntity);
            }
            httpRequest = r;
        }

        super.fillHttpHeader(httpRequest,request);
        super.fillHttpConfig(httpRequest,request);
        return super.handleHttpResponse(httpRequest);
    }
}
