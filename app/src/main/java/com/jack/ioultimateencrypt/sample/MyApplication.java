package com.jack.ioultimateencrypt.sample;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jack.ioultimateencrypt.sample.service.Login;
import com.jack.ioultimateencrypt.sample.service.NetWork;
import com.jack.ioultimateencrypt.sample.service.impl.LoginImpl;
import com.jack.ioultimateencrypt.sample.service.impl.NetWorkImpl;
import com.jackyang.android.support.event.EventDispatcher;
import com.jackyang.android.support.event.impl.DefaultEventDispatcher;
import com.jackyang.android.support.injection.Injections;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);


        Injections.initialize(this)
                .registerSingleton(EventDispatcher.class, DefaultEventDispatcher.class)
                .registerSingleton(Login.class, LoginImpl.class)
                .registerSingleton(NetWork.class, NetWorkImpl.class)
        ;


    }
}
