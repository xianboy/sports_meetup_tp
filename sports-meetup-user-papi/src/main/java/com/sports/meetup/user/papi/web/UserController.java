package com.sports.meetup.user.papi.web;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.user.papi.domain.BeanTest;
import com.sports.meetup.user.papi.domain.Comment;
import com.sports.meetup.user.papi.domain.User;
import com.sports.meetup.user.papi.service.IUserService;

@RestController
@RequestMapping(value={"/users", "/v1.0/users"})
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	private static final String CLASS = "UserController";
	
	@Autowired
	private IUserService userService;
	
	@PostMapping(value="/login")
	public ResponseEntity<?> login(@Validated @RequestBody User user, BindingResult bindResult) throws Exception{
		
		if(LOG.isDebugEnabled()) {
			LOG.debug("========  Enter {}, method login() ========" + UserController.CLASS);
		}
		if(bindResult.hasErrors()) {
			String errorMsg = bindResult.getAllErrors().get(0).getDefaultMessage();
			LOG.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		return userService.login(user);
	}
	
	@PutMapping(value="/updatePassword")
	public ResponseEntity<?> updatePassword(@Validated @RequestBody User user, BindingResult bindResult) throws BadRequestException{
		if(bindResult.hasErrors()) {
			String errorMsg = bindResult.getAllErrors().get(0).getDefaultMessage();
			LOG.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		return userService.updatePassword(user);
	}
	
	//我的帮助留言
	@PostMapping(value="/user/comments")
	public ResponseEntity<?> userComment(@RequestBody Comment comment){
		LOG.info("Papi UserController 用户帮助留言");
		return userService.userComment(comment);
	}
	
	//修改个人信息
	@PostMapping(value="/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user) {

		return userService.updateUser(user);
	}


	//测试(查询)
	@GetMapping (value="/test/{name}")
	public ResponseEntity<?> test(@PathVariable String name) {
		return userService.test(name);
	}
	//测试（更改）
	@PostMapping(value="/updateTest")
	public ResponseEntity<?> updateTest(@RequestBody BeanTest beanTest) {
		return userService.updateTest(beanTest);
	}
	
}
