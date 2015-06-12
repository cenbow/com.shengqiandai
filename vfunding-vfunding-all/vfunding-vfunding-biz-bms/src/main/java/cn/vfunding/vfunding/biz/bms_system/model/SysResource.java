package cn.vfunding.vfunding.biz.bms_system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class SysResource extends BaseModel{
    private String id;

    private String icon;

    private String name;

    private String remark;

    private Integer seq;

    private String url;

    private String pid;

    private Integer resourcetypeId;

    private Integer groupId;
    

	private SysResourceType resourceType;

	private SysResourceGroup resourceGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        this.url = url == null ? null : url.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getResourcetypeId() {
        return resourcetypeId;
    }

    public void setResourcetypeId(Integer resourcetypeId) {
        this.resourcetypeId = resourcetypeId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

	public SysResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(SysResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public SysResourceGroup getResourceGroup() {
		return resourceGroup;
	}

	public void setResourceGroup(SysResourceGroup resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
    
    
}