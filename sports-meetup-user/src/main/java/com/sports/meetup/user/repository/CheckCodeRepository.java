package com.sports.meetup.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.user.domain.CheckCode;

public interface CheckCodeRepository extends JpaRepository<CheckCode, String> {

	CheckCode findCheckCodeByCode(String verificationCode);


	

}
