package com.sports.meetup.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.user.domain.HealthCheckBean;
import com.sports.meetup.user.repository.HealthCheckBeanRepository;
import com.sports.meetup.user.service.HealthCheckService;
import com.sports.meetup.user.util.ResponseUtil;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

	@Autowired
	private HealthCheckBeanRepository healthCheckRepository;
	
	@Override
	public ResponseUtil healthCheck(HealthCheckBean healthCheckBean) {
		ResponseUtil response = new ResponseUtil();
		
		HealthCheckBean healthBean = healthCheckRepository.save(healthCheckBean);
		if(null != healthBean) {
			response.setMessage("保存成功！");
			response.setResponseCode("000");
			response.setResponseBody(healthBean);
		}else {
			response.setMessage("保存失败！");
			response.setResponseBody(null);
		}
		
		return response; 
	}

	@Override
	public ResponseUtil healthCheck(Integer id) {
		ResponseUtil response = new ResponseUtil();
		HealthCheckBean healthBean = this.healthCheckRepository.findOne(id);
		if(null != healthBean) {
			response.setMessage("测试成功！");
			response.setResponseCode("000");
			response.setResponseBody(healthBean);
		}else {
			response.setMessage("测试失败！");
			response.setResponseBody(null);
		}
		return response;
	}

}
