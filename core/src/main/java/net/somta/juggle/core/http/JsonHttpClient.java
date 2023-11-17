package net.somta.juggle.core.http;

import net.somta.core.helper.JsonSerializeHelper;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

/**
 * @author husong
 */
public class JsonHttpClient extends AbstractHttpClient{

    @Override
    protected void buildRequestParams(HttpUriRequestBase httpRequest, Request request) {
        if (request.getRequestParams() != null) {
            String jsonParam = JsonSerializeHelper.serialize(request.getRequestParams());
            StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(stringEntity);
        }
    }
}
