package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

@SuppressWarnings("serial")
public class Scrollpic extends ScrollpicMobile {
	private Short id;

	private Short siteId;

	private Short status;

	private Short order;

	private Short flag;

	private Short typeId;

	private String name;

	private String summary;

	private Integer hits;

	private Integer addtime;

	private String addip;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Short getSiteId() {
		return siteId;
	}

	public void setSiteId(Short siteId) {
		this.siteId = siteId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getOrder() {
		return order;
	}

	public void setOrder(Short order) {
		this.order = order;
	}

	public Short getFlag() {
		return flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
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

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getAddtime() {
		return addtime;
	}
	
	public String getAddtimeStr() {
		if (EmptyUtil.isNotEmpty(addtime)) {
			return DateUtil.getStringDateByLongDate(Long.parseLong(addtime.toString()), DateUtil.DATETIMESHOWFORMAT);
		}
		return null;
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
}