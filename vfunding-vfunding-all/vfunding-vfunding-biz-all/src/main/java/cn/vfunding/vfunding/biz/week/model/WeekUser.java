package cn.vfunding.vfunding.biz.week.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class WeekUser extends BaseModel{
    private Integer id;

    private Integer empUser;

    private Integer vfundingUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpUser() {
        return empUser;
    }

    public void setEmpUser(Integer empUser) {
        this.empUser = empUser;
    }

    public Integer getVfundingUser() {
        return vfundingUser;
    }

    public void setVfundingUser(Integer vfundingUser) {
        this.vfundingUser = vfundingUser;
    }
}