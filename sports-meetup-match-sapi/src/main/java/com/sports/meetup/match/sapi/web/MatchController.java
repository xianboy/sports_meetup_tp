package com.sports.meetup.match.sapi.web;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import com.sports.meetup.match.sapi.domain.*;
import com.sports.meetup.match.sapi.domain.entity.UserApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sports.meetup.match.sapi.domain.entity.SportMatch;
import com.sports.meetup.match.sapi.service.IMatchService;

@RestController
@RequestMapping(value= {"/matches", "/v1.0/matches"})
public class MatchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MatchController.class);
	
	@Autowired
	private IMatchService matchService;

	//创建比赛
	@PostMapping(value="/saveMatch")
	public SportMatch saveMatch(@RequestBody InitialMatchRequest request) {
		LOG.info("Enter Match Sapi Controller saveMatch ...");
		return matchService.saveMatch(request.getMatch());
	}
	//加入比赛
	@PostMapping(value="/joinMatch")
	public UserApply joinMatch(@RequestBody UserApply request){
		LOG.info("Enter Match Sapi Controller joinMatch ...");
		return matchService.joinMatch(request);
	}
	//加入比赛后返回的信息
	@GetMapping("/findMatchinfoById/{matchId}")
	public MyMatchBean findMatchinfoById(@PathVariable BigInteger matchId) {
		MyMatchBean match = this.matchService.findMatchinfoById(matchId);
		return match;
	}
	/**
	 * 判断是否已加入该比赛
	 * @param request
	 * @return
	 */
	@PostMapping("/isJoinedMatches")
	public Boolean isJoinedMatches(@RequestBody UserApply request){
		Boolean result = this.matchService.isJoinedMatches(request);
		return result;
	}

	//获取附近的比赛(不用了)
	/*@GetMapping("/{longitude}/{latitude:.+}")
	public List<SportMatch> findNearbyMatches(@PathVariable Double longitude, @PathVariable Double latitude){
		LOG.info("开始执行 Match Sapi Controller getNearbyMatches 方法 ...");
		return this.matchService.findNearbyMatches(longitude, latitude);
	}*/


	//获取单个比赛信息
	@GetMapping("/match/{matchId}")
	public SportMatch findMatchById(@PathVariable Long matchId) {
		SportMatch match = this.matchService.findMatchById(matchId);
		return match;
	}



	/**
	 *获取单个比赛信息
	 * @param matchId
	 * @return
	 */
	@GetMapping("/matchAndAddress/{matchId}")
	public SportMatchAndAddress findMatchById(@PathVariable BigInteger matchId) {
		SportMatchAndAddress match = this.matchService.findMatchById(matchId);
		return match;
	}

	/**
	 * 通过matchid获取创建者信息
	 * @param matchId
	 * @return
	 */
	@GetMapping("/CreatedUserInfo/{matchId}")
	public CreatedUserBean findCreatedUserInfoById(@PathVariable BigInteger matchId) {
		CreatedUserBean match = this.matchService.findCreatedUserInfoById(matchId);
		return match;
	}

	/**
	 * 通过matchid获取申请成员信息
	 * @param matchId
	 * @return
	 */
	@GetMapping("/appliedUserInfo/{matchId}")
	public List<AppliedUserBean> findAppliedUserInfoById(@PathVariable BigInteger matchId) {
		List<AppliedUserBean> match = this.matchService.findAppliedUserInfoById(matchId);
		return match;
	}


	//通过fieldid获取场地比赛信息（注意时间）
	@GetMapping(value="/getMatchesByFieldId/{fieldId}/{pageInfo}")
	public List<MyMatchBean> getMatchesByFieldId(@PathVariable Long fieldId,@PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.findMatchByFieldId(fieldId,pageInfo);
//		if(0==matches.size()) {
//			matches = null;
//		}
		return  matches;
	}
	/**
	 * 通过fieldid获取场地比赛信息（注意时间）
	 * @param fieldId
	 * @param pageInfo
	 * @return
	 */
	@GetMapping(value="/getMatchesByFieldId/{fieldId}/{date}/{pageInfo}")
	public List<MyMatchBean> getMatchesByFieldIdAndDate(@PathVariable Long fieldId, @PathVariable Date date, @PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.findMatchByFieldId(fieldId,date,pageInfo);
		return  matches;
	}

	//首页：获取附近的比赛
	@GetMapping(value="/getNearbyMatches/{longitude}/{latitude}/{pageInfo}")
	public List<NearbyMatchBean>getNearByMatches(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable  String
			pageInfo){
		List<NearbyMatchBean> matches = this.matchService.getNearbyMatches(longitude, latitude, pageInfo);
		return  matches;
	}

	/**\
	 *获取比赛信息（通过比赛类型筛选）
	 * @param longitude
	 * @param latitude
	 * @param pageInfo
	 * @return
	 */
	@GetMapping(value="/getNearbyMatches/{longitude}/{latitude}/{fieldType}/{pageInfo}")
	public List<NearbyMatchBean>getNearByMatches(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable String fieldType,@PathVariable  String
			pageInfo){
		List<NearbyMatchBean> matches = this.matchService.getNearbyMatches(longitude, latitude,fieldType, pageInfo);
		return  matches;
	}
	//获取我发布的比赛（将要进行的比赛）
	@GetMapping(value="/getMatchesByCreatorId/{creatorinfo}/{pageInfo}")
	public List<MyMatchBean> getMatchesByCreatorId(@PathVariable Long creatorinfo,@PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.findSportMatchByCreatorId(creatorinfo,pageInfo);
//		if(0==matches.size()) {
//			matches = null;
//		}
		return  matches;
	}

	//获取我发布的比赛（已结束的比赛）
	@GetMapping(value="/getOldMatchesByCreatorId/{creatorinfo}/{pageInfo}")
	public List<MyMatchBean> getOldMatchesByCreatorId(@PathVariable Long creatorinfo,@PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.findSportOldMatchByCreatorId(creatorinfo,pageInfo);
//		if(0==matches.size()) {
//			matches = null;
//		}
		return  matches;
	}
	//获取我申请加入的比赛信息（未开始）
	@GetMapping(value="/getMyMatches/{userId}/{pageInfo}")
	public List<MyMatchBean> getMyMatches(@PathVariable Long userId,@PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.getMyMatches(userId,pageInfo);
//		if(0==matches.size()) {
//			matches = null;
//		}
		return  matches;
	}
	//获取我申请加入过的比赛信息（已结束）
	@GetMapping(value="/getMyOldMatches/{userid}/{pageInfo}")
	public List<MyMatchBean> getMyOldMatches(@PathVariable Long userid,@PathVariable  String pageInfo){
		List<MyMatchBean> matches = this.matchService.getMyOldMatches(userid,pageInfo);
//		if(0==matches.size()) {
//			matches = null;
//		}
		return  matches;
	}





}
