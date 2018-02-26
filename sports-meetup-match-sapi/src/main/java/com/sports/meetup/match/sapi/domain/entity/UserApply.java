package com.sports.meetup.match.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Component
public class UserApply {
    @Id
    @GeneratedValue
    private Long applyId;

    private Long matchId;

    private Long userId;

    private Timestamp applyTime;

    private String applyResult;

    private String remark;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyResult() {
        return applyResult;
    }

    public void setApplyResult(String applyResult) {
        this.applyResult = applyResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserApply{" +
                "applyId=" + applyId +
                ", matchId=" + matchId +
                ", userId=" + userId +
                ", applyTime=" + applyTime +
                ", applyResult='" + applyResult + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
