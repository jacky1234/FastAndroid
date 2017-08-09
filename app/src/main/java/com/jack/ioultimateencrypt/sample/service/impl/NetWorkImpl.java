package com.jack.ioultimateencrypt.sample.service.impl;

import com.jack.ioultimateencrypt.sample.service.NetWork;
import com.jack.test.logger.Log;
import com.jackyang.android.support.lang.Disposable;
import com.jackyang.android.support.lang.Initializable;

import static android.content.ContentValues.TAG;

/**
 * 2017/8/9.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class NetWorkImpl implements NetWork, Initializable, Disposable {

    @Override
    public void initialize() {
        Log.d(TAG, "NetWorkImpl initialize...");
    }

    @Override
    public void destroy() {
        Log.d(TAG, "NetWorkImpl destroy...");
    }
}
