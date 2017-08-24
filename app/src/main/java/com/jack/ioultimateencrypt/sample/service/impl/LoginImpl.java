package com.jack.ioultimateencrypt.sample.service.impl;

import com.jack.ioultimateencrypt.sample.service.Login;
import com.jack.ioultimateencrypt.sample.service.NetWork;
import com.jack.test.logger.Log;
import com.jackyang.android.support.exception.FAndroidException;
import com.jackyang.android.support.injection.Autowired;
import com.jackyang.android.support.lang.Disposable;
import com.jackyang.android.support.lang.Initializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 2017/8/9.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class LoginImpl implements Login, Initializable, Disposable, Runnable {
    private final String TAG = "LoginImpl";

    private String name;
    private String pwd;

    @Autowired
    private NetWork netWork;

    @Override
    public void login(String name, String pwd) {
        if (netWork == null) {
            throw new FAndroidException("netWork service is null,have you register it in Injects");
        }
        this.name = name;
        this.pwd = pwd;
        new Thread(this).start();
    }

    @Override
    public void destroy() {
        Log.d(TAG, "LoginImpl destroy...");
    }

    @Override
    public void initialize() {
        Log.d(TAG, "LoginImpl initialize...");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            if (StringUtils.equals(name, "jackyang") && StringUtils.equals(pwd, "123456")) {
                Log.d(TAG, "登陆成功");
            } else {
                Log.d(TAG, "登陆失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
