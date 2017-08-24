package com.jackyang.android.support.repository;

import java.util.List;

/**
 * KeyValueStore, 存储key-value健值对
 * <p/>
 * Created by jackyang on 4/10/15.
 */
public interface KeyValueStore {
    /**
     * 清楚缓存
     */
    void clear();

    /**
     * 根据键名获取KeyValue
     *
     * @param key
     * @param targetType
     * @param <T>
     * @return not null
     */
    <T> List<T> getList(String key, Class<T> targetType);

    /**
     * 根据键名获取KeyValue
     *
     * @param key        键名
     * @param targetType 类型
     * @return KeyValue
     */
    <T> T get(String key, Class<T> targetType);


    /**
     * 设置健值对
     *
     * @param key    键名
     * @param target 值
     */
    <T> void set(String key, T target);

    /**
     * 获取字符串类型
     *
     * @param key 键名
     * @return 键值
     */
    String getString(String key);


    /**
     * 获取整型
     *
     * @param key 键名
     * @return 键值
     */
    Long getLong(String key);

    /**
     * 获取整型
     *
     * @param key 键名
     * @return 键值
     */
    Integer getInteger(String key);

    /**
     * 获取浮点型
     *
     * @param key 键名
     * @return 键值
     */
    Double getDouble(String key);

    /**
     * 获取浮点型
     *
     * @param key 键名
     * @return 键值
     */
    Float getFloat(String key);
}
