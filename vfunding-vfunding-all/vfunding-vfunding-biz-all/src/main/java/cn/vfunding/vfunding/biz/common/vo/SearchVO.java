package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;


public class SearchVO extends BaseVO{
	private Integer id;
	
	private Integer userId;
	
	private String username;
	
	private Integer status;
	
	private Integer type;
	
	private String startTime;
	
	private String endTime;
	
	private String keyWord;
	
	private String biaoType;
	
	private Integer cashId;
	
	private Integer rechargeId;
	private String tradeNo;
	
	private String bank;
	
	private String email;
	
	private String phone;

	private String sourceName;
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Integer getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

	public Integer getCashId() {
		return cashId;
	}

	public void setCashId(Integer cashId) {
		this.cashId = cashId;
	}

	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 转换时间戳：传入：2014-01-01 return:1388505600
	 * @param startTime
	 * @author liuhuan
	 */
	/*public void setStartTime(String startTime) {
		if(startTime == null || "".equals(startTime)){
			this.startTime = null;
		} else {
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.startTime = (int) (date.getTime()/1000);
		}
	}*/

	/**
	 * 转换时间戳：传入：2014-01-01 return:1388505600
	 * @param endTime
	 * @author liuhuan
	 */
	/*public void setEndTime(String endTime) {
		if(endTime == null || "".equals(endTime)){
			this.endTime = null;
		} else {
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.endTime = (int) (date.getTime()/1000);
		}
	}*/

	public String getKeyWord() {
		return keyWord;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
