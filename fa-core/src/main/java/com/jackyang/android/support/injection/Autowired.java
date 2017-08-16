package com.jackyang.android.support.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this getAnnotation to indicate field upon which a class is dependent.
 * The dependency injecting framework will inject
 * a value into the field, based on the class or object registered for the type of the field.
 * <p/>
 * The getAnnotation works on any field (regardless of access, and regardless of being static or not).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
