package cn.vfunding.vfunding.biz.sina.vo.notify;

public class WithdrawNotify {
	private String notify_type;//	通知类型	String(32)	订单处理结果通知类型，
								//见附录通知类型	非空	trade_status_sync
	private String notify_id;//	通知编号	String(32)	32位长，随机生成	非空	8e123abff8e54fe8aaa2b5e4c7fbffeb
	private String input_charset;// 	参数编码字符集	String(10)	商户网站使用的编码格式，如utf-8、gbk、gb2312等。	非空	UTF-8
	private String notify_time;//	通知时间	String(14)	通知时间，格式yyyyMMddhhmmss	非空	20140619183930
	private String sign;//	签名	String(256)	参见“签名机制”。	非空	d51ed71656b065dfce702c3eb00e678a
	private String sign_type;//	签名方式	String(10)	签名方式支持RSA、MD5。建议使用MD5	非空	MD5 
	private String version;//	接口版本号	Number(5)	接口版本号	非空	1.0
	private String memo;//	备注	String(1000)	说明信息，与请求中memo内容一致	可空	
	private String error_code;//	返回错误码	String(30)	错误码，但出现业务状态为失败的情况提供错误原因，具体见附录响应码	可空	BLANCE_NOT_ENOUGH
	private String error_message;//	返回错误原因	String(200)	错误消息	可空	余额不足
	private String outer_trade_no;//	商户网站唯一订单号或者交易原始凭证号	String(32)	商户网站唯一订单号或者交易原始凭证号	非空	1403173620429
	private String inner_trade_no;//	内部交易凭证号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	101140324364961833614
	private String withdraw_status;//	提现状态	String(20)	提现状态，详见附录中的提现状态	非空	SUCCESS
	private String withdraw_amount;//	提现金额	Number(15,2)	单位元，可以含小数点	非空	12.08
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getOuter_trade_no() {
		return outer_trade_no;
	}
	public void setOuter_trade_no(String outer_trade_no) {
		this.outer_trade_no = outer_trade_no;
	}
	public String getInner_trade_no() {
		return inner_trade_no;
	}
	public void setInner_trade_no(String inner_trade_no) {
		this.inner_trade_no = inner_trade_no;
	}
	public String getWithdraw_status() {
		return withdraw_status;
	}
	public void setWithdraw_status(String withdraw_status) {
		this.withdraw_status = withdraw_status;
	}
	public String getWithdraw_amount() {
		return withdraw_amount;
	}
	public void setWithdraw_amount(String withdraw_amount) {
		this.withdraw_amount = withdraw_amount;
	}
	
	
}
