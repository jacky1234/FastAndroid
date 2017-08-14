package com.jackyang.android.aop;

import android.annotation.TargetApi;

import com.jackyang.android.aop.annotation.Cacheable;
import com.jackyang.android.aop.interval.AopConstant;
import com.jackyang.android.aop.interval.Utils;
import com.safframework.cache.Cache;
import com.safframework.log.L;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by jackyang on 16/3/23.
 */
@TargetApi(14)
@Aspect
public class CacheAspect {

    @Around("execution(!synthetic * *(..)) && onCacheMethod()")
    public Object doCacheMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return cacheMethod(joinPoint);
    }

    @Pointcut("@within(com.jackyang.android.aop.annotation.Cacheable)||@annotation(com.jackyang.android.aop.annotation.Cacheable)")
    public void onCacheMethod() {
    }

    private Object cacheMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        Object result;

        if (cacheable != null) {
            String key = cacheable.key();
            int expiry = cacheable.expiry();

            result = joinPoint.proceed();
            try {
                Cache cache = Cache.get(Utils.getContext());
                if (expiry > 0) {
                    cache.put(key, (Serializable) result, expiry);
                } else {
                    cache.put(key, (Serializable) result);
                }
            } catch (Exception e) {
                L.e(AopConstant.TAG, e);
            }
        } else {
            // 不影响原来的流程
            result = joinPoint.proceed();
        }

        return result;
    }
}
