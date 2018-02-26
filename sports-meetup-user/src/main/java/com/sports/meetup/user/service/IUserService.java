package com.sports.meetup.user.service;

import org.springframework.stereotype.Service;

import com.sports.meetup.user.domain.User;
import com.sports.meetup.user.util.ResponseUtil;

@Service
public interface IUserService {

	String checkUser(String phoneNumber);

	ResponseUtil addUser(User user);

	String checkPhoneNumber(User user);

	String checkUser(User user);

	String checkPhoneNumber(String phoneNumber);

	ResponseUtil updatePassword(User user);

	User updateUser(User user);

}
