package com.sports.meetup.match.sapi.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MatchType {
	@Id
	@GeneratedValue
	private Long typeId;
	
	private String typeName;
	
	private Long totalNumber;
	
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	
	
}
