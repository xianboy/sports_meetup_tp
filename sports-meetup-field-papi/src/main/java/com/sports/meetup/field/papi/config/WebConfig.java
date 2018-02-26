package com.sports.meetup.field.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ConfigurationProperties(prefix = "config")
@Component
public class WebConfig {

	private String findNearbySportFieldsUrl;

	private String findNearFieldByMatchtypeUrl;

	private String addSportFieldUrl;

	private String accessType;

	private String usingArea;

	private String fieldPicsStorePath;

	private String findNearFieldsHaveMatches;

	private String findSportFieldByIdUrl;

	private String localIp;

	private String appPort;

	// 多选（jdbc）
	private String findNearFieldsByMatchtypeUrl;

	
	
	public String getFindNearFieldsByMatchtypeUrl() {
		return findNearFieldsByMatchtypeUrl;
	}

	public void setFindNearFieldsByMatchtypeUrl(String findNearFieldsByMatchtypeUrl) {
		this.findNearFieldsByMatchtypeUrl = findNearFieldsByMatchtypeUrl;
	}

	public String getAppPort() {
		return appPort;
	}

	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getFindSportFieldByIdUrl() {
		return findSportFieldByIdUrl;
	}

	public void setFindSportFieldByIdUrl(String findSportFieldByIdUrl) {
		this.findSportFieldByIdUrl = findSportFieldByIdUrl;
	}

	public String getAddSportFieldUrl() {
		return addSportFieldUrl;
	}

	public void setAddSportFieldUrl(String addSportFieldUrl) {
		this.addSportFieldUrl = addSportFieldUrl;
	}

	public String getFieldPicsStorePath() {
		return fieldPicsStorePath;
	}

	public void setFieldPicsStorePath(String fieldPicsStorePath) {
		this.fieldPicsStorePath = fieldPicsStorePath;
	}

	public String getUsingArea() {
		return usingArea;
	}

	public void setUsingArea(String usingArea) {
		this.usingArea = usingArea;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getFindNearbySportFieldsUrl() {
		return findNearbySportFieldsUrl;
	}

	public void setFindNearbySportFieldsUrl(String findNearbySportFieldsUrl) {
		this.findNearbySportFieldsUrl = findNearbySportFieldsUrl;
	}

	public String getFindNearFieldByMatchtypeUrl() {
		return findNearFieldByMatchtypeUrl;
	}

	public void setFindNearFieldByMatchtypeUrl(String findNearFieldByMatchtypeUrl) {
		this.findNearFieldByMatchtypeUrl = findNearFieldByMatchtypeUrl;
	}

	public String getFindNearFieldsHaveMatches() {
		return findNearFieldsHaveMatches;
	}

	public void setFindNearFieldsHaveMatches(String findNearFieldsHaveMatches) {
		this.findNearFieldsHaveMatches = findNearFieldsHaveMatches;
	}

	public int getappPort() {
		// TODO Auto-generated method stub
		return 0;
	}
}
