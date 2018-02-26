package com.sports.meetup.match.sapi.service.impl;

import com.sports.meetup.match.sapi.domain.AppliedUserBean;
import com.sports.meetup.match.sapi.domain.CreatedUserBean;
import com.sports.meetup.match.sapi.domain.NearbyMatchBean;
import com.sports.meetup.match.sapi.repository.MatchRepository;
import com.sports.meetup.match.sapi.service.INearMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Repository
public class NearMatchServiceImpl implements INearMatchService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MatchRepository matchRepository;

    /**
     * 获取附近的比赛（通过经纬度和场地类型）
     *
     * @param longitude
     * @param latitude
     * @param fieldTypes
     * @param pageInfo
     * @return
     */
    public List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String fieldTypes, String pageInfo) {
        String[] pageParams = pageInfo.split(",");
        Integer pageNum = Integer.parseInt(pageParams[0]);
        Integer pageSize = Integer.parseInt(pageParams[1]);
        Integer m = pageNum * pageSize;
        Integer n = pageSize;
        String sql="SELECT DISTINCT user.`name`,DATE,start_time,end_time,icon,(6371 * ACOS(COS(RADIANS("+latitude+")) * COS(RADIANS(sport_match.latitude)) * COS(RADIANS(sport_match.longitude) - RADIANS("+longitude+")) + SIN(RADIANS("+latitude+")) * SIN(RADIANS(sport_match.latitude)))) AS distance," +
                "match_type,field_type,total_number,joined_amount,sport_field.`address`,sport_match.`match_id` " +
                "FROM USER,sport_match,sport_field,match_type " +
                "WHERE USER.`user_id` = sport_match.`creator_id` AND sport_match.`field_id` = sport_field.`field_id` AND sport_match.`match_type` = match_type.`type_id`  " +
                "AND field_type IN("+fieldTypes+") " +
                "AND SYSDATE() < end_time AND DATEDIFF(end_time,NOW())<3 AND DATEDIFF(end_time,NOW())>=0 HAVING distance < 20 " +
                "ORDER BY distance LIMIT "+m+","+n+"";
        List<NearbyMatchBean> objs = this.jdbcTemplate.query(sql,new BeanPropertyRowMapper(NearbyMatchBean.class));
        List<NearbyMatchBean> matches = customConvertor(objs);
        if (0 == matches.size()) {
            matches = new ArrayList<NearbyMatchBean>();
        }

        return matches;

    }

    /**
     * 转换List<Object> to NearbyMatchBean
     *
     * @param obs
     * @return
     */
    public List<NearbyMatchBean> customConvertor(List<NearbyMatchBean> obs) {
        List<NearbyMatchBean> matches = new ArrayList<NearbyMatchBean>();
        for(int i=0;i<obs.size();i++){
            NearbyMatchBean nb = obs.get(i);
            //截取小数点后两位
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
            nb.setDistance(Double.valueOf(df.format(nb.getDistance())));
            //通过matchID得到比赛里面的成员信息
            BigInteger matchid = nb.getMatchId();
            List<Object> userinfo = this.matchRepository.findAppliedUserinfo(matchid);
            List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
            int length = appliedUserinfo.size();
            //创建数组
            AppliedUserBean[] userArray = new AppliedUserBean[length];
            for (int j = 0; j < userArray.length; j++) {
                userArray[j] = appliedUserinfo.get(j);
            }
            nb.setAppliedUsersInfo(userArray);
            //通过matchID得到比赛创建者的信息
            Object obj = this.matchRepository.findCreatedUserinfo(matchid);
            CreatedUserBean CreatedUserinfo = customConvertor04(obj);
            nb.setCreatedUserInfo(CreatedUserinfo);
            matches.add(nb);
        }
        return matches;
    }
    /**
     * 通过matchid获取参加比赛的成员信息(转换List<Object> to AppliedUserBean)
     *
     * @param obs
     * @return
     */
    public List<AppliedUserBean> customConvertor03(List<Object> obs) {
        List<AppliedUserBean> matches = new ArrayList<AppliedUserBean>();
        for (Iterator iterator = obs.iterator(); iterator.hasNext(); ) {
            AppliedUserBean nb = new AppliedUserBean();
            Object[] objs = (Object[]) iterator.next();
            if (objs.length > 0) {
                nb.setUserId((BigInteger) objs[0]);
                nb.setApplyResult((String) objs[1]);
                nb.setAddress((String) objs[2]);
                nb.setCreatedTime((Timestamp) objs[3]);
                nb.setGender((String) objs[4]);
                nb.setHobbies((String) objs[5]);
                nb.setIcon((String) objs[6]);
                nb.setName((String) objs[7]);
                nb.setPhoneNumber((String) objs[8]);
                nb.setUpdatedTime((Timestamp) objs[9]);
                nb.setWeekday((String) objs[10]);
                nb.setWeekend((String) objs[11]);
                nb.setCity((String) objs[12]);
                matches.add(nb);
            }
        }
        return matches;
    }

    /**
     * 获取创建者信息    转换List<Object> to CreatedUserBean
     *
     * @param obs
     * @return
     */
    public CreatedUserBean customConvertor04(Object obs) {
        CreatedUserBean nb = new CreatedUserBean();
        Object[] objs = (Object[]) obs;
        if (obs.toString().length() > 0) {
            nb.setCreatedId((BigInteger) objs[0]);
            nb.setAddress((String) objs[1]);
            nb.setCreatedTime((Timestamp) objs[2]);
            nb.setGender((String) objs[3]);
            nb.setHobbies((String) objs[4]);
            nb.setIcon((String) objs[5]);
            nb.setName((String) objs[6]);
            nb.setPhoneNumber((String) objs[7]);
            nb.setUpdatedTime((Timestamp) objs[8]);
            nb.setWeekday((String) objs[9]);
            nb.setWeekend((String) objs[10]);
            nb.setCity((String) objs[11]);
        }
        return nb;
    }
}

