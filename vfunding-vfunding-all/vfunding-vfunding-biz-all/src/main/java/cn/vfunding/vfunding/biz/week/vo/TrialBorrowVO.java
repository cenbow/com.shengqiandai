package cn.vfunding.vfunding.biz.week.vo;

import java.util.ArrayList;
import java.util.List;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;

@SuppressWarnings("serial")
public class TrialBorrowVO extends BaseVO{
	private List<WeekBorrow> wbs = new ArrayList<WeekBorrow>();

	public List<WeekBorrow> getWbs() {
		return wbs;
	}

	public void setWbs(List<WeekBorrow> wbs) {
		this.wbs = wbs;
	}
	
	
}
