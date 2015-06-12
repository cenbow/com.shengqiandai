package cn.vfunding.common.plat.sender.model;

import java.util.Date;

public class RealnameRecord {
	
	public RealnameRecord(){
		
	}
	
	public RealnameRecord(String name,String cardId){
		this.realName=name;
		this.cardId=cardId;
	}
    private Integer id;

    private String realName;

    private String cardId;

    private Date addTime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}