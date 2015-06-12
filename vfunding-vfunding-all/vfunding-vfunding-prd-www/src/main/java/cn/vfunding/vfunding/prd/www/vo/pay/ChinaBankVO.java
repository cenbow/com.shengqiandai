package cn.vfunding.vfunding.prd.www.vo.pay;


/**
 * 网银在线支付vo 
 * @author liuyijun
 *
 */
public class ChinaBankVO extends PayBaseVO {

	//protected String v_mid="22831524";//
	protected String v_mid="22955280";
	//protected String key="YLV>fu;nding<T9?";//
	protected String key="WJjwJJ{1203}weijin";
	//private String v_mid="1001";
	//private String key="test";
	protected String v_url="/pay/chianBankReceive";
	protected String v_oid;
	protected String v_amount;
	protected String v_moneytype="CNY";
	protected String v_md5info;
	
	protected String pmode_id;//银行编码
	
	protected String remark2="/pay/chinaBankAutoReceive";
	public String getV_mid() {
		return v_mid;
	}
	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getV_url() {
		return v_url;
	}
	public void setV_url(String v_url) {
		this.v_url = v_url;
	}
	public String getV_oid() {
		return v_oid;
	}
	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}
	public String getV_amount() {
		return v_amount;
	}
	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}
	public String getV_moneytype() {
		return v_moneytype;
	}
	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}
	public String getV_md5info() {
		return v_md5info;
	}
	public void setV_md5info(String v_md5info) {
		this.v_md5info = v_md5info;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getPmode_id() {
		return pmode_id;
	}
	public void setPmode_id(String pmode_id) {
		this.pmode_id = pmode_id;
	} 
	
	
}
