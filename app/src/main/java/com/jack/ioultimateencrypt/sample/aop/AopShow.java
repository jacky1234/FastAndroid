package com.jack.ioultimateencrypt.sample.aop;

import android.os.Looper;

import com.jack.ioultimateencrypt.sample.module.Car;
import com.jack.ioultimateencrypt.sample.module.Person;
import com.jack.test.logger.Log;
import com.jackyang.android.aop.annotation.Async;
import com.jackyang.android.aop.annotation.Cacheable;
import com.jackyang.android.aop.annotation.DebugTrace;
import com.jackyang.android.aop.annotation.LogMethod;
import com.jackyang.android.aop.annotation.Prefs;
import com.jackyang.android.aop.annotation.Safe;

/**
 * 2017/8/11.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class AopShow {
    private static final String TAG = "AopShow";

    @Async
    public static void asynTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("current thread=").append(Thread.currentThread().getId())
                .append("\r\n")
                .append("ui thread=")
                .append(Looper.getMainLooper().getThread().getId());
        Log.d(TAG, sb.toString());
    }

    @Cacheable(key = "person")
    public static Person cacheable_save() {
        Person p = new Person();
        p.setAge(10);
        p.setName("jackyang");
        Log.d(TAG, p.toString());
        return p;
    }

    @Cacheable(key = "car", expiry = 5)
    public static Car cacheable_save2() {
        Car car = new Car();
        car.setBrand("奔腾汽车");
        car.setManufacturer("上海一汽大众");
        Log.d(TAG, "car save in 5s:" + car.toString());
        return car;
    }

    @Async
    @DebugTrace
    public static void debugTest() {
        try {
            Thread.sleep(200);
            Log.d(TAG, "in debug test");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @LogMethod
    public static Person logTest(String name, int age) {
        Person p = new Person();
        p.setName(name);
        p.setAge(age);

        return p;
    }

    @Prefs(key = "Person")
    public static Person pref_save() {
        Person p = new Person();
        p.setAge(100);
        p.setName("alice");
        Log.d(TAG, "pref_save Person:" + p.toString());
        return p;
    }

    @Safe
    public static void safe_test(){
        String s = null;
        s.hashCode();
    }
}
