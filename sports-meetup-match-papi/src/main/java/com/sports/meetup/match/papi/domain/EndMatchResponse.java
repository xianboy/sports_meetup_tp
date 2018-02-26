package com.sports.meetup.match.papi.domain;

import java.util.List;

public class EndMatchResponse {
    private List<MyMatchBean> OldMatchesByCreatorId;
    private List<MyMatchBean> MyOldMatches;

    public List<MyMatchBean> getOldMatchesByCreatorId() {
        return OldMatchesByCreatorId;
    }

    public void setOldMatchesByCreatorId(List<MyMatchBean> oldMatchesByCreatorId) {
        OldMatchesByCreatorId = oldMatchesByCreatorId;
    }

    public List<MyMatchBean> getMyOldMatches() {
        return MyOldMatches;
    }

    public void setMyOldMatches(List<MyMatchBean> myOldMatches) {
        MyOldMatches = myOldMatches;
    }
}
