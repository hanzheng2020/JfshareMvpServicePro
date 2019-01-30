package com.jfshare.aop;


import com.jfshare.bonusPoints.bonuspoints.monitor.MetricNames;
import com.jfshare.bonusPoints.bonuspoints.monitor.MonitorUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class CatAopService {

    private Logger logger = LoggerFactory.getLogger(CatAopService.class);

    @Pointcut("execution(* com.jfshare.mvp.server.controller.AdminController.*(..))")
    public void pointCut() {
        //define  pointCut
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(CatAnnotation.class);

        Object result = null;

        if (flag) {
            logger.info("CatAopService ----> flag is true  methodname is "+method.getName());
            try {

                result = pjp.proceed();// 正常的业务处理
                MonitorUtils.countSuccess(method.getName());

            } catch (Throwable e) {
                MonitorUtils.countException(MetricNames.Address_Add);
                e.printStackTrace();
            } finally {

            }

        } else {
            logger.info("CatAopService ----> flag is false ");
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
