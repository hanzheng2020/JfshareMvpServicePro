package com.jfshare.mvp.server.exception;

/**
 * @author fengxiang
 * @date 2018-06-26
 */
public class DataException extends RuntimeException {

	private static final long serialVersionUID = -5785929167842464263L;
	
	public DataException(String message) {
		super(message);
	}
}
