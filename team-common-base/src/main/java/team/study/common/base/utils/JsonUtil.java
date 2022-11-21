package team.study.common.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 *
 * @author JiaHao
 * @date 2022-11-20 18:54
 * @description json工具类
 */
public class JsonUtil {
    public JsonUtil() {
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String jsonToString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将jsonString转成泛型bean
     *
     * @param jsonString json 字符串
     * @param cls        bean类型
     * @return bean类型
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        return JSON.parseObject(jsonString, cls);
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param jsonString json 字符串
     * @param cls        bean类型
     * @return List<bean></bean>类型
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }

    /**
     * 转成list中有map的
     *
     * @param jsonString json 字符串
     * @return List<Map < String, bean>>类型
     */
    public static <T> List<Map<String, T>> jsonToListMaps(String jsonString) {
        return JSON.parseObject(jsonString, new TypeReference<List<Map<String, T>>>() {
        });
    }

    /**
     * 转成map的
     *
     * @param jsonString json 字符串
     * @return Map<String, bean>
     */
    public static <T> Map<String, T> jsonToMaps(String jsonString) {
        return JSON.parseObject(jsonString, new TypeReference<HashMap<String, T>>() {
        });
    }

    /**
     * 把一个bean（或者其他的字符串什么的）转成json
     *
     * @param object bean对象
     * @return String
     */
    public static String beanToJson(Object object) {
        return JSON.toJSONString(object);
    }
}
