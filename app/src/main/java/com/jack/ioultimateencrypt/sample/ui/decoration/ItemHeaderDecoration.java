package com.jack.ioultimateencrypt.sample.ui.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 2017/8/29.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 *         <p>
 *         说明：实现了title挤压效果
 *         目前只考虑了支持 LinearLayoutManager 的情况
 *         参考： http://www.jianshu.com/p/5864db231ed5
 */

public abstract class ItemHeaderDecoration extends RecyclerView.ItemDecoration {
    private View mDecorView;
    private int mDecorHeight;

    public abstract int getDecorationLayoutId();

    public abstract boolean isStickHeaderChange(int curPos, int nextPos);

    public abstract void setDataOnView(int curPos, View view);

    public abstract int getHeadCount();

    //当前RecyclerView中的数据总数
    public abstract int getTotalCount();

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            int pos = layoutManager.findFirstVisibleItemPosition();
            if (pos == RecyclerView.NO_POSITION || pos < getHeadCount()) {
                return;
            }

            final int childCount = layoutManager.getChildCount();
            if (mDecorView == null) {
                for (int i = getHeadCount(); i < childCount + getHeadCount(); i++) {
                    if (parent.findViewHolderForLayoutPosition(i).itemView != null) {
                        mDecorView = parent.findViewHolderForLayoutPosition(i).itemView.findViewById(getDecorationLayoutId());
                        if (mDecorView != null) {
                            mDecorHeight = mDecorView.getHeight();
                            break;
                        }
                    }
                }

                if (mDecorView == null) {
                    return;
                }
            }

            boolean flag = false;
            if (pos + 1 < getHeadCount() + getTotalCount() && isStickHeaderChange(pos - getHeadCount(), pos - getHeadCount() + 1)) {
                final int top = parent.findViewHolderForLayoutPosition(pos + 1).itemView.getTop();
                if (top > 0 && top < mDecorHeight) {
                    c.save();
                    flag = true;

                    c.translate(0, top - mDecorHeight);
                }
            }

            setDataOnView(pos - getHeadCount(), mDecorView);

            mDecorView.layout(parent.getLeft(), parent.getTop(), parent.getLeft() + mDecorView.getWidth(), parent.getTop() + mDecorView.getHeight());
            mDecorView.draw(c);

            if (flag) {
                c.restore();
            }
        }
    }
}
