package net.somta.juggle.core.http;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import java.io.IOException;

/**
 * @author husong
 */
public class CustomRetryStrategy implements HttpRequestRetryStrategy {
    private Request request;
    public CustomRetryStrategy(Request request) {
        this.request = request;
    }

    @Override
    public boolean retryRequest(HttpRequest httpRequest, IOException e, int executionCount, HttpContext httpContext) {
        if(request.getRetryCount() == null && request.getRetryCount() > 0){
            return false;
        }
        return executionCount < request.getRetryCount();
    }

    @Override
    public boolean retryRequest(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
        if(request.getRetryCount() == null && request.getRetryCount() > 0){
            return false;
        }
        int statusCode = httpResponse.getCode();
        return statusCode >= 500 && executionCount < request.getRetryCount();
    }

    @Override
    public TimeValue getRetryInterval(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
        return TimeValue.ofMilliseconds(request.getRetryInterval());
    }
}
