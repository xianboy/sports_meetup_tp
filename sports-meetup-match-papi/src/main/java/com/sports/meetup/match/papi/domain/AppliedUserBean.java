package com.sports.meetup.match.papi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;

@Component
public class AppliedUserBean {

	private BigInteger userId;

	private String applyResult;

	private String phoneNumber;

//	private String verificationCode;
//	private String password;
	// 个人头像
	private String icon;

	private String name;

	// 常住地址
	private String city;

	private String address;

	private String gender;

	private String weekday;

	// 个人可运动时间
	private String weekend;

	// 个人爱好(运动标签)：足球、篮球
	private String hobbies;

	// 注册使时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;
//	private Date createdTime;

	// 修改个人资料时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp updatedTime;

//	private Date updatedTime;


	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getApplyResult() {
		return applyResult;
	}

	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getWeekend() {
		return weekend;
	}

	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "AppliedUserBean{" +
				"userId=" + userId +
				", applyResult='" + applyResult + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", icon='" + icon + '\'' +
				", name='" + name + '\'' +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				", gender='" + gender + '\'' +
				", weekday='" + weekday + '\'' +
				", weekend='" + weekend + '\'' +
				", hobbies='" + hobbies + '\'' +
				", createdTime=" + createdTime +
				", updatedTime=" + updatedTime +
				'}';
	}
}
