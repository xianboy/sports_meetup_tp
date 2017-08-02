package com.sports.meetup.field.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class GPSLocation {
	
	@Id
	@GeneratedValue
	private Long gpsLocationId;
	
	//经度
	private Double longitude;
	//纬度
	private Double latitude;

	public double getLongitude() {
		return longitude;
	}

	public Long getGpsLocationId() {
		return gpsLocationId;
	}

	public void setGpsLocationId(Long gpsLocationId) {
		this.gpsLocationId = gpsLocationId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gpsLocationId == null) ? 0 : gpsLocationId.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		GPSLocation other = (GPSLocation) obj;
		if (gpsLocationId == null) {
			if (other.gpsLocationId != null)
				return false;
		} else if (!gpsLocationId.equals(other.gpsLocationId))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	
	
}
