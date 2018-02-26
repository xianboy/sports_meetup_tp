package com.sports.meetup.match.sapi.web;

import com.sports.meetup.match.sapi.domain.NearbyMatchBean;
import com.sports.meetup.match.sapi.service.INearMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= {"/nearMatches", "/v1.0/matches"})
public class NearMatchController {

    private static final Logger LOG = LoggerFactory.getLogger(NearMatchController.class);
    @Autowired
    private INearMatchService nearMatchService;

    /**\
     *获取比赛信息（通过比赛类型筛选）
     * @param longitude
     * @param latitude
     * @param pageInfo
     * @return
     */
    @GetMapping(value="/getNearbyMatches/{longitude}/{latitude}/{fieldType}/{pageInfo}")
    public List<NearbyMatchBean> getNearByMatches(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable String fieldType, @PathVariable  String
            pageInfo){
        List<NearbyMatchBean> matches = this.nearMatchService.getNearbyMatches(longitude, latitude,fieldType, pageInfo);
        return  matches;
    }

}
