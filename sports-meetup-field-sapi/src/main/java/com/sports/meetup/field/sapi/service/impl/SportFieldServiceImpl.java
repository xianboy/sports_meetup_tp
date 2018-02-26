package com.sports.meetup.field.sapi.service.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.field.sapi.domain.NearField;
import com.sports.meetup.field.sapi.domain.SportField;
import com.sports.meetup.field.sapi.repository.SportFieldRepository;
import com.sports.meetup.field.sapi.service.ISportFieldService;

@Service
public class SportFieldServiceImpl implements ISportFieldService {
	private static final Logger LOG = LoggerFactory.getLogger(SportFieldServiceImpl.class);
	
	@Autowired
	private SportFieldRepository sportFieldRepository;

	/**
	 * 保存附近的场地信息
	 * @param sportField
	 * @return
	 */
	@Override
	public SportField saveSportField(SportField sportField) {
		SportField sField = new SportField();
		try {
			sportField.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			sField = this.sportFieldRepository.save(sportField);
		}catch (Exception e) {
			LOG.error("添加比赛失败：{}", e.getMessage());
		}
		return sField;
	}

	/**
	 * 首页    附件的场地信息
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public List<NearField> findNearbySportFields(Double longitude, Double latitude,String pageInfo) {
		LOG.info("Enter Sapi SportFieldServiceImpl method findNearbySportFields");
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum*pageSize;
		Integer n = pageSize;
		List<Object> objs  = this.sportFieldRepository.findNearbySportFields(longitude, latitude,m,n);
		List<NearField> matches = customConvertor(objs);
		if(matches.size()==0) {
			matches = null;
		}
//		LOG.info("附近场地: {}", nearbySportFields.get(0).toString());
		return matches;
	}


	/**
	 * 附件   附件的场地信息（由场地类型判断）
	 * @param longitude
	 * @param latitude
	 * @param fieldTypes
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<NearField> findNearbySportFields(Double longitude, Double latitude,String fieldTypes,String pageInfo) {
		List<NearField> nearFields = new ArrayList<NearField>();
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum*pageSize;
		Integer n = pageSize;
		if(fieldTypes.equals("null")) {
		List<Object> objs = this.sportFieldRepository.findNearbySportFields01(longitude, latitude, m, n);
			List<NearField> matches = customConvertor(objs);
			if(0==matches.size()) {
				matches = new ArrayList<NearField>();
//				matches = null;
			}
			return matches;
		}
		String[] typeParams = fieldTypes.split(",");
		for(int i=0;i<typeParams.length;i++){
			String fieldType = typeParams[i];
			List<Object> objs = this.sportFieldRepository.findNearbySportFields(longitude, latitude, fieldType, m, n);
			List<NearField> matches = customConvertor(objs);
			if(matches.size()>0) {
				for (int j = 0; j <matches.size() ; j++) {
					nearFields.add(matches.get(j));
				}
			}else{
				nearFields = null;
			}
		}
		return nearFields;
	}

	/**
	 * 转换List<Object> to SportField
	 * @param obs
	 * @return
	 */
	public List<NearField> customConvertor(List<Object> obs){
		List<NearField> matches = new ArrayList<NearField>();
		for (Iterator iterator = obs.iterator(); iterator.hasNext();) {
			NearField nb = new NearField();
			Object[] objs = (Object[]) iterator.next();
			if(objs.length>0) {
				nb.setFieldId((BigInteger) objs[0]);
				nb.setCreatorId((BigInteger) objs[4]);
				nb.setFieldName((String) objs[6]);
				nb.setAddress((String) objs[1]);
				nb.setFieldType((String) objs[7]);
				nb.setAdminPhone((String) objs[2]);
				nb.setPicsOfField((String) objs[13]);
				nb.setIsApproved((Boolean) objs[9]);
				nb.setLatitude((Double) objs[11]);
				nb.setLongitude((Double) objs[12]);
				nb.setCreatedDate((Timestamp) objs[3]);
				nb.setIsFree((Boolean) objs[10]);
				nb.setHasMatch((Boolean) objs[8]);
				nb.setPricePerHour((Double) objs[14]);
				nb.setFieldDetail((String) objs[5]);
				//截取小数点后两位
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
				nb.setDistance(Double.valueOf((df.format(objs[15]))));
//				nb.setDistance((Double) objs[15]);
				matches.add(nb);
			}
		}

		return matches;
	}
	/**
	 * 首页  附件的场地信息（有比赛）
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public List<NearField> findNearFieldsHaveMatches(Double longitude, Double latitude,String pageInfo) {
		LOG.info("Enter Sapi SportFieldServiceImpl method findNearbySportFields");
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum*pageSize;
		Integer n = pageSize;
		List<Object> objs  = this.sportFieldRepository.findNearFieldsHaveMatches(longitude, latitude,m,n);
		List<NearField> matches = customConvertor(objs);
		if(matches.size()==0) {
			matches = null;
		}else {
			for (int i = 0; i < matches.size(); i++) {
				matches.get(i).setHasMatch(true);
			}
		}
		return matches;
	}

	/**
	 * 通过fieldId查询场地信息
	 * @param fieldId
	 * @return
	 */
	@Override
	public SportField findSportFieldByFieldId(Long fieldId) {
		SportField sportField = null;
		try {
			sportField = this.sportFieldRepository.findOne(fieldId);
		}catch (Exception e) {
			try {
				throw new SQLException("查询数据库异常");
			} catch (SQLException e1) {
				LOG.error(e1.getMessage());
			}
		}
		return sportField;
	}

}
