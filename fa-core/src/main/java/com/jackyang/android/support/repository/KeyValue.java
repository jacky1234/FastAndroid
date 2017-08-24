package com.jackyang.android.support.repository;

import java.util.List;

/**
 * 健值对
 *
 * @param <T>
 */
public interface KeyValue<T> {

    /**
     * 获取list值
     * @return
     */
    List<T> getValues();

    /**
     * @return 获取键名
     */
    String getKey();

    /**
     * @return 获取值
     */
    T getValue();


    /**
     * 设置值
     *
     * @param target 新值
     */
    void setValue(T target);
}
