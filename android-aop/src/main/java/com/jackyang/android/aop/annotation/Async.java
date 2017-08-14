package com.jackyang.android.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by jackyang on 16/3/23.
 */
@Target({METHOD})
@Retention(CLASS)
public @interface Async {
}
