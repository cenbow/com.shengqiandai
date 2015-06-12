package cn.vfunding.vfunding.biz.prize.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class PrizeChooseRule extends BaseModel {
    private Integer userId;

    private Integer chooseCount;

    private Integer chooseRealityCount;

    private Date modifyTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChooseCount() {
        return chooseCount;
    }

    public void setChooseCount(Integer chooseCount) {
        this.chooseCount = chooseCount;
    }

    public Integer getChooseRealityCount() {
        return chooseRealityCount;
    }

    public void setChooseRealityCount(Integer chooseRealityCount) {
        this.chooseRealityCount = chooseRealityCount;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}