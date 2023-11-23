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