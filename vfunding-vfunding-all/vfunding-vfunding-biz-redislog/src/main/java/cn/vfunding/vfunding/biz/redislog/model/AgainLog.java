package cn.vfunding.vfunding.biz.redislog.model;

import java.io.Serializable;
import java.util.Date;

public class AgainLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1341082921114670748L;

	public AgainLog(){
		super();
	}
	
	public AgainLog(String actionName,String category,String arg,String url){
		super();
		this.actionName=actionName;
		this.category=category;
		this.arg=arg;
		this.url=url;
	}
	private String id;

	private String actionName;

	private String category;

	private String arg;

	private String url;

	private Integer stuts = 0;

	private Integer count = 0;
	
	
	
	private String requestMethod="post";
	
	private Date nextTime;
	
	
	private boolean locked;
	

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName == null ? null : actionName.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg == null ? null : arg.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getStuts() {
		return stuts;
	}

	public void setStuts(Integer stuts) {
		this.stuts = stuts;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}