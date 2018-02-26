package com.sports.meetup.match.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class InitialMatchRequest {
	
	private SportMatch match;

	public SportMatch getMatch() {
		return match;
	}

	public void setMatch(SportMatch match) {
		this.match = match;
	}
}
