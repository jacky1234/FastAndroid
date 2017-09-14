package com.jack.ioultimateencrypt.sample.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 2017/9/1.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 * 简单的decor绘制
 */

public class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;
    private int orientation;

    private boolean needOffsetHeader = true;
    private boolean needOffsetFooter = false;

    public void setNeedOffsetHeader(boolean needOffsetHeader) {
        this.needOffsetHeader = needOffsetHeader;
    }

    public void setNeedOffsetFooter(boolean needOffsetFooter) {
        this.needOffsetFooter = needOffsetFooter;
    }

    public SimpleDividerDecoration(Context context) {
        this(context, Color.GRAY, 8, RecyclerView.VERTICAL);
    }

    public interface DividerInterceptor {
        boolean intercept(int pos);
    }

    /**
     * @param context
     * @param dividerColor
     * @param dividerInDp
     * @param orientation
     */
    public SimpleDividerDecoration(Context context, int dividerColor, float dividerInDp, int orientation) {
        this.orientation = orientation;

        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(dividerColor);
        dividerHeight = dp2px(context, dividerInDp);
    }

    public static int dp2px(final Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        int count = parent.getLayoutManager().getItemCount();

        if (orientation == RecyclerView.VERTICAL) {
            outRect.bottom = dividerHeight;
            if (pos == 0 && needOffsetHeader) outRect.top = dividerHeight;
            if (pos == count - 1 && !needOffsetFooter) outRect.bottom = 0;

        } else {
            outRect.right = dividerHeight;
            if (pos == 0 && needOffsetHeader) outRect.left = dividerHeight;
            if (pos == count - 1 && !needOffsetFooter) outRect.right = 0;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            final int b = view.getBottom();
            final int r = view.getRight();

            if (orientation == RecyclerView.VERTICAL) {
                c.drawRect(left, b, right, b + dividerHeight, dividerPaint);
            } else {
                c.drawRect(r, top, r + dividerHeight, bottom, dividerPaint);
            }
        }
    }
}
