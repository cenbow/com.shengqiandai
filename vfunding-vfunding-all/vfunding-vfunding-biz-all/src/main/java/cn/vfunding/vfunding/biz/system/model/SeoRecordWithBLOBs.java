package cn.vfunding.vfunding.biz.system.model;


@SuppressWarnings("serial")
public class SeoRecordWithBLOBs extends SeoRecord {
    private String description;

    private String keywords;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }
}