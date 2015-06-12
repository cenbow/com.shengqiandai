package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

/**
 * 文章
 * 
 * @author liuhuan
 * 
 */
@SuppressWarnings("serial")
public class Article extends BaseModel {
	private Integer id;
	private Integer userId;
	private Integer siteId;
	private String name;
	private String littitle;
	private Integer status;
	private String litpic;
	private String flag;
	private String source;
	private String publish;
	private String isJump;
	private String author;
	private String jumpurl;
	private String summary;
	private Integer province;
	private Integer city;
	private Integer area;
	private String content;
	private Integer order;
	private Integer hits;
	private Integer comment;
	private String isComment;
	private String addtime;
	private String addip;

	private String seoTitle;
	private String seoKeywords;
	private String seoDescription;

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLittitle() {
		return littitle;
	}

	public void setLittitle(String littitle) {
		this.littitle = littitle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getIsJump() {
		return isJump;
	}

	public void setIsJump(String isJump) {
		this.isJump = isJump;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getSortContent() {
		if (EmptyUtil.isNotEmpty(content) && StringUtil.isLen(this.content, 10)) {
			return StringUtil.intercept(this.content, 7);
		} else {
			return this.content;
		}

	}

	public String getSortName() {
		if (StringUtil.isLen(this.name, 10)) {
			return StringUtil.intercept(this.name, 7);
		} else {
			return this.name;
		}

	}

	public String getSortSummary() {
		if (StringUtil.isLen(this.summary, 30)) {
			return StringUtil.intercept(this.summary, 29);
		} else {
			return this.summary;
		}

	}

	public String getLitpicPath() {
		if (EmptyUtil.isNotEmpty(litpic)) {
			if (litpic.startsWith("data/")) {
				return "http://www.8jielicai.com/" + litpic;
			} else {
				return "http://file.8jielicai.com/ori/" + litpic;
			}
		} else {
			return litpic;
		}
	}

	public String getUrlPath() {
		return "http://mobile.8jielicai.com/article/detail/" + id;
	}
}
