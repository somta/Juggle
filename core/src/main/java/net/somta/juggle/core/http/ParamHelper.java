package net.somta.juggle.core.http;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import org.apache.commons.lang3.ArrayUtils;

import java.net.URISyntaxException;
import java.util.Map;

public class ParamHelper {


    /**
     * 构建GET请求参数
     * @param url v1/user/info
     * @param params name=gavin
     * @return eg: v1/user/info?name=gavin
     */
    public static String buildFormParams(String url, Map<String, Object> params){
        UriTemplateBuilder templateBuilder = UriTemplate.buildFromTemplate(url);
        if(params != null){
            templateBuilder.query(ArrayUtils.toStringArray(params.keySet().toArray()));
            UriTemplate template = templateBuilder.build();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                template.set(param.getKey(), param.getValue());
            }
            return template.expand();
        }
        return url;
    }

    /**
     * 填充请求体参数至Post请求中
     * @param httpPost HttpPost
     * @param params 请求参数
     * @return HttpPost
     */
    /*public static HttpPost setParamsToPost(HttpPost httpPost, Map<String, Object> params) {
        if(params == null){
            return httpPost;
        }
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = null;
            if (entry.getValue() == null) {
                pair = new BasicNameValuePair(entry.getKey(), null);
            } else {
                pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
            }
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        return httpPost;
    }*/

    /**
     * 填充请求体参数至Put请求中
     * @param httpPut httpPut
     * @param params 请求参数
     * @return HttpPost
     */
    /*public static HttpPut setParamsToPut(HttpPut httpPut, Map<String, Object> params) {

        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = null;
            if (entry.getValue() == null) {
                pair = new BasicNameValuePair(entry.getKey(), null);
            } else {
                pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
            }
            pairList.add(pair);
        }
        httpPut.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        return httpPut;
    }*/

    /**
     * 填充请求体参数至Delete请求中
     * @param httpDelete HttpDelete
     * @param url 请求地址
     * @param params 请求参数
     * @return HttpDelete
     * @throws URISyntaxException url exception
     */
    /*public static HttpDelete setParamsToDelete(HttpDelete httpDelete,String url, Map<String, Object> params) throws URISyntaxException {
        if (params != null) {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    uriBuilder.setParameter(key, params.get(key).toString());
                }
            }
            URI uri = uriBuilder.build();
            httpDelete.setURI(uri);
        }
        return httpDelete;
    }*/


    /*public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("name","husong");
        params.put("age",18);
        buildGetParams("v1/name",params);
    }*/

}
