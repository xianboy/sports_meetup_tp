package com.sports.meetup.field.sapi.service.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sports.meetup.field.sapi.domain.NearField;
import com.sports.meetup.field.sapi.repository.SportFieldRepository;
import com.sports.meetup.field.sapi.service.INearFieldService;

@Service
public class NearFieldServiceImpl implements INearFieldService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SportFieldRepository sportFieldRepository;
    /**
     * 附件   附件的场地信息（由场地类型判断）
     * @param longitude
     * @param latitude
     * @param fieldTypes
     * @param pageInfo
     * @return
     */
    @Override
    public List<NearField> findNearbySportFields(Double longitude, Double latitude, String fieldTypes, String pageInfo) {
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
            }
            return matches;
        }
        String sql="SELECT *,(6371*ACOS(COS(RADIANS("+latitude+"))*COS(RADIANS(latitude))* COS( RADIANS( longitude )-RADIANS("+longitude+") )+ SIN(RADIANS("+latitude+"))*SIN(RADIANS(latitude)))) AS distance " +
                "FROM Sport_Field WHERE field_type IN("+fieldTypes+") HAVING distance < 20 ORDER BY distance LIMIT "+m+", "+n+"";

        List<NearField> matches = this.jdbcTemplate.query(sql,new BeanPropertyRowMapper(NearField.class));
        if (0 == matches.size()) {
            matches = new ArrayList<NearField>();
        }
        return matches;
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
}
