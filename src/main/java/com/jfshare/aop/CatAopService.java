package com.jfshare.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.jfshare.bonusPoints.bonuspoints.monitor.MetricNames;
import com.jfshare.bonusPoints.bonuspoints.monitor.MonitorUtils;

import java.lang.reflect.Method;

@Component
@Aspect
public class CatAopService {

    @Pointcut("execution(* com.jfshare.mvp.server.controller.AdminController.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(CatAnnotation.class);

        Object result = null;

        if (flag) {
            System.out.println("CatAopService ----> flag is true  methodname is "+method.getName());
            try {

                result = pjp.proceed();// 正常的业务处理
                MonitorUtils.countSuccess(method.getName());

            } catch (Throwable e) {
                MonitorUtils.countException(MetricNames.Address_Add);
                e.printStackTrace();
            } finally {

            }

        } else {
            System.out.println("CatAopService ----> flag is false ");
            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                MonitorUtils.countException(method.getName());
                e.printStackTrace();
            }
        }

        return result;
    }

}
