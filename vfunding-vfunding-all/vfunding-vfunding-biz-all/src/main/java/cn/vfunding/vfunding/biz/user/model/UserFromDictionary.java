package cn.vfunding.vfunding.biz.user.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserFromDictionary extends BaseModel  {
    private Integer id;

    private String fromName;

    private String fromKey;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey == null ? null : fromKey.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}