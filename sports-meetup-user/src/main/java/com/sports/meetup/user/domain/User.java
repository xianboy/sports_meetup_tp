package com.sports.meetup.user.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@Length(min=11, max=11, message="请输入正确的手机号.")
	private String phoneNumber;
	
	@NotBlank(message="验证码不能为空.")
	private String verificationCode;
	
	@NotBlank(message="密码不能为空.")
	private String password;

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
	private Date createdTime;
	
	//修改个人资料时间
	private Date updatedTime;
	
	

	public User(Long userId, String phoneNumber, String verificationCode, String password) {
		super();
		this.userId = userId;
		this.phoneNumber = phoneNumber;
		this.verificationCode = verificationCode;
		this.password = password;
	}

	
	public User() {
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


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((verificationCode == null) ? 0 : verificationCode.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (verificationCode == null) {
			if (other.verificationCode != null)
				return false;
		} else if (!verificationCode.equals(other.verificationCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", phoneNumber=" + phoneNumber + ", verificationCode=" + verificationCode
				+ ", password=" + password + "]";
	}
	
	
		
}
