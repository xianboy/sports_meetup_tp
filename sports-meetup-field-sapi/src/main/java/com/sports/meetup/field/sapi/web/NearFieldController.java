package com.sports.meetup.field.sapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.field.sapi.domain.NearField;
import com.sports.meetup.field.sapi.service.INearFieldService;

@RestController
@RequestMapping(value = { "/NearSportfields", "/v1.0/sportfields" })
public class NearFieldController {

    @Autowired
    private INearFieldService nearfieldservice;
    //首页：获取附近的场地信息(通过类型筛选)
    @GetMapping(value="/findNearbySportFields/{longitude}/{latitude}/{fieldType}/{pageInfo}")
    public List<NearField> findNearbySportFields(@PathVariable Double longitude, @PathVariable Double latitude, @PathVariable String fieldType, @PathVariable String pageInfo){
        List<NearField> matches = this.nearfieldservice.findNearbySportFields(longitude, latitude, fieldType ,pageInfo);
        return  matches;
    }
}
