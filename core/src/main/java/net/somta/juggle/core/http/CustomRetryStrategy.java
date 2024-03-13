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

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import java.io.IOException;

/**
 * @author husong
 * @since 1.0.0
 */
public class CustomRetryStrategy implements HttpRequestRetryStrategy {
    private Request request;
    public CustomRetryStrategy(Request request) {
        this.request = request;
    }

    @Override
    public boolean retryRequest(HttpRequest httpRequest, IOException e, int executionCount, HttpContext httpContext) {
        if(request.getRetryCount() == null || request.getRetryCount() <= 0){
            return false;
        }
        return executionCount < request.getRetryCount();
    }

    @Override
    public boolean retryRequest(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
        if(request.getRetryCount() == null || request.getRetryCount() <= 0){
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
