package com.jackyang.android.support.repository.support;


import com.jackyang.android.support.repository.KeyValue;

import java.util.List;

/**
 * KeyValueImpl
 * <p/>
 * Created by jackyang on 4/10/15.
 */
public class KeyValueImpl<T> implements KeyValue<T> {

    private String key;

    private T value;

    private List<T> values;

    public KeyValueImpl(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueImpl(String key, List<T> values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public List<T> getValues() {
        return values;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public T getValue() {
        return this.value;
    }


    @Override
    public void setValue(T target) {
        this.value = target;
    }
}
