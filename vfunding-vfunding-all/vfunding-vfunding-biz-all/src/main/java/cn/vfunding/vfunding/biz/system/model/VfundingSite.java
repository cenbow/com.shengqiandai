package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class VfundingSite extends BaseModel  {
    private Integer siteId;

    private String code;

    private String name;

    private String nid;

    private Integer pid;

    private String rank;

    private String url;

    private String aurl;

    private String isurl;

    private Integer order;

    private Integer status;

    private String style;

    private String litpic;

    private String listName;

    private String contentName;

    private String sitedir;

    private String visitType;

    private String indexTpl;

    private String listTpl;

    private String contentTpl;

    private String title;

    private String keywords;

    private String description;

    private String userId;

    private String addtime;

    private String addip;

    private String content;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl == null ? null : aurl.trim();
    }

    public String getIsurl() {
        return isurl;
    }

    public void setIsurl(String isurl) {
        this.isurl = isurl == null ? null : isurl.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic == null ? null : litpic.trim();
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName == null ? null : contentName.trim();
    }

    public String getSitedir() {
        return sitedir;
    }

    public void setSitedir(String sitedir) {
        this.sitedir = sitedir == null ? null : sitedir.trim();
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType == null ? null : visitType.trim();
    }

    public String getIndexTpl() {
        return indexTpl;
    }

    public void setIndexTpl(String indexTpl) {
        this.indexTpl = indexTpl == null ? null : indexTpl.trim();
    }

    public String getListTpl() {
        return listTpl;
    }

    public void setListTpl(String listTpl) {
        this.listTpl = listTpl == null ? null : listTpl.trim();
    }

    public String getContentTpl() {
        return contentTpl;
    }

    public void setContentTpl(String contentTpl) {
        this.contentTpl = contentTpl == null ? null : contentTpl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}