package com.jackyang.android.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by jackyang on 2016/12/7.
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface HookMethod {

    String beforeMethod() default "";

    String afterMethod() default "";
}
