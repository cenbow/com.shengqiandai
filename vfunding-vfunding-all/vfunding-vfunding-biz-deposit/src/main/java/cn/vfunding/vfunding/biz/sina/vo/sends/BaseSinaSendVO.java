package cn.vfunding.vfunding.biz.sina.vo.sends;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;

/**
 * 新浪资金托管 发送 基本参数VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class BaseSinaSendVO extends BaseVO{
	private String service;// 接口名称
	
	private String version;// 接口版本
	
	private String request_time;// 请求时间
	
	private String partner_id;// 合作者身份ID
	
	private String _input_charset;// 参数编码字符集
	
	private String sign_type;// 签名方式
	
	private String sign; 	//签名
	
	private String sign_version;	//签名版本号	Number(5)	签名密钥版本，默认1.0	可空	1.0
	
	private String encrypt_version;	//加密版本号	Number(5)	加密密钥版本，默认1.0	可空	1.0
	
	private String notify_url;	//系统异步回调通知地址	String(1000)	钱包处理发生状态变迁后异步通知结果，响应结果为“success”，全部小写	可空	http://www.test.com/receive_notify.htm 
	
	private String return_url;	//页面跳转同步返回页面路径	String(1000)	钱包处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。	可空	http://www.test.com/receive_return.htm 
	
	private String memo;	//备注	String(1000)	说明信息，原文返回。客户可根据需要存放需要在响应时带回的信息。	可空	


	//默认构造器
	public BaseSinaSendVO() {
		super();
		this.partner_id = SinaParamsUtil.getInstance().getPartnerId();
		this._input_charset = SinaParamsUtil.getInstance().getInputCharset();
		this.sign_type = SinaParamsUtil.getInstance().getSignType();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		this.request_time = sdf.format(new Date());
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_version() {
		return sign_version;
	}

	public void setSign_version(String sign_version) {
		this.sign_version = sign_version;
	}

	public String getEncrypt_version() {
		return encrypt_version;
	}

	public void setEncrypt_version(String encrypt_version) {
		this.encrypt_version = encrypt_version;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	@Override
	public String toString() {
		return "BaseSinaSendVO [service=" + service + ", version=" + version
				+ ", request_time=" + request_time + ", partner_id="
				+ partner_id + ", _input_charset=" + _input_charset
				+ ", sign_type=" + sign_type + ", sign=" + sign
				+ ", sign_version=" + sign_version + ", encrypt_version="
				+ encrypt_version + ", notify_url=" + notify_url
				+ ", return_url=" + return_url + ", memo=" + memo + "]";
	}
	
	
	
}
