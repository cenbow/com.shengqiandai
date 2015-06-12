package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Message extends BaseModel  {
	private Integer id;

	private Integer sentUser;

	private Integer receiveUser;

	private String name;

	private Integer status;

	private String type;

	private String sented;

	private Integer deltype;

	private String addtime;

	private String addip;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSentUser() {
		return sentUser;
	}

	public void setSentUser(Integer sentUser) {
		this.sentUser = sentUser;
	}

	public Integer getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(Integer receiveUser) {
		this.receiveUser = receiveUser;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getSented() {
		return sented;
	}

	public void setSented(String sented) {
		this.sented = sented == null ? null : sented.trim();
	}

	public Integer getDeltype() {
		return deltype;
	}

	public void setDeltype(Integer deltype) {
		this.deltype = deltype;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime == null ? null : addtime.trim();
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getAddtimeStr() {
		if (EmptyUtil.isNotEmpty(this.addtime)) {
			return DateUtil.getStringSortDateByLongDate(new Long(this.addtime));
		}
		return null;

	}

	public String getShortName() {
		if (EmptyUtil.isNotEmpty(this.name)) {
			return StringUtil.intercept(this.name, 35);
		}
		return null;

	}

}