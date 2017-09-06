package com.jack.ioultimateencrypt.sample.ui.widget;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jack.ioultimateencrypt.sample.R;


/**
 * 2017/9/6.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 *         simlar to org.xinkb.blackboard.android.ui.view.LinearProgressView
 */

public class LineProgress extends View implements Runnable {
    private Paint mProgressPaint;
    private Paint mBackgroudPaint;
    private final int DEFAULT_PROGRESS_COLOR = 0x353535;
    private final int DEFAULT_TOTOAL_COLOR = 0x5C5C5C;
    private RectF mBgRectF = new RectF(0, 0, 0, 0);
    private RectF mToRectF = new RectF(0, 0, 0, 0);

    private boolean isInAnimation = false;
    private int total = 100;
    private int current = 0;
    private int to = 0;
    ValueAnimator valueAnimator;

    public LineProgress(Context context) {
        this(context, null);
    }

    public LineProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LineProgress);
        try {
            int progressColor = a.getColor(R.styleable.LineProgress_progressColor, DEFAULT_PROGRESS_COLOR);
            int bgColor = a.getColor(R.styleable.LineProgress_bgColor, DEFAULT_TOTOAL_COLOR);
            total = a.getInteger(R.styleable.LineProgress_total, 100);
            to = a.getInteger(R.styleable.LineProgress_to, 0);
            mProgressPaint.setColor(progressColor);
            mBackgroudPaint.setColor(bgColor);

            assert total > to && to > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

        post(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 200;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 200;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        final int radius = getMeasuredHeight() / 2;

        int drawTo = (int) ((current * 1.0f / total) * getWidth());

        mBgRectF.set(0, 0, getWidth(), getHeight());
        mToRectF.set(0, 0, drawTo, getHeight());

        canvas.drawRoundRect(mBgRectF, radius, radius, mBackgroudPaint);
        canvas.drawRoundRect(mToRectF, radius, radius, mProgressPaint);
    }

    public void smoothTo(int to) {
        valueAnimator = ValueAnimator.ofInt(0, to);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                current = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isInAnimation = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                isInAnimation = true;
            }
        });
        valueAnimator.setDuration(800).start();
    }

    public void to(int to) {
        this.current = to;
        invalidate();
    }

    @Override
    public void run() {
        smoothTo(to);
    }
}
