package com.sports.meetup.field.papi.service.impl;

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
import org.springframework.web.client.RestTemplate;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.field.papi.config.WebConfig;
import com.sports.meetup.field.papi.domain.SportField;
import com.sports.meetup.field.papi.service.ISportFieldService;

@Service
public class SportFieldServiceImpl implements ISportFieldService{

	private static final Logger LOG = LoggerFactory.getLogger(SportFieldServiceImpl.class);
	
	@Autowired
	private WebConfig config;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 添加场地信息
	 * @param sportField
	 * @return
	 */
	@Override
	public ApiDefaultResponse addSportField(SportField sportField) {
		HttpHeaders headers = new HttpHeaders();
		ApiDefaultResponse apiDefaultResponse = new ApiDefaultResponse();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<SportField> requestEntity = new HttpEntity<SportField>(sportField, headers);
		String url = config.getAddSportFieldUrl();
		
		ResponseEntity<SportField> responseEntity = null;
		
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SportField.class);
		}catch (Exception e) {
			apiDefaultResponse.setResponseCode(ConstantFields.getSapiError509Code());
			apiDefaultResponse.setMessage(e.getMessage());
			return apiDefaultResponse;
		}
		apiDefaultResponse = new ApiDefaultResponse(ConstantFields.getSuccessCode(),
				ConstantFields.getSuccessMsg(), responseEntity.getBody());
		
		return apiDefaultResponse;
	}
	//获取附近的场馆
	@Override
	public ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude,String pageInfo) {
//		ApiDefaultResponse response = new ApiDefaultResponse();
//		GPSLocation gps = new GPSLocation();
//		gps.setLongitude(longitude);
//		gps.setLatitude(latitude);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		HttpEntity<GPSLocation> requestEntity = new HttpEntity<GPSLocation>(gps, headers);
//		String url = config.getFindNearbySportFieldsUrl();
//		LOG.info("callSAPI 获取({}, {})点附近的场地", longitude, latitude);
//		LOG.info("URL is :{}", url);
//		ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, List.class);
//		if(HttpStatus.OK.equals(responseEntity.getStatusCode())) {
//			response = new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), responseEntity.getBody());
//			return response;
//		}else {
//			response.setResponseCode(ConstantFields.getSapiError509Code());
//			response.setMessage(ConstantFields.getSapiError509Msg());
//			return response;
//		}
		RestTemplate restTemplate = new RestTemplate();
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			String url = config.getFindNearbySportFieldsUrl();
			url = url +"/"+ longitude + "/" + latitude+"/" + pageInfo;
			LOG.info("根据经纬度获取附近场地的URL为: {}", url);
			ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
			if(null == responseEntity.getBody()) {
				response.setResponseBody(responseEntity.getBody());
				response.setResponseCode(ConstantFields.getSuccessCode());
				response.setMessage(ConstantFields.getSuccessMsg());
			}else {
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
	 * 获取附件的场地(由比赛类型判断)
	 * @param longitude
	 * @param latitude
	 * @param fieldType
	 * @param pageInfo
	 * @return
	 */
	@Override
	public ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude,String fieldType,String pageInfo) {
		RestTemplate restTemplate = new RestTemplate();
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			String url = config.getFindNearFieldsByMatchtypeUrl();
			url = url +"/"+ longitude + "/" + latitude+"/"+ fieldType + "/" + pageInfo;
			LOG.info("根据经纬度由比赛类型获取附近场地的URL为: {}", url);
			ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
			if(null == responseEntity.getBody()) {
				response.setResponseBody(responseEntity.getBody());
				response.setResponseCode(ConstantFields.getSuccessCode());
				response.setMessage(ConstantFields.getSuccessMsg());
			}else {
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
	 * 获取附件的场地（有比赛）
	 * @param longitude
	 * @param latitude
	 * @param pageInfo
	 * @return
	 */
	@Override
	public ApiDefaultResponse findNearFieldsHaveMatches(Double longitude, Double latitude,String pageInfo) {
		RestTemplate restTemplate = new RestTemplate();
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			String url = config.getFindNearFieldsHaveMatches();
			url = url +"/"+ longitude + "/" + latitude+"/" + pageInfo;
			LOG.info("根据经纬度获取附近场地的URL为: {}", url);
			ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
			if(null == responseEntity.getBody()) {
				response.setResponseBody(responseEntity.getBody());
				response.setResponseCode(ConstantFields.getSuccessCode());
				response.setMessage(ConstantFields.getSuccessMsg());
			}else {
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
	 * 通过fieldId获取场地信息
	 */
	@Override
	public SportField getSportFieldById(Long fieldId) {
		SportField sportField = new SportField();
		String url = config.getFindSportFieldByIdUrl()+fieldId;
		LOG.info("通过fieldId获取场地信息的URL为：{}", url);
		try {
			sportField = restTemplate.getForObject(url, SportField.class);
		}catch (Exception e) {
			LOG.error("通过Id获取场地失败：{}", e.getMessage());
		}
		
		return sportField;
	}


}
