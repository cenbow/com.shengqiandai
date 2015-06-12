package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class FeelVO extends BaseVO{
	
	private String no;
	
	private Integer userId;
	
	private String username;
	
	private String bindingStatus;//绑定状态
	
	private Integer useStatus;//激活状态
	
	private Integer addtime;//激活时间
	
	private Integer bid;//借款编号
	
	private String bname;//借款标题
	
	private Integer limitDay;//期限
	
	private String repayTime;//还款时间
	
	private BigDecimal account;//借款金额
	
	private BigDecimal interest;//应还利息
	
	private Integer repayStatus; //还款状态
	
	private Integer currentPage;//当前页
	
	private Integer pageSize;//每页条数
	
	private Integer count;
	
	private Integer type;//查询类型：理财师1、虚拟组2
	private Integer startNo;
	private Integer endNo;
	
	private String startTime;//激活开始时间
	private String endTime;//激活结束时间

	private String firstRecharge;//第一次充值时间
	private String tenders;//投资人
	
	private String groupname;
	
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getFirstRecharge() {
		return firstRecharge;
	}
	public String getFirstRechargeStr() {
		if(EmptyUtil.isNotEmpty(firstRecharge)){
			return DateUtil.getStringDateByLongDate(Long.parseLong(firstRecharge), DateUtil.DATETIMESHOWFORMAT);
		} else {
			return "-";
		}
	}
	public void setFirstRecharge(String firstRecharge) {
		this.firstRecharge = firstRecharge;
	}
	public String getTenders() {
		return tenders;
	}
	public void setTenders(String tenders) {
		this.tenders = tenders;
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
	public Integer getStartNo() {
		return startNo;
	}
	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
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
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getBindingStatus() {
		return bindingStatus;
	}
	public void setBindingStatus(String bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getAddtime() {
		return addtime;
	}
	
	public String getAddtimeStr(){
		if(EmptyUtil.isNotEmpty(addtime)){
			return DateUtil.getStringDateByLongDate(addtime.longValue(), DateUtil.DATETIMESHOWFORMAT);
		} else {
			return "-";
		}
	}
	
	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(Integer limitDay) {
		this.limitDay = limitDay;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Integer getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(Integer repayStatus) {
		this.repayStatus = repayStatus;
	}
}
