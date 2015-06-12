package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class Pic {
    private String pickey;

    private String url;

    private String description;

    public String getPickey() {
        return pickey;
    }

    public void setPickey(String pickey) {
        this.pickey = pickey == null ? null : pickey.trim();
    }

    public String getUrl() {
    	if (EmptyUtil.isNotEmpty(url)) {
			if (url.startsWith("/")) {
				return "http://static.vfunding.cn" + url;
			} else if (url.startsWith("http")) {
				return url;
			} else {
				return "http://file.8jielicai.com/ori/" + url;
			}
		}
		return url;
		
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}