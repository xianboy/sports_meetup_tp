package com.sports.meetup.field.sapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sports.meetup.field.sapi.domain.NearField;
import com.sports.meetup.field.sapi.domain.SportField;


@Service
public interface ISportFieldService {

	SportField saveSportField(SportField sportField);
//　　　NearField saveSportField(SportField sportField);
	List<NearField> findNearbySportFields(Double longitude, Double latitude,String pageInfo);
	SportField findSportFieldByFieldId(Long fieldId);
	List<NearField> findNearbySportFields(Double longitude,Double latitude,String fieldType,String pageInfo);
	List<NearField> findNearFieldsHaveMatches(Double longitude, Double latitude,String pageInfo);
}
