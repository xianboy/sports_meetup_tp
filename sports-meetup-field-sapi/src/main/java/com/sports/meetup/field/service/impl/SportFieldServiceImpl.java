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
	
	@Override
	public SportField saveSportField(SportField sportField) {
		return this.sportFieldRepository.save(sportField);
	}

	@Override
	public void getNearbySportFields(Double longitude, Double latitude) {
		
	}

}
