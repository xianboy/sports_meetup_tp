package com.sports.meetup.match.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "config")
@Component
public class WebConfig {

	private String saveMatchUrl;

	private String joinMatchUrl;

	private String MatchesByCreatorIdUrl;

	private String OldMatchesByCreatorIdUrl;

	private String nearbyMatchesUrl;

	private String findMatchByIdUrl;

	private String findMatchesByFieldIdUrl;

	private String ApplyMatchesUrl;

	private String OldApplyMatchesUrl;

	private String MatchAndAddressUrl;

	private String CreatedUserInfoUrl;

	private String AppliedUserInfoUrl;
	// 判断是否加入比赛
	private String isJoinedMatchesUrl;
	// 加入比赛后的比赛（格式）
	private String findMatchinfoByIdUrl;

	// 根据fieldId获取场地信息
	private String findSportFieldByIdUrl;

	// 通过类型多选(JDBC)
	private String nearMatchesbyFieldTypeUrl;

	
	
	public String getNearMatchesbyFieldTypeUrl() {
		return nearMatchesbyFieldTypeUrl;
	}

	public void setNearMatchesbyFieldTypeUrl(String nearMatchesbyFieldTypeUrl) {
		this.nearMatchesbyFieldTypeUrl = nearMatchesbyFieldTypeUrl;
	}

	public String getFindSportFieldByIdUrl() {
		return findSportFieldByIdUrl;
	}

	public void setFindSportFieldByIdUrl(String findSportFieldByIdUrl) {
		this.findSportFieldByIdUrl = findSportFieldByIdUrl;
	}

	public String getOldApplyMatchesUrl() {
		return OldApplyMatchesUrl;
	}

	public void setOldApplyMatchesUrl(String oldApplyMatchesUrl) {
		OldApplyMatchesUrl = oldApplyMatchesUrl;
	}

	public String getApplyMatchesUrl() {
		return ApplyMatchesUrl;
	}

	public void setApplyMatchesUrl(String applyMatchesUrl) {
		ApplyMatchesUrl = applyMatchesUrl;
	}

	public String getFindMatchesByFieldIdeUrl() {
		return findMatchesByFieldIdUrl;
	}

	public void setFindMatchesByFieldIdUrl(String findMatchesByFieldIdUrl) {
		this.findMatchesByFieldIdUrl = findMatchesByFieldIdUrl;
	}

	public String getFindMatchByIdUrl() {
		return findMatchByIdUrl;
	}

	public void setFindMatchByIdUrl(String findMatchByIdUrl) {
		this.findMatchByIdUrl = findMatchByIdUrl;
	}

	public String getNearbyMatchesUrl() {
		return nearbyMatchesUrl;
	}

	public void setNearbyMatchesUrl(String nearbyMatchesUrl) {
		this.nearbyMatchesUrl = nearbyMatchesUrl;
	}

	public String getOldMatchesByCreatorIdUrl() {

		return OldMatchesByCreatorIdUrl;
	}

	public void setOldMatchesByCreatorIdUrl(String oldMatchesByCreatorIdUrl) {
		this.OldMatchesByCreatorIdUrl = oldMatchesByCreatorIdUrl;
	}

	public String getMatchesByCreatorIdUrl() {

		return MatchesByCreatorIdUrl;
	}

	public void setMatchesByCreatorIdUrl(String MatchesByCreatorIdUrl) {
		this.MatchesByCreatorIdUrl = MatchesByCreatorIdUrl;
	}

	public String getSaveMatchUrl() {
		return saveMatchUrl;
	}

	public void setSaveMatchUrl(String saveMatchUrl) {
		this.saveMatchUrl = saveMatchUrl;
	}

	public String getJoinMatchUrl() {
		return joinMatchUrl;
	}

	public void setJoinMatchUrl(String joinMatchUrl) {
		this.joinMatchUrl = joinMatchUrl;
	}

	public String getFindMatchesByFieldIdUrl() {
		return findMatchesByFieldIdUrl;
	}

	public String getMatchAndAddressUrl() {
		return MatchAndAddressUrl;
	}

	public void setMatchAndAddressUrl(String matchAndAddressUrl) {
		MatchAndAddressUrl = matchAndAddressUrl;
	}

	public String getCreatedUserInfoUrl() {
		return CreatedUserInfoUrl;
	}

	public void setCreatedUserInfoUrl(String createdUserInfoUrl) {
		CreatedUserInfoUrl = createdUserInfoUrl;
	}

	public String getAppliedUserInfoUrl() {
		return AppliedUserInfoUrl;
	}

	public void setAppliedUserInfoUrl(String appliedUserInfoUrl) {
		AppliedUserInfoUrl = appliedUserInfoUrl;
	}

	public String getIsJoinedMatchesUrl() {
		return isJoinedMatchesUrl;
	}

	public void setIsJoinedMatchesUrl(String isJoinedMatchesUrl) {
		this.isJoinedMatchesUrl = isJoinedMatchesUrl;
	}

	public String getFindMatchinfoByIdUrl() {
		return findMatchinfoByIdUrl;
	}

	public void setFindMatchinfoByIdUrl(String findMatchinfoByIdUrl) {
		this.findMatchinfoByIdUrl = findMatchinfoByIdUrl;
	}
}
