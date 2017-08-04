package com.sports.meetup.field.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.field.domain.Location;
import com.sports.meetup.field.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository localRepository;

	public List<Location> getNearbyLocations(double lng, double lat) {
		List<Location> locations = localRepository.findLocationsNearby(lng, lat);
		return locations;
	}
	
	
}
