package com.sports.meetup.match.sapi.domain;

import com.sports.meetup.match.sapi.domain.entity.SportMatch;
import com.sports.meetup.match.sapi.domain.entity.UserApply;

public class InitialMatchRequest {
	
	private SportMatch match;

	private UserApply userapply;

	public SportMatch getMatch() {
		return match;
	}

	public void setMatch(SportMatch match) {
		this.match = match;
	}

	public UserApply getUserapply() {
		return userapply;
	}

	public void setUserapply(UserApply userapply) {
		this.userapply = userapply;
	}
}
