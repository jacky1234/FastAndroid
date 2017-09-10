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

public class TranslateFloatingTransition implements FloatingTransition {

    private float mTranslateY;
    private long mDuration;

    public TranslateFloatingTransition() {
        mTranslateY = -200f;
        mDuration = 1500;
    }


    public TranslateFloatingTransition(float translateY, long duration) {
        this.mTranslateY = translateY;
        this.mDuration = duration;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {

        ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, mTranslateY);
        translateAnimator.setDuration(mDuration);
        translateAnimator.setStartDelay(50);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationY(0);
                yumFloating.setAlpha(0f);
                yumFloating.clear();        //需要回收资源
            }
        });

        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(mDuration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });

        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 10, 15)
                .reboundListener(new SimpleReboundListener() {
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);

        alphaAnimator.start();
        translateAnimator.start();
    }

}
