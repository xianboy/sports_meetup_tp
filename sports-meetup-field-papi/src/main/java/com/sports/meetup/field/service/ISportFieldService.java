package com.sports.meetup.field.service;

import com.sports.common.domain.ApiDefaultResponse;
import com.sports.common.domain.SportField;

public interface ISportFieldService {

	ApiDefaultResponse addSportField(SportField sportField);

	ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude);

}
