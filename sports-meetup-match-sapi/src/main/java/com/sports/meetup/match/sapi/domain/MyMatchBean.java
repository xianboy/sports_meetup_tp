package com.sports.meetup.match.sapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Component
public class MyMatchBean {

    private Date date;

    //比赛开始时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    //比赛结束时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

    private String matchType;

    private String fieldType;

    private String totalNumber;

    private Integer joinedAmmount;

    private String address;

    private BigInteger matchId;

    private BigInteger creatorId;

    private AppliedUserBean[] appliedUsersInfo;

    private CreatedUserBean createdUserInfo;

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

    public BigInteger getMatchId() {
        return matchId;
    }

    public void setMatchId(BigInteger matchId) {
        this.matchId = matchId;
    }

    public BigInteger getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(BigInteger creatorId) {
        this.creatorId = creatorId;
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



}
