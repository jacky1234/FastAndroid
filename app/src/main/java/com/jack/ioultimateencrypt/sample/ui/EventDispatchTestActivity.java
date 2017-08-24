package com.jack.ioultimateencrypt.sample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.base.BaseActivity;
import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.events.LoginEvent;
import com.jack.ioultimateencrypt.sample.events.PwdChangeEvent;
import com.jack.test.logger.Log;
import com.jackyang.android.support.event.EventDispatcher;
import com.jackyang.android.support.event.EventListener;
import com.jackyang.android.support.injection.Injections;

/**
 * 2017/8/8.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class EventDispatchTestActivity extends BaseActivity {
    private EventDispatcher eventDispatcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventDispatcher = Injections.getBean(EventDispatcher.class);
    }

    @Override
    protected void description() {

    }

    @Override
    protected int getArrayId() {
        return R.array.eventDispatch;
    }

    private EventListener<LoginEvent> loginEventEventListener = new EventListener<LoginEvent>() {
        @Override
        public void onMain(LoginEvent event) {
            Log.d(TAG, "receiver login event");
        }

        @Override
        public boolean support(Class type) {
            return type == LoginEvent.class;
        }
    };

    private EventListener<PwdChangeEvent> pwdChangeEventEventListener = new EventListener<PwdChangeEvent>() {
        @Override
        public void onMain(PwdChangeEvent event) {
            Log.d(TAG, "receiver pwd change event");
        }

        @Override
        public boolean support(Class type) {
            return type == PwdChangeEvent.class;
        }
    };

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "register event:loginEventEventListener,pwdChangeEventEventListener");
                eventDispatcher.registerListeners(loginEventEventListener, pwdChangeEventEventListener);
                break;
            case 1:
                Log.d(TAG, "dispatch event:pwdChangeEventEventListener");
                eventDispatcher.dispatch(new PwdChangeEvent());
                break;
            case 2:
                Log.d(TAG, "dispatch event:loginEventEventListener");
                eventDispatcher.dispatch(new LoginEvent());
                break;
            case 3:
                Log.d(TAG, "unRegister event:loginEventEventListener,pwdChangeEventEventListener");
                eventDispatcher.unregisterListeners(loginEventEventListener, pwdChangeEventEventListener);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventDispatcher.unregisterListeners(loginEventEventListener, pwdChangeEventEventListener);
    }
}
