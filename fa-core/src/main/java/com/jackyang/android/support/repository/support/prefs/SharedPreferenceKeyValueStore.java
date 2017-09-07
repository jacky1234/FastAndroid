package com.jackyang.android.support.repository.support.prefs;

import android.content.SharedPreferences;

import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.KeyValueImpl;
import com.jackyang.android.support.utils.JsonUtils;

import java.util.List;


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
    public void clear() {
        this.delegate.edit().clear().commit();
    }

    @Override
    public <T> List<T> getList(String key, Class<T> targetType) {
        String value = delegate.getString(key, null);
        return new KeyValueImpl<T>(key, JsonUtils.getListFromJSON(value, targetType)).getValues();
    }

    @Override
    public <T> T get(String key, Class<T> targetType) {
        String value = delegate.getString(key, "");
        return new KeyValueImpl<T>(key, JsonUtils.fromJSON(value, targetType)).getValue();
    }

    @Override
    public String getString(String key) {
        return get(key, String.class);
    }

    @Override
    public Long getLong(String key) {
        return get(key, Long.class);
    }

    @Override
    public Integer getInteger(String key) {
        return get(key, Integer.class);
    }

    @Override
    public Double getDouble(String key) {
        return get(key, Double.class);
    }

    @Override
    public Float getFloat(String key) {
        return get(key, Float.class);
    }

    @Override
    public Boolean getBoolean(String key) {
        return get(key, Boolean.class);
    }

    @Override
    public <T> void set(String key, T target) {
        delegate.edit().putString(key, JsonUtils.toJSON(target)).commit();
    }
}
