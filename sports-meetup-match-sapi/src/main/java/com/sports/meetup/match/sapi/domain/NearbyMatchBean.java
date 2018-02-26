package com.sports.meetup.match.sapi.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

@Component
public class NearbyMatchBean {

	private String name;

	private Date date;

	//比赛开始时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startTime;

	//比赛结束时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp endTime;

	private String icon;

	private String matchType;

	private String fieldType;

	private String totalNumber;
	
	private Integer joinedAmmount;
	
	private String address;

	private Double distance;

	private BigInteger matchId;

	private AppliedUserBean[] appliedUsersInfo;

	private CreatedUserBean createdUserInfo;
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getJoinedAmmount() {
		return joinedAmmount;
	}

	public void setJoinedAmmount(Integer joinedAmmount) {
		this.joinedAmmount = joinedAmmount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(BigInteger matchId) {
		this.matchId = matchId;
	}

	public AppliedUserBean[] getAppliedUsersInfo() {
		return appliedUsersInfo;
	}

	public void setAppliedUsersInfo(AppliedUserBean[] appliedUsersInfo) {
		this.appliedUsersInfo = appliedUsersInfo;
	}


	public CreatedUserBean getCreatedUserInfo() {
		return createdUserInfo;
	}

	public void setCreatedUserInfo(CreatedUserBean createdUserInfo) {
		this.createdUserInfo = createdUserInfo;
	}

	@Override
	public String toString() {
		return "NearbyMatchBean{" +
				"name='" + name + '\'' +
				", date=" + date +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", icon='" + icon + '\'' +
				", matchType='" + matchType + '\'' +
				", fieldType='" + fieldType + '\'' +
				", totalNumber='" + totalNumber + '\'' +
				", joinedAmmount=" + joinedAmmount +
				", address='" + address + '\'' +
				", distance=" + distance +
				", matchId=" + matchId +
				", appliedUsersInfo=" + Arrays.toString(appliedUsersInfo) +
				", createdUserInfo=" + createdUserInfo +
				'}';
	}
}
