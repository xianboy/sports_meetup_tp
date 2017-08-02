package com.sports.meetup.field.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.field.domain.SportField;
import com.sports.meetup.field.repository.SportFieldRepository;
import com.sports.meetup.field.service.ISportFieldService;

@Service
public class SportFieldServiceImpl implements ISportFieldService {


	@Autowired
	private SportFieldRepository sportFieldRepository; 

	@Autowired
	private SportField sportFeld;
	
	@Override
	public SportField saveSportField(SportField sportField) {
		this.sportFeld = this.sportFieldRepository.save(sportField);
		return sportFeld;
	}

}
