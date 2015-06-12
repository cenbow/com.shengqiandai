package cn.vfunding.vfunding.biz.thirdplat.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class JjCard extends BaseModel  {
	
	public transient final static int DAY_RACE_UNSEND = 11;
	public transient final static int DAY_RACE_SEND = 12;
	public transient final static int WEEK_RACE_UNSEND = 21;
	public transient final static int WEEK_RACE_SEND = 22;
	public transient final static int MONTH_RACE_UNSEND = 31;
	public transient final static int MONTH_RACE_SEND = 32;
	
    private Integer id;

    private String cardNo;

    /**
     * 11 日赛未发放
     * 12 日赛已发放
     * 21 周赛未发放
     * 22 周赛已发放
     * 31 月赛未发放
     * 32 月赛已发放
     */
    private Integer status;

    private Date sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}