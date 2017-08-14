package com.jack.ioultimateencrypt.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.jack.test.logger.Log;
import com.jack.test.logger.LogCatWrapper;
import com.jack.test.logger.LogFragment;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;
    private boolean init;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final View contentView = getContentView();
        if (contentView != null) {
            FrameLayout container = (FrameLayout) findViewById(R.id.content);
            container.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        addLogFragment();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        setupSpinnerSelection((Spinner) findViewById(R.id.spinner), spinnerAdapter, getArrayId());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !init) {
            init = true;
            description();
        }
    }

    protected View getContentView() {
        return null;
    }

    protected String getSpInfo(int position) {
        return (String) ((Spinner) findViewById(R.id.spinner)).getAdapter().getItem(position + 1);
    }

    protected abstract void description();

    protected abstract int getArrayId();

    protected abstract void onSpinnerItemClick(Spinner sp, View view, int position);

    protected void setupSpinnerSelection(final Spinner sp, ArrayAdapter<String> adapter, int arrayId) {
        final String[] array = getResources().getStringArray(arrayId);

        adapter.add("- select -");

        /**
         * a list of selection for other tests
         */
        for (String s : array) {
            adapter.add(s);
        }

        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    onSpinnerItemClick(sp, view, position - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void addLogFragment() {
        final android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final LogFragment fragment = new LogFragment();
        transaction.replace(R.id.framelog, fragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeLogging();
    }

    private void initializeLogging() {
        LogFragment logFragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.framelog);
        LogCatWrapper logcat = new LogCatWrapper();
        logcat.setNext(logFragment.getLogView());

        Log.setLogNode(logcat);
    }

    public <T extends Activity> void startActivity(Class<T> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
