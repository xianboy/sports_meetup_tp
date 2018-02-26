/**
 * 
 */
package com.sports.meetup.match.papi.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Administrator7
 *
 */
public class SportField {

	private Long fieldId;

	// 添加人的Id
	private Long creatorId;

	// 场地名称
	private String fieldName;

	// 场地地址
	@NotBlank(message = "场地地址不能为空.")
	private String address;

	// 场地类型
	@NotBlank(message = "场地类型不能为空.")
	private String fieldType;

	// 场地管理人电话
	private String adminPhone;

	private String fieldDetail;

	// 场地图片信息
	private String picsOfField;

	// 经度
	@NotNull
	private Double longitude;
	// 纬度
	@NotNull
	private Double latitude;

	// 是否审核通过
	private Boolean isApproved;

	private Date createdDate;

	private Boolean isFree;
	
	private Boolean hasMatch;
	
	private Double pricePerHour;
	
	
	public SportField() {
		super();
	}

	
	public Double getPricePerHour() {
		return pricePerHour;
	}


	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}


	public Boolean getHasMatch() {
		return hasMatch;
	}
		
	public void setHasMatch(Boolean hasMatch) {
		this.hasMatch = hasMatch;
	}
		

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getFieldDetail() {
		return fieldDetail;
	}

	public void setFieldDetail(String fieldDetail) {
		this.fieldDetail = fieldDetail;
	}

	public String getPicsOfField() {
		return picsOfField;
	}

	public void setPicsOfField(String picsOfField) {
		this.picsOfField = picsOfField;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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

}
