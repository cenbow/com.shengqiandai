package cn.vfunding.union.plat.mq.model;

/**
 * 报表统计辅助VO
 * 
 * @author liuyijun
 * 
 */
public class ProfitStatisticsVO {

	public ProfitStatisticsVO() {

	}

	public ProfitStatisticsVO(String start, String end) {
		this.start = start;
		this.end = end;
	}

	private String start;
	private String end;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
