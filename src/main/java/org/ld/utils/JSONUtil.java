package org.ld.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ld.exception.CodeException;

import java.io.IOException;
import java.util.*;

/**
 * Created by leon on 17-5-10.
 */
public class JSONUtil {

    public static <T> List<T> json2List(String json, Class<T> cls) {
        JsonNode jsonNode = toJsonNode(json);
        if (jsonNode == null) return Collections.emptyList();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
        return objectMapper.convertValue(jsonNode, type);
    }

    public static <T> T json2Obj(String json, Class<T> cls) {
        JsonNode jsonNode = toJsonNode(json);
        if (jsonNode == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(jsonNode, cls);
    }

    public static  Map<String, Object> String2Map(String json) {
        JsonNode jsonNode = toJsonNode(json);
        if (jsonNode == null) return Collections.emptyMap();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        return objectMapper.convertValue(jsonNode, type);
    }

    public static Map<String, Object> getMap(String json) {
        if (StringUtil.isBlank(json)) return Collections.emptyMap();
        try {
            return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new CodeException(e);
        }
    }

    private static JsonNode toJsonNode(String expression) {
        if (StringUtil.isBlank(expression)) return null;
        try {
            return new ObjectMapper().readTree(expression);
        } catch (IOException e) {
            throw new CodeException(e);
        }
    }
}
