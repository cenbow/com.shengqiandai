package cn.vfunding.vfunding.biz.trial.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class GiftboxBatch extends BaseModel {
    private Integer id;

    private Integer createUser;

    private Integer templateId;

    private Integer status;

    private String title;

    private BigDecimal moneyCount;

    private Integer userCount;

    private Date addtime;

    private Integer trialUser;
    
    private String verifyRemark;
    
    private String templateName;//模板名称
    
    private String templateContent;//模板内容
    
    private String createUserName;//提交人姓名
    
    private Integer templateType;//模板类型
    
    
    public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getTrialUser() {
        return trialUser;
    }

    public void setTrialUser(Integer trialUser) {
        this.trialUser = trialUser;
    }
}