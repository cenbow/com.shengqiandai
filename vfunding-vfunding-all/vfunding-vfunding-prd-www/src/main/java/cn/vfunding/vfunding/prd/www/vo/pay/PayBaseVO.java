package cn.vfunding.vfunding.prd.www.vo.pay;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class PayBaseVO extends BaseVO {
	protected String rechargeMoney;
	
	private String rechargeBankType;//信用卡、储值卡标记
	
	private String rechargeBankCode;//银行编号

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	

	public String getRechargeBankType() {
		return rechargeBankType;
	}

	public void setRechargeBankType(String rechargeBankType) {
		this.rechargeBankType = rechargeBankType;
	}

	public String getRechargeBankCode() {
		return rechargeBankCode;
	}

	public void setRechargeBankCode(String rechargeBankCode) {
		this.rechargeBankCode = rechargeBankCode;
	}
	
	

}
