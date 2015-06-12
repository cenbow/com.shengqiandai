package cn.vfunding.vfunding.biz.report.model;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.CalendarFormatUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;


public class OperateReportdays {
	private Long zyh;

	private Long dyxzyh;

	private Long drxzyh;

	private Long dryhdls;

	private Long drdlzs;

	private Long dqczzrs;

	private Long drczrs;

	private Long drtbrs;

	private Double drczze;

	private Double dqczze;

	private Double drtbze;


	private Double dryhzye;

	private Double drfbze;

	private Long dqzbs;
	
	private Integer dqcgzbs;

	private Double dqptbze;

	private Date fromaddtime;

	private String startTime;

	private String endTime;

	@SuppressWarnings("unused")
	private String fromaddtimeStr;

	public Long getZyh() {
		if (zyh == null) {
			zyh = 0l;
		}
		return zyh;
	}

	public void setZyh(Long zyh) {
		this.zyh = zyh;
	}

	public Long getDyxzyh() {
		if (dyxzyh == null) {
			dyxzyh = 0l;
		}
		return dyxzyh;
	}

	public void setDyxzyh(Long dyxzyh) {
		this.dyxzyh = dyxzyh;
	}

	public Long getDrxzyh() {
		if (drxzyh == null) {
			drxzyh = 0l;
		}
		return drxzyh;
	}

	public void setDrxzyh(Long drxzyh) {
		this.drxzyh = drxzyh;
	}

	public Long getDryhdls() {
		if (dryhdls == null) {
			dryhdls = 0l;
		}
		return dryhdls;
	}

	public void setDryhdls(Long dryhdls) {
		this.dryhdls = dryhdls;
	}

	public Long getDrdlzs() {
		if (drdlzs == null) {
			drdlzs = 0l;
		}
		return drdlzs;
	}

	public void setDrdlzs(Long drdlzs) {
		this.drdlzs = drdlzs;
	}

	public Long getDqczzrs() {
		if (dqczzrs == null) {
			dqczzrs = 0l;
		}
		return dqczzrs;
	}

	public void setDqczzrs(Long dqczzrs) {
		this.dqczzrs = dqczzrs;
	}

	public Long getDrczrs() {
		if (drczrs == null) {
			drczrs = 0l;
		}
		return drczrs;
	}

	public void setDrczrs(Long drczrs) {
		this.drczrs = drczrs;
	}

	public Long getDrtbrs() {
		if (drtbrs == null) {
			drtbrs = 0l;
		}
		return drtbrs;
	}

	public void setDrtbrs(Long drtbrs) {
		this.drtbrs = drtbrs;
	}

	public Double getDrczze() {
		if (drczze == null) {
			drczze = 0d;
		}
		return drczze;
	}

	public void setDrczze(Double drczze) {
		this.drczze = drczze;
	}

	public Double getDqczze() {
		if (dqczze == null) {
			dqczze = 0d;
		}
		return dqczze;
	}

	public void setDqczze(Double dqczze) {
		this.dqczze = dqczze;
	}

	public Double getDrtbze() {
		if (drtbze == null) {
			drtbze = 0d;
		}
		return drtbze;
	}

	public void setDrtbze(Double drtbze) {
		this.drtbze = drtbze;
	}


	public Double getDryhzye() {
		if (dryhzye == null) {
			dryhzye = 0d;
		}
		return dryhzye;
	}

	public void setDryhzye(Double dryhzye) {
		this.dryhzye = dryhzye;
	}

	public Double getDrfbze() {
		if (drfbze == null) {
			drfbze = 0d;
		}
		return drfbze;
	}

	public void setDrfbze(Double drfbze) {
		this.drfbze = drfbze;
	}

	public Long getDqzbs() {
		if (dqzbs == null) {
			dqzbs = 0l;
		}
		return dqzbs;
	}

	public void setDqzbs(Long dqzbs) {
		this.dqzbs = dqzbs;
	}

	public Double getDqptbze() {
		if (dqptbze == null) {
			dqptbze = 0d;
		}
		return dqptbze;
	}

	public void setDqptbze(Double dqptbze) {
		this.dqptbze = dqptbze;
	}

	public Date getFromaddtime() {
		return fromaddtime;
	}

	public void setFromaddtime(Date fromaddtime) {
		this.fromaddtime = fromaddtime;
	}

	public String getFromaddtimeStr() {
		return CalendarFormatUtil.format(fromaddtime, DateUtil.DATESHOWFORMAT);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDqcgzbs() {
		return dqcgzbs;
	}

	public void setDqcgzbs(Integer dqcgzbs) {
		this.dqcgzbs = dqcgzbs;
	}
	
	
	

}