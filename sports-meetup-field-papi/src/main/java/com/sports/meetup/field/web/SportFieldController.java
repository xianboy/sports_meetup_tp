package com.sports.meetup.field.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.common.domain.SportField;
import com.sports.meetup.field.service.ISportFieldService;

@RestController
@RequestMapping(value = {"/sportfields", "/v1.0/sportfields"})
public class SportFieldController {
	private static final Logger LOG = LoggerFactory.getLogger(SportFieldController.class);

	@Autowired
	private ISportFieldService sportFieldService;

	ResponseEntity<ApiDefaultResponse> response = null;

	@PostMapping(value="/addSportField")
	public ResponseEntity<?> addSportField(@RequestBody SportField sportField, BindingResult bindResult) {
		if (bindResult.hasErrors()) {
			String errorMsg = bindResult.getAllErrors().get(0).getDefaultMessage();
			LOG.error(errorMsg);
			response = new ResponseEntity<ApiDefaultResponse>(
					new ApiDefaultResponse(ConstantFields.getBadRequestCode(), errorMsg, null), HttpStatus.OK);
		}
		ApiDefaultResponse apiDefaultResponse = sportFieldService.addSportField(sportField);
		return new ResponseEntity<ApiDefaultResponse>(apiDefaultResponse, HttpStatus.CREATED);
	}

}
