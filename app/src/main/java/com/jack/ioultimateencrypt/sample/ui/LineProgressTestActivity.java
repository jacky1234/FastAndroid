package com.jack.ioultimateencrypt.sample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.base.BaseActivity;
import com.jack.test.logger.Log;

/**
 * 2017/9/6.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class LineProgressTestActivity extends BaseActivity {
    @Override
    protected void description() {
        Log.d(TAG, "it is a example of showing LineProgress");
    }

    @Override
    protected int getArrayId() {
        return R.array.line_progress;
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.view_line_progress, null);
    }

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {

    }
}
