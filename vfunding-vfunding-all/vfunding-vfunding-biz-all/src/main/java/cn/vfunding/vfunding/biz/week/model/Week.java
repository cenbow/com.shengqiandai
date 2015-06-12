package cn.vfunding.vfunding.biz.week.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Week extends BaseModel{
	private Integer id;

	private String name;

	private Integer status;

	private Integer saveStatus;

	private Integer timeLimit;

	private BigDecimal apr;

	private Integer singleMin;

	private Integer singleMax;

	private Integer buyBase;

	private BigDecimal sharePrice;

	private Integer shareCount;

	private Integer shareYescount;

	private BigDecimal planMoney;

	private BigDecimal realityMoney;

	private Integer holderUser;

	private Integer createUser;

	private Integer verifyUser;

	private String verifyRemark;

	private Date createTime;

	private Date verifyTime;

	private Date saleTime;

	private Date expireTime;

	private Date publishTime;

	private List<WeekBorrow> weekBorrowList;
    
	private WeekRate wr;
	//week是否已偿还
	private boolean weekIsRepay;

	

	public WeekRate getWr() {
		return wr;
	}

	public void setWr(WeekRate wr) {
		this.wr = wr;
	}

	public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(Integer saveStatus) {
		this.saveStatus = saveStatus;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getSingleMin() {
		return singleMin;
	}

	public void setSingleMin(Integer singleMin) {
		this.singleMin = singleMin;
	}

	public Integer getSingleMax() {
		return singleMax;
	}

	public void setSingleMax(Integer singleMax) {
		this.singleMax = singleMax;
	}

	public Integer getBuyBase() {
		return buyBase;
	}

	public void setBuyBase(Integer buyBase) {
		this.buyBase = buyBase;
	}

	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getShareYescount() {
		return shareYescount;
	}

	public void setShareYescount(Integer shareYescount) {
		this.shareYescount = shareYescount;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public BigDecimal getRealityMoney() {
		return realityMoney;
	}

	public void setRealityMoney(BigDecimal realityMoney) {
		this.realityMoney = realityMoney;
	}

	public Integer getHolderUser() {
		return holderUser;
	}

	public void setHolderUser(Integer holderUser) {
		this.holderUser = holderUser;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(Integer verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark == null ? null : verifyRemark.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
    public List<WeekBorrow> getWeekBorrowList() {
		return weekBorrowList;
	}

	public void setWeekBorrowList(List<WeekBorrow> weekBorrowList) {
		this.weekBorrowList = weekBorrowList;
	}
	
	public boolean isWeekIsRepay() {
		return weekIsRepay;
	}

	public void setWeekIsRepay(boolean weekIsRepay) {
		this.weekIsRepay = weekIsRepay;
	}

	//获取百分比和百分比图片id
	public String getCompletePercent() {
		BigDecimal _shareCount = null;
		BigDecimal _shareYesCount = null;
		if(EmptyUtil.isEmpty(this.getShareCount())){
			_shareCount = new BigDecimal(0);
		}else{
			_shareCount = new BigDecimal(this.getShareCount());
		}
		if(EmptyUtil.isEmpty(this.getShareYescount())){
			_shareYesCount = new BigDecimal(0);
		}else{
			_shareYesCount = new BigDecimal(this.getShareYescount());
		}		
		if (_shareYesCount != null && _shareCount != null) {
			BigDecimal a = _shareYesCount.divide(_shareCount, 20, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100))
					.setScale(2, BigDecimal.ROUND_DOWN);
			return a.toString() + "%";
		}
		return null;
	}
	
	public Integer getCompleteInteger() {

		Integer result = 0;
		String completeString = this.getCompletePercent();
		if (completeString != null) {
			completeString = completeString.substring(0, completeString.indexOf("."));
			Integer complete = new Integer(completeString);
			if (complete == 0) {
				result = 0;
			} else if (complete >= 1 && complete <= 5) {
				result = 5;
			} else if (complete >= 6 && complete <= 10) {
				result = 10;
			} else if (complete >= 11 && complete <= 15) {
				result = 15;
			} else if (complete >= 16 && complete <= 20) {
				result = 20;
			} else if (complete >= 21 && complete <= 25) {
				result = 25;
			} else if (complete >= 26 && complete <= 30) {
				result = 30;
			} else if (complete >= 31 && complete <= 35) {
				result = 35;
			} else if (complete >= 36 && complete <= 40) {
				result = 40;
			} else if (complete >= 41 && complete <= 45) {
				result = 45;
			} else if (complete >= 46 && complete <= 50) {
				result = 50;
			} else if (complete >= 51 && complete <= 55) {
				result = 55;
			} else if (complete >= 56 && complete <= 60) {
				result = 60;
			} else if (complete >= 61 && complete <= 65) {
				result = 65;
			} else if (complete >= 66 && complete <= 70) {
				result = 70;
			} else if (complete >= 71 && complete <= 75) {
				result = 75;
			} else if (complete >= 76 && complete <= 80) {
				result = 80;
			} else if (complete >= 81 && complete <= 85) {
				result = 85;
			} else if (complete >= 86 && complete <= 90) {
				result = 90;
			} else if (complete >= 91 && complete <= 95) {
				result = 95;
			} else if (complete >= 96 && complete <= 100) {
				result = 100;
			}
		}
		return result;
	}
	
	public String getRealityCount(){
		BigDecimal _realityMoney = null;
		if(EmptyUtil.isEmpty(this.getRealityMoney())){
			_realityMoney = new BigDecimal(0);
		}else{
			_realityMoney = this.getRealityMoney();
		}
		return _realityMoney.divide(this.getSharePrice()).toString();
	}
	
	public boolean getPayNaN(){
		Date date = new Date();
		Long _dateTimeStart = date.getTime() - this.getSaleTime().getTime();
		Long _dateTimeEnd = date.getTime() - this.getExpireTime().getTime();
		if(_dateTimeStart >= 0 && _dateTimeEnd < 0){
			return true;
		}
		return false;
	}
	
	public boolean getEndNaN(){
		Date date = new Date();
		Long _dateTimeEnd = date.getTime() - this.getExpireTime().getTime();
		if(_dateTimeEnd >= 0){
			return true;
		}
		return false;
	}
	
}