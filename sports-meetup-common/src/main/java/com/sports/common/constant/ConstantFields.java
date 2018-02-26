package com.sports.common.constant;

public class ConstantFields {
	private final static String SUCCESS_CODE ="000";
	private final static String SUCCESS_MSG ="请求成功!";
	private final static String BAD_REQUEST_CODE = "BAD_REQUEST_400";
	private final static String NOT_FOUND_CODE = "NOT_FOUND_404"; 
	private final static String NOT_FOUND_MSG = "请求有误，请核对链接地址."; 
	private final static String USER_ERROR501_CODE = "USER_ERROR_501";
	private final static String USER_ERROR501_MSG = "用户不存在,请注册.";
	private final static String USER_ERROR502_CODE = "USER_ERROR_502";
	private final static String USER_ERROR502_MSG = "用户名或密码错误.";
	private final static String USER_ERROR503_CODE = "USER_ERROR_503";
	private final static String USER_ERROR503_MSG ="用户已存在.";
	private final static String USER_ERROR_504 = "USER_ERROR_504";
	private final static String USER_ERROR504_MSG = "验证码发送失败,请重新获取.";
	private final static String USER_ERROR505_CODE = "USER_ERROR_505";
	private final static String USER_ERROR505_MSG = "验证码已失效,请重新获取.";
	private final static String USER_ERROR506_CODE = "USER_ERROR_506";
	private final static String USER_ERROR506_MSG = "新旧密码不能相同.";
	private final static String USER_ERROR507_CODE = "USER_ERROR_507";
	private final static String USER_ERROR507_MSG = "用户账户异常.";
	private final static String URL_ERROR508_CODE = "URL_ERROR_508"; 
	private final static String URL_ERROR508_MSG = "URL 不正确."; 
	private final static String SAPI_ERROR509_CODE = "SAPI_ERROR_509";
	private final static String SAPI_ERROR509_MSG = "调用SAPI出错.";
	private final static String P_SERVICEERROR_CODE = "P_SERVICEERROR_510";
	private final static String P_SERVICEERROR_MSG = "PAPI service 调用失败.";
	private final static String S_SERVICEERROR_CODE = "S_SERVICEERROR_511";
	private final static String S_SERVICEERROR_MSG = "SAPI service 调用失败.";
	
	private final static String P_FULLPERSON_CODE = "P_SERVICEERROR_512";
	private final static String P_FULLPERSON_MSG = "比赛人数已满";
	private final static String P_HAVEJOINED_CODE = "P_SERVICEERROR_513";
	private final static String P_HAVEJOINED_MSG = "已经加入该比赛";
	private final static String P_DEFAULTJOINED_CODE = "P_SERVICEERROR_514";
	private final static String P_DEFAULTJOINED_MSG = "创建者已默认加入比赛";

	private final static String HEALTHCHECK_CODE="HI,I'AM WORKING FINE!";
	
	public static String getpFullpersonCode() {
		return P_FULLPERSON_CODE;
	}
	public static String getpFullpersonMsg() {
		return P_FULLPERSON_MSG;
	}
	public static String getpHavejoinedCode() {
		return P_HAVEJOINED_CODE;
	}
	public static String getpHavejoinedMsg() {
		return P_HAVEJOINED_MSG;
	}
	public static String getpDefaultjoinedCode() {
		return P_DEFAULTJOINED_CODE;
	}
	public static String getpDefaultjoinedMsg() {
		return P_DEFAULTJOINED_MSG;
	}
	public static String getHealthcheckCode() {
		return HEALTHCHECK_CODE;
	}
	public static String getpServiceerrorCode() {
		return P_SERVICEERROR_CODE;
	}
	public static String getpServiceerrorMsg() {
		return P_SERVICEERROR_MSG;
	}
	public static String getsServiceerrorCode() {
		return S_SERVICEERROR_CODE;
	}
	public static String getsServiceerrorMsg() {
		return S_SERVICEERROR_MSG;
	}
	public static String getSapiError509Code() {
		return SAPI_ERROR509_CODE;
	}
	public static String getSapiError509Msg() {
		return SAPI_ERROR509_MSG;
	}
	public static String getUrlError508Code() {
		return URL_ERROR508_CODE;
	}
	public static String getUrlError508Msg() {
		return URL_ERROR508_MSG;
	}
	public static String getUserError507Code() {
		return USER_ERROR507_CODE;
	}
	public static String getUserError507Msg() {
		return USER_ERROR507_MSG;
	}
	public static String getUserError506Code() {
		return USER_ERROR506_CODE;
	}
	public static String getUserError506Msg() {
		return USER_ERROR506_MSG;
	}
	public static String getUserError504() {
		return USER_ERROR_504;
	}
	public static String getUserError504Msg() {
		return USER_ERROR504_MSG;
	}
	public static String getUserError505Code() {
		return USER_ERROR505_CODE;
	}
	public static String getUserError505Msg() {
		return USER_ERROR505_MSG;
	}
	public static String getUserError503Code() {
		return USER_ERROR503_CODE;
	}
	public static String getUserError503Msg() {
		return USER_ERROR503_MSG;
	}
	public static String getSuccessMsg() {
		return SUCCESS_MSG;
	}
	public static String getNotFoundCode() {
		return NOT_FOUND_CODE;
	}
	public static String getNotFoundMsg() {
		return NOT_FOUND_MSG;
	}
	public static String getSuccessCode() {
		return SUCCESS_CODE;
	}

	public static String getBadRequestCode() {
		return BAD_REQUEST_CODE;
	}
	public static String getUserError501Code() {
		return USER_ERROR501_CODE;
	}
	public static String getUserError501Msg() {
		return USER_ERROR501_MSG;
	}
	public static String getUserError502Code() {
		return USER_ERROR502_CODE;
	}
	public static String getUserError502Msg() {
		return USER_ERROR502_MSG;
	}
	
	
}
