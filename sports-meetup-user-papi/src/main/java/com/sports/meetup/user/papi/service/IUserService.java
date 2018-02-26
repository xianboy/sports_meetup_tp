package com.sports.meetup.user.papi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sports.common.exception.UserException;
import com.sports.meetup.user.papi.domain.BeanTest;
import com.sports.meetup.user.papi.domain.Comment;
import com.sports.meetup.user.papi.domain.User;

@Service
public interface IUserService {

	ResponseEntity<?> login(User user) throws RuntimeException, UserException;

	ResponseEntity<?> updatePassword(User user);

	ResponseEntity<?> userComment(Comment comment);

	ResponseEntity<?> updateUser(User user);

	ResponseEntity<?> test(String name);

	ResponseEntity<?> updateTest(BeanTest beanTest);

}
