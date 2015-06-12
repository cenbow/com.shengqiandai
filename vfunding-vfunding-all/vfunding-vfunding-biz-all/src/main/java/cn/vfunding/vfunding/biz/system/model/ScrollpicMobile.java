package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class ScrollpicMobile  extends BaseModel {

	private String url;

	private String pic;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPic() {
		if (EmptyUtil.isNotEmpty(pic)) {
			if (pic.startsWith("/")) {
				return "http://static.vfunding.cn" + pic;
			} else if (pic.startsWith("http")) {
				return pic;
			} else {
				return "http://file.8jielicai.com/ori/" + pic;
			}
			/*if (pic.startsWith("data/")) {
				return "http://www.vfunding.cn/" + pic;
			} else {
				return "http://file.vfunding.cn/" + pic;
			}*/
		}else{
			return pic;
		}
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

}