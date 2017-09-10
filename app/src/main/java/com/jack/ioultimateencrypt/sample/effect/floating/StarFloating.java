package com.jack.ioultimateencrypt.sample.effect.floating;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.ufreedom.floatingview.spring.SimpleReboundListener;
import com.ufreedom.floatingview.spring.SpringHelper;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.transition.YumFloating;

/**
 * 2017/9/10.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class StarFloating implements FloatingTransition {

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 10, 15)
                .reboundListener(new SimpleReboundListener() {
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);


        ValueAnimator rotateAnimator = ObjectAnimator.ofFloat(0, 360);
        rotateAnimator.setDuration(500);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setRotation((float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
        translateAnimator.setDuration(600);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setTranslationY(-(Float) valueAnimator.getAnimatedValue());
            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setAlpha(0f);
                yumFloating.clear();
            }
        });
        rotateAnimator.start();
        translateAnimator.start();
    }
}
