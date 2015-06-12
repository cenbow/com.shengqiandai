package cn.vfunding.common.framework.easyui.page.utils;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class Resource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4052671867242004877L;

	private String id;

	private String icon;

	private String name;

	private String remark;

	private Integer seq;

	private String url;

	private String pid;
	private String typeId;
	private String typeName;

	private String pname;

	private String iconCls;
	private Integer groupId;
	private String groupName;

	private Integer visiable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
		this.icon = iconCls;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		this.iconCls = icon;
	}

	public Integer getVisiable() {
		return visiable;
	}

	public void setVisiable(Integer visiable) {
		this.visiable = visiable;
	}

	public String getVisToStr() {
		if (EmptyUtil.isNotEmpty(this.visiable)) {
			if (this.visiable == 1) {
				return "是";
			} else {
				return "否";
			}

		} else {
			return null;
		}

	}
}
