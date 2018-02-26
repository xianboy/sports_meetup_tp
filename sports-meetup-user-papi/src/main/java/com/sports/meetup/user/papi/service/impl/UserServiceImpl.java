package com.sports.meetup.user.papi.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.common.exception.UserException;
import com.sports.meetup.user.papi.config.SpringConfig;
import com.sports.meetup.user.papi.domain.BeanTest;
import com.sports.meetup.user.papi.domain.Comment;
import com.sports.meetup.user.papi.domain.LoginResponse;
import com.sports.meetup.user.papi.domain.User;
import com.sports.meetup.user.papi.service.IUserService;
import com.sports.meetup.user.papi.utils.UserUtil;

@Service
@Component
public class UserServiceImpl implements IUserService{

	private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	private final static String CLASS = "UserServiceImpl";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SpringConfig config;
	
	@Autowired
	private User dbUser;
	
	
	/**
	 * login: user data passes from FE:
	 * 1.get user detail from sapi in userDB by user;
	 * 2.check whether the user data from FE eaquals user detail from db;
	 * 3.give response to FE;
	 * @param user
	 * @return
	 * @throws LoginFaildException 
	 * @throws Exception 
	 * @throws UserNotExistException 
	 */
	public ResponseEntity<?> login(User user) throws UserException {
		if(LOG.isDebugEnabled()) {
			LOG.debug("======== enter class {} to call login method ========", UserServiceImpl.CLASS);
		}
		ResponseEntity<ApiDefaultResponse> response = null;
		LoginResponse loginResponse = new LoginResponse();
// 1. get user from sapi by phoneNumber
		dbUser = findUserByPhoneNumber(user.getPhoneNumber());
		if(dbUser==null) {
			LOG.error("======== 登录时, 用户不存在 ========");
			throw new UserException(ConstantFields.getUserError501Code(),ConstantFields.getUserError501Msg());
		}
		if("Y".equals(UserUtil.checkLoginUser(user, dbUser))) {
			response = new ResponseEntity<ApiDefaultResponse>(
					new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), loginResponse), HttpStatus.OK);
		}else {
			LOG.error("======== 登录时, 密码错误 ========");
			throw new UserException(ConstantFields.getUserError502Code(), ConstantFields.getUserError502Msg());
		}


		//fengzhaung

//		if(StringUtils.isNotBlank(user.getUserId()){
//			loginResponse.setUserId(loginResponse.getUserId());
//		}
		loginResponse.setUserId(dbUser.getUserId());
		if(StringUtils.isNotBlank(dbUser.getCity())){
			loginResponse.setCity(dbUser.getCity());
		}
		if(StringUtils.isNotBlank(dbUser.getName())){
			loginResponse.setName(dbUser.getName());
		}
		if(StringUtils.isNotBlank(dbUser.getPhoneNumber())){
			loginResponse.setPhoneNumber(dbUser.getPhoneNumber());
		}
		if(StringUtils.isNotBlank(dbUser.getIcon())){
			loginResponse.setIcon(dbUser.getIcon());
		}
		if(StringUtils.isNotBlank(dbUser.getAddress())){
			loginResponse.setAddress(dbUser.getAddress());
		}
		if(StringUtils.isNotBlank(dbUser.getGender())) {
			loginResponse.setGender(dbUser.getGender());
		}
		if(StringUtils.isNotBlank(dbUser.getHobbies())) {
			loginResponse.setHobbies(dbUser.getHobbies());
		}
		if(StringUtils.isNotBlank(dbUser.getWeekday())) {
			loginResponse.setWeekday(dbUser.getWeekday());
		}
		if(StringUtils.isNotBlank(dbUser.getWeekend())) {
			loginResponse.setWeekend(dbUser.getWeekend());
		}
		loginResponse.setCreatedTime(dbUser.getCreatedTime());
		loginResponse.setUpdatedTime(dbUser.getUpdatedTime());

		if(LOG.isDebugEnabled()) {
			LOG.debug("======== out method login ========");
		}
		return response;
	}

	public User findUserByPhoneNumber(String phoneNumber) throws RuntimeException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> requestEntity = new HttpEntity<>(phoneNumber, headers);
		String url = config.getFindUserByPhoneNumberUrl();
		//1.call sapi to get userByPhoneNumber from 
		ResponseEntity<User> responseEntity = null;
		try {
			responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class, phoneNumber);

			if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				dbUser = responseEntity.getBody();
			}else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			LOG.error("调用SAPI异常"+e.getMessage());
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
		
		return dbUser;
	}
	

	/**
	 * 1. 校验用户
	 * 1.1 用户是否存在 
	 * 1.2 用户验证码是否正确
	 * 1.3 新密码是否与旧密码重复
	 */
	public ResponseEntity<?> updatePassword(User user) {
//		this.restTemplate.exchange(, method, requestEntity, responseType);
		return null;
	}

	
	//我的帮助
	@Override
	public ResponseEntity<?> userComment(Comment comment) {
//		URI url = new URI(config.getAddHelpMsgUrl());
		Comment cmnt = this.restTemplate.postForObject(config.getAddHelpMsgUrl(), comment, Comment.class);
		ApiDefaultResponse response = new ApiDefaultResponse();
		response.setResponseCode(ConstantFields.getSuccessCode());
		response.setMessage(ConstantFields.getSuccessMsg());
		response.setResponseBody(cmnt);
//		ResponseEntity<Comment> response = new ResponseEntity<Comment>(cmnt, HttpStatus.OK);
//		return response;
		return new ResponseEntity<ApiDefaultResponse>(response, HttpStatus.OK);

	}

	//修改个人信息
	@Override
	public ResponseEntity<?> updateUser(User user) {
		long id = user.getUserId();
//		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		String url = config.getUpdateUserUrl()+"/"+id;
//		User usr = restTemplate.patchForObject("http://localhost:8082/sports-meetup-sapi/users/user/2", user, User.class, id);
		User usr = null;
		ApiDefaultResponse response = new ApiDefaultResponse();
		try {
			usr = restTemplate.postForObject(url, user, User.class);
		}catch (Exception e) {
			LOG.error("修改用户信息保存失败：{}", e.getMessage());
			response.setResponseCode(ConstantFields.getSapiError509Code());
			response.setMessage("修改用户信息保存失败");
		}
		response.setResponseCode(ConstantFields.getSuccessCode());
		response.setMessage(ConstantFields.getSuccessMsg());
		response.setResponseBody(usr);
//		User usr = this.restTemplate.patchForObject(url, user, User.class);
		return new ResponseEntity<ApiDefaultResponse>(response, HttpStatus.OK);
	}

	//测试、(查询)
	public ResponseEntity<?> test(String name) {
		String url = config.getTestUserUrl();
		url = url+"/"+name;
		ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, List.class);
		return responseEntity;
	}
	//测试、(更改)
	@Override
	public ResponseEntity<?> updateTest(BeanTest beanTest) {
		String url = config.getUpdateTestUrl();
		BeanTest bean = this.restTemplate.postForObject(url, beanTest, BeanTest.class);
		ApiDefaultResponse response = new ApiDefaultResponse();
		response.setResponseCode(ConstantFields.getSuccessCode());
		response.setMessage(ConstantFields.getSuccessMsg());
		response.setResponseBody(bean);
//		User usr = this.restTemplate.patchForObject(url, user, User.class);
		return new ResponseEntity<ApiDefaultResponse>(response, HttpStatus.OK);
	}
		
}
