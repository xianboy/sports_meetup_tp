package com.sports.meetup.field.sapi.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.field.sapi.domain.NearField;
import com.sports.meetup.field.sapi.domain.SportField;
import com.sports.meetup.field.sapi.service.ISportFieldService;

@RestController
@RequestMapping(value = { "/sportfields", "/v1.0/sportfields" })
public class SportFieldController {
	private static final Logger LOG = LoggerFactory.getLogger(SportFieldController.class);
	
	@Autowired
	private ISportFieldService sportFieldService;
	
	@PostMapping(value="/saveSportField")
	public SportField saveSportField(@RequestBody SportField sportField) {
		SportField sField = this.sportFieldService.saveSportField(sportField);
		return sField;
	}
	
//	@PostMapping(value="/findNearbySportFields")
//	public List<NearField> findNearbySportFields(@RequestBody GPSLocation gps){
//		LOG.info("Enter SAPI SportFieldController findNearbySportFields method: ");
//		List<NearField> nearbySportFields = this.sportFieldService.findNearbySportFields(gps.getLongitude(), gps.getLatitude());
//		return nearbySportFields;
//	}

	/**
	 * 获取场地信息
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@GetMapping(value="/findNearbySportFields/{longitude}/{latitude}/{pageInfo}")
	public List<NearField> findNearbySportFields(@PathVariable Double longitude, @PathVariable Double latitude,@PathVariable String pageInfo){
		LOG.info("Enter SAPI SportFieldController findNearbySportFields method: ");
		List<NearField> nearbySportFields = this.sportFieldService.findNearbySportFields(longitude,latitude,pageInfo);
		return nearbySportFields;
	}

	//首页：获取附近的场地信息(通过类型筛选)
	@GetMapping(value="/findNearbySportFields/{longitude}/{latitude}/{fieldType}/{pageInfo}")
	public List<NearField> findNearbySportFields(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable String fieldType, @PathVariable String pageInfo){
		List<NearField> matches = this.sportFieldService.findNearbySportFields(longitude, latitude, fieldType ,pageInfo);
		return  matches;
	}

	//获取附近的场地信息(有比赛)
	@GetMapping(value="/findNearFieldsHaveMatches/{longitude}/{latitude}/{pageInfo}")
	public List<NearField> findNearFieldsHaveMatches(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable String pageInfo){
		List<NearField> matches = this.sportFieldService.findNearFieldsHaveMatches(longitude, latitude,pageInfo);
		return  matches;
	}
	
	
	//首页 "场地"信息
	@GetMapping(value="/{fieldId}")
	public SportField findSportFieldByFieldId(@PathVariable Long fieldId) {
		return this.sportFieldService.findSportFieldByFieldId(fieldId);
	}
	
	
}
