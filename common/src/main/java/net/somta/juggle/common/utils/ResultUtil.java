package net.somta.juggle.common.utils;

import java.util.*;

public class ResultUtil {

    public static Object getResultFromData(String paramKey,Map<String, Object> originalResult){
        boolean containsDot = paramKey.contains(".");
        if(containsDot){
            Object value = null;
            String[] params = paramKey.split("\\.");
            for (int i = 0; i < params.length; i++) {
                String currentKey = params[i];
                System.out.println(params[i]);
                if(i < params.length){
                    Map<String, Object> temp = (Map<String, Object>) originalResult.get(currentKey);
                }else {
                    value = originalResult.get(currentKey);
                }

            }

            List<String> stringList = Arrays.asList(params);

            // 获取迭代器
            Iterator<String> iterator = stringList.iterator();

            // 使用迭代器遍历列表
            while (iterator.hasNext()) {
                String str = iterator.next();
                System.out.println(str);

            }

            return value;
        } else {
            return originalResult.get(paramKey);
        }
    }

    public static void main(String[] args) {
        Map<String, Object> nestedMap = new HashMap<>();
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put("key2", "value2");

        nestedMap.put("key1", "value1");
        nestedMap.put("key3", innerMap);

        // 使用JsonPath获取嵌套Map中的值
        //String value = JsonPath.read(nestedMap, "$.key3.key2");
        /*Object value1 = getResultFromData("key1",nestedMap);
        System.out.println(value1); // 输出 "value2"*/

        Object value2 = getResultFromData("key3.key2",nestedMap);
        System.out.println(value2);

    }

}
