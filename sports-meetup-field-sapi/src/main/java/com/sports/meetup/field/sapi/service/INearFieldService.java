package com.sports.meetup.field.sapi.service;

import java.util.List;

import com.sports.meetup.field.sapi.domain.NearField;

public interface INearFieldService {
    List<NearField> findNearbySportFields(Double longitude, Double latitude, String fieldType, String pageInfo);
}
