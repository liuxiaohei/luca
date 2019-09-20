package org.ld.utils;

import org.springframework.lang.NonNull;

@SuppressWarnings("unused")
public class JSONUtil {
    private static final Logger logger = Logger.newInstance(JSONUtil.class);


    /**
     * 将json字符串转换为对象
     */
    public static <T> T json2Obj(String json, @NonNull Class<T> c) {
        //todo
        return null;
    }

    /**
     * 检查空JSON
     */
    private static boolean isEmptyJson(String json) {
        return (StringUtil.isEmpty(json) || json.matches("\\{\\s*\\}"));
    }

}
