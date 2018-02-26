package com.sports.meetup.field.sapi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;

@Component
public class NearField {
    @GeneratedValue
    private BigInteger fieldId;

    // 添加人的Id
    private BigInteger creatorId;

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

    private Double distance;

    public BigInteger getFieldId() {
        return fieldId;
    }

    public void setFieldId(BigInteger fieldId) {
        this.fieldId = fieldId;
    }

    public BigInteger getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(BigInteger creatorId) {
        this.creatorId = creatorId;
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

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean approved) {
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean free) {
        isFree = isFree;
    }

    public Boolean getHasMatch() {
        return hasMatch;
    }

    public void setHasMatch(Boolean hasMatch) {
        this.hasMatch = hasMatch;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getFieldDetail() {
        return fieldDetail;
    }

    public void setFieldDetail(String fieldDetail) {
        this.fieldDetail = fieldDetail;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "NearField{" +
                "fieldId=" + fieldId +
                ", creatorId=" + creatorId +
                ", fieldName='" + fieldName + '\'' +
                ", address='" + address + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", picsOfField='" + picsOfField + '\'' +
                ", isApproved=" + isApproved +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", createdDate=" + createdDate +
                ", isFree=" + isFree +
                ", hasMatch=" + hasMatch +
                ", pricePerHour=" + pricePerHour +
                ", fieldDetail='" + fieldDetail + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
