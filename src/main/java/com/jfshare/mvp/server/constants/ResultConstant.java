package com.jfshare.mvp.server.constants;


/**
 * @author fengxiang
 * @date 2018-06-26
 */
public class ResultConstant {
	// 请求成功
	public final static int SUCCESS_CODE = 200;
	public final static String SUCCESS_CODE_DESC = "请求成功";
	// 请求失败
	public final static int FAIL_CODE_SYSTEM_ERROR = 500;
	public final static String FAIL_CODE_SYSTEM_ERROR_DESC = "系统繁忙，请稍后重试 ";
	// 参数错误
	public static final int FAIL_CODE_PARAM_ERROR = 502;
	public static final int FAIL_TOKEN_ERROR = 501;   //鉴权校验失败错误码
	public static final String FAIL_CODE_PARAM_ERROR_DESC = "参数错误，请检查";
	
	private int code;
	
	private String desc;
	
	private Object data;
	
	public static ResultConstant ofSuccess(Object datas){
		ResultConstant result =new ResultConstant();
		result.setCode(SUCCESS_CODE);
		result.setDesc(SUCCESS_CODE_DESC);
		result.setData(datas);
		return result;
	}
	
	public static ResultConstant ofSuccess(){
		ResultConstant result =new ResultConstant();
		result.setCode(SUCCESS_CODE);
		result.setDesc(SUCCESS_CODE_DESC);
		return result;
	}
	
	public static ResultConstant ofFail(int code ,String desc){
		ResultConstant result =new ResultConstant();
		result.setCode(code);
		result.setDesc(desc);
		return result;
	}
	
	public static ResultConstant ofFail(int code ,String desc, Object datas){
		ResultConstant result =new ResultConstant();
		result.setCode(code);
		result.setDesc(desc);
		result.setData(datas);
		return result;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
