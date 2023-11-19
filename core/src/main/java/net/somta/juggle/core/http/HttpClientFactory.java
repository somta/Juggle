package net.somta.juggle.core.http;

import net.somta.juggle.core.enums.RequestContentTypeEnum;

/**
 * @author husong
 */
public class HttpClientFactory {

    public static IHttpClient getHttpClient(RequestContentTypeEnum requestContentType){
        IHttpClient httpClient = null;
        if (RequestContentTypeEnum.APPLICATION_JSON == requestContentType){
            httpClient = JsonHttpClient.getHttpClientInstance();
        } else if(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED == requestContentType){
            httpClient = FormHttpClient.getHttpClientInstance();
        } else {
            throw new IllegalArgumentException("requestContentType is not support!");
        }
        return httpClient;
    }
}
