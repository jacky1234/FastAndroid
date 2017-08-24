package com.jack.ioultimateencrypt.sample;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.jack.ioultimateencrypt.sample.service.Login;
import com.jack.ioultimateencrypt.sample.service.NetWork;
import com.jack.ioultimateencrypt.sample.service.impl.LoginImpl;
import com.jack.ioultimateencrypt.sample.service.impl.NetWorkImpl;
import com.jackyang.android.support.event.EventDispatcher;
import com.jackyang.android.support.event.impl.DefaultEventDispatcher;
import com.jackyang.android.support.injection.Injections;
import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.prefs.SharedPreferenceKeyValueStore;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class MyApplication extends Application {
    public static Context gContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gContext = this;
        Utils.init(this);
        Stetho.initializeWithDefaults(this);


        Injections.initialize(this)
                .registerBean(KeyValueStore.class, new SharedPreferenceKeyValueStore(getSharedPreferences("fast_android", MODE_PRIVATE)))
                .registerSingleton(EventDispatcher.class, DefaultEventDispatcher.class)
                .registerSingleton(Login.class, LoginImpl.class)
                .registerSingleton(NetWork.class, NetWorkImpl.class)
        ;

    }

}
