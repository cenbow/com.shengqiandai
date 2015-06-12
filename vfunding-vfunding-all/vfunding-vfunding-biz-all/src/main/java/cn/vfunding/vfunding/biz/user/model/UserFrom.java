package cn.vfunding.vfunding.biz.user.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserFrom extends BaseModel  {
	private Integer id;

	private Integer userId;

	private String fromUrl;

	private String fromName;

	private Date addtime;
	
	private Integer type;

	private Integer regUser;

	private Integer tenderUser;

	private Integer feelTenderUser;

	private BigDecimal sumTender;

	private BigDecimal sumCollection;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFeelTenderUser() {
		return feelTenderUser;
	}

	public void setFeelTenderUser(Integer feelTenderUser) {
		this.feelTenderUser = feelTenderUser;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl == null ? null : fromUrl.trim();
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName == null ? null : fromName.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getRegUser() {
		return regUser;
	}

	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}

	public Integer getTenderUser() {
		return tenderUser;
	}

	public void setTenderUser(Integer tenderUser) {
		this.tenderUser = tenderUser;
	}

	public BigDecimal getSumTender() {
		return sumTender;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setSumTender(BigDecimal sumTender) {
		this.sumTender = sumTender;
	}

	public BigDecimal getSumCollection() {
		return sumCollection;
	}

	public void setSumCollection(BigDecimal sumCollection) {
		this.sumCollection = sumCollection;
	}

}