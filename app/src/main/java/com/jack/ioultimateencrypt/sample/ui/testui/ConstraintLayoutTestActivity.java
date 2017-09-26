package com.jack.ioultimateencrypt.sample.ui.testui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.base.BaseTestActivity;

/**
 * 2017/9/25.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class ConstraintLayoutTestActivity extends BaseTestActivity {

    @Override
    protected View getContentView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_test_contraint,null);
    }

    @Override
    protected void description() {

    }

    @Override
    protected int getArrayId() {
        return 0;
    }

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {

    }
}
