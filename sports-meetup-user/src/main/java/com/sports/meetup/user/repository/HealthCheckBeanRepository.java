package com.sports.meetup.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.user.domain.HealthCheckBean;

public interface HealthCheckBeanRepository extends JpaRepository<HealthCheckBean, Integer> {

}
