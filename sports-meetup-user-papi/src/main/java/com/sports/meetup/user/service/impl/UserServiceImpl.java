package com.sports.meetup.user.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sports.meetup.user.config.SpringConfig;
import com.sports.meetup.user.domain.User;
import com.sports.meetup.user.service.IUserService;
import com.sports.meetup.user.utils.CustomResponse;
import com.sports.meetup.user.utils.UserUtil;

@Service
@Component
public class UserServiceImpl implements IUserService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SpringConfig config;
	
	@Autowired
	private User dbUser;
	
	@Autowired
	private CustomResponse customResponse;
	
	
	/**
	 * login: user data passes from FE:
	 * 1.get user detail from sapi in userDB by user;
	 * 2.check whether the user data from FE eaquals user detail from db;
	 * 3.give response to FE;
	 * @param user
	 * @return
	 */
	public ResponseEntity<?> login(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		String url = config.getUrl()+"/"+user.getPhoneNumber();
		//1.call sapi to get userByPhoneNumber from 
		ResponseEntity<User> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class);
		
		ResponseEntity<CustomResponse> response = null;
		if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			dbUser = responseEntity.getBody();
		}else {
			customResponse.setResponseCode("LE003");
			customResponse.setMessage("Intenel Server Error.");
			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		}
		if(dbUser==null) {
			customResponse.setResponseCode("LE001");
			customResponse.setMessage("用户 "+user.getPhoneNumber() +" 不存在.");
			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		}
		if("Y".equals(UserUtil.checkLoginUser(user, dbUser))) {
			response = new ResponseEntity<CustomResponse>(customResponse.responseHandler(dbUser), HttpStatus.OK);
		}
		return response;
	}

}
