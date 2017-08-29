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
 *         说明：
 *         目前只考虑了支持 LinearLayoutManager 的情况
 *         如果想要挤压效果，需要是 TitleView 单独为一个ViewType。
 *         参考： http://www.jianshu.com/p/5864db231ed5
 */

public abstract class ItemHeaderDecoration extends RecyclerView.ItemDecoration {
    private View mDecorView;
    private int mDecorHeight;

    public abstract int getDecorationLayoutId();

    public abstract boolean isStickHeaderChange(int curPos, int nextPos);

    public abstract void setDataOnView(int curPos, View view);

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
            if (pos == RecyclerView.NO_POSITION) {
                return;
            }
            View child = parent.findViewHolderForLayoutPosition(pos).itemView;

            if (mDecorView == null) {
                for (int i = 0; i < layoutManager.getChildCount(); i++) {
                    mDecorView = parent.findViewHolderForLayoutPosition(i).itemView.findViewById(getDecorationLayoutId());
                    if (mDecorView != null) {
                        mDecorHeight = mDecorView.getHeight();
                        break;
                    }
                }

                if (mDecorView == null) {
                    return;
                }
            }


            boolean flag = false;
//            if (pos + 1 < layoutManager.getChildCount() && isStickHeaderChange(pos, pos + 1)) {
//                c.save();
//                flag = true;
//                c.translate(0, parent.findViewHolderForLayoutPosition(pos + 1).itemView.getTop() - mDecorHeight);
//            }

            setDataOnView(pos, mDecorView);

            mDecorView.layout(parent.getLeft(), parent.getTop(), parent.getLeft() + mDecorView.getWidth(), parent.getTop() + mDecorView.getHeight());
            mDecorView.draw(c);

            if (flag) {
                c.restore();
            }
        }
    }
}
