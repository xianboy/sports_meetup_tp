package com.sports.meetup.user.sapi.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.user.sapi.domain.Comment;
import com.sports.meetup.user.sapi.domain.UpdateUserRequest;
import com.sports.meetup.user.sapi.domain.User;
import com.sports.meetup.user.sapi.domain.UserInfo;
import com.sports.meetup.user.sapi.repository.CommentRepository;
import com.sports.meetup.user.sapi.repository.UserRepository;
import com.sports.meetup.user.sapi.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 通过phoneNumber获取用户信息
	 * @param phoneNumber
	 * @return
	 */
	@Override
	public User getUser(String phoneNumber) {
		User user = this.userRepository.findByPhoneNumber(phoneNumber);
		return user;
	}

	/**
	 * 意见反馈
	 * @param comment
	 * @return
	 */
	@Override
	public Comment addComment(Comment comment) {
		LOG.info("Sapi UserService 添加帮助留言：{}", comment.toString());
		comment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		Comment cmt = this.commentRepository.save(comment);
		LOG.info("Sapi UserService 成功添加帮助留言：{} ", cmt.toString());
		return cmt;
	}

	/**
	 * 修改个人信息
	 * @param updateUser
	 * @param id
	 * @return
	 */
	@Override
	public User updateUser(UpdateUserRequest updateUser, Long id) {
		User user = this.userRepository.findOne(id);
		if(StringUtils.isNotBlank(updateUser.getName())){
			user.setName(updateUser.getName());
		}
		if(StringUtils.isNotBlank(updateUser.getIcon())){
			user.setIcon(updateUser.getIcon());
		}
		if(StringUtils.isNotBlank(updateUser.getCity())){
			user.setCity(updateUser.getCity());
		}
		if(StringUtils.isNotBlank(updateUser.getAddress())){
			user.setAddress(updateUser.getAddress());
		}
		if(StringUtils.isNotBlank(updateUser.getGender())) {
			user.setGender(updateUser.getGender());
		}
		if(StringUtils.isNotBlank(updateUser.getHobbies())) {
			user.setHobbies(updateUser.getHobbies());
		}
		if(StringUtils.isNotBlank(updateUser.getWeekday())) {
			user.setWeekday(updateUser.getWeekday());
		}
		if(StringUtils.isNotBlank(updateUser.getWeekend())) {
			user.setWeekend(updateUser.getWeekend());
		}
		user.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
		User u = this.userRepository.save(user);
		u.setPassword("");
		u.setVerificationCode("");
		return u;
	}

}
