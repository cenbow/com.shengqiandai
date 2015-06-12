package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class AccountRechargeVO extends BaseVO {
	
	private Integer userId;//用户id
	
	private Integer rechargeId;//充值id
	
	private String tradeNo;
	
	private String userName;
	
	private String realName;
	
	private Integer type;
	
	private String bank;//银行
	
	private String fee;
	
	private BigDecimal money;//充值金额
	
	private BigDecimal account;//到账金额
	
	private Integer status;//提现状态 1:成功；2失败
	
	private String remark;//审核备注
	
	private Integer hongbao;
	
	private String addtime;
	
	private String verifyTime;
	
	private String ip;
	
	private String reward;


	public String getVerifyTime() {
		return verifyTime;
	}
	
	public String getVerifyTimeStr() {
		if(EmptyUtil.isNotEmpty(verifyTime)&&!"0".equals(verifyTime)){
			return DateUtil.getStringDateByLongDate(Long.parseLong(verifyTime),DateUtil.DATETIMESHOWFORMAT);
		} else {
			return "-";
		}
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getHongbao() {
		return hongbao;
	}

	public void setHongbao(Integer hongbao) {
		this.hongbao = hongbao;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
}
