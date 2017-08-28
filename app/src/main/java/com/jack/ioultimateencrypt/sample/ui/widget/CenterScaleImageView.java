package com.jack.ioultimateencrypt.sample.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jack.ioultimateencrypt.sample.R;

/**
 * 2017/8/28.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class CenterScaleImageView extends ImageView {
    private float ratio;

    public CenterScaleImageView(Context context) {
        this(context, null, 0);
    }

    public CenterScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CenterScaleImageView);
        int w = ta.getInteger(R.styleable.CenterScaleImageView_widthRadio, 1);
        int h = ta.getInteger(R.styleable.CenterScaleImageView_heightRadio, 1);
        ratio = h * 1.0f / w;
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, (int) (ratio * widthSize));
        }
    }
}
