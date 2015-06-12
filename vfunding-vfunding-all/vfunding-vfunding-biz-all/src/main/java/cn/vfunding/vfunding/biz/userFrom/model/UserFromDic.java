package cn.vfunding.vfunding.biz.userFrom.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserFromDic extends BaseModel{
    private Integer id;

    private String fromNo;

    private String fromName;

    private String toUrl;
    private String weiToUrl;
    private String keywords;
    
    public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getWeiToUrl() {
		return weiToUrl;
	}

	public void setWeiToUrl(String weiToUrl) {
		this.weiToUrl = weiToUrl;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromNo() {
        return fromNo;
    }

    public void setFromNo(String fromNo) {
        this.fromNo = fromNo == null ? null : fromNo.trim();
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl == null ? null : toUrl.trim();
    }
}