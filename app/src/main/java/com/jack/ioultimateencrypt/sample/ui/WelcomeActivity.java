package com.jack.ioultimateencrypt.sample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.ui.fragment.WelcomeFragment;

/**
 * 2017/8/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class WelcomeActivity extends AppCompatActivity implements WelcomeFragment.OnSelectPageListener {
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new WelcomeFragment())
                .commit();
    }

    @Override
    public void selectColor(int c) {
        immersionBar = ImmersionBar.with(this)
                .statusBarColor(c)
                .statusBarAlpha(0.3f)
                .navigationBarColor(c)
                .navigationBarAlpha(0.4f);
        immersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }
}
