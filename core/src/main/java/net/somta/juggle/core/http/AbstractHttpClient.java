package net.somta.juggle.core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.somta.core.helper.JsonSerializeHelper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author husong
 */
public abstract class AbstractHttpClient implements IHttpClient{

    protected final static String UTF8_ENCODE = "UTF-8";

    protected CloseableHttpClient httpClient;
    protected ObjectMapper objectMapper = new ObjectMapper();

    public AbstractHttpClient() {
        this.httpClient = HttpClients.createDefault();
    }

    protected void fillHttpHeader(HttpRequestBase httpRequest,Request request){
        Map<String,String> headers = request.getRequestHeaders();
        if(headers != null){
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpRequest.addHeader(header.getKey(), header.getValue());
            }
        }
    }

    protected void fillHttpConfig(HttpRequestBase httpRequest,Request request){
        RequestConfig.Builder builder = RequestConfig.custom();
        if(request.getTimeout() != null){
            // 设置响应超时时间
            builder.setSocketTimeout(request.getTimeout());
        }
        httpRequest.setConfig(builder.build());
    }

    protected Map<String, Object> handleHttpResponse(HttpRequestBase httpRequest){
        Map<String,Object> resultMap = new HashMap<>(8);
        try {
            // 发送同步请求  https://blog.csdn.net/weixin_32265569/article/details/108606783
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            System.out.println(httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            // 获取响应体的字符串
            String result = EntityUtils.toString(entity);
            resultMap = JsonSerializeHelper.deserialize(result, Map.class);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
