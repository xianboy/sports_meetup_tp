package com.sports.meetup.field.sapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sports.meetup.field.sapi.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM Location HAVING distance < 5 ORDER BY distance LIMIT 10", nativeQuery = true)
	List<Location> findLocationsNearby(Double lng, Double lat);
	
}
