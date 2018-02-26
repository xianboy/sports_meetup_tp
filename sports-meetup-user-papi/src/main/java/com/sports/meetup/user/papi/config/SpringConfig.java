package com.sports.meetup.user.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sapi.config")
@Component
public class SpringConfig {
	
	private String findUserByPhoneNumberUrl;
	
	private String addHelpMsgUrl;
	
	private String updateUserUrl;

	//测试（查询）
	private String testUserUrl;
    //测试（更改）
	private String updateTestUrl;
	
	
	public String getUpdateUserUrl() {
		return updateUserUrl;
	}

	public void setUpdateUserUrl(String updateUserUrl) {
		this.updateUserUrl = updateUserUrl;
	}

	public String getAddHelpMsgUrl() {
		return addHelpMsgUrl;
	}

	public void setAddHelpMsgUrl(String addHelpMsgUrl) {
		this.addHelpMsgUrl = addHelpMsgUrl;
	}

	public String getFindUserByPhoneNumberUrl() {
		return findUserByPhoneNumberUrl;
	}

	public void setFindUserByPhoneNumberUrl(String findUserByPhoneNumberUrl) {
		this.findUserByPhoneNumberUrl = findUserByPhoneNumberUrl;
	}

	public String getTestUserUrl() {
		return testUserUrl;
	}

	public void setTestUserUrl(String testUserUrl) {
		this.testUserUrl = testUserUrl;
	}

	public String getUpdateTestUrl() {
		return updateTestUrl;
	}

	public void setUpdateTestUrl(String updateTestUrl) {
		this.updateTestUrl = updateTestUrl;
	}
}
