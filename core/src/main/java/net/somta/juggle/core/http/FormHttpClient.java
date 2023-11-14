package net.somta.juggle.core.http;

import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 */
public class FormHttpClient extends AbstractHttpClient{
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
                List<NameValuePair> list = new ArrayList<>();
                Iterator iterator = request.getRequestParams().entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
                }
                HttpEntity entity = null;
                try {
                    entity = new UrlEncodedFormEntity(list, AbstractHttpClient.UTF8_ENCODE);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                r.setEntity(entity);
            }
            httpRequest = r;
        }

        super.fillHttpHeader(httpRequest,request);
        super.fillHttpConfig(httpRequest,request);
        return super.handleHttpResponse(httpRequest);
    }
}
