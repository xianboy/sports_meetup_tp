package com.sports.meetup.match.sapi.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import com.sports.meetup.match.sapi.domain.*;
import com.sports.meetup.match.sapi.domain.entity.SportMatch;
import com.sports.meetup.match.sapi.domain.entity.UserApply;

public interface IMatchService {

	SportMatch saveMatch(SportMatch match);
	UserApply joinMatch(UserApply match);

	List<MyMatchBean> findSportMatchByCreatorId(Long creatorinfo, String pageInfo);

	List<MyMatchBean> findSportOldMatchByCreatorId(Long creatorinfo, String pageInfo);

	List<SportMatch> findNearbyMatches(Double longitude, Double latitude);

	SportMatch findMatchById(Long matchId);
	SportMatchAndAddress findMatchById(BigInteger matchId);
	CreatedUserBean findCreatedUserInfoById(BigInteger matchId);
	List<AppliedUserBean> findAppliedUserInfoById(BigInteger matchId);
	//判断是否已加入该比赛
	Boolean isJoinedMatches(UserApply request);
	//加入比赛后返回的信息
	MyMatchBean findMatchinfoById(BigInteger matchId);


	//获取场地比赛信息（注意时间）
	List<MyMatchBean> findMatchByFieldId(Long fieldId, String pageInfo);
	List<MyMatchBean> findMatchByFieldId(Long fieldId, Date date, String pageInfo);

	List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String pageInfo);
	List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String fieldType,String pageInfo);
	//获得我参加的比赛信息（未开始）
	List<MyMatchBean> getMyMatches(Long userid,String pageInfo);
	//获得我参加的历史比赛信息
	List<MyMatchBean> getMyOldMatches(Long userid,String pageInfo);
}
