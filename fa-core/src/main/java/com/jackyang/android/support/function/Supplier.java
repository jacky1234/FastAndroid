package com.jackyang.android.support.function;

/**
 * 2017/4/6.
 * <p>
 * jackyang
 */

public interface Supplier<T> {
    T supply() throws Exception;
}