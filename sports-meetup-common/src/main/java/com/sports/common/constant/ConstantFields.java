package com.sports.common.constant;

public class ConstantFields {
	private final static String SUCCESS_RESPONSE_CODE ="000";
	private final static String SUCCESS_RESPONSE_MSG ="请求成功!";
	private final static String BAD_REQUEST_CODE = "BAD_REQUEST_400";
	private final static String LOGIN_ERROR501_CODE = "LOGINERROR_501";
	private final static String LOGIN_ERROR501_MSG = "用户名不存在.";
	private final static String LOGIN_ERROR502_CODE = "LOGIN_ERROR_502";
	private final static String LOGIN_ERROR502_MSG = "用户名或密码错误.";
	
	private final static String NOT_FOUND_CODE = "NOT_FOUND_404"; 
	private final static String NOT_FOUND_MSG = "请求有误，请核对链接地址."; 
//	private final static String BAD_REQUEST_ERROR_CODE = "BAD_REQUEST_ERROR_400";
	
	
	public static String getSuccessResponseMsg() {
		return SUCCESS_RESPONSE_MSG;
	}
	public static String getNotFoundCode() {
		return NOT_FOUND_CODE;
	}
	public static String getNotFoundMsg() {
		return NOT_FOUND_MSG;
	}
	public static String getSuccessResponseCode() {
		return SUCCESS_RESPONSE_CODE;
	}

	public static String getBadRequestCode() {
		return BAD_REQUEST_CODE;
	}
	public static String getLoginError501Code() {
		return LOGIN_ERROR501_CODE;
	}
	public static String getLoginError501Msg() {
		return LOGIN_ERROR501_MSG;
	}
	public static String getLoginError502Code() {
		return LOGIN_ERROR502_CODE;
	}
	public static String getLoginError502Msg() {
		return LOGIN_ERROR502_MSG;
	}
	
	
}
