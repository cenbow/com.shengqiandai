package cn.vfunding.vfunding.common.module.activemq.message;

import java.util.Date;
/**
 * 投资者投资时产生的消息封装对象
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class InvestMessage extends BaseMessage {

	public InvestMessage(){
		super();
	}
	/**
	 * 投资者ID
	 */
	private Integer userId;
	/**
	 * 投资者用户名
	 */
	private String userName;
	/**
	 * 投资时间
	 */
	private Date investTime;
	/**
	 * 投资金额
	 */
	private Float money;
	
	/**
	 * 返利期数
	 */
	private Integer periods;
	
	/**
	 * 广告ID
	 */
	private String productId;
	
	/**
	 * 网站联盟用户ID
	 */
	private String unionUserId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUnionUserId() {
		return unionUserId;
	}

	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}
	
	
	
}
