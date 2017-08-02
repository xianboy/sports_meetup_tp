/**
 * 
 */
package com.sports.meetup.field.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Administrator7
 *
 */
public class SportField {

	private Long id;

	// 场地地址
	@NotBlank(message="详细地址不能为空.")
	private String fieldLocation;

	// 场地类型
	@NotBlank(message="场地类型不能为空.")
	private String fieldType;

	// 场地管理人电话
	private String adminPhone;

	// 场地图片信息
	private String picsOfField;

	// 是否审核通过
	private Boolean isApproved;

	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;

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

	public SportField() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getFieldLocation() {
		return fieldLocation;
	}

	public void setFieldLocation(String fieldLocation) {
		this.fieldLocation = fieldLocation;
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

	public SportField(Long id, String fieldLocation, String fieldType, String adminPhone, String picsOfField,
			Boolean isApproved, Double longitude, Double latitude) {
		super();
		this.id = id;
		this.fieldLocation = fieldLocation;
		this.fieldType = fieldType;
		this.adminPhone = adminPhone;
		this.picsOfField = picsOfField;
		this.isApproved = isApproved;
		this.longitude = longitude;
		this.latitude = latitude;
	}


}
