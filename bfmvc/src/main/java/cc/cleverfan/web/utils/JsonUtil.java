package cc.cleverfan.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * POJO转换为JSON
 */
public class JsonUtil {

    /**
     * 将POJO转化为JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T obj) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 将JSON转化为POJO
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        T pojo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            pojo = mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pojo;
    }
}
