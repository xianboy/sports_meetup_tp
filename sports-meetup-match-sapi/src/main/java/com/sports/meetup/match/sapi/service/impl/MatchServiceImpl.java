package com.sports.meetup.match.sapi.service.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sports.meetup.match.sapi.domain.*;
import com.sports.meetup.match.sapi.domain.entity.UserApply;
import com.sports.meetup.match.sapi.repository.UserApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.match.sapi.domain.entity.SportMatch;
import com.sports.meetup.match.sapi.repository.MatchRepository;
import com.sports.meetup.match.sapi.service.IMatchService;

@Service
public class MatchServiceImpl implements IMatchService {

	private static final Logger LOG = LoggerFactory.getLogger(MatchServiceImpl.class);

	@Autowired
	private UserApplyRepository userApplyRepository;

	@Autowired
	private MatchRepository matchRepository;

	/**
	 * 保存比赛信息
	 *
	 * @param match
	 * @return
	 */
	@Override
	public SportMatch saveMatch(SportMatch match) {
		LOG.info("Envoke Match SPai serivce saveMatch");
		match.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		match.setDate(new Date(System.currentTimeMillis()));
		SportMatch mach = new SportMatch();
		try {
			mach = matchRepository.save(match);
		} catch (Exception e) {
			LOG.error("保存数据出错：{}", e.getMessage());
		}
		return mach;
	}

	/**
	 * 申请加入比赛。1.通过RequestBody获取matchid 2.参加人数加一 3.保存申请表信息
	 *
	 * @param userapply
	 * @return
	 */
	@Override
	public UserApply joinMatch(UserApply userapply) {
		LOG.info("Envoke Match SPai serivce joinMatch");
		Long matchId = userapply.getMatchId();
		userapply.setApplyTime(new Timestamp(System.currentTimeMillis()));
		SportMatch mach = this.findMatchById(matchId);
		mach.setJoinedAmount(mach.getJoinedAmount() + 1);
		userapply.setApplyResult("y");
		UserApply matchinfo = new UserApply();
		try {
			matchinfo = userApplyRepository.save(userapply);
		} catch (Exception e) {
			LOG.error("数据库出错：{}", e.getMessage());
		}
		return matchinfo;
	}

	/**
	 * 加入比赛后返回的信息
	 * 
	 * @param matchId
	 * @return
	 */
	public MyMatchBean findMatchinfoById(BigInteger matchId) {
		Object obj = new Object();
		try {
			obj = this.matchRepository.findMatchinfoById(matchId);
		} catch (Exception e) {
			LOG.error("调用数据库出错:{}", e.getMessage());
		}
		if (obj == null) {
			return new MyMatchBean();
		}
		MyMatchBean matche = customConvertor06(obj);
		return matche;
	}

	/**
	 * 加入比赛时判断用户是否已经加入过该比赛 （true:未加入 false：已加入）
	 * 
	 * @param request
	 * @return
	 */
	public Boolean isJoinedMatches(UserApply request) {
		List<Object> userinfo = new ArrayList<Object>();
		try {
			userinfo = this.matchRepository.findAppliedUserinfo(BigInteger.valueOf(request.getMatchId()));
		} catch (Exception e) {
			LOG.error("获取已申请加入比赛的用户失败:{}", e.getMessage());
		}
		List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
		for (int i = 0; i < appliedUserinfo.size(); i++) {
			BigInteger applieduser = appliedUserinfo.get(i).getUserId();
			if (applieduser == BigInteger.valueOf(request.getUserId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取发布的比赛（未结束）
	 *
	 * @param creatorid
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<MyMatchBean> findSportMatchByCreatorId(Long creatorid, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findByCreatorId(creatorid, m, n);
		} catch (Exception e) {
			LOG.error("获取我发布的比赛失败：{}", e.getMessage());
		}
		List<MyMatchBean> matches = customConvertor01(objs);
		if (0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 获取发布的比赛（已结束）
	 *
	 * @param creatorid
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<MyMatchBean> findSportOldMatchByCreatorId(Long creatorid, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findByOldCreatorId(creatorid, m, n);
		} catch (Exception e) {
			LOG.error("获取比赛历史失败：{}", e.getMessage());
		}
		List<MyMatchBean> matches = customConvertor01(objs);
		if (0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 获取比赛具体信息
	 *
	 * @param matchId
	 * @return
	 */
	@Override
	public SportMatch findMatchById(Long matchId) {
		SportMatch match = this.matchRepository.findSportMatchByMatchId(matchId);
		LOG.info("获取的比赛ID为：{}", matchId);
		if (match == null) {
			return null;
		} else {
			LOG.info(match.toString());
			return match;
		}
	}

	/**
	 * 获取比赛具体信息，有成员信息
	 *
	 * @param matchId
	 * @return
	 */
	@Override
	public SportMatchAndAddress findMatchById(BigInteger matchId) {
		LOG.info("获取的比赛ID为：{}", matchId);
		// 获取比赛信息
		Object obj = this.matchRepository.findSportMatchByMatchId(matchId);
		if (obj == null) {
			return null;
		} else {
			SportMatchAndAddress matches = customConvertor05(obj);
			LOG.info(matches.toString());
			return matches;
		}
	}

	/**
	 * 获取创建者信息
	 * 
	 * @param matchId
	 * @return
	 */
	public CreatedUserBean findCreatedUserInfoById(BigInteger matchId) {
		LOG.info("获取的比赛ID为：{}", matchId);
		// 获取创建者信息
		Object obj = this.matchRepository.findCreatedUserinfo(matchId);
		if (obj == null) {
			CreatedUserBean match = new CreatedUserBean();
			return match;
		} else {
			CreatedUserBean match = customConvertor04(obj);
			LOG.info(match.toString());
			return match;
		}
	}

	/**
	 * 获取比赛成员信息
	 * 
	 * @param matchId
	 * @return
	 */
	public List<AppliedUserBean> findAppliedUserInfoById(BigInteger matchId) {
		List<Object> userInfo = null;
		try {
			userInfo = this.matchRepository.findAppliedUserinfo(matchId);
		} catch (Exception e) {
			LOG.error("获取已申请加入比赛的用户失败:{}", e.getMessage());
		}
		List<AppliedUserBean> appliedUserinfo = customConvertor03(userInfo);
		if (appliedUserinfo.size() == 0) {
			appliedUserinfo = new ArrayList<AppliedUserBean>();
		}
		LOG.info(appliedUserinfo.toString());
		return appliedUserinfo;

	}

	/**
	 * 根据经纬度获取附近的比赛（没分页）
	 *
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public List<SportMatch> findNearbyMatches(Double longitude, Double latitude) {
		List<SportMatch> matches = null;
		try {
			matches = this.matchRepository.findSportMatchByLongitudeAndLatitude(longitude, latitude);
		} catch (Exception e) {
			LOG.error("获取附近的比赛失败:{}", e.getMessage());
		}
		if (matches.size() == 0 || matches == null) {
			matches = new ArrayList<SportMatch>();
		}
		return matches;
	}

	/**
	 * 首页"比赛信息"(通过场地)(返回的是球场信息)
	 *
	 * @param fieldId
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<MyMatchBean> findMatchByFieldId(Long fieldId, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findMatchByFieldId(fieldId, m, n);
		} catch (Exception e) {
			LOG.error("获取比赛信息失败:{}", e.getMessage());
		}
		List<MyMatchBean> matches = customConvertor02(objs);
		if (null == matches || 0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 首页"比赛信息"(通过场地)（由时间选取）
	 *
	 * @param fieldId
	 * @param date
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<MyMatchBean> findMatchByFieldId(Long fieldId, Date date, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findMatchByFieldId(fieldId, date, m, n);
		} catch (Exception e) {
			LOG.error("获取制定日期的比赛失败: {}", e.getMessage());
		}
		List<MyMatchBean> matches = customConvertor02(objs);
		if (null == matches || 0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 获取附近的比赛（通过经纬度）
	 *
	 * @param longitude
	 * @param latitude
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = new ArrayList<Object>();
		try {
			objs = this.matchRepository.findMatches(longitude, latitude, m, n);
		}catch (Exception e) {
			LOG.error("获取附近的比赛失败:{}", e.getMessage());
		}
		
		List<NearbyMatchBean> matches = customConvertor(objs);
		if (matches==null || 0 == matches.size()) {
			matches = new ArrayList<NearbyMatchBean>();
		}
		return matches;
	}

	/**
	 * 获取附近的比赛（通过经纬度和场地类型）
	 *
	 * @param longitude
	 * @param latitude
	 * @param fieldTypes
	 * @param pageInfo
	 * @return
	 */
	public List<NearbyMatchBean> getNearbyMatches(Double longitude, Double latitude, String fieldTypes,
			String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		if (fieldTypes.equals("null")) {
			List<Object> objs = this.matchRepository.findMatches(longitude, latitude, m, n);
			List<NearbyMatchBean> matches = customConvertor(objs);
			if (0 == matches.size()) {
				matches = new ArrayList<NearbyMatchBean>();
				// matches = null;
			}
			return matches;
		}
		List<NearbyMatchBean> nearbymatchbeans = null;
		try {
			nearbymatchbeans = new ArrayList<NearbyMatchBean>();
		} catch (Exception e) {
			LOG.error("获取附近的比赛失败：{}", e.getMessage());
		}
		String[] typeParams = fieldTypes.split(",");
		for (int i = 0; i < typeParams.length; i++) {
			String fieldType = typeParams[i];
			List<Object> objs = this.matchRepository.findMatches(longitude, latitude, fieldType, m, n);
			List<NearbyMatchBean> matches = customConvertor(objs);
			matches.iterator();
			if (matches.size() > 0) {
				for (int j = 0; j < matches.size(); j++) {
					nearbymatchbeans.add(matches.get(j));
				}
			} else {
				nearbymatchbeans = new ArrayList<NearbyMatchBean>();
			}
		}
		if(nearbymatchbeans==null || nearbymatchbeans.size()==0) {
			nearbymatchbeans = new ArrayList<NearbyMatchBean>();
		}
		return nearbymatchbeans;
	}

	/**
	 * 我的页面：我的比赛(我申请加入的尚未开始的比赛)
	 *
	 * @param userId
	 *            申请比赛用户的ID
	 * @param pageInfo
	 *            (pageNum,pageSize) n 第几页； m 获取几条数据
	 * @return
	 */
	@Override
	public List<MyMatchBean> getMyMatches(Long userId, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findMyMatches(userId, m, n);
		}catch (Exception e) {
			LOG.error("获取我的比赛失败：{}", e.getMessage());
		}
		List<MyMatchBean> matches = customConvertor01(objs);
		if (0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 获取首页比赛信息（申请加入的历史比赛信息）
	 *
	 * @param userid
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<MyMatchBean> getMyOldMatches(Long userid, String pageInfo) {
		String[] pageParams = pageInfo.split(",");
		Integer pageNum = Integer.parseInt(pageParams[0]);
		Integer pageSize = Integer.parseInt(pageParams[1]);
		Integer m = pageNum * pageSize;
		Integer n = pageSize;
		List<Object> objs = null;
		try {
			objs = this.matchRepository.findMyOldMatches(userid, m, n);
		}catch (Exception e) {
			LOG.error("获取比赛历史失败：{}", e.getMessage());
		}
		
		List<MyMatchBean> matches = customConvertor01(objs);
		if (0 == matches.size()) {
			matches = new ArrayList<MyMatchBean>();
		}
		return matches;
	}

	/**
	 * 转换List<Object> to NearbyMatchBean
	 *
	 * @param obs
	 * @return
	 */
	public List<NearbyMatchBean> customConvertor(List<Object> obs) {
		List<NearbyMatchBean> matches = new ArrayList<NearbyMatchBean>();
		for (Iterator iterator = obs.iterator(); iterator.hasNext();) {
			NearbyMatchBean nb = new NearbyMatchBean();
			Object[] objs = (Object[]) iterator.next();
			if (objs.length > 0) {
				nb.setName(""+objs[0]);
				nb.setDate((Date) objs[1]);
				nb.setStartTime((Timestamp) objs[2]);
				nb.setEndTime((Timestamp) objs[3]);
				nb.setIcon((String) objs[4]);
				// 截取小数点后两位
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
				nb.setDistance(Double.valueOf((df.format(objs[5]))));
				nb.setMatchType(""+objs[6]);
				nb.setFieldType(""+objs[7]);
				nb.setTotalNumber(""+objs[8]);
				nb.setJoinedAmmount((Integer) objs[9]);
				nb.setAddress((String) objs[10]);
				nb.setMatchId((BigInteger) objs[11]);
				// 通过matchID得到比赛里面的成员信息
				BigInteger matchid = (BigInteger) objs[11];
				List<Object> userinfo = this.matchRepository.findAppliedUserinfo(matchid);
				List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
				int length = appliedUserinfo.size();
				// 创建数组
				AppliedUserBean[] userArray = new AppliedUserBean[length];
				for (int i = 0; i < userArray.length; i++) {
					userArray[i] = appliedUserinfo.get(i);
				}
				nb.setAppliedUsersInfo(userArray);
				// 通过matchID得到比赛创建者的信息
				Object obj = this.matchRepository.findCreatedUserinfo(matchid);
				CreatedUserBean CreatedUserinfo = customConvertor04(obj);
				nb.setCreatedUserInfo(CreatedUserinfo);
				matches.add(nb);
			}
		}
		return matches;
	}

	/**
	 * 转换List<Object> to MyMatchBean
	 *
	 * @param obs
	 * @return
	 */
	public List<MyMatchBean> customConvertor01(List<Object> obs) {
		List<MyMatchBean> matches = new ArrayList<MyMatchBean>();
		for (Iterator iterator = obs.iterator(); iterator.hasNext();) {
			MyMatchBean nb = new MyMatchBean();
			Object[] objs = (Object[]) iterator.next();
			if (objs.length > 0) {
				nb.setDate((Date) objs[0]);
				nb.setStartTime((Timestamp) objs[1]);
				nb.setEndTime((Timestamp) objs[2]);
				nb.setMatchType(objs[3].toString());
				nb.setFieldType(objs[4].toString());
				nb.setTotalNumber(objs[5].toString());
				nb.setJoinedAmmount((Integer) objs[6]);
				nb.setAddress((String) objs[7]);
				nb.setMatchId((BigInteger) objs[8]);
				// 通过matchID得到比赛里面的成员信息
				BigInteger matchid = (BigInteger) objs[8];
				List<Object> userinfo = this.matchRepository.findAppliedUserinfo(matchid);
				List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
				int length = appliedUserinfo.size();
				// 创建数组
				AppliedUserBean[] userArray = new AppliedUserBean[length];
				for (int i = 0; i < userArray.length; i++) {
					userArray[i] = appliedUserinfo.get(i);
				}
				nb.setAppliedUsersInfo(userArray);
				// 通过matchID得到比赛创建者的信息
				Object obj = this.matchRepository.findCreatedUserinfo(matchid);
				CreatedUserBean CreatedUserinfo = customConvertor04(obj);
				nb.setCreatedUserInfo(CreatedUserinfo);
				matches.add(nb);
			}
		}
		return matches;
	}

	/**
	 * 转换List<Object> to MyMatchBean
	 *
	 * @param obs
	 * @return
	 */
	public List<MyMatchBean> customConvertor02(List<Object> obs) {
		List<MyMatchBean> matches = new ArrayList<MyMatchBean>();
		for (Iterator iterator = obs.iterator(); iterator.hasNext();) {
			MyMatchBean nb = new MyMatchBean();
			Object[] objs = (Object[]) iterator.next();
			if (objs.length > 0) {
				nb.setDate((Date) objs[0]);
				nb.setStartTime((Timestamp) objs[1]);
				nb.setEndTime((Timestamp) objs[2]);
				nb.setMatchType(objs[3].toString());
				nb.setFieldType(objs[4].toString());
				nb.setTotalNumber(objs[5].toString());
				nb.setJoinedAmmount((Integer) objs[6]);
				nb.setAddress((String) objs[7]);
				nb.setMatchId((BigInteger) objs[8]);
				nb.setCreatorId((BigInteger) objs[9]);
				// 通过matchID得到比赛里面的成员信息
				BigInteger matchid = (BigInteger) objs[8];
				List<Object> userinfo = this.matchRepository.findAppliedUserinfo(matchid);
				List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
				int length = appliedUserinfo.size();
				// 创建数组
				AppliedUserBean[] userArray = new AppliedUserBean[length];
				for (int i = 0; i < userArray.length; i++) {
					userArray[i] = appliedUserinfo.get(i);
				}
				nb.setAppliedUsersInfo(userArray);
				// 通过matchID得到比赛创建者的信息
				Object obj = this.matchRepository.findCreatedUserinfo(matchid);
				CreatedUserBean CreatedUserinfo = customConvertor04(obj);
				nb.setCreatedUserInfo(CreatedUserinfo);
				matches.add(nb);
			}
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
		for (Iterator iterator = obs.iterator(); iterator.hasNext();) {
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
	 * 获取创建者信息 转换List<Object> to CreatedUserBean
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

	/**
	 * 获取创建者信息 转换List<Object> to CreatedUserBean
	 *
	 * @param obs
	 * @return
	 */
	public SportMatchAndAddress customConvertor05(Object obs) {
		SportMatchAndAddress nb = new SportMatchAndAddress();
		Object[] objs = (Object[]) obs;
		if (obs.toString().length() > 0) {
			nb.setMatchId((BigInteger) objs[0]);
			nb.setCreatedTime((Timestamp) objs[1]);
			nb.setEndTime((Timestamp) objs[2]);
			nb.setFieldId((BigInteger) objs[3]);
			nb.setJoinedAmount((Integer) objs[4]);
			nb.setLatitude((Double) objs[5]);
			nb.setLongitude((Double) objs[6]);
			nb.setMatchType((BigInteger) objs[7]);
			nb.setRemarks((String) objs[8]);
			nb.setStartTime((Timestamp) objs[9]);
			nb.setDate((Date) objs[10]);
			nb.setAddress((String) objs[11]);
		}
		return nb;
	}

	/**
	 * 获取加入比赛后的比赛信息（）
	 * 
	 * @param obs
	 * @return
	 */
	public MyMatchBean customConvertor06(Object obs) {
		MyMatchBean nb = new MyMatchBean();
		Object[] objs = (Object[]) obs;
		if (obs.toString().length() > 0) {
			nb.setDate((Date) objs[0]);
			nb.setStartTime((Timestamp) objs[1]);
			nb.setEndTime((Timestamp) objs[2]);
			nb.setMatchType(objs[3].toString());
			nb.setFieldType(objs[4].toString());
			nb.setTotalNumber(objs[5].toString());
			nb.setJoinedAmmount((Integer) objs[6]);
			nb.setAddress((String) objs[7]);
			nb.setMatchId((BigInteger) objs[8]);
			nb.setCreatorId((BigInteger) objs[9]);
			// 通过matchID得到比赛里面的成员信息
			BigInteger matchid = (BigInteger) objs[8];
			List<Object> userinfo = this.matchRepository.findAppliedUserinfo(matchid);
			List<AppliedUserBean> appliedUserinfo = customConvertor03(userinfo);
			int length = appliedUserinfo.size();
			// 创建数组
			AppliedUserBean[] userArray = new AppliedUserBean[length];
			for (int i = 0; i < userArray.length; i++) {
				userArray[i] = appliedUserinfo.get(i);
			}
			nb.setAppliedUsersInfo(userArray);
			// 通过matchID得到比赛创建者的信息
			Object obj = this.matchRepository.findCreatedUserinfo(matchid);
			CreatedUserBean CreatedUserinfo = customConvertor04(obj);
			nb.setCreatedUserInfo(CreatedUserinfo);
		}
		return nb;
	}

}
