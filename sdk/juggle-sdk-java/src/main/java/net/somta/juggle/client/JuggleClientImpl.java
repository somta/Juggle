package net.somta.juggle.client;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.client.model.FlowResultModel;
import net.somta.juggle.client.model.FlowTriggerDataParam;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 * @since 1.2.0
 */
public class JuggleClientImpl implements JuggleClient {

    private static final String TRIGGER_FLOW_URL = "/open/v1/flow/trigger/%s/%s";
    private static final String GET_ASYNC_FLOW_RESULT_URL = "/open/v1/flow/getAsyncFlowResult/%s";

    private final JuggleConfig juggleConfig;
    private PoolingHttpClientConnectionManager connectionManager;

    public JuggleClientImpl(JuggleConfig juggleConfig) {
        this.juggleConfig = juggleConfig;
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(10);
    }

    @Override
    public ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException{
        String url = juggleConfig.getServerAddr() + String.format(TRIGGER_FLOW_URL, flowVersion, flowKey);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpPost(url);
        fillCommonHttpHeader(request, juggleConfig.getAccessToken());
        if (triggerData.getFlowData() != null) {
            String triggerDataJson = JsonSerializeHelper.serialize(triggerData);
            StringEntity entity = new StringEntity(triggerDataJson, ContentType.APPLICATION_JSON);
            request.setEntity(entity);
        }

        final HttpClientResponseHandler<ResponseDataResult<FlowResultModel>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            ResponseDataResult<FlowResultModel> result = JsonSerializeHelper.deserialize(resultContent, ResponseDataResult.class, FlowResultModel.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return result;
        };

        ResponseDataResult<FlowResultModel> response = httpClient.execute(request,responseHandler);
        return response;
    }

    @Override
    public ResponseDataResult<Map<String, Object>> getAsyncFlowResult(String flowInstanceId) throws IOException {
        String url = juggleConfig.getServerAddr() + String.format(GET_ASYNC_FLOW_RESULT_URL, flowInstanceId);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpGet(url);
        fillCommonHttpHeader(request, juggleConfig.getAccessToken());

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
     * @param accessToken request access token
     */
    private void fillCommonHttpHeader(HttpUriRequestBase httpRequest, String accessToken){
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.addHeader("Juggle-Token",accessToken);
    }


}
