package com.sports.meetup.field.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.field.domain.SportField;
import com.sports.meetup.field.service.ISportFieldService;

@RestController
@RequestMapping(value = { "/sportfields", "/v1.0/sportfields" })
public class SportFieldController {
	
	@Autowired
	private ISportFieldService sportFieldService;
	
	@PostMapping(value="/saveSportField")
	public SportField saveSportField(@RequestBody SportField sportField) {
		SportField sField = this.sportFieldService.saveSportField(sportField);
		return sField;
	}
	
}
