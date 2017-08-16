package com.jack.ioultimateencrypt.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.jack.ioultimateencrypt.sample.BaseActivity;
import com.jack.ioultimateencrypt.sample.R;
import com.jack.test.logger.Log;
import com.jackyang.android.support.queue.Operation;
import com.jackyang.android.support.queue.OperationRunnable;
import com.jackyang.android.support.queue.Queue;

/**
 * 2017/8/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class QueueTestActivity extends BaseActivity implements Runnable {
    Queue queue;


    @Override
    protected View getContentView() {
        final TextView textView = new TextView(this);
        textView.setId(R.id.textviewid);
        return textView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = new Queue("HelloWorld");
        queue.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (queue != null) {
            queue.stop();
        }
    }

    @Override
    protected void description() {
        Log.d(TAG, "QueueTest...");
    }

    @Override
    protected int getArrayId() {
        return R.array.queue;
    }

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "add operation ...");
                new Thread(this).start();
                break;
        }
    }

    int count = 0;

    @Override
    public void run() {
        while (true) {
            queue.addOperation(new Operation() {
                @Override
                public void run(Queue queue, Bundle bundle) {
                    Log.d(TAG, "Thread name:" + Thread.currentThread().getName() + ",count:" + count++);

                    /**
                     * no ui thread can touch {@link android.widget.Toast} not {@link View} state.
                     */
                    //Only the original thread that created a view hierarchy can touch its views.
//                    TextView textView = (TextView) findViewById(R.id.textviewid);
//                    textView.setText("hello world");
//                    Toast.makeText(QueueTestActivity.this, "test in NoUIThread", Toast.LENGTH_SHORT).show();
                }
            });
            OperationRunnable.sleep(400);
        }
    }
}
