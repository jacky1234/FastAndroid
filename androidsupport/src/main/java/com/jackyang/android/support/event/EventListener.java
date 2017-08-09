package com.jackyang.android.support.event;

import android.os.Handler;
import android.os.Looper;

/**
 * 事件监听器
 * <p/>
 * Created by jackyang on 4/19/15.
 */
public abstract class EventListener<T extends Event> {

    /**
     * 当发生事件后回调
     *
     * @param event 事件
     */
    public void onEvent(final T event) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    onMain(event);
                }
            });
        } else {
            onMain(event);
        }
    }

    /**
     * 切换到主线程
     *
     * @param event
     */
    public abstract void onMain(T event);

    /**
     * 是否支持指定的类型
     *
     * @param type 类型
     * @return support?
     */
    public abstract boolean support(Class type);
}
