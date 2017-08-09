package com.jackyang.android.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.facebook.stetho.Stetho;
import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.prefs.SharedPreferenceKeyValueStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

@RunWith(AndroidJUnit4.class)
public class KeyValueTest {
    private Context appContext;
    private SharedPreferences sharedPreferences;
    private KeyValueStore keyValueStore;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
        sharedPreferences = appContext.getSharedPreferences("blackboard_pref", Context.MODE_PRIVATE);
        keyValueStore = new SharedPreferenceKeyValueStore(sharedPreferences);
        Stetho.initializeWithDefaults(appContext);
    }


    @Test
    public void store() {
        // Context of the app under test.
        keyValueStore.set("intkey", 10);
    }

    @Test
    public void read() {
        final Integer intkey = keyValueStore.get("intkey", Integer.class).getValue();
        Utils.P(intkey);
    }
}
