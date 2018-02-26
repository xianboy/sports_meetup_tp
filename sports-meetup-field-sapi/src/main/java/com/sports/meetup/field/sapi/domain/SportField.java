/**
 * 
 */
package com.sports.meetup.field.sapi.domain;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

/**
 * @author Administrator7
 *
 */
@Entity
@Component
public class SportField {

	@Id
	@GeneratedValue
	private Long fieldId;

	// 添加人的Id
	private Long creatorId;

	// 场地名称
	private String fieldName;

	// 场地地址
	@NotBlank(message = "详细地址不能为空.")
	private String address;

	// 场地类型
	@NotBlank(message = "场地类型不能为空.")
	private String fieldType;

	// 场地管理人电话
	private String adminPhone;

	// 场地图片信息
	@Column(columnDefinition = "text")
	private String picsOfField;

	// 是否审核通过
	private Boolean isApproved;

	// 经度
	@NotNull
	private Double longitude;
	
	// 纬度
	@NotNull
	private Double latitude;

	//场地添加日期
	private Timestamp createdDate;

	//场地是否收费
	private Boolean isFree;
	
	//场地最近是否有比赛
	private Boolean hasMatch;

	//收费场地小时单价
	private Double pricePerHour;
	
	//场地详细信息
	private String fieldDetail;
	
	
	public String getFieldDetail() {
		return fieldDetail;
	}

	public void setFieldDetail(String fieldDetail) {
		this.fieldDetail = fieldDetail;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public SportField() {
		super();
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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

	public String getPicsOfField() {
		return picsOfField;
	}

	public void setPicsOfField(String picsOfField) {
		this.picsOfField = picsOfField;
	}

	@Override
	public String toString() {
		return "SportField [fieldId=" + fieldId + ", creatorId=" + creatorId + ", fieldName=" + fieldName + ", address="
				+ address + ", fieldType=" + fieldType + ", adminPhone=" + adminPhone + ", picsOfField=" + picsOfField
				+ ", isApproved=" + isApproved + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", createdDate=" + createdDate + ", isFree=" + isFree + ", hasMatch=" + hasMatch + ", pricePerHour="
				+ pricePerHour + "]";
	}

	
}
