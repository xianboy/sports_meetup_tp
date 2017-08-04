package com.sports.meetup.field.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.common.domain.SportField;
import com.sports.meetup.field.service.ISportFieldService;

@Service
public class SportFieldServiceImpl implements ISportFieldService{

	private static final Logger LOG = LoggerFactory.getLogger(SportFieldServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ApiDefaultResponse addSportField(SportField sportField) {
		HttpHeaders headers = new HttpHeaders();
		ApiDefaultResponse apiDefaultResponse = new ApiDefaultResponse();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<SportField> requestEntity = new HttpEntity<SportField>(sportField, headers);
		String url = "http://localhost:8085/sports-meetup-sapi/sportfields/saveSportField";
		
//		ResponseEntity<SportField> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SportField.class);
		URI ul = null;
		try {
			ul = new URI(url);
		} catch (URISyntaxException e) {
			LOG.error("URL 有误.");
			apiDefaultResponse.setMessage(ConstantFields.getUrlError508Msg());
			apiDefaultResponse.setResponseCode(ConstantFields.getUrlError508Code());
			return apiDefaultResponse;
		}
		SportField sField = null;
		try {
		sField = restTemplate.postForObject(ul, requestEntity, SportField.class);
		}catch (Exception e) {
			apiDefaultResponse.setResponseCode(ConstantFields.getSapiError509Code());
			apiDefaultResponse.setMessage(e.getMessage());
			return apiDefaultResponse;
		}
		apiDefaultResponse = new ApiDefaultResponse(ConstantFields.getSuccessResponseCode(),
				ConstantFields.getSuccessResponseMsg(), sField);
		
		return apiDefaultResponse;
	}

	//获取附近的场馆
	@Override
	public ApiDefaultResponse getNearbySportFields(Double longitude, Double latitude) {
		/*HttpHeaders headers = new HttpHeaders();
		ApiDefaultResponse apiDefaultResponse = new ApiDefaultResponse();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<SportField> requestEntity = new HttpEntity<SportField>(sportField, headers);*/
		String url = "http://localhost:8085/sports-meetup-sapi/sportfields/findNearbySportFields";
		
		MultiValueMap<String, Double> params = new LinkedMultiValueMap<String, Double>();
		ResponseEntity<SportField[]> responseEntity = restTemplate.getForEntity(url, SportField[].class, params);
		
		return null;
	}
	
}
