package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Flag extends  BaseModel{
    private Integer id;

    private String name;

    private String nid;

    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}