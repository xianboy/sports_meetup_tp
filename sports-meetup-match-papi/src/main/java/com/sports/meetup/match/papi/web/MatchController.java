package com.sports.meetup.match.papi.web;


import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.match.papi.domain.InitialMatchRequest;
import com.sports.meetup.match.papi.domain.UserApply;
import com.sports.meetup.match.papi.service.IMatchService;

@RestController
@RequestMapping(value= {"/matches", "/v1.0/matches"})
public class MatchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MatchController.class);
	
	@Autowired
	private IMatchService matchService;
	//创建比赛
	@PostMapping(value="/initialMatch")
	public ApiDefaultResponse initialMatch(@RequestBody InitialMatchRequest request) {
		LOG.info("Enter Match Papi controller: initialMatch ...");
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			response = matchService.initialMatch(request);
		}catch (Exception e) {
			response.setResponseCode(ConstantFields.getpServiceerrorCode());
			response.setMessage(ConstantFields.getpServiceerrorMsg());
			response.setResponseBody(e.getCause());
		}
		return  response;
	}

	/**
	 * 申请加入比赛
	 * @param request
	 * @return
	 */
	@PostMapping(value="/joinMatch")
	public ApiDefaultResponse joinMatch(@RequestBody UserApply request) {
		LOG.info("Enter Match Papi controller: joinMatch ...");
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			response = matchService.joinMatch(request);
		}catch (Exception e) {
			response.setResponseCode(ConstantFields.getpServiceerrorCode());
			response.setMessage(ConstantFields.getpServiceerrorMsg());
			response.setResponseBody(e.getCause());
		}
		return  response;
	}
	
	/*//获取附近场地的比赛信息（附近）
	@GetMapping(value="/{longitude}/{latitude:.+}")
	public ApiDefaultResponse getNearbyMatches(@PathVariable Double longitude, @PathVariable Double latitude){
	
		return this.matchService.getNearbyMatches(longitude, latitude);
	}*/

	//获取附近场地的比赛信息(加分页参数)
	@GetMapping(value="/getNearbyMatches/{longitude}/{latitude}/{pageInfo}")
	public ApiDefaultResponse getNearbyMatches(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable  String
			pageInfo){

		return this.matchService.getNearbyMatches(longitude, latitude,pageInfo);
	}

	/**
	 * 由场地ID筛选比赛
	 * @param longitude
	 * @param latitude
	 * @param fieldID
	 * @param pageInfo
	 * @return
	 */
	@GetMapping(value="/getNearbyMatches/{longitude}/{latitude}/{fieldType}/{pageInfo}")
	public ApiDefaultResponse getNearbyMatches(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable String fieldType,@PathVariable  String pageInfo){
		return this.matchService.getNearbyMatches(longitude, latitude,fieldType,pageInfo);
	}

	//获取我申请加入的比赛信息(未开始)
	@GetMapping(value="/getApplyMatches/{userId}/{pageInfo}")
	public ApiDefaultResponse getApplyMatches(@PathVariable Long userId,@PathVariable  String pageInfo){
		return this.matchService.getApplyMatches(userId,pageInfo);
	}
	//获取我申请加入的比赛信息(已结束)
	@GetMapping(value="/getOldApplyMatches/{userId}/{pageInfo}")
	public ApiDefaultResponse getOldApplyMatches(@PathVariable Long userId,@PathVariable  String pageInfo){
		return this.matchService.getOldApplyMatches(userId,pageInfo);
	}
	
	//获取我发起的比赛（未开始）
	@GetMapping(value="/getMyMatches/{creatorinfo}/{pageInfo}")
	public ApiDefaultResponse getMyMatches(@PathVariable Long creatorinfo,@PathVariable  String pageInfo){
		return this.matchService.getMyMatches(creatorinfo,pageInfo);
	}
	//获取我发起的比赛信息(已结束)
	@GetMapping(value="/getMyOldMatches/{creatorinfo}/{pageInfo}")
	public ApiDefaultResponse getMyOldMatches(@PathVariable Long creatorinfo,@PathVariable  String pageInfo){
		return this.matchService.getMyOldMatches(creatorinfo,pageInfo);
	}
	//获取单个比赛详细信息
	@GetMapping(value="/match/{matchId}")
	public ApiDefaultResponse getMatch(@PathVariable Long matchId) {
		return this.matchService.getMatch(matchId);
	}

	//获取具体场地将要进行的比赛（按时间排序）
	@GetMapping(value="/getMatchesByFieldId/{fieldId}/{pageInfo}")
	public ApiDefaultResponse getMatchesByFieldId(@PathVariable Long fieldId,@PathVariable  String pageInfo) {
		return this.matchService.getMatchesByFieldId(fieldId,pageInfo);
	}
	//获取具体场地将要进行的比赛（按时间排序）
	@GetMapping(value="/getMatchesByFieldId/{fieldId}/{date}/{pageInfo}")
	public ApiDefaultResponse getMatchesByFieldId(@PathVariable Long fieldId, @PathVariable Date date, @PathVariable  String pageInfo) {
		return this.matchService.getMatchesByFieldId(fieldId,date,pageInfo);
	}
	//获取历史比赛信息(包括发起的和申请加入的)
	@GetMapping(value="/getEndMatches/{userId}/{pageInfo}")
	public ApiDefaultResponse getEndMatches(@PathVariable Long userId,@PathVariable  String pageInfo){
		return this.matchService.getEndMatches(userId,pageInfo);
	}

}
