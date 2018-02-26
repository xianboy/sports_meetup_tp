package com.sports.meetup.match.sapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;


@Component
public class SportMatchAndAddress {

	private BigInteger matchId;

//	//发起人Id
//	private BigInteger creatorId;

	//比赛场地Id
	private BigInteger fieldId;

	//创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;

	//比赛开始时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startTime;

	//比赛结束时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp endTime;

	//比赛类型
	private BigInteger matchType;

	//已有人数
	private Integer joinedAmount;

	//经度
	private Double longitude;

	//纬度
	private Double latitude;

	//备注
	@Column(columnDefinition = "text")
	private String remarks;

	private Date date;

	private String address;

	public SportMatchAndAddress() {
		super();
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(BigInteger matchId) {
		this.matchId = matchId;
	}

	public BigInteger getFieldId() {
		return fieldId;
	}

	public void setFieldId(BigInteger fieldId) {
		this.fieldId = fieldId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
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


	public BigInteger getMatchType() {
		return matchType;
	}

	public void setMatchType(BigInteger matchType) {
		this.matchType = matchType;
	}

	public Integer getJoinedAmount() {
		return joinedAmount;
	}

	public void setJoinedAmount(Integer joinedAmount) {
		this.joinedAmount = joinedAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
}
