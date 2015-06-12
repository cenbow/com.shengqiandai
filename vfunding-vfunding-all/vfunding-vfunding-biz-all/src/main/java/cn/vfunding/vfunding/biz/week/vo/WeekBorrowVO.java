package cn.vfunding.vfunding.biz.week.vo;

import java.util.List;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;

@SuppressWarnings("serial")
public class WeekBorrowVO extends BaseVO{

	private WeekBorrow wb;
	
	private WeekBorrowPawn wbp;
	
	private List<WeekBorrowPawnPic> listWbpc;

	public WeekBorrow getWb() {
		return wb;
	}

	public void setWb(WeekBorrow wb) {
		this.wb = wb;
	}

	public WeekBorrowPawn getWbp() {
		return wbp;
	}

	public void setWbp(WeekBorrowPawn wbp) {
		this.wbp = wbp;
	}

	public List<WeekBorrowPawnPic> getListWbpc() {
		return listWbpc;
	}

	public void setListWbpc(List<WeekBorrowPawnPic> listWbpc) {
		this.listWbpc = listWbpc;
	}
}
