package net.somta.juggle.starter.impl;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.http.CustomRetryStrategy;
import net.somta.juggle.core.http.Request;
import net.somta.juggle.core.model.FlowResult;
import net.somta.juggle.starter.properties.JuggleOpenProperties;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
public class JuggleTemplateImpl implements IJuggleTemplate{

    private static final String TRIGGER_FLOW_URL = JUGGLE_SERVER_VERSION + "/open/flow/trigger/%s/%s";
    private static final String GET_ASYNC_FLOW_RESULT_URL = JUGGLE_SERVER_VERSION + "/open/flow/getAsyncFlowResult/%s";

    private PoolingHttpClientConnectionManager connectionManager;
    private final JuggleOpenProperties juggleOpenProperties;
    public JuggleTemplateImpl(JuggleOpenProperties juggleOpenProperties) {
        this.juggleOpenProperties = juggleOpenProperties;
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(5);
    }

    @Override
    public ResponseDataResult<FlowResult> triggerFlow(String flowVersion, String flowKey, TriggerDataParam triggerData) throws IOException {
        String url = juggleOpenProperties.getServerAddr() + String.format(TRIGGER_FLOW_URL, flowVersion, flowKey);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpPost(url);
        fillCommonHttpHeader(request, juggleOpenProperties.getCredential());
        if (triggerData.getFlowData() != null) {
            String bodyJson = JsonSerializeHelper.serialize(triggerData.getFlowData());
            StringEntity stringEntity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
            request.setEntity(stringEntity);
        }

        final HttpClientResponseHandler<ResponseDataResult<FlowResult>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            ResponseDataResult<FlowResult> result = JsonSerializeHelper.deserialize(resultContent, ResponseDataResult.class, FlowResult.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return result;
        };

        ResponseDataResult<FlowResult> response = httpClient.execute(request,responseHandler);
        return response;
    }

    @Override
    public ResponseDataResult<Map<String, Object>> getAsyncFlowResult(String flowInstanceId) throws IOException {
        String url = juggleOpenProperties.getServerAddr() + String.format(GET_ASYNC_FLOW_RESULT_URL, flowInstanceId);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpGet(url);
        fillCommonHttpHeader(request, juggleOpenProperties.getCredential());

        final HttpClientResponseHandler<ResponseDataResult<Map<String, Object>>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            ResponseDataResult<Map<String, Object>> result = JsonSerializeHelper.deserialize(resultContent, ResponseDataResult.class, Map.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return result;
        };
        ResponseDataResult<Map<String, Object>> response = httpClient.execute(request,responseHandler);
        return response;
    }

    /**
     * get http client
     * @return HttpClient
     */
    private HttpClient getHttpClient() {
        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        return httpClient;
    }

    /**
     * fill common http header
     * @param httpRequest http request
     * @param credential request credential
     */
    private void fillCommonHttpHeader(HttpUriRequestBase httpRequest, String credential){
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.addHeader("Juggle-Credential",credential);
    }
}
