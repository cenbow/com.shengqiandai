package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Attestation extends  BaseModel{
	private Integer id;

	private Integer userId;

	private Short typeId;

	private String name;

	private Byte status = 0;

	private String litpic;

	private String content;

	private Integer jifen = 0;

	private String pic2;

	private String pic3;

	private Integer verifyTime;

	private Integer verifyUser;

	private String verifyRemark;

	private Integer addtime;

	private String addip;

	private String pic;

	private AttestationType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}
	
	public String getSortName() {
		return StringUtil.intercept(name, 6);
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic == null ? null : litpic.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getJifen() {
		return jifen;
	}

	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2 == null ? null : pic2.trim();
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3 == null ? null : pic3.trim();
	}

	public Integer getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Integer verifyTime) {
		this.verifyTime = verifyTime;
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

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public AttestationType getType() {
		return type;
	}

	public void setType(AttestationType type) {
		this.type = type;
	}

	public String getTypeName() {
		if (EmptyUtil.isNotEmpty(type)) {
			return StringUtil.intercept(type.getName(), 6);
		}
		return null;
	}

	public String getAddtimeStr() {
		if (EmptyUtil.isNotEmpty(addtime) && addtime > 0) {
			return DateUtil.getStringSortDateByLongDate(new Long(addtime));
		} else {
			return "暂无信息";
		}
	}

	public String getVerifyTimeStr() {
		if (EmptyUtil.isNotEmpty(verifyTime) && verifyTime > 0) {
			return DateUtil.getStringSortDateByLongDate(new Long(verifyTime));
		} else {
			return "暂未审核";
		}
	}

}