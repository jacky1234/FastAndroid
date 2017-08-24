package com.jack.ioultimateencrypt.sample.ui.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class BottomBehavior extends CoordinatorLayout.Behavior{

    public BottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        final int transferY = dependency.getTop() * (-1);
        child.setTranslationY(transferY);
        return true;
    }
}
