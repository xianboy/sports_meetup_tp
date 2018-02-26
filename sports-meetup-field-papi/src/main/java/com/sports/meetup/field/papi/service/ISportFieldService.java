package com.sports.meetup.field.papi.service;

import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.field.papi.domain.SportField;

public interface ISportFieldService {

	ApiDefaultResponse addSportField(SportField sportField);

	ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude,String pageInfo);

	ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude,String fieldType,String pageInfo);

	ApiDefaultResponse findNearFieldsHaveMatches(Double longitude, Double latitude,String pageInfo);

	SportField getSportFieldById(Long fieldId);

}
