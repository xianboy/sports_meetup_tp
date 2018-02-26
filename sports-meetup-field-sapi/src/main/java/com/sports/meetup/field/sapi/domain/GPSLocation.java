package com.sports.meetup.field.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class GPSLocation {
	
	private Long gpsLocationId;
	
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	
	
	public GPSLocation() {
		super();
	}
	public Long getGpsLocationId() {
		return gpsLocationId;
	}
	public void setGpsLocationId(Long gpsLocationId) {
		this.gpsLocationId = gpsLocationId;
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
