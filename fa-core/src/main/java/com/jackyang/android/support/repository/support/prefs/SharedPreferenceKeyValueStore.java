package com.jackyang.android.support.repository.support.prefs;

import android.content.SharedPreferences;

import com.jackyang.android.support.repository.KeyValue;
import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.KeyValueImpl;
import com.jackyang.android.support.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;


/**
 * SharedPreferenceKeyValueStore,使用android SharedPreferences存储key-value
 * <p/>
 * Created by jackyang on 4/10/15.
 */
public class SharedPreferenceKeyValueStore implements KeyValueStore {

    /**
     * SharedPreferences
     */
    private SharedPreferences delegate;

    public SharedPreferenceKeyValueStore(SharedPreferences delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> KeyValue<T> get(String key, Class<T> targetType) {
        String value = delegate.getString(key, StringUtils.EMPTY);
        return new KeyValueImpl<T>(key, JsonUtils.fromJSON(value, targetType));
    }

    @Override
    public String getString(String key) {
        return get(key, String.class).getValue();
    }

    @Override
    public Long getLong(String key) {
        return get(key, Long.class).getValue();
    }

    @Override
    public Integer getInteger(String key) {
        return get(key, Integer.class).getValue();
    }

    @Override
    public Double getDouble(String key) {
        return get(key, Double.class).getValue();
    }

    @Override
    public Float getFloat(String key) {
        return get(key, Float.class).getValue();
    }

    @Override
    public <T> void set(String key, T target) {
        delegate.edit().putString(key, JsonUtils.toJSON(target)).commit();
    }
}
