package com.sports.meetup.field.papi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.field.papi.domain.SportField;
import com.sports.meetup.field.papi.service.ISportFieldService;

@RestController
@RequestMapping(value = {"/sportfields", "/v1.0/sportfields"})
public class SportFieldController {
	private static final Logger LOG = LoggerFactory.getLogger(SportFieldController.class);

	@Autowired
	private ISportFieldService sportFieldService;

	ResponseEntity<ApiDefaultResponse> response = null;

	/**
	 * 添加场地信息
	 * @param sportField
	 * @param bindResult
	 * @return
	 */
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
//	//获取附近场地
//	@RequestMapping(value="/getNearbySportFields")
//	public ResponseEntity<?> getNearbySportFields(@RequestBody GPSLocation gps) {
//		LOG.info("获取({}, {})点附近的场地", gps.getLongitude(), gps.getLatitude());
//		ApiDefaultResponse apiDefaultResponse = sportFieldService.getNearbySportFields(gps.getLongitude(), gps.getLatitude());
//		return new ResponseEntity<ApiDefaultResponse>(apiDefaultResponse, HttpStatus.CREATED);
//	}

	/**
	 * 获取附件场地
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@GetMapping(value="/getNearbySportFields/{longitude}/{latitude}/{pageInfo}")
	public ResponseEntity<?> getNearbySportFields(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable String pageInfo) {
		LOG.info("获取({}, {})点附近的场地", longitude, latitude);
		ApiDefaultResponse apiDefaultResponse = sportFieldService.getNearbySportFields(longitude, latitude,pageInfo);
		return new ResponseEntity<ApiDefaultResponse>(apiDefaultResponse, HttpStatus.CREATED);
	}
	/**
	 * 获取附件场地（由比赛类型判断）
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@GetMapping(value="/getNearbySportFields/{longitude}/{latitude}/{fieldType}/{pageInfo}")
	public ResponseEntity<?> getNearbySportFields(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable String fieldType, @PathVariable String pageInfo) {
		LOG.info("获取({},{},{})点附近的场地", longitude, latitude,fieldType);
		ApiDefaultResponse apiDefaultResponse = sportFieldService.getNearbySportFields(longitude, latitude,fieldType,pageInfo);
		return new ResponseEntity<ApiDefaultResponse>(apiDefaultResponse, HttpStatus.CREATED);
	}

	/**
	 * 获取附件场地（有比赛）
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@GetMapping(value="/findNearFieldsHaveMatches/{longitude}/{latitude}/{pageInfo}")
	public ResponseEntity<?> findNearFieldsHaveMatches(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable String pageInfo) {
		LOG.info("获取({}, {})点附近的场地", longitude, latitude);
		ApiDefaultResponse apiDefaultResponse = sportFieldService.findNearFieldsHaveMatches(longitude, latitude,pageInfo);
		return new ResponseEntity<ApiDefaultResponse>(apiDefaultResponse, HttpStatus.CREATED);
	}


	@GetMapping(value="/{fieldId}")
	public SportField getFieldById(@PathVariable Long fieldId) {
		SportField sportField = this.sportFieldService.getSportFieldById(fieldId);
		return sportField;
	}
	
}
