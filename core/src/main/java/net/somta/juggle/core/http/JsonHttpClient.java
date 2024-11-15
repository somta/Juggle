/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

/**
 * @author husong
 * @since 1.0.0
 */
public class JsonHttpClient extends AbstractHttpClient{

    private static volatile IHttpClient jsonHttpClient;

    private JsonHttpClient(){
        super();
    }

    public static IHttpClient getHttpClientInstance() {
        if(jsonHttpClient == null){
            synchronized (JsonHttpClient.class){
                if(jsonHttpClient == null){
                    jsonHttpClient = new JsonHttpClient();
                }
            }
        }
        return jsonHttpClient;
    }

    @Override
    protected void buildRequestParams(HttpUriRequestBase httpRequest, Request request) {
        if (request.getRequestParams() != null) {
            String jsonParam = null;
            try {
                jsonParam = JsonSerializeHelper.serialize(request.getRequestParams());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(stringEntity);
        }
    }
}
