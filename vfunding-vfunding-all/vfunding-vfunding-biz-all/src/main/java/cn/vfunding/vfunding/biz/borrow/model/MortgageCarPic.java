package cn.vfunding.vfunding.biz.borrow.model;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class MortgageCarPic extends BaseModel {
	private Integer id;

	private Integer carId;

	private String type;

	private String pic;

	private Date addtime;

	private Integer[] types;

	public Integer[] getTypes() {
		return types;
	}

	public void setTypes(Integer[] types) {
		this.types = types;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getPic() {
		if (EmptyUtil.isNotEmpty(pic)) {
			if (pic.startsWith("http")) {
				return pic;
			} else {
				return "http://file.8jielicai.com/ori/" + pic;
			}
		} else {
			return pic;
		}
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}