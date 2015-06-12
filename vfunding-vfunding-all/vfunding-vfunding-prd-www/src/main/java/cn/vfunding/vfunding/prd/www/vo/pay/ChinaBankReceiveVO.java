package cn.vfunding.vfunding.prd.www.vo.pay;


/**
 * 网银在线支付接收vo 
 * @author liuyijun
 *
 */
public class ChinaBankReceiveVO extends ChinaBankVO {
	
	private String v_pmode;
	
	private String v_pstatus;
	
	private String v_pstring;
	
	private String v_md5str;
		
	public String getV_pmode() {
		return v_pmode;
	}
	public void setV_pmode(String v_pmode) {
		this.v_pmode = v_pmode;
	}
	public String getV_pstatus() {
		return v_pstatus;
	}
	public void setV_pstatus(String v_pstatus) {
		this.v_pstatus = v_pstatus;
	}
	public String getV_pstring() {
		return v_pstring;
	}
	public void setV_pstring(String v_pstring) {
		this.v_pstring = v_pstring;
	}
	public String getV_md5str() {
		return v_md5str;
	}
	public void setV_md5str(String v_md5str) {
		this.v_md5str = v_md5str;
	}
	
	
}
