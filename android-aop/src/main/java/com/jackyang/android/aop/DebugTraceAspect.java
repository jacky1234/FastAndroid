package com.jackyang.android.aop;

import com.jackyang.android.aop.annotation.DebugTrace;
import com.jackyang.android.aop.interval.StopWatch;
import com.safframework.log.L;
import com.safframework.tony.common.utils.Preconditions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by jackyang on 16/3/22.
 */
@Aspect
public class DebugTraceAspect {

    private static final String POINTCUT_METHOD = "execution(@com.jackyang.android.aop.annotation.DebugTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.jackyang.android.aop.annotation.DebugTrace *.new(..))";

    private static final int ns = 1000*1000;

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedTrace() {
    }

    @Around("methodAnnotatedWithTrace() || constructorAnnotatedTrace()")
    public Object traceMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        DebugTrace trace = methodSignature.getMethod().getAnnotation(DebugTrace.class);
        if (trace!=null && !trace.enable()) {
            return joinPoint.proceed();
        }

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        if (Preconditions.isBlank(className)) {
            className = "Anonymous class";
        }

        L.i(className, buildLogMessage(methodName, stopWatch.getElapsedTime()));

        return result;
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String methodName, long methodDuration) {

        if (methodDuration > 10 * ns) {
            return String.format("%s() take %d ms", methodName, methodDuration / ns);
        } else if (methodDuration > ns) {
            return String.format("%s() take %dms %dns", methodName, methodDuration / ns,
                    methodDuration % ns);
        } else {
            return String.format("%s() take %dns", methodName, methodDuration % ns);
        }
    }
}
