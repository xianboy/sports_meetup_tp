package com.sports.meetup.match.papi.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class SportMatch {
	
	private Long matchId;
	
	//发起人Id
	private Long creatorId;
	
	//比赛场地Id
	private Long fieldId;
	
	//创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;
	
	//比赛开始时间
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	//比赛结束时间
	@JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
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
	
	//备注
	private String remarks;

	//比赛举行日期
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
		result = prime * result + ((creatorPhone == null) ? 0 : creatorPhone.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		result = prime * result + ((joinedAmount == null) ? 0 : joinedAmount.hashCode());
		result = prime * result + ((matchType == null) ? 0 : matchType.hashCode());
		result = prime * result + ((matchId == null) ? 0 : matchId.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SportMatch other = (SportMatch) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (creatorId == null) {
			if (other.creatorId != null)
				return false;
		} else if (!creatorId.equals(other.creatorId))
			return false;
		if (creatorPhone == null) {
			if (other.creatorPhone != null)
				return false;
		} else if (!creatorPhone.equals(other.creatorPhone))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		if (joinedAmount == null) {
			if (other.joinedAmount != null)
				return false;
		} else if (!joinedAmount.equals(other.joinedAmount))
			return false;
		if (matchType == null) {
			if (other.matchType != null)
				return false;
		} else if (!matchType.equals(other.matchType))
			return false;
		if (matchId == null) {
			if (other.matchId != null)
				return false;
		} else if (!matchId.equals(other.matchId))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	
	
	
}
