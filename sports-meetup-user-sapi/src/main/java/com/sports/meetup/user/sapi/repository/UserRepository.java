package com.sports.meetup.user.sapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.user.sapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByPhoneNumber(String phoneNumber);

}
