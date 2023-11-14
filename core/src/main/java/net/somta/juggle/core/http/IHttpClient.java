package net.somta.juggle.core.http;

import java.util.Map;

/**
 * @author husong
 */
public interface IHttpClient {

    Map<String,Object> sendRequest(Request request);
}
