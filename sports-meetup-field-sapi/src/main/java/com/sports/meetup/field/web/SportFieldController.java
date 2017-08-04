package com.sports.meetup.field.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value="/findNearbySportFields")
	public ResponseEntity<?> findNearbySportFields(@RequestParam(value="longitude")Double longitude, @RequestParam(value="latitude")Double latitude){
			this.sportFieldService.getNearbySportFields(longitude, latitude);
		return null;
	}
	
}
