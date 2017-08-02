package com.sports.meetup.field.service;

import org.springframework.stereotype.Service;

import com.sports.meetup.field.domain.SportField;


@Service
public interface ISportFieldService {

	SportField saveSportField(SportField sportField);

}
