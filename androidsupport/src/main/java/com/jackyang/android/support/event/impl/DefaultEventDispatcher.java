package com.jackyang.android.support.event.impl;


import com.jackyang.android.support.event.Event;
import com.jackyang.android.support.event.EventDispatcher;
import com.jackyang.android.support.event.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultEventDispatcher
 * <p/>
 * Created by jackyang on 4/19/15.
 * 默认的事件分发实现.
 * 简单实现，没有线程切换等操作。
 * 注意： dispatch 与 EventListener#onEvent 是在一个线程。即发送线程与接收线程相同
 */
public class DefaultEventDispatcher implements EventDispatcher {


    private List<EventListener> eventListeners = new ArrayList<EventListener>();


    @SuppressWarnings("unchecked")
    @Override
    public void dispatch(Event event) {
        for (EventListener eventListener : eventListeners) {
            if (eventListener.support(event.getClass())) {
                eventListener.onEvent(event);
            }
        }
    }

    @Override
    public void registerListener(EventListener listener) {
        if (!this.eventListeners.contains(listener)) {
            this.eventListeners.add(listener);
        }
    }

    @Override
    public void registerListeners(EventListener... listeners) {
        if (listeners != null) {
            for (EventListener listener : listeners) {
                registerListener(listener);
            }
        }
    }

    @Override
    public void unregisterListeners(EventListener... listeners) {
        if (listeners != null) {
            for (EventListener listener : listeners) {
                unregisterListener(listener);
            }
        }
    }

    @Override
    public void unregisterListener(EventListener listener) {
        this.eventListeners.remove(listener);
    }
}
