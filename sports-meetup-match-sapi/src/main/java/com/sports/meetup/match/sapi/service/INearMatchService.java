package com.sports.meetup.match.sapi.service;

import com.sports.meetup.match.sapi.domain.NearbyMatchBean;

import java.util.List;

public interface INearMatchService {
    List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String fieldType, String pageInfo);
}
