package com.sports.meetup.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.user.domain.HealthCheckBean;
import com.sports.meetup.user.service.HealthCheckService;
import com.sports.meetup.user.util.ResponseUtil;

@RestController
public class HealthCheckController {
	
	@Autowired
	private HealthCheckService healthCheckService;
	
	/**
	 * 测试app处理请求功能
	 * @return
	 */
	@GetMapping(value="/healthCheck")
	public String healthCheck() {
		return "Hi, I'm  working fine!";
	}
	
	/**
	 * 测试App访问数据库功能保存功能
	 * @param testUser
	 * @return
	 */
	@PostMapping(value="/healthCheck")
	public ResponseUtil healthCheck(@RequestBody HealthCheckBean healthCheckBean ) {
		
		return healthCheckService.healthCheck(healthCheckBean);
	}
	
	@GetMapping(value="/healthCheck/{id}")
	public ResponseUtil healthCheck(@PathVariable Integer id) {
		return this.healthCheckService.healthCheck(id);
	}
}
