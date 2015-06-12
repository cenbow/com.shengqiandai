package cn.vfunding.vfunding.biz.week.model;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class WeekBorrowPawnPic extends BaseModel{
    private Integer id;

    private Integer pawnId;

    private String type;

    private String pic;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
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
			} else if (pic.startsWith("/")) {
				return "http://static.vfunding.cn" + pic;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}