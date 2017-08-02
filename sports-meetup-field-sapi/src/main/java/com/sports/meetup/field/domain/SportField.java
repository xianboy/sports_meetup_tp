/**
 * 
 */
package com.sports.meetup.field.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private Long id;
	
	//场地地址
	private String fieldLocation;
	
	//场地类型
	private String fieldType;
	
	//场地管理人电话
	private String adminPhone;
	
	//场地GPS信息 
	private Long gpsLocationId;
	
	//场地图片信息
	private String picsOfField;
	
	//是否审核通过
	private Boolean isApproved;
	

	public SportField() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGpsLocation(Long gpsLocationId) {
		this.gpsLocationId = gpsLocationId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setGpsLocationId(Long gpsLocationId) {
		this.gpsLocationId = gpsLocationId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminPhone == null) ? 0 : adminPhone.hashCode());
		result = prime * result + ((fieldLocation == null) ? 0 : fieldLocation.hashCode());
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
		result = prime * result + ((gpsLocationId == null) ? 0 : gpsLocationId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isApproved == null) ? 0 : isApproved.hashCode());
		result = prime * result + ((picsOfField == null) ? 0 : picsOfField.hashCode());
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
		SportField other = (SportField) obj;
		if (adminPhone == null) {
			if (other.adminPhone != null)
				return false;
		} else if (!adminPhone.equals(other.adminPhone))
			return false;
		if (fieldLocation == null) {
			if (other.fieldLocation != null)
				return false;
		} else if (!fieldLocation.equals(other.fieldLocation))
			return false;
		if (fieldType == null) {
			if (other.fieldType != null)
				return false;
		} else if (!fieldType.equals(other.fieldType))
			return false;
		if (gpsLocationId == null) {
			if (other.gpsLocationId != null)
				return false;
		} else if (!gpsLocationId.equals(other.gpsLocationId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isApproved == null) {
			if (other.isApproved != null)
				return false;
		} else if (!isApproved.equals(other.isApproved))
			return false;
		if (picsOfField == null) {
			if (other.picsOfField != null)
				return false;
		} else if (!picsOfField.equals(other.picsOfField))
			return false;
		return true;
	}

	
}
