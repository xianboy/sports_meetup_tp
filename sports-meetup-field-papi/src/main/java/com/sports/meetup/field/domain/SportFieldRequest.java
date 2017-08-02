package com.sports.meetup.field.domain;


import org.hibernate.validator.constraints.NotBlank;

public class SportFieldRequest {
	//场地地址
		@NotBlank(message="详细地址不能为空.")
		private String fieldLocation;
		
		//场地类型
		@NotBlank(message="场地类型不能为空.")
		private String fieldType;
		
		//场地管理人电话
		private String adminPhone;
		
		//场地GPS信息 
		private GPSLocation gpsLocation;
		
		//场地图片信息
		private String picsOfField;
		
		//是否审核通过
		private Boolean isApproved;
		
}
