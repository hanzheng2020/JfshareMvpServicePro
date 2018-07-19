package com.jfshare.mvp.server.exception;

/**
 * @author fengxiang
 * @date 2018-06-26
 */
public class InterfaceRequestException extends RuntimeException {

	private static final long serialVersionUID = -5785929167842464263L;
	
	public InterfaceRequestException(String message) {
		super(message);
	}
	
	public InterfaceRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
