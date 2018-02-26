package com.sports.meetup.user.service;

import org.springframework.stereotype.Service;

import com.sports.meetup.user.domain.HealthCheckBean;
import com.sports.meetup.user.util.ResponseUtil;

@Service
public interface HealthCheckService {

	ResponseUtil healthCheck(HealthCheckBean healthCheckBean);

	ResponseUtil healthCheck(Integer id);
	
}
