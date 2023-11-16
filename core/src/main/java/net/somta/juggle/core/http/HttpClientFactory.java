package net.somta.juggle.core.http;

import net.somta.juggle.core.enums.RequestContentTypeEnum;

/**
 * @author husong
 */
public class HttpClientFactory {

    public static IHttpClient getHttpClient(RequestContentTypeEnum requestContentType){
        IHttpClient httpClient = null;
        if (RequestContentTypeEnum.APPLICATION_JSON == requestContentType){
            httpClient = new JsonHttpClient();
        } else if(RequestContentTypeEnum.APPLICATION_FORM_URLENCODED == requestContentType){
            httpClient = new FormHttpClient();
        } else {
            throw new IllegalArgumentException("requestContentType is not support!");
        }
        return httpClient;
    }
}
