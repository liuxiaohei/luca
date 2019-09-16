package org.ld.utils;


import com.google.gson.*;
import org.springframework.lang.NonNull;


public class JSONUtil {
    private static final Logger logger = Logger.newInstance(JSONUtil.class);

    private static final Gson GSON = new GsonBuilder().create();
    private static final Gson GSON_PRETTY = new GsonBuilder().setPrettyPrinting().create();


    /**
     * 将json字符串转换为对象
     */
    public static <T> T json2Obj(String json, @NonNull Class<T> c) {
        if (isEmptyJson(json)) {
            return null;
        }
        try {
            return GSON.fromJson(json, c);
        } catch (Exception e) {
            logger.printStackTrace(e);
            return null;
        }
    }

    /**
     * 检查空JSON
     */
    private static boolean isEmptyJson(String json) {
        return (StringUtil.isEmpty(json) || json.matches("\\{\\s*\\}"));
    }

}
