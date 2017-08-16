package com.jackyang.android.support.event;

/**
 * 事件分发器
 * <p/>
 * Created by snowway on 4/19/15.
 */
public interface EventDispatcher {

    /**
     * 分发事件
     */
    void dispatch(Event event);


    /**
     * 注册事件监听器
     *
     * @param listener 事件监听器
     */
    void registerListener(EventListener listener);

    /**
     * 注册多个事件监听器
     *
     * @param listeners 事件监听器数组
     */
    void registerListeners(EventListener... listeners);


    /**
     * 取消注册监听器
     *
     * @param listener 事件监听器
     */
    void unregisterListener(EventListener listener);

    /**
     * 取消注册多个监听器
     *
     * @param listeners 事件监听器数组
     */
    void unregisterListeners(EventListener... listeners);
}
