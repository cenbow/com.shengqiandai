package cn.vfunding.vfunding.biz.user.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;
/**
 * 通过网站联盟用户的广告信息带来的注册用户与联盟用户和广告信息关系的记录表
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class UserFromUnion extends BaseModel  {
	/**
	 * 微积金用户ID
	 */
    private Integer userId;
    /**
     * 冗余微积金用户的用户名信息
     */
    private String userName;

    /**
	 * 对应的网站联盟中用户ID（表示该微积金用户是由该网站联盟用户介绍而来）
	 */
    private String unionUserId;
    /**
	 * 网站联盟用户的广告产品ID
	 */
    private String productId;

    /**
	 * 返利的期数
	 */
    private Integer periods;

    /**
	 * 返利状态
	 */
    private String status;

    /**
	 * 注册时间
	 */
    private Date insertTime;

    /**
	 * 更新期数的时间
	 */
    private Date updatePeriodsTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUnionUserId() {
        return unionUserId;
    }

    public void setUnionUserId(String unionUserId) {
        this.unionUserId = unionUserId == null ? null : unionUserId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdatePeriodsTime() {
        return updatePeriodsTime;
    }

    public void setUpdatePeriodsTime(Date updatePeriodsTime) {
        this.updatePeriodsTime = updatePeriodsTime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}