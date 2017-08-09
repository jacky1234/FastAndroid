package com.jack.ioultimateencrypt.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.service.Login;
import com.jack.test.logger.Log;
import com.jackyang.android.support.injection.Injections;

/**
 * 2017/8/8.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class InjectTestActivity extends BaseActivity {

    private boolean init;
    private Login login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !init) {
            init = true;
            Log.d(TAG, "Injects is in Application#onCreate\n"
                    + "实现了IOC控制反转，DI自动注入所需字段，只要在Injects中注册就可以。"
            );
        }
    }

    @Override
    protected int getArrayId() {
        return R.array.inject;
    }

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {
        switch (position) {
            case 0:
                login = Injections.getBean(Login.class);
                Log.d(TAG,"get login service");
                break;
            case 1:
                login.login("jackyang", "123456");
                break;
            case 2:
                Log.d(TAG, "injects destroy");
                Injections.destroy();
                break;
        }
    }
}
