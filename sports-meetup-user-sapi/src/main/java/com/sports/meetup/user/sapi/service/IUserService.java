package com.sports.meetup.user.sapi.service;


import org.springframework.stereotype.Service;

import com.sports.meetup.user.sapi.domain.Comment;
import com.sports.meetup.user.sapi.domain.UpdateUserRequest;
import com.sports.meetup.user.sapi.domain.User;

@Service
public interface IUserService {

	User getUser(String phoneNumber);
	Comment addComment(Comment comment);
	User updateUser(UpdateUserRequest updateUser, Long id);

}
