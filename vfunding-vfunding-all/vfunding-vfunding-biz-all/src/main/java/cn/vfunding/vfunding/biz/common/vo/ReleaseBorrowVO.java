package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class ReleaseBorrowVO extends BaseVO{

	private Integer id;
	
	private BigDecimal account;
	
	private BigDecimal apr;
	
	private String BigAccount;
	
	private Integer mortgageTypeid;
	
	private Integer timeLimit;
	
	private Integer validTime;
	
	private Integer style;//还款方式
	
	private Integer danbao;
	
	private Integer isFast;
	
	private BigDecimal lowestAccount;
	
	private String pwd;
	
	private String litpic;
	
	private String use;
	
	private String ownerName;
	
	private String ownerCardid; // 车主身份证号码
	
	private String ownerMobile;
	
	private String ownerAddress; //车主籍贯
	
	private String carStrutNum; //车架号
	
	private String carLicense; //车牌号
	
	private String carNumber; //发动机号
	
	private String name;
	
	private String content;
	
	private Integer openAccount;
	
	private Integer openBorrow;
	
	private Integer openTender;
	
	private Integer openCredit;
	
	private Integer mortgageId;
	
	private Integer mortgageTypeId;
	
	private Integer handleType;
	
	private Date contractStart;
	
	private Date contractEnd;
	
	
	public Integer getHandleType() {
		return handleType;
	}
	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public Integer getMortgageTypeId() {
		return mortgageTypeId;
	}

	public void setMortgageTypeId(Integer mortgageTypeId) {
		this.mortgageTypeId = mortgageTypeId;
	}

	public Integer getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(Integer mortgageId) {
		this.mortgageId = mortgageId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public String getBigAccount() {
		return BigAccount;
	}

	public void setBigAccount(String bigAccount) {
		BigAccount = bigAccount;
	}

	public Integer getMortgageTypeid() {
		return mortgageTypeid;
	}

	public void setMortgageTypeid(Integer mortgageTypeid) {
		this.mortgageTypeid = mortgageTypeid;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getDanbao() {
		return danbao;
	}

	public void setDanbao(Integer danbao) {
		this.danbao = danbao;
	}

	public Integer getIsFast() {
		return isFast;
	}

	public void setIsFast(Integer isFast) {
		this.isFast = isFast;
	}

	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}
	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerCardid() {
		return ownerCardid;
	}
	public void setOwnerCardid(String ownerCardid) {
		this.ownerCardid = ownerCardid;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getCarStrutNum() {
		return carStrutNum;
	}
	public void setCarStrutNum(String carStrutNum) {
		this.carStrutNum = carStrutNum;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getOpenAccount() {
		return openAccount;
	}
	public void setOpenAccount(Integer openAccount) {
		this.openAccount = openAccount;
	}
	public Integer getOpenBorrow() {
		return openBorrow;
	}
	public void setOpenBorrow(Integer openBorrow) {
		this.openBorrow = openBorrow;
	}
	public Integer getOpenTender() {
		return openTender;
	}
	public void setOpenTender(Integer openTender) {
		this.openTender = openTender;
	}
	public Integer getOpenCredit() {
		return openCredit;
	}
	public void setOpenCredit(Integer openCredit) {
		this.openCredit = openCredit;
	}
	public Date getContractStart() {
		return contractStart;
	}
	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}
	public Date getContractEnd() {
		return contractEnd;
	}
	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}
	
}
