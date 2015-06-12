package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 5.1	存钱罐基金收益率查询VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryFundYieldReturnVO extends BaseSinaReturnVO{
	private String yield_list;	//最近30日收益率及净值	String(1000)	参数格式：日期1^7日年化收益率^万份净值|日期1^7日年化收益率^万份净值|…
	//非空	20140630^4.1234^1.2121|20140629^4.1234^1.2121

	public String getYield_list() {
		return yield_list;
	}

	public void setYield_list(String yield_list) {
		this.yield_list = yield_list;
	}

	
}
