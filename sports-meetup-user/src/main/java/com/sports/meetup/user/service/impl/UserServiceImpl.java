package com.sports.meetup.user.service.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sports.meetup.user.domain.CheckCode;
import com.sports.meetup.user.domain.User;
import com.sports.meetup.user.domain.UserInfo;
import com.sports.meetup.user.repository.CheckCodeRepository;
import com.sports.meetup.user.repository.UserJpaRepository;
import com.sports.meetup.user.service.IUserService;
import com.sports.meetup.user.util.EncryptionUtil;
import com.sports.meetup.user.util.ResponseUtil;

@Service
public class UserServiceImpl implements IUserService {

	private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@Autowired
	private CheckCodeRepository checkCodeRepository;

	@Autowired
	private CheckCode checkCode;
	
	//valid phoneNumber
	/**
	 * Y means the number is used.
	 * N means the number is not used.
	 */
	@Override
	public String checkPhoneNumber(String phoneNumber) {
		String isUsed = "N";
		try {
//			 number = this.phoneNumberRepositery.findPhoneNumberByNumber(phoneNumber);
			 List<User> users = this.userJpaRepository.findUserByPhoneNumber(phoneNumber);
			 if(users.size()>0) {
				 isUsed = "Y";
			 }else {
				 isUsed = "N";
			 }
		}catch (Exception e) {
			LOG.debug("Some issue occurred while running method checkUser()");
		}
		return isUsed;
	}
	
	//用户注册
	@Override
	@Transactional
	public ResponseUtil addUser(User user) {
		String isUsed = checkPhoneNumber(user.getPhoneNumber());
		 if(isUsed.equals("Y")) {
			 responseUtil.setResponseCode("USER_ERROR_503");
			 responseUtil.setMessage("用户已存在.");
			 return responseUtil;
		 }
//		 2 校验用户验证码
		 String passed = checkVerificationCode(user);
		 if(passed.equals("N")) {
			 responseUtil.setResponseCode("USER_ERROR_505");
			 responseUtil.setMessage("验证码已失效,请重新获取.");
			 return responseUtil;
		 }
		try {
			if("N".equals(isUsed)&&"Y".equals(passed)) {
				user.setPassword(EncryptionUtil.getEncryptString(user.getPassword()));
				user.setVerificationCode(EncryptionUtil.getEncryptString(user.getVerificationCode()));
				user.setCreatedTime(new Date(System.currentTimeMillis()));
				user.setName(user.getPhoneNumber());
				this.userJpaRepository.save(user);
                //返回user信息
				String phoneNumber = user.getPhoneNumber();
				Object obj = this.userJpaRepository.findUserInfoByPhoneNumber(phoneNumber);
				UserInfo userinfo = customConvertor(obj);
				responseUtil.setResponseBody(userinfo);
				responseUtil.setResponseCode("000");
				responseUtil.setMessage("注册成功！");
			}
		}catch (Exception e) {
			LOG.error("保存注册用户出错  : {}",  e.getCause());
		}
		return responseUtil;
	}

	/**
	 * 注册信息的转换
	 * @param obs
	 * @return
	 */
	public UserInfo customConvertor(Object obs) {
		UserInfo nb = new UserInfo();
		Object[] objs = (Object[]) obs;
		if (obs.toString().length() > 0) {
			nb.setUserId((BigInteger) objs[0]);
			nb.setName((String) objs[1]);
			nb.setPhoneNumber((String) objs[2]);
			nb.setAddress((String) objs[3]);
			nb.setCreatedTime((java.util.Date) objs[4]);
			nb.setGender((String) objs[5]);
			nb.setHobbies((String) objs[6]);
			nb.setIcon((String) objs[7]);
			nb.setUpdatedTime((java.util.Date) objs[8]);
			nb.setWeekday((String) objs[9]);
			nb.setWeekend((String) objs[10]);
			nb.setCity((String) objs[11]);
		}
		return nb;
	}

	/*
	 * 更新密码
	 * 1. 校验用户是否存在
	 * 2.校验验证码
	 * 3.更新数据库
	 * (non-Javadoc)
	 * @see com.newlife.meetup.service.IUserService#updatePassword(com.newlife.meetup.domain.User)
	 */
	@Override
	@Transactional

	public ResponseUtil updatePassword(User user) {
		 List<User> users = this.userJpaRepository.findUserByPhoneNumber(user.getPhoneNumber());
		 if(users.size()==0) {
			 LOG.error("========= 用户未注册 ========");
			 responseUtil.setResponseCode("USER_ERROR_501");
			 responseUtil.setMessage("用户不存在,请注册.");
			 return responseUtil;
		 }
		 if(users.size()>1) {
			 LOG.error("========= 账户异常 一个手机号注册多个账户========");
			 responseUtil.setResponseCode("USER_ERROR_507");
			 responseUtil.setMessage("账户异常.");
			 return responseUtil;
		 }

//		1. 校验用户是否存在
		String isUsed = checkPhoneNumber(user.getPhoneNumber());
		 if(isUsed.equals("N")) {
			 LOG.error("========= 用户未注册 ========");
			 responseUtil.setResponseCode("USER_ERROR_501");
			 responseUtil.setMessage("用户不存在,请注册.");
			 return responseUtil;
		 }

//		 校验新旧密码是否相同
		 String eVerificationCode = EncryptionUtil.getEncryptString(user.getPassword());
		 if(eVerificationCode.equals(users.get(0).getPassword())) {
			 LOG.error("新旧密码相同.");
			 responseUtil.setResponseCode("USER_ERROR_506");
			 responseUtil.setMessage("新旧密码不能相同.");
			 return responseUtil;
		 }
//		 2.校验验证码
		 String passed = checkVerificationCode(user);
		 if("N".equals(passed)) {
			 responseUtil.setResponseCode("USER_ERROR_505");
			 responseUtil.setMessage("验证码已失效,请重新获取.");
			 return responseUtil;
		 }

//		 3.更新数据库()
		 if(isUsed.equals("Y")&&passed.equals("Y")) {
//			this.userJpaRepository.delete(users.get(0));
			 User usr = users.get(0);
			 usr.setPassword(EncryptionUtil.getEncryptString(user.getPassword()));
			 usr.setVerificationCode(EncryptionUtil.getEncryptString(user.getVerificationCode()));
			 usr.setUpdatedTime(new Date(System.currentTimeMillis()));
			this.userJpaRepository.save(usr);
			responseUtil.setResponseCode("000");
			responseUtil.setMessage("请求成功!");
			}
		return responseUtil;
	}

	/*public ResponseUtil updatePassword(User user) {
		User usr = this.userJpaRepository.findOne(user.getUserId());

//		1. 校验用户是否存在
		String isUsed = checkPhoneNumber(user.getPhoneNumber());
		if(null == usr) {
			LOG.error("========= 用户未注册 ========");
			responseUtil.setResponseCode("USER_ERROR_501");
			responseUtil.setMessage("用户不存在,请注册.");
			return responseUtil;
		}
		if(isUsed.equals("N")) {
			LOG.error("========= 用户未注册 ========");
			responseUtil.setResponseCode("USER_ERROR_501");
			responseUtil.setMessage("用户不存在,请注册.");
			return responseUtil;
		}

//		 校验新旧密码是否相同
		String eVerificationCode = EncryptionUtil.getEncryptString(user.getPassword());
		if(eVerificationCode.equals(usr.getPassword())) {
			LOG.error("新旧密码相同.");
			responseUtil.setResponseCode("USER_ERROR_506");
			responseUtil.setMessage("新旧密码不能相同.");
			return responseUtil;
		}
//		 2.校验验证码
		String passed = checkVerificationCode(user);
		if("N".equals(passed)) {
			responseUtil.setResponseCode("USER_ERROR_505");
			responseUtil.setMessage("验证码已失效,请重新获取.");
			return responseUtil;
		}

//		 3.更新数据库()
		if(isUsed.equals("Y")&&passed.equals("Y")) {
//			this.userJpaRepository.delete(usr);
			usr.setPassword(EncryptionUtil.getEncryptString(user.getPassword()));
			usr.setVerificationCode(EncryptionUtil.getEncryptString(user.getVerificationCode()));
			usr.setUpdatedTime(new Date(System.currentTimeMillis()));
			this.userJpaRepository.save(usr);
			responseUtil.setResponseCode("000");
			responseUtil.setMessage("请求成功!");
		}
		return responseUtil;
	}*/

	 //校验用户验证码
		@Transactional
		public String checkVerificationCode(User user) {
			String result = "N";
			String verificationCode = "";
			try {
				checkCode = checkCodeRepository.findOne(user.getPhoneNumber());
				if(checkCode == null) {
					return result;
				}
				verificationCode = checkCode.getCode();
				if(checkCode.getIsUsed()) {
					return result;
				}
				if(checkCode.getExpireAt().before(new Timestamp(System.currentTimeMillis()))) {
					return result;
				}
				String eVerificationCode = EncryptionUtil.getEncryptString(user.getVerificationCode());
				if (eVerificationCode.equals(verificationCode)) {
					checkCode.setUsingAt(new Timestamp(System.currentTimeMillis()));
					checkCode.setIsUsed(true);
					checkCode.setCode(EncryptionUtil.getEncryptString(user.getVerificationCode()));
					checkCodeRepository.saveAndFlush(checkCode);
					result = "Y";
				}
			}catch (Exception e) {
				if(verificationCode==null) {
					result = "N";
				}
			}
			return result;
		}
	
		
	@Override
	public String checkUser(User user) {
		User user2 = findUserByPhoneNumber(user.getPhoneNumber());
		if(user.equals(user2)) {
			return "Y";
		}else {
			return "N";
		}
	}
	
	public User findUserByPhoneNumber(String phoneNumber){
		List<User> users = this.userJpaRepository.findUserByPhoneNumber(phoneNumber);
		if(users.size()!=0) {
			return users.get(0);
		}else {
			return null;
		}
	}

	@Override
	public String checkUser(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkPhoneNumber(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	//修改个人信息
	@Override
	public User updateUser(User user) {
		User u = new User();
		try {
			u = this.userJpaRepository.save(user);
		}catch (Exception e) {
			LOG.error("修改个人信息失败：{}", e.getMessage());
		}
		
		return u;
	}


	
}
