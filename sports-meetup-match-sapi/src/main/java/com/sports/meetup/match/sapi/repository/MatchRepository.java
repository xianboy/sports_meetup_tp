package com.sports.meetup.match.sapi.repository;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sports.meetup.match.sapi.domain.entity.SportMatch;

public interface MatchRepository extends JpaRepository<SportMatch, Long> {

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM sport_match,sport_field,match_type WHERE sport_match.creator_id = ?1 AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id` AND  SYSDATE() < end_time ORDER BY start_time ASC limit ?2,?3", nativeQuery = true)
	List<Object> findByCreatorId(Long creatorId,int pageNum, int pageSize);

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM sport_match,sport_field,match_type WHERE sport_match.creator_id = ?1 AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id` AND  SYSDATE() >= end_time ORDER BY end_time DESC limit ?2,?3", nativeQuery = true)
	List<Object> findByOldCreatorId(Long creatorId,int pageNum, int pageSize);

//	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM Sport_Match HAVING distance < 20 ORDER BY distance LIMIT 10", nativeQuery = true)
//	List<SportMatch> findMatchesByLongitudeAndLatitude(Double longitude, Double latitude);

	SportMatch findSportMatchByMatchId(Long matchId);

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,address,match_id,sport_match.creator_id FROM sport_match,sport_field,match_type WHERE sport_match.`match_id` = ?1 AND sport_match.field_id = sport_field.field_id AND sport_match.`match_type` = match_type.`type_id`", nativeQuery = true)
	Object findMatchinfoById(BigInteger matchId);

	@Query(value="SELECT match_id,created_time,end_time,sport_field.field_id,joined_amount,sport_match.latitude,sport_match.longitude,match_type,remarks,start_time,DATE,address FROM sport_match ,sport_field WHERE sport_match.`field_id`=sport_field.`field_id` AND match_id=?1", nativeQuery = true)
	Object findSportMatchByMatchId(BigInteger matchId);

	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM Sport_Match HAVING distance < 20 ORDER BY distance LIMIT 10", nativeQuery = true)
	List<SportMatch> findSportMatchByLongitudeAndLatitude(Double longitude, Double latitude);
	
	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,address,match_id,sport_match.creator_id FROM sport_match,sport_field,match_type WHERE sport_match.field_id = ?1 AND sport_match.field_id = sport_field.field_id AND sport_match.`match_type` = match_type.`type_id` AND SYSDATE() < end_time ORDER BY start_time ASC LIMIT ?2,?3", nativeQuery = true)
	List<Object> findMatchByFieldId(Long fieldId,int pageNum, int pageSize);

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,address,match_id,sport_match.creator_id FROM sport_match,sport_field,match_type WHERE sport_match.field_id = ?1 AND sport_match.field_id = sport_field.field_id AND sport_match.`match_type` = match_type.`type_id` AND sport_match.date = ?2 AND SYSDATE() < end_time ORDER BY start_time ASC LIMIT ?3,?4", nativeQuery = true)
	List<Object> findMatchByFieldId(Long fieldId, Date date, int pageNum, int pageSize);

//	@Query(value="SELECT *,(6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) AS distance FROM sport_field WHERE field_id in (SELECT field_id  FROM sport_match where (6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) < 20 ORDER BY (6371*acos(cos(radians(?2))*cos(radians(latitude))* cos( radians( longitude )-radians(?1) )+ sin(radians(?2))*sin(radians(latitude)))) )HAVING distance < 20 ORDER BY distance LIMIT 10", nativeQuery = true)
//	List<NearbyMatchBean> findNearybyMatches(Double longitude, Double latitude);

	@Query(value="  SELECT DISTINCT user.`name`,date,start_time,end_time,icon,(6371 * ACOS(COS(RADIANS(?2)) * COS(RADIANS(sport_match.latitude)) * COS(RADIANS(sport_match.longitude) - RADIANS(?1)) + SIN(RADIANS(?2)) * SIN(RADIANS(sport_match.latitude)))) AS distance,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM user,sport_match,sport_field,match_type WHERE user.`user_id` = sport_match.`creator_id` AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id`  AND SYSDATE() < end_time AND DATEDIFF(end_time,NOW())<3 AND DATEDIFF(end_time,NOW())>=0 HAVING distance < 20 ORDER BY distance limit ?3,?4", nativeQuery = true)
	List<Object> findMatches(Double longitude, Double latitude, int pageNum, int pageSize );

	@Query(value="  SELECT DISTINCT user.`name`,date,start_time,end_time,icon,(6371 * ACOS(COS(RADIANS(?2)) * COS(RADIANS(sport_match.latitude)) * COS(RADIANS(sport_match.longitude) - RADIANS(?1)) + SIN(RADIANS(?2)) * SIN(RADIANS(sport_match.latitude)))) AS distance,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM user,sport_match,sport_field,match_type WHERE user.`user_id` = sport_match.`creator_id` AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id`  AND field_type IN(?3) AND SYSDATE() < end_time AND DATEDIFF(end_time,NOW())<3 AND DATEDIFF(end_time,NOW())>=0 HAVING distance < 20 ORDER BY distance limit ?4,?5", nativeQuery = true)
	List<Object> findMatches(Double longitude, Double latitude, String fieldType, int pageNum, int pageSize );

	@Query(value="SELECT sport_match.`creator_id`,address,user.created_time,gender,hobbies,icon,name,phone_number,updated_time,weekday,weekend,city FROM user,sport_match WHERE user.`user_id` = sport_match.`creator_id` AND match_id =?1", nativeQuery = true)
	Object findCreatedUserinfo(BigInteger matchid);

	@Query(value="SELECT user_apply.user_id,apply_result,address,created_time,gender,hobbies,icon, name,phone_number,updated_time,weekday,weekend,city FROM user,user_apply WHERE user.`user_id` = user_apply.`user_id` AND match_id = ?1", nativeQuery = true)
	List<Object> findAppliedUserinfo(BigInteger matchid);

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM sport_match,sport_field,match_type WHERE match_id IN (SELECT match_id FROM user_apply WHERE user_id =?1) AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id` AND  SYSDATE() < end_time ORDER BY start_time ASC limit ?2,?3", nativeQuery = true)
	List<Object> findMyMatches(Long userinfo,int pageNum, int pageSize);

	@Query(value="SELECT date,start_time,end_time,match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` FROM sport_match,sport_field,match_type WHERE match_id IN (SELECT match_id FROM user_apply WHERE user_id =?1) AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id` AND  SYSDATE() >= end_time ORDER BY end_time DESC limit ?2,?3", nativeQuery = true)
	List<Object> findMyOldMatches(Long userinfo,int pageNum, int pageSize);

}
