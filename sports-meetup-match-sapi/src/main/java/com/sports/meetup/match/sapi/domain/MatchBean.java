package com.sports.meetup.match.sapi.domain;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class MatchBean {

    private String icon;

    private String name;

    private Date startTime;

    private Date time;

    private Date QZTime;

    private String matchType;

    private Integer joinedAmmount;

    private String address;

    private Double distance;

    private Double fieldType;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getQZTime() {
        return QZTime;
    }

    public void setQZTime(Date QZTime) {
        this.QZTime = QZTime;
    }

    public Double getFieldType() {
        return fieldType;
    }

    public void setFieldType(Double fieldType) {
        this.fieldType = fieldType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public Integer getJoinedAmmount() {
        return joinedAmmount;
    }

    public void setJoinedAmmount(Integer joinedAmmount) {
        this.joinedAmmount = joinedAmmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MatchBean{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", time=" + time +
                ", QZTime=" + QZTime +
                ", matchType='" + matchType + '\'' +
                ", joinedAmmount=" + joinedAmmount +
                ", address='" + address + '\'' +
                ", distance=" + distance +
                ", fieldType=" + fieldType +
                '}';
    }
}

