package cn.vfunding.vfunding.biz.user.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

/**
 * 网站联盟用户和微积金用户关系及分利状态记录表对象
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class UserUnion extends BaseModel  {
	
	/**
	 * 网站联盟用户ID
	 */
    private String unionUserId;

    /**
	 * 微积金用户ID
	 */
    private Integer userId;

    /**
	 * 返利状态
	 */
    private String status;

    /**
	 * 复活次数（每个网站联盟客户有两次的复活机会）
	 */
    private Integer reviveNum;
    
    private Date lastReviveTime;

    public String getUnionUserId() {
        return unionUserId;
    }

    public void setUnionUserId(String unionUserId) {
        this.unionUserId = unionUserId == null ? null : unionUserId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getReviveNum() {
        return reviveNum;
    }

    public void setReviveNum(Integer reviveNum) {
        this.reviveNum = reviveNum;
    }

	public Date getLastReviveTime() {
		return lastReviveTime;
	}

	public void setLastReviveTime(Date lastReviveTime) {
		this.lastReviveTime = lastReviveTime;
	}
    
    
}