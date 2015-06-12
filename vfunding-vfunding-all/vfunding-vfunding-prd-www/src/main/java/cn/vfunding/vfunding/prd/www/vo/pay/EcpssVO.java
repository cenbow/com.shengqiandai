package cn.vfunding.vfunding.prd.www.vo.pay;


/**
 * 汇潮支付
 * @author liuyijun
 *
 */
public class EcpssVO extends PayBaseVO {
	private String MD5key= "FBA}sBw("; //MD5key值
	
	//private String MD5key= "MLwTupKP"; //MD5key值
	
	private String MerNo="19050";   //商户ID
	
	//private String MerNo="19902";   //商户ID

	private String BillNo;  //订单编号
	
	private String defaultBankNumber;//银行编码
 
	private String Amount;  //支付金额
  
	private String ReturnURL="/pay/ecpssReceive";   //返回地址
    
	private String SignInfo; //MD5加密后的字符串
  
	private String AdviceURL ="/pay/ecpssAdvice";   //[必填]支付完成后，后台接收支付结果，可用来更新数据库值
	
	private String Succeed;
	
	private String Result;
	
	private String SignMD5info;
	
	private String  orderTime; 

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String mD5key) {
		MD5key = mD5key;
	}

	public String getMerNo() {
		return MerNo;
	}

	public void setMerNo(String merNo) {
		MerNo = merNo;
	}

	public String getBillNo() {
		return BillNo;
	}

	public void setBillNo(String billNo) {
		BillNo = billNo;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}


	public String getSignInfo() {
		return SignInfo;
	}

	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}

	

	public String getAdviceURL() {
		return AdviceURL;
	}

	public void setAdviceURL(String adviceURL) {
		AdviceURL = adviceURL;
	}

	public String getSucceed() {
		return Succeed;
	}

	public void setSucceed(String succeed) {
		Succeed = succeed;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getSignMD5info() {
		return SignMD5info;
	}

	public void setSignMD5info(String signMD5info) {
		SignMD5info = signMD5info;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDefaultBankNumber() {
		return defaultBankNumber;
	}

	public void setDefaultBankNumber(String defaultBankNumber) {
		this.defaultBankNumber = defaultBankNumber;
	}
	
	
	
}
