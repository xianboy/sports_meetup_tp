package com.sports.meetup.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.upload.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
}
