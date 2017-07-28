package com.newlife.meetup.util;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {	
	
	private String responseCode;
	
	private String message;
	
	private Object object;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}
