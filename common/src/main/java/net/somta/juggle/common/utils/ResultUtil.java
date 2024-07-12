package net.somta.juggle.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class ResultUtil {

    public static Object getValueFromResultData(String paramKey,Map<String, Object> originalResult){
        boolean containsDot = paramKey.contains(".");
        if(containsDot){
            Object value = null;
            String[] params = paramKey.split("\\.");
            Map<String, Object> temp = new HashMap<>();
            for (int i = 0; i < params.length; i++) {
                String currentKey = params[i];
                if(i+1 < params.length){
                    if(temp.isEmpty()){
                        temp = (Map<String, Object>) originalResult.get(currentKey);
                    }else {
                        temp = (Map<String, Object>) temp.get(currentKey);
                    }
                }else {
                    if(temp.isEmpty()){
                        value = originalResult.get(currentKey);
                    }else {
                        value = temp.get(currentKey);
                    }
                }
            }
            return value;
        } else {
            return originalResult.get(paramKey);
        }
    }

    public static Map<String, Object> buildResultMap(Map<String, Object> result){

        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> nestedMap = new HashMap<>();

        Map<String, Object> innerMap = new HashMap<>();
        innerMap.put("key2", "value2");

        Map<String, Object> map = new HashMap<>();
        map.put("key3", "value3");
        innerMap.put("key21", map);

        nestedMap.put("key1", "value1");
        nestedMap.put("map1", innerMap);


        // 使用JsonPath获取嵌套Map中的值
        //String value = JsonPath.read(nestedMap, "$.key3.key2");

        /*Object value1 = getValueFromResultData("key1",nestedMap);
        System.out.println(value1); // 输出 "value2"*/

        /*Object value2 = getValueFromResultData("map1.key2",nestedMap);
        System.out.println(value2);*/

        Object value3 = getValueFromResultData("map1.key21.key3",nestedMap);
        System.out.println(value3);

    }

}
