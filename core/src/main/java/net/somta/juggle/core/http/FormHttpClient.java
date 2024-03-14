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

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 * @since 1.0.0
 */
public class FormHttpClient extends AbstractHttpClient{

    private static volatile IHttpClient formHttpClient;

    private FormHttpClient(){
        super();
    }

    public static IHttpClient getHttpClientInstance() {
        if(formHttpClient == null){
            synchronized (FormHttpClient.class){
                if(formHttpClient == null){
                    formHttpClient = new FormHttpClient();
                }
            }
        }
        return formHttpClient;
    }

    @Override
    protected void buildRequestParams(HttpUriRequestBase httpRequest, Request request) {
        if (request.getRequestParams() != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> param : request.getRequestParams().entrySet()) {
                list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
            }
            try {
                URI uri = new URIBuilder(new URI(request.getRequestUrl()))
                        .addParameters(list)
                        .build();
                httpRequest.setUri(uri);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
