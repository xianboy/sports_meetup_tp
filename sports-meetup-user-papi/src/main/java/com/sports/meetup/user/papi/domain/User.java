package com.sports.meetup.user.papi.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class User {
	
	private Long userId;
	
	@Length(min=11, max=11, message="请正确输入手机号.")
	private String phoneNumber;
	
/*	@NotBlank(message="请输入正确的验证码.")
	@Length(min=6,max=6, message="请输入正确的验证码.")*/
	private String verificationCode;
	
	@NotBlank(message="密码不能为空.")
	private String password;
	
	//个人头像
	private String icon;
	
	private String name;
	//城市
	private String city;
	//常住地址
	private String address;
	
	private String gender;
	
	private String weekday;
	
	//个人可运动时间
	private String weekend;
	
	//个人爱好(运动标签)：足球、篮球
	private String hobbies;

	// 注册使时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;
//	private Date createdTime;

	// 修改个人资料时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp updatedTime;
//	private Date updatedTime;
	
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
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", phoneNumber='" + phoneNumber + '\'' +
				", verificationCode='" + verificationCode + '\'' +
				", password='" + password + '\'' +
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((hobbies == null) ? 0 : hobbies.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((updatedTime == null) ? 0 : updatedTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((verificationCode == null) ? 0 : verificationCode.hashCode());
		result = prime * result + ((weekday == null) ? 0 : weekday.hashCode());
		result = prime * result + ((weekend == null) ? 0 : weekend.hashCode());
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
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (hobbies == null) {
			if (other.hobbies != null)
				return false;
		} else if (!hobbies.equals(other.hobbies))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
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
		if (updatedTime == null) {
			if (other.updatedTime != null)
				return false;
		} else if (!updatedTime.equals(other.updatedTime))
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
		if (weekday == null) {
			if (other.weekday != null)
				return false;
		} else if (!weekday.equals(other.weekday))
			return false;
		if (weekend == null) {
			if (other.weekend != null)
				return false;
		} else if (!weekend.equals(other.weekend))
			return false;
		return true;
	}

		
}
