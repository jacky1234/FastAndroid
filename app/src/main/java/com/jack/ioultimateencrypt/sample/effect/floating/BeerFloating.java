package com.jack.ioultimateencrypt.sample.effect.floating;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;

import com.ufreedom.floatingview.transition.BaseFloatingPathTransition;
import com.ufreedom.floatingview.transition.FloatingPath;
import com.ufreedom.floatingview.transition.PathPosition;
import com.ufreedom.floatingview.transition.YumFloating;

/**
 * 2017/9/10.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class BeerFloating extends BaseFloatingPathTransition {


    @Override
    public FloatingPath getFloatingPath() {
        Path path = new Path();
        path.rLineTo(-100, 0);
        path.quadTo(0, -200, 100, 0);
        path.quadTo(0, 200, -100, 0);
        return FloatingPath.create(path, false);
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
        translateAnimator.setDuration(600);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                PathPosition floatingPosition = getFloatingPosition(value);
                yumFloating.setTranslationX(floatingPosition.x);
                yumFloating.setTranslationY(floatingPosition.y);

            }
        });


        ValueAnimator alphaAnimation = ObjectAnimator.ofFloat(1f, 0f);
        alphaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        alphaAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.clear();
            }
        });
        alphaAnimation.setStartDelay(550);
        alphaAnimation.setDuration(300);
        translateAnimator.start();
        alphaAnimation.start();
    }
}