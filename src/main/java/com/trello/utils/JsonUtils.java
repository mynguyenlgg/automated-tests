package com.trello.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> List<T> toList(String jsonPath, Type type) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    public static <T> List<T> toList(String jsonPath, Class<T> clazz) {
        Type type = TypeToken.getParameterized(Collection.class, clazz).getType();
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    public static <T> List<T> toList(JsonArray array, Class<T> clazz) {
        Type type = TypeToken.getParameterized(Collection.class, clazz).getType();
        Gson gson = new Gson();
        return gson.fromJson(array, type);
    }

    public static <T> T to(String jsonPath, Type type) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    public static <T> T to(String jsonPath, Class<T> clazz) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    public static <T> T to(String jsonPath, String key, Class<T> clazz) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        if (!json.has(key)) {
            throw new RuntimeException(String.format("Cannot find the key '%s' on json file %s ", key, jsonPath));
        }
        String value = json.getAsJsonObject(key).toString();
        return gson.fromJson(value, clazz);
    }

    public static <T> T to(String jsonPath, String key, Type type) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        if (!json.has(key)) {
            throw new RuntimeException(String.format("Cannot find the key '%s' on json file %s ", key, jsonPath));
        }
        String value = json.getAsJsonObject(key).toString();
        return gson.fromJson(value, type);
    }

    public static <T> List<T> toList(String jsonPath, String key, Class<T> clazz) {
        log.debug("Load json from {}", jsonPath);
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        String value = json.getAsJsonArray(key).toString();
        Type type = TypeToken.getParameterized(Collection.class, clazz).getType();
        return gson.fromJson(value, type);
    }

    private static JsonReader getJsonReader(String jsonPath) {
        JsonReader reader;
        try {
            reader = new JsonReader(new InputStreamReader(new FileInputStream(jsonPath), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new RuntimeException("Cannot read json file from " + jsonPath + "\n" + e.getMessage());
        }
        return reader;
    }

    public static JsonObject toJsonObject(Object object) {
        Gson gson = new Gson();
        return gson.toJsonTree(object).getAsJsonObject();
    }

    public static JsonObject toJsonObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonObject.class);
    }

    public static <T> T toObject(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> T toObject(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static boolean isNotNull(JsonElement json) {
        return json != null && !json.isJsonNull();
    }

    public static Map<String, Object> toMap(JsonElement jsonObject) {
        return new Gson().fromJson(jsonObject, new TypeToken<HashMap<String, Object>>() {}.getType());
    }
}
