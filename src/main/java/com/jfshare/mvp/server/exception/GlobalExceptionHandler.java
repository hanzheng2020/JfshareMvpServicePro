package com.jfshare.mvp.server.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jfshare.mvp.server.constants.ResultConstant;




/**
 * @author fengxiang
 * @date 2018-06-03
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	 /**
     * 捕获异常
     * @param request  request
     * @param exception
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    public ResultConstant runtimeExceptionHandler(HttpServletRequest request,  
            Exception exception) {
    	exception.printStackTrace();
    	logger.error(exception.getMessage(), exception);
        return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, ResultConstant.FAIL_CODE_SYSTEM_ERROR_DESC);
    }
}
