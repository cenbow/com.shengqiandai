package cn.vfunding.vfunding.biz.report.model;

public class FinanceReportData {
	
	private transient final static Double DefaultDoubleValue=0D;

	private String userName;

	private Double sumCashFees;

	private Double cashFees;

	private Double sumHongbao;

	//平台担保字段
	private Integer typeId;       //用户类型  (27 or 31集团用户  其他:普通用户)
	private Double rateFee;       //平台服务费率
	private Double serviceFees;	  //平台服务费
	private Double guaranteeFees; //担保服务费
	private String borrowName;    //标的名称
	private String repayTime;     //预计回款时间
	private String repayYestime;  //实际回款时间
	private Integer timeLimit;    //标的期限
	
	private Double sumServiceFees;//平台服务费总计
	private Double sumGuaranteeFees;//担保服务费总计
	private Double sumWaitServiceFees;//待收平台服务费总计
	private Double sumWaitGuaranteeFees;//待收担保服务费总计
	

	//用户提现抵扣券
	private Double hongbao;    //体现抵扣券
	
	
	/**
	 * vip金额
	 */
	private String vipRemark;

	private Double vipMoney;

	private Double sumVipMoney;
	
	//用户现金红包
	private Double unusedRedEnvelope; 	// 未使用现金红包  or 未使用红包总计
	private Double usedRedEnvelope;		// 已使用现金红包  or 已使用红包总计
	private Double expiredRedEnvelope;	// 过期的现金红包 or 已过期红包总计
	private Double sumUsedRedEnvelope;  // 已使用红包金额总计

	// 添加时间
	private String addTime;
	
	//查询条件
	private String startTime;    // 起始时间
	private String endTime;      // 截至时间
	
	private String repayYestimeStart;   //回款时间
	private String repayYestimeEnd;     //回款时间
	
	

	public Double getHongbao() {
		if(hongbao==null){
			hongbao=0d;
		}
		return hongbao;
	}

	public void setHongbao(Double hongbao) {
		this.hongbao = hongbao;
	}

	public Double getSumVipMoney() {
		if (sumVipMoney == null) {
			sumVipMoney = 0d;
		}
		return sumVipMoney;
	}

	public void setSumVipMoney(Double sumVipMoney) {
		this.sumVipMoney = sumVipMoney;
	}

	public String getUserName() {
		return userName;
	}

	public Double getSumHongbao() {
		return sumHongbao;
	}

	public void setSumHongbao(Double sumHongbao) {
		this.sumHongbao = sumHongbao;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getServiceFees() {
		if (serviceFees == null) {
			serviceFees = 0d;
		}
		return serviceFees;
	}

	public Double getSumCashFees() {
		if (sumCashFees == null) {
			sumCashFees = 0d;
		}
		return sumCashFees;
	}

	public void setSumCashFees(Double sumCashFees) {
		this.sumCashFees = sumCashFees;
	}

	public Double getSumServiceFees() {
		if (sumServiceFees == null) {
			sumServiceFees = 0d;
		}
		return sumServiceFees;
	}

	public void setSumServiceFees(Double sumServiceFees) {
		this.sumServiceFees = sumServiceFees;
	}

	public Double getSumGuaranteeFees() {
		if (sumGuaranteeFees == null) {
			sumGuaranteeFees = 0d;
		}
		return sumGuaranteeFees;
	}

	public void setSumGuaranteeFees(Double sumGuaranteeFees) {
		this.sumGuaranteeFees = sumGuaranteeFees;
	}

	public void setServiceFees(Double serviceFees) {
		this.serviceFees = serviceFees;
	}

	public Double getCashFees() {
		if (cashFees == null) {
			cashFees = 0d;
		}
		return cashFees;
	}

	public void setCashFees(Double cashFees) {
		this.cashFees = cashFees;
	}

	public Double getGuaranteeFees() {
		if (guaranteeFees == null) {
			guaranteeFees = 0d;
		}
		return guaranteeFees;
	}

	public void setGuaranteeFees(Double guaranteeFees) {
		this.guaranteeFees = guaranteeFees;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Double getRateFee() {
		return rateFee;
	}

	public void setRateFee(Double rateFee) {
		this.rateFee = rateFee;
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

	public String getVipRemark() {
		return vipRemark;
	}

	public void setVipRemark(String vipRemark) {
		this.vipRemark = vipRemark;
	}

	public Double getVipMoney() {
		if (vipMoney == null) {
			vipMoney = 0d;
		}
		return vipMoney;
	}

	public void setVipMoney(Double vipMoney) {
		this.vipMoney = vipMoney;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Double getUnusedRedEnvelope() {
		if(unusedRedEnvelope == null){
			return DefaultDoubleValue;
		}
		return unusedRedEnvelope;
	}

	public void setUnusedRedEnvelope(Double unusedRedEnvelope) {
		this.unusedRedEnvelope = unusedRedEnvelope;
	}

	public Double getUsedRedEnvelope() {
		if(usedRedEnvelope == null){
			return DefaultDoubleValue;
		}
		return usedRedEnvelope;
	}

	public void setUsedRedEnvelope(Double usedRedEnvelope) {
		this.usedRedEnvelope = usedRedEnvelope;
	}

	public Double getExpiredRedEnvelope() {
		if(expiredRedEnvelope == null){
			return DefaultDoubleValue;
		}
		return expiredRedEnvelope;
	}

	public void setExpiredRedEnvelope(Double expiredRedEnvelope) {
		this.expiredRedEnvelope = expiredRedEnvelope;
	}

	public Double getSumUsedRedEnvelope() {
		if(sumUsedRedEnvelope == null){
			return DefaultDoubleValue;
		}
		return sumUsedRedEnvelope;
	}

	public void setSumUsedRedEnvelope(Double sumUsedRedEnvelope) {
		this.sumUsedRedEnvelope = sumUsedRedEnvelope;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public String getRepayYestime() {
		return repayYestime;
	}

	public void setRepayYestime(String repayYestime) {
		this.repayYestime = repayYestime;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getRepayYestimeStart() {
		return repayYestimeStart;
	}

	public void setRepayYestimeStart(String repayYestimeStart) {
		this.repayYestimeStart = repayYestimeStart;
	}

	public String getRepayYestimeEnd() {
		return repayYestimeEnd;
	}

	public void setRepayYestimeEnd(String repayYestimeEnd) {
		this.repayYestimeEnd = repayYestimeEnd;
	}

	public Double getSumWaitServiceFees() {
		return sumWaitServiceFees;
	}

	public void setSumWaitServiceFees(Double sumWaitServiceFees) {
		this.sumWaitServiceFees = sumWaitServiceFees;
	}

	public Double getSumWaitGuaranteeFees() {
		return sumWaitGuaranteeFees;
	}

	public void setSumWaitGuaranteeFees(Double sumWaitGuaranteeFees) {
		this.sumWaitGuaranteeFees = sumWaitGuaranteeFees;
	}
	
	
	

}
