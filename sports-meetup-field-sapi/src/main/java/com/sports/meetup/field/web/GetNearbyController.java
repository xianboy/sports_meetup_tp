package com.sports.meetup.field.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.field.domain.Location;
import com.sports.meetup.field.service.impl.LocationService;

@RestController
public class GetNearbyController {
	@Autowired
	private LocationService localService;
	
	@GetMapping(value="/getNearbyLocations/{lng}/{lat}")
	public List<Location> getNearbyLocations(@PathVariable Double lng, @PathVariable Double lat){
		return this.localService.getNearbyLocations(lng, lat);
	}
}
