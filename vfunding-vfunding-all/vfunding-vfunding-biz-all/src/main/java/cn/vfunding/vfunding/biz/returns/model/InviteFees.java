package cn.vfunding.vfunding.biz.returns.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class InviteFees extends BaseModel  {
    private Integer id;

    private Integer bId;

    private Integer tenderId;
    
    private Integer collectionId;

    private Integer tuserId;

    private Integer guserId;

    private Integer suserId;

    private String tfees;

    private String gfees;

    private String sfees;

    private String addtime;

    private String addip;

    private String repaymentTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    

    public Integer getTuserId() {
		return tuserId;
	}

	public void setTuserId(Integer tuserId) {
		this.tuserId = tuserId;
	}

	public Integer getGuserId() {
		return guserId;
	}

	public void setGuserId(Integer guserId) {
		this.guserId = guserId;
	}

	public Integer getSuserId() {
		return suserId;
	}

	public void setSuserId(Integer suserId) {
		this.suserId = suserId;
	}

	public String getTfees() {
		return tfees;
	}

	public void setTfees(String tfees) {
		this.tfees = tfees;
	}

	public String getGfees() {
		return gfees;
	}

	public void setGfees(String gfees) {
		this.gfees = gfees;
	}

	public String getSfees() {
		return sfees;
	}

	public void setSfees(String sfees) {
		this.sfees = sfees;
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

    public String getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(String repaymentTime) {
        this.repaymentTime = repaymentTime == null ? null : repaymentTime.trim();
    }

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
}