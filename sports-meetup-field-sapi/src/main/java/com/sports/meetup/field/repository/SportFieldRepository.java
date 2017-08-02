package com.sports.meetup.field.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.field.domain.SportField;


public interface SportFieldRepository extends JpaRepository<SportField, Long> {
	
}
