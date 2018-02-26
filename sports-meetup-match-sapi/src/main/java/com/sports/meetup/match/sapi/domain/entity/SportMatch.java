package com.sports.meetup.match.sapi.domain.entity;



import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
public class SportMatch {
	
	@Id
	@GeneratedValue
	private Long matchId;
	
	//发起人Id
	private Long creatorId;
	
	//比赛场地Id
	private Long fieldId;
	
	//创建时间
	private Timestamp createdTime;
	
	//比赛开始时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startTime;
	
	//比赛结束时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp endTime;
	
	//比赛类型
	private String matchType;
	
	//已有人数
	private Integer joinedAmount;
	
	//联系电话
	private String creatorPhone;
	
	//经度
	private Double longitude;
	
	//纬度
	private Double latitude;
	
	
	private Date date;
	
	//备注
	@Column(columnDefinition = "text")
	private String remarks;
	
	public SportMatch() {
		super();
	}

	
	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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



	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
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


	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public Integer getJoinedAmount() {
		return joinedAmount;
	}

	public void setJoinedAmount(Integer joinedAmount) {
		this.joinedAmount = joinedAmount;
	}

	public String getCreatorPhone() {
		return creatorPhone;
	}

	public void setCreatorPhone(String creatorPhone) {
		this.creatorPhone = creatorPhone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", creatorId=" + creatorId + ", fieldId=" + fieldId + ", createdTime="
				+ createdTime + ", startTime=" + startTime + ", endTime=" + endTime + ", matchType=" + matchType
				+ ", joinedAmount=" + joinedAmount + ", creatorPhone=" + creatorPhone + ", remarks=" + remarks + "]";
	}

}
