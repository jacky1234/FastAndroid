package com.jack.ioultimateencrypt.sample.ui;

import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.base.BaseActivity;
import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.aop.AopShow;
import com.jack.ioultimateencrypt.sample.module.Car;
import com.jack.ioultimateencrypt.sample.module.Person;
import com.jack.test.logger.Log;
import com.jackyang.android.aop.annotation.HookMethod;
import com.safframework.cache.Cache;
import com.safframework.prefs.AppPrefs;

/**
 * 2017/8/11.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class AopTestActivity extends BaseActivity {

    @Override
    protected void description() {
        Log.d(TAG, "Aop test...");
    }

    @Override
    protected int getArrayId() {
        return R.array.aop;
    }

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {
        Log.d(TAG, getSpInfo(position));
        switch (position) {
            case 0:
                AopShow.asynTest();
                break;
            case 1:
                AopShow.cacheable_save();
                AopShow.cacheable_save2();
                break;
            case 2:
                Cache cache = Cache.get(this);
                Person p = (Person) cache.getObject("person");
                Log.d(TAG, "read person:" + p.toString());

                Car car = (Car) cache.getObject("car");
                Log.d(TAG, "read car:" + (car != null ? car.toString() : "null"));
                break;
            case 3:
                AopShow.debugTest();
                break;
            case 4:
                hookTest();
                break;
            case 5:
                AopShow.logTest("jackyang", 20);
                break;
            case 6:
                AopShow.pref_save();
                break;
            case 7:
                AppPrefs appPrefs = AppPrefs.get(this);
                Log.d(TAG, "read person:" + appPrefs.getObject("Person").toString());
                break;
            case 8:
                AopShow.safe_test();
                break;
        }
    }

    @HookMethod(beforeMethod = "method1", afterMethod = "method2")
    private void hookTest() {
        Log.d(TAG, "originExecute...");
    }

    private void method1() {
        Log.d(TAG, "method1() is called before hookTest()");
    }

    private void method2() {
        Log.d(TAG, "method2() is called after hookTest()");
    }
}
