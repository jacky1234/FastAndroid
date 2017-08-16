package com.jackyang.android.support.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class JsonUtils {
    /**
     * JSON转换工具类附格式
     *
     * @param <T>
     * @param json {"status":1,"message":"success","results":[{..},{..}]}或
     *             {"status":1,"message":"success","results":{..}}
     * @returnd
     * @notice 用于转换对象及对象中的数组的JSON格式到Bean中
     */
    public static <T> T fromJSON(String json, Class<T> t) {
        Gson gson = new Gson();
        T obj = gson.fromJson(json, t);
        return obj;
    }

    /**
     * JSON转换工具类附格式
     *
     * @param t    泛型
     * @param json JSON数据  [{"id":0,"name":"name0"},{"id":1,"name":"name1"}]
     * @return List<T>
     * @notice 用于转换纯数组JSON格式到Bean中, 在3.0的项目中基本不会使用到
     */
    public static <T> List<T> getListFromJSON(Class<T> t, String json) {
        Gson gson = new Gson();
        List<T> lists = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();
            for (final JsonElement jsonElement : array) {
                T entity = gson.fromJson(jsonElement, t);
                lists.add(entity);
            }
        } catch (Exception e) {
            Log.e("==", e.getMessage());
        }
        return lists;
    }

    /**
     * JSON转List
     *
     * @param t    List泛型的ParameterizedType
     * @param json
     * @return
     * @author shaowei.ma
     * @date 2014年9月24日
     */
    public static <T> List<T> getListFromJSON(ParameterizedType t, String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }

    /**
     * 对象转JSON
     *
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
