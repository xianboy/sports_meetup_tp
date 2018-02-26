package com.sports.meetup.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sports.meetup.user.domain.User;


public interface UserJpaRepository extends JpaRepository<User, Long> {


	List<User> findUserByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT user_id,NAME,phone_number,address,created_time,gender,hobbies,icon,updated_time,WEEKDAY,weekend,city FROM user WHERE phone_number=?1" , nativeQuery = true)
	Object findUserInfoByPhoneNumber(String phoneNumber);
}
