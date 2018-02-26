package com.sports.meetup.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import java.math.BigInteger;
import java.util.Date;

@Component
public class UserInfo {

	@GeneratedValue
	private BigInteger userId;

	@Length(min=11, max=11, message="请输入正确的手机号.")
	private String phoneNumber;

	//个人头像
	private String icon;

	private String name;

	//常住地址
	private String address;

	private String gender;

	private String weekday;

	//个人可运动时间
	private String weekend;

	//个人爱好(运动标签)：足球、篮球
	private String hobbies;

	//注册使时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;

	//修改个人资料时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedTime;

	private String city;



	public UserInfo() {
		super();
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


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userId=" + userId +
				", phoneNumber='" + phoneNumber + '\'' +
				", icon='" + icon + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", gender='" + gender + '\'' +
				", weekday='" + weekday + '\'' +
				", weekend='" + weekend + '\'' +
				", hobbies='" + hobbies + '\'' +
				", createdTime=" + createdTime +
				", updatedTime=" + updatedTime +
				", city='" + city + '\'' +
				'}';
	}
}
