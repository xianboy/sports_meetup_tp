package com.sports.meetup.match.papi.domain;

import java.util.List;

public class JoinMatchResponse {

    private SportMatchAndAddress MatchAndAddress;
    private CreatedUserBean CreatedUserInfo;
    private List<AppliedUserBean> AppliedUserInfo;

    public SportMatchAndAddress getMatchAndAddress() {
        return MatchAndAddress;
    }

    public void setMatchAndAddress(SportMatchAndAddress matchAndAddress) {
        MatchAndAddress = matchAndAddress;
    }

    public CreatedUserBean getCreatedUserInfo() {
        return CreatedUserInfo;
    }

    public void setCreatedUserInfo(CreatedUserBean createdUserInfo) {
        CreatedUserInfo = createdUserInfo;
    }

    public List<AppliedUserBean> getAppliedUserInfo() {
        return AppliedUserInfo;
    }

    public void setAppliedUserInfo(List<AppliedUserBean> appliedUserInfo) {
        AppliedUserInfo = appliedUserInfo;
    }
}
