package com.sports.meetup.match.papi.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.match.papi.domain.InitialMatchRequest;
import com.sports.meetup.match.papi.domain.UserApply;

@Service
public interface IMatchService {

	ApiDefaultResponse initialMatch(InitialMatchRequest request);

	ApiDefaultResponse joinMatch(UserApply request);

	//获取我发起的比赛信息(未开始)
	ApiDefaultResponse getMyMatches(Long creatorinfo,String pageInfo);

	//获取我发起的比赛信息(已结束)
	ApiDefaultResponse getMyOldMatches(Long creatorinfo,String pageInfo);

	ApiDefaultResponse getNearbyMatches(Double lng, Double lat);

	//获取附件的比赛（含分页参数）
	ApiDefaultResponse getNearbyMatches(Double lng, Double lat,String pageInfo);
	ApiDefaultResponse getNearbyMatches(Double lng, Double lat,String fieldID,String pageInfo);
    //获取申请加入的比赛信息
	ApiDefaultResponse getApplyMatches(Long userinfo,String pageInfo);
	//获取申请加入的比赛信息(已结束)
	ApiDefaultResponse getOldApplyMatches(Long userinfo,String pageInfo);

	ApiDefaultResponse getMatch(Long matchId);
    //通过场地获取比赛
	ApiDefaultResponse getMatchesByFieldId(Long fieldId,String pageInfo);
	ApiDefaultResponse getMatchesByFieldId(Long fieldId,Date date,String pageInfo);

	//获取历史比赛信息（包括发起的和申请加入的）
	ApiDefaultResponse getEndMatches(Long userinfo,String pageInfo);
}
