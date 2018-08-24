package com.jfshare.mvp.server.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.constants.ResultConstant;

/**
 * 打印controller日志
 * @author fengxiang
 * @date 2018-07-06
 */
@Aspect
@Component
public class LogAspectConfig {
	private final static Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
	
	
	@Pointcut("execution(* com.jfshare.mvp.server.controller.*Controller.*(..))")  
    public void webLog(){}
	
	@Before("webLog()")
	public void requestLog(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        // 记录下请求内容  
        logger.info("Request: URL={}, HTTP_METHOD={}, IP={}, CLASS_METHOD={}, ARGS={}", 
        		request.getRequestURL().toString(), 
        		request.getMethod(), 
        		request.getHeader("X-Real-IP"), 
        		joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
        		Arrays.toString(joinPoint.getArgs()));
	}
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void responseLog(Object ret) {
		if (ret instanceof ResultConstant) {
			logger.info("Response: {}", JSON.toJSONString(ret));
		}
	}
}
