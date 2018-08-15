package com.hyt.server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmergencyAspect {

    @Pointcut("@annotation(com.hyt.server.anno.Emergency)")
    public void emergency(){}

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("emergency()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("进入环绕通知");
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        return result;
    }
}
