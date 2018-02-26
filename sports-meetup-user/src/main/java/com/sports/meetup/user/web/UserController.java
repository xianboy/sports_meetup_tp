package com.sports.meetup.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.user.domain.User;
import com.sports.meetup.user.service.IUserService;
import com.sports.meetup.user.util.ResponseUtil;

@RestController
@RequestMapping({"/users", "/users/v1.0"})
public class UserController {
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ResponseUtil responseUtil;
	
	
	/**
	 * verify the phoneNumber when a user is trying to register with the phoneNumber
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/checkUser/{phoneNumber}")
	public String checkUser(@PathVariable String phoneNumber){
		
		return this.userService.checkUser(phoneNumber);
	}
	
	/**
	 * add the user into db when the user use a variable phoneNumber to register
	 * @param user
	 * @return
	 */
	//add User
	@PostMapping("/addUser")
	public ResponseUtil addUser(@RequestBody @Validated User user, BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			responseUtil.setMessage(bindResult.getAllErrors().get(0).getDefaultMessage());
			responseUtil.setResponseCode("BAD_REQUEST_400");
			return responseUtil;
		}
		return this.userService.addUser(user);
//		return responseUtil;
	}
	
	@PutMapping(value="/updatePassword")
	public ResponseUtil updatePassword(@RequestBody @Validated User user, BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			responseUtil.setMessage(bindResult.getAllErrors().get(0).getDefaultMessage());
			responseUtil.setResponseCode("BAD_REQUEST_400");
			return responseUtil;
		}
		return this.userService.updatePassword(user);
	}
	
	//修个个人信息
	@PutMapping(value="/user/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		User u = this.userService.updateUser(user);
		return u;
	}
}
