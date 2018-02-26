package com.sports.meetup.user.sapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.meetup.user.sapi.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
