package com.sports.meetup.match.papi.service.impl;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.match.papi.config.WebConfig;
import com.sports.meetup.match.papi.domain.CreatedUserBean;
import com.sports.meetup.match.papi.domain.EndMatchResponse;
import com.sports.meetup.match.papi.domain.InitialMatchRequest;
import com.sports.meetup.match.papi.domain.MyMatchBean;
import com.sports.meetup.match.papi.domain.SportField;
import com.sports.meetup.match.papi.domain.SportMatch;
import com.sports.meetup.match.papi.domain.UserApply;
import com.sports.meetup.match.papi.service.IMatchService;

@Service
public class MatchServiceImpl implements IMatchService {

    private static final Logger LOG = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private WebConfig config;

    /**
     * 创建比赛
     *
     * @param request
     * @return
     */
    @Override
    public ApiDefaultResponse initialMatch(InitialMatchRequest request) {
        LOG.info("==== 开始执行  Match Papi service initialMatch ...");
        if (LOG.isDebugEnabled()) {
            LOG.debug("the request of Match Papi to call service initialMatch is: {}", request.getMatch().toString());
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ApiDefaultResponse apiDefaultResponse = new ApiDefaultResponse();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Long fieldId = request.getMatch().getFieldId();
        
        //通过fieldId获取场地经纬度，再赋值给比赛对象
        SportField sportField = getSportFieldById(fieldId);
        request.getMatch().setLongitude(sportField.getLongitude());
        request.getMatch().setLatitude(sportField.getLatitude());
        //创建比赛时的时间校验
        Date startTime = request.getMatch().getStartTime();
        Date endTime = request.getMatch().getEndTime();
        if(startTime.getTime() >= endTime.getTime()){
            apiDefaultResponse.setResponseCode(ConstantFields.getBadRequestCode());
            apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
            apiDefaultResponse.setResponseBody(new Array[]{});
            return apiDefaultResponse;
        }

        HttpEntity<InitialMatchRequest> requestEntity = new HttpEntity<InitialMatchRequest>(request, headers);
        String url = config.getSaveMatchUrl();
        ResponseEntity<SportMatch> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SportMatch.class);
            apiDefaultResponse.setResponseCode(ConstantFields.getSuccessCode());
            apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
            apiDefaultResponse.setResponseBody(responseEntity.getBody());
        } catch (Exception e) {
            apiDefaultResponse.setResponseCode(ConstantFields.getSapiError509Code());
            apiDefaultResponse.setMessage(e.getMessage());
            apiDefaultResponse.setResponseBody(e.getCause());
        }
        LOG.info("==== 结束执行  Match Papi service initialMatch ...");
        return apiDefaultResponse;
    }

    //
    private SportField getSportFieldById(Long fieldId) {
		SportField sportField = new SportField();
		String findSportFieldByIdUrl =  config.getFindSportFieldByIdUrl()+fieldId;
		 RestTemplate restTemplate = new RestTemplate();
		try {
			sportField = restTemplate.getForObject(findSportFieldByIdUrl, SportField.class);
		}catch (Exception e) {
			LOG.error("通过fieldId获取场地信息失败: {}", e.getMessage());
		}
		return sportField;
	}

	/**
     * 申请加入比赛
     *
     * @param request
     * @return
     */
    @Override
    public ApiDefaultResponse joinMatch(UserApply request) {
        LOG.info("==== 开始执行  Match Papi service joinMatch ...");
        ApiDefaultResponse apiDefaultResponse = new ApiDefaultResponse();
        RestTemplate restTemplate = new RestTemplate();
        //查询SAPI, 校验sportmatch 人数是否已满
        Long matchid = request.getMatchId();
        StringBuilder url = new StringBuilder(config.getFindMatchByIdUrl());
        SportMatch match = null;
        try {
            match = restTemplate.getForObject(new URI(url.append(matchid).toString()), SportMatch.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //加入比赛前的比赛信息（格式）
        String findmatchinfobyidurl = config.getFindMatchinfoByIdUrl();
        findmatchinfobyidurl = findmatchinfobyidurl + matchid;
        LOG.info("根据matchid获取含创建者和成员的比赛的URL为: {}", findmatchinfobyidurl);
        MyMatchBean mymatchbean = restTemplate.getForObject(findmatchinfobyidurl, MyMatchBean.class);
        //创建者信息
        String getCreatedUserInfoUrl = config.getCreatedUserInfoUrl();
        getCreatedUserInfoUrl = getCreatedUserInfoUrl + matchid;
        LOG.info("根据matchid获取比赛创建者信息的URL为: {}", getCreatedUserInfoUrl);
        CreatedUserBean createduserbean = restTemplate.getForObject(getCreatedUserInfoUrl, CreatedUserBean.class);
        //判断比赛是否已满
        if (Integer.parseInt(match.getMatchType()) * 2 == match.getJoinedAmount()) {
            apiDefaultResponse.setResponseBody(mymatchbean);
            apiDefaultResponse.setResponseCode(ConstantFields.getpFullpersonCode());
            apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
            return apiDefaultResponse;
        } else {
            //判断是否已加入该比赛
            String isjoinedmatchesurl = config.getIsJoinedMatchesUrl();
            LOG.info("根据request获取用户是否已经加入比赛的URL为: {}", isjoinedmatchesurl);
            Boolean isjoined = restTemplate.postForObject(isjoinedmatchesurl, request, boolean.class);
            if (isjoined.equals(false)) {
                apiDefaultResponse.setResponseBody(mymatchbean);
                apiDefaultResponse.setResponseCode(ConstantFields.getpHavejoinedCode());
                apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
                return apiDefaultResponse;
            }
            //创建者不可申请加入该比赛
            BigInteger createid = createduserbean.getCreatedId();
            if (createid.longValue() == request.getUserId()) {
                apiDefaultResponse.setResponseBody(mymatchbean);
                apiDefaultResponse.setResponseCode(ConstantFields.getpDefaultjoinedCode());
                apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
                return apiDefaultResponse;
            }
            String url01 = config.getJoinMatchUrl();
            //保存数据
            try {
                ResponseEntity<?> responseEntity = restTemplate.postForEntity(url01, request, UserApply.class);
                //加入比赛后的比赛信息（格式）
                String findmatchsinfobyidurl = config.getFindMatchinfoByIdUrl();
                findmatchsinfobyidurl = findmatchsinfobyidurl + matchid;
                LOG.info("根据matchid获取含创建者和成员的比赛的URL为: {}", findmatchsinfobyidurl);
                MyMatchBean mymatchbeans = restTemplate.getForObject(findmatchsinfobyidurl, MyMatchBean.class);
                if (null == responseEntity.getBody()) {
                    apiDefaultResponse.setResponseBody(mymatchbeans);
                    apiDefaultResponse.setResponseCode(ConstantFields.getSuccessCode());
                    apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
                } else {
                    apiDefaultResponse.setResponseBody(mymatchbeans);
                    apiDefaultResponse.setResponseCode(ConstantFields.getSuccessCode());
                    apiDefaultResponse.setMessage(ConstantFields.getSuccessMsg());
                }
            } catch (Exception e) {
                apiDefaultResponse.setResponseCode(ConstantFields.getSapiError509Code());
                apiDefaultResponse.setMessage(e.getMessage());
                apiDefaultResponse.setResponseBody(e.getCause());
                LOG.error(e.getMessage().toString());
            }
            LOG.info("==== 结束执行  Match Papi service joinMatch ...");
            return apiDefaultResponse;
        }
    }


    /**
     * 获取我的发布(未结束)
     *
     * @param creatorinfo
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getMyMatches(Long creatorinfo, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getMatchesByCreatorIdUrl();
            url = url + creatorinfo + "/" + pageInfo;
            LOG.info("获取我发起的比赛信息(未结束)的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 获取我发起的比赛信息(已结束)
     *
     * @param creatorinfo
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getMyOldMatches(Long creatorinfo, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getOldMatchesByCreatorIdUrl();
            url = url + creatorinfo + "/" + pageInfo;
            LOG.info("获取我发起的比赛信息(已结束)的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}",e.getMessage());
        }
        return response;
    }

    /**
     * 通过matchId获取比赛具体信息
     *
     * @param matchId
     * @return
     */
    @Override
    public ApiDefaultResponse getMatch(Long matchId) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder url = new StringBuilder(config.getFindMatchByIdUrl());
        SportMatch match = null;
        try {
            match = restTemplate.getForObject(new URI(url.append(matchId).toString()), SportMatch.class);
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), match);
    }

    /**
     * 根据经纬度获取附近比赛(已不用)
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public ApiDefaultResponse getNearbyMatches(Double longitude, Double latitude) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        String url = config.getNearbyMatchesUrl();
        url = url + longitude + "/" + latitude;
        LOG.info("根据经纬度获取附近比赛的URL为: {}", url);
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
        response.setResponseBody(responseEntity.getBody());
        response.setResponseCode(ConstantFields.getSuccessCode());
        response.setMessage(ConstantFields.getSuccessMsg());
        return response;
    }

    /**
     * 根据经纬度获取附近比赛（含分页参数）
     *
     * @param longitude
     * @param latitude
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getNearbyMatches(Double longitude, Double latitude, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getNearbyMatchesUrl();
            url = url + longitude + "/" + latitude + "/" + pageInfo;
            LOG.info("根据经纬度获取附近比赛的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (responseEntity.getBody() == null) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
        	LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
        }
        return response;
    }

    /**
     * 根据经纬度获取附近比赛(由场地类型筛选)
     *
     * @param longitude
     * @param latitude
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getNearbyMatches(Double longitude, Double latitude, String fieldId, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = 	config.getNearMatchesbyFieldTypeUrl();
            url = url + longitude + "/" + latitude + "/" + fieldId + "/" + pageInfo;
            LOG.info("由场地类型筛选根据经纬度获取附近比赛的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (responseEntity.getBody() == null) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 获取申请加入的比赛的信息(未结束)
     *
     * @param userinfo
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getApplyMatches(Long userinfo, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getApplyMatchesUrl();
            url = url + userinfo + "/" + pageInfo;
            LOG.info("获取申请加入的比赛的信息的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 获取申请加入的比赛的信息(已结束)
     *
     * @param userinfo
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getOldApplyMatches(Long userinfo, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getOldApplyMatchesUrl();
            url = url + userinfo + "/" + pageInfo;
            LOG.info("获取申请加入的比赛的信息的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 首页获取比赛信息（通过比赛场地）
     *
     * @param fieldId
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getMatchesByFieldId(Long fieldId, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getFindMatchesByFieldIdeUrl();
            url = url + fieldId + "/" + pageInfo;
            LOG.info("获取申请加入的比赛的信息的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 首页获取比赛信息（通过比赛场地）(某天的)
     *
     * @param fieldId
     * @param date
     * @param pageInfo
     * @return
     */
    @Override
    public ApiDefaultResponse getMatchesByFieldId(Long fieldId, Date date, String pageInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ApiDefaultResponse response = new ApiDefaultResponse();
        try {
            String url = config.getFindMatchesByFieldIdeUrl();
            url = url + fieldId + "/" + date + "/" + pageInfo;
            LOG.info("获取某一天申请加入的比赛的信息的URL为: {}", url);
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
            if (null == responseEntity.getBody()) {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(responseEntity.getBody());
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;
    }

    /**
     * 获取历史比赛信息（包括发起的和申请加入的）
     *
     * @param userinfo
     * @param pageInfo
     * @return
     */

    public ApiDefaultResponse getEndMatches(Long userinfo, String pageInfo) {
        LOG.info("==== 开始执行  Match Papi service joinMatch ...");
        ApiDefaultResponse response = new ApiDefaultResponse();
        EndMatchResponse endmatchresponse = new EndMatchResponse();
        RestTemplate restTemplate = new RestTemplate();
        //申请加入的历史比赛
        try {
            String url = config.getOldApplyMatchesUrl();
            url = url + userinfo + "/" + pageInfo;
            LOG.info("获取申请加入的历史比赛的信息的URL为: {}", url);
            List<MyMatchBean> MyOldMatches = restTemplate.getForObject(url, List.class);
            endmatchresponse.setMyOldMatches(MyOldMatches);
            if (null == endmatchresponse) {
                response.setResponseBody(endmatchresponse);
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(endmatchresponse);
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        //创建的历史比赛
        try {
            String url = config.getOldMatchesByCreatorIdUrl();
            url = url + userinfo + "/" + pageInfo;
            LOG.info("获取创建比赛的历史信息的URL为: {}", url);
            List<MyMatchBean> OldMatchesByCreatorId = restTemplate.getForObject(url, List.class);
            endmatchresponse.setOldMatchesByCreatorId(OldMatchesByCreatorId);
            if (null == endmatchresponse) {
                response.setResponseBody(endmatchresponse);
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            } else {
                response.setResponseBody(endmatchresponse);
                response.setResponseCode(ConstantFields.getSuccessCode());
                response.setMessage(ConstantFields.getSuccessMsg());
            }
        } catch (Exception e) {
            response.setResponseCode(ConstantFields.getSapiError509Code());
            response.setMessage("");
            response.setResponseBody(e.getCause().getLocalizedMessage());
            LOG.error("调用sapi 获取数据出错:{}", e.getMessage());
        }
        return response;

    }



}
