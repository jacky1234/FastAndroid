package com.jackyang.android.support.repository.support;


import com.jackyang.android.support.repository.KeyValue;

/**
 * KeyValueImpl
 * <p/>
 * Created by jackyang on 4/10/15.
 */
public class KeyValueImpl<T> implements KeyValue<T> {

    private String key;

    private T value;

    public KeyValueImpl(String key, T value) {
        this.key = key;
        this.value = value;
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
