package com.sports.meetup.match.sapi.repository;

import com.sports.meetup.match.sapi.domain.entity.UserApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplyRepository extends JpaRepository<UserApply, Long>  {
}
