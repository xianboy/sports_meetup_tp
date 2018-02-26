package com.sports.meetup.user.service;

import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.sports.meetup.user.util.ResponseUtil;

@Service
public interface ISendSmsService {

	ResponseUtil getVerificationCode(String phoneNumber);

	ResponseUtil getVerificationCode(String phoneNumber, int flag) throws ClientException;
	
}
