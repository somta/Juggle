package net.somta.juggle.core.http;

import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 * https://www.wdbyte.com/tool/httpclient5/
 */
public class FormHttpClient extends AbstractHttpClient{
    @Override
    public Map<String, Object> sendRequest(Request request) {
        HttpUriRequestBase httpRequest = null;
        if (request.getRequestType() == RequestTypeEnum.GET) {
            //httpRequest = new HttpGet(ParamHelper.buildFormParams(request.getRequestUrl(),request.getRequestParams()));
            httpRequest = new HttpGet(request.getRequestUrl());
            List<NameValuePair> nvps = new ArrayList<>();
            if (request.getRequestParams() != null) {
                for (Map.Entry<String, Object> param : request.getRequestParams().entrySet()) {
                    nvps.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
                }
                try {
                    URI uri = new URIBuilder(new URI(request.getRequestUrl()))
                            .addParameters(nvps)
                            .build();
                    httpRequest.setUri(uri);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if (request.getRequestType() == RequestTypeEnum.DELETE) {
            httpRequest = new HttpDelete(request.getRequestUrl());
        } else {
            if (request.getRequestType() == RequestTypeEnum.PUT) {
                httpRequest = new HttpPut(request.getRequestUrl());
            } else {
                httpRequest = new HttpPost(request.getRequestUrl());
            }
            if (request.getRequestParams() != null) {
                List<NameValuePair> list = new ArrayList<>();
                Iterator iterator = request.getRequestParams().entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));
                }
                HttpEntity entity = new UrlEncodedFormEntity(list);
                httpRequest.setEntity(entity);
            }
        }

        super.fillHttpHeader(httpRequest,request);
        super.fillHttpConfig(httpRequest,request);
        return super.handleHttpResponse(httpRequest);
    }
}
