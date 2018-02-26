package com.sports.meetup.field.sapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sports.meetup.field.sapi.domain.SportField;


public interface SportFieldRepository extends JpaRepository<SportField, Long> {
	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM sport_field HAVING distance < 20 ORDER BY distance LIMIT ?3, ?4 ", nativeQuery = true)
	List<Object> findNearbySportFields(Double longitude, Double latitude,Integer m, Integer n);

	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM sport_field WHERE field_type in(?3) HAVING distance < 20 ORDER BY distance LIMIT ?4, ?5 ", nativeQuery = true)
	List<Object> findNearbySportFields(Double longitude, Double latitude,String fieldType,Integer m, Integer n);

	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM sport_field WHERE field_type = field_type HAVING distance < 20 ORDER BY distance LIMIT ?3, ?4 ", nativeQuery = true)
	List<Object> findNearbySportFields01(Double longitude, Double latitude,Integer m, Integer n);

	@Query(value="  SELECT DISTINCT sport_field.*,(6371 * ACOS(COS(RADIANS(?2)) * COS(RADIANS(sport_field.latitude)) * COS(RADIANS(sport_field.longitude) - RADIANS(?1)) + SIN(RADIANS(?2)) * SIN(RADIANS(sport_field.latitude)))) AS distance FROM sport_field ,sport_match WHERE sport_field.`field_id` = sport_match.`field_id` AND SYSDATE()  < end_time HAVING distance < 20 ORDER BY distance LIMIT ?3, ?4", nativeQuery = true)
	List<Object> findNearFieldsHaveMatches(Double longitude, Double latitude,Integer m, Integer n);

}
