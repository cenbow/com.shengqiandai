package cn.vfunding.vfunding.biz.sina.beanUtil;

/**
 * 
 * @author wang.zeyan
 * @date 2015年1月15日
 * @version 1.0
 * 
 */
public class SinaMemberParmUtil {

	public static final String success="success";
	public static final String SUCCESS="SUCCESS";
	public static final String Y="Y";
	public static final String N="N";
	/**
	 * 存钱罐 对接基金编码
	 */
	public static final String FUND_CODE="000330";
	/**
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 * @version 1.0
	 */
	public class CustomResponseCode {

		/**
		 * Sina创建激活用户对象为空
		 */
		public static final String SINA_CREATEACTIVATEMEMBER_ISNULL = "SINA_CREATEACTIVATEMEMBER_ISNULL";

		/**
		 * Sina创建激活用户对象 用户标识信息 或 用户标识类型 为空！
		 */
		public static final String SINA_CREATEACTIVATEMEMBER_IDENTITYID_OR_IDENTITYTYPE_ISNULL = "SINA_CREATEACTIVATEMEMBER_IDENTITYID_OR_IDENTITYTYPE_ISNULL";

		/**
		 * RegisterVO 注册vo 为空
		 */
		public static final String REGISTERVO_ISNULL = "REGISTERVO_ISNULL";
		
		/**
		 * Sina实名认证信息不全
		 */
		public static final String SET_REALNAME_INCOMPLETE_INFO = "SET_REALNAME_INCOMPLETE_INFO";
		
		/**
		 * Sina实名认证信息为空
		 */
		public static final String SET_REALNAME_REQUEST_ISNULL = "SET_REALNAME_REQUEST_ISNULL";
		
		/**
		 * Vfunding userId 为空
		 */
		public static final String V_USER_ID_ISNULL = "V_USER_ID_ISNULL";
		
		/**
		 * Vfunding user 为空
		 */
		public static final String V_USER_ISNULL = "V_USER_ISNULL";
		
		/**
		 * Sina绑定认证 请求消息为空
		 */
		public static final String SINA_VERIFT_REQUEST_ISNULL = "SINA_VERIFT_REQUEST_ISNULL";
		
		/**
		 * Sina绑定认证 信息不全
		 */
		public static final String SINA_VERIFT_INCOMPLETE_INFO = "SINA_VERIFT_INCOMPLETE_INFO";
		
		/**
		 * Sina解绑认证 请求消息为空
		 */
		public static final String UN_SINA_VERIFT_REQUEST_ISNULL = "UN_SINA_VERIFT_REQUEST_ISNULL";
		
		/**
		 * Sina解绑认证 信息不全
		 */
		public static final String UN_SINA_VERIFT_INCOMPLETE_INFO = "UN_SINA_VERIFT_INCOMPLETE_INFO";
		
		/**
		 * 查询Sina认证 请求消息为空
		 */
		public static final String QUERY_SINA_VERIFT_REQUEST_ISNULL = "QUERY_SINA_VERIFT_REQUEST_ISNULL";
		
		/**
		 * 查询Sina认证 信息不全
		 */
		public static final String QUERY_SINA_VERIFT_INCOMPLETE_INFO = "QUERY_SINA_VERIFT_INCOMPLETE_INFO";
	}

	/**
	 * 外部业务码
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class OutTradeCode{
		public static final String OutTradeCode = "OutTradeCode";
		/**
		 * 代收投资金
		 */
		public static final String _1001 = "1001";
		/**
		 * 代收还款金
		 */
		public static final String _1002 = "1002";
		/**
		 * 代付借款金
		 */
		public static final String _2001 = "2001"; 
		/**
		 * 代付（本金/收益）金
		 */
		public static final String _2002 = "2002";

	}

	/**
	 * 交易啊状态
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class TradeStatus{
		public static final String TradeStatus = "TradeStatus";
		/**
		 * 等待付款
		 */
		public static final String WAIT_PAY = "WAIT_PAY";
		/**
		 * 已付款
		 */
		public static final String PAY_FINISHED = "PAY_FINISHED";
		/**
		 * 交易失败
		 */
		public static final String TRADE_FAILED = "TRADE_FAILED";
		/**
		 * 交易结束
		 */
		public static final String TRADE_FINISHED ="TRADE_FINISHED";
		/**
		 * 交易关闭
		 */
		public static final String TRADE_CLOSED = "TRADE_CLOSED";

	}
	
	/**
	 * 退款状态
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class Refund{
		public static final String Refund = "Refund";
		/**
		 * 等待退款（处理中）
		 */
		public static final String WAIT_REFUND = "WAIT_REFUND";
		/**
		 * 已扣款（处理中）
		 */
		public static final String PAY_FINISHED = "PAY_FINISHED";
		/**
		 * 退款成功
		 */
		public static final String SUCCESS = "SUCCESS";
		/**
		 * 退款失败
		 */
		public static final String FAILED = "FAILED";
	}
	
	/**
	 * 支付状态
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class PayStatus{
		public static final String PayStatus = "PayStatus";
		/**
		 * 成功
		 */
		public static final String SUCCESS = "SUCCESS";
		/**
		 * 失败
		 */
		public static final String FAILED = "FAILED";
		/**
		 * 处理中
		 */
		public static final String PROCESSING = "PROCESSING";
	}
	
	/**
	 * 接口名称
	 * @author jianwei
	 * @date 2015年1月19日
	 */
	public class interfaceName{
		/**
		 * 2.1创建激活会员
		 */
		public static final String create_activate_member = "创建激活会员";
		/**
		 * 2.2设置实名信息
		 */
		public static final String set_real_name = "设置实名信息";
		/**
		 * 2.3绑定认证信息
		 */
		public static final String binding_verify = "绑定认证信息";
		/**
		 * 2.4解绑认证信息
		 */
		public static final String unbinding_verify = "解绑认证信息";
		/**
		 * 2.5查询认证信息
		 */
		public static final String query_verify = "查询认证信息";
		/**
		 * 2.6绑定银行卡
		 */
		public static final String binding_bank_card = "绑定银行卡";
		/**
		 * 2.7绑定银行卡推进
		 */
		public static final String binding_bank_card_advance = "绑定银行卡推进";
		/**
		 * 2.8解绑银行卡
		 */
		public static final String unbinding_bank_card = "解绑银行卡";
		/**
		 * 2.9查询银行卡
		 */
		public static final String query_bank_card = "查询银行卡";
		/**
		 * 2.10查询余额/基金份额
		 */
		public static final String query_balance = "查询余额/基金份额";
		/**
		 * 2.11查询收支明细
		 */
		public static final String query_account_details = "查询收支明细";
		/**
		 * 2.12冻结余额（暂不支持存钱罐）
		 */
		public static final String balance_freeze = "冻结余额";
		/**
		 * 2.13解冻余额（暂不支持存钱罐）
		 */
		public static final String balance_unfreeze = "解冻余额";
		/**
		 * 3.1创建托管代收交易
		 */
		public static final String create_hosting_collect_trade = "创建托管代收交易";
		/**
		 * 3.2创建单笔托管代付交易
		 */
		public static final String create_single_hosting_pay_trade = "创建单笔托管代付交易";
		
		/**
		 * 3.3创建批量托管代付交易
		 */
		public static final String create_batch_hosting_pay_trade = "创建批量托管代付交易";
		/**
		 * 3.4托管交易支付
		 */
		public static final String pay_hosting_trade = "托管交易支付";
		
		/**
		 * 3.5支付结果查询
		 */
		public static final String query_pay_result = "支付结果查询";
		/**
		 * 3.6托管交易查询
		 */
		public static final String query_hosting_trade = "托管交易查询";
		/**
		 * 3.7托管退款
		 */
		public static final String create_hosting_refund = "托管退款";
		/**
		 * 3.8托管退款查询
		 */
		public static final String query_hosting_refund = "托管退款查询";
		/**
		 * 3.9托管充值
		 */
		public static final String create_hosting_deposit = "托管充值";
		/**
		 * 3.10托管充值查询
		 */
		public static final String query_hosting_deposit = "托管充值查询";
		/**
		 * 3.11托管提现
		 */
		public static final String create_hosting_withdraw = "托管提现";
		/**
		 * 3.12托管提现查询
		 */
		public static final String query_hosting_withdraw = "托管提现查询";
		/**
		 * 3.13转账接口
		 */
		public static final String create_hosting_transfer = "转账接口";
		/**
		 * 3.14支付推进
		 */
		public static final String advance_hosting_pay = "支付推进";
	}
	
	/**
	 * 提现状态
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class WithdrawStatus{
		public static final String WithdrawStatus = "WithdrawStatus";
		/**
		 * 成功
		 */
		public static final String SUCCESS = "SUCCESS";
		/**
		 * 失败
		 */
		public static final String FAILED = "FAILED";
		/**
		 * 处理中
		 */
		public static final String PROCESSING = "PROCESSING";
		/**
		 * 退票
		 */
		public static final String RETURNT_TICKET = "RETURNT_TICKET";

	}
	
	/**
	 * 	1.1   证件类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class PaperType{
		public static final String PaperType = "PaperType";
		/**
		 * 身份证
		 */
		public static final String IC = "IC";	
		/**
		 * 护照
		 */
		public static final String PP = "PP";	
		/**
		 * 港澳通行证
		 */
		public static final String HMP = "HMP";	

	}
	/**
	 * 	1.1   账户类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class AccountType{
		public static final String AccountType = "AccountType";
		/**
		 * 基本户
		 */
		public static final String BASIC = "BASIC";	
		/**
		 * 保证金户
		 */
		public static final String ENSURE = "ENSURE";	
		/**
		 * 准备金
		 */
		public static final String RESERVE = "RESERVE";	
		/**
		 * 存钱罐
		 */
		public static final String SAVING_POT = "SAVING_POT";	

	}
	/**
	 * 	1.1   认证类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class VerifyType{
		public static final String VerifyType = "VerifyType";
		/**
		 * 手机
		 */
		public static final String MOBILE = "MOBILE";	
		/**
		 * 邮箱
		 */
		public static final String EMAIL = "EMAIL";	

	}
	/**
	 * 	1.1   卡类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class CardType{
		public static final String CardType = "CardType";
		/**
		 * 借记
		 */
		public static final String DEBIT = "DEBIT";	
		/**
		 * 贷记（信用卡）
		 */
		public static final String CREDIT = "CREDIT";	

	}
	/**
	 * 	1.1   卡属性
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class CardParam{
		public static final String CardParam = "CardParam";	
		/**
		 * 对私
		 */
		public static final String C = "C";	
		/**
		 * 对公
		 */
		public static final String B = "B";	

	}
	/**
	 * 	1.1   卡认证方式
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class CardVerify{
		public static final String CardVerify = "CardVerify";
		/**
		 * 签约认证
		 */
		public static final String SIGN = "SIGN";	
	}
	/**
	 * 1.1   通知类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class SyncType{
		public static final String SyncType = "SyncType";
		/**
		 * 交易结果通知
		 */
		public static final String trade_status_sync = "trade_status_sync";	
		/**
		 * 交易退款结果通知
		 */
		public static final String refund_status_sync = "refund_status_sync";	
		/**
		 * 充值结果通知
		 */
		public static final String deposit_status_sync = "deposit_status_sync";	
		/**
		 * 提现结果通知
		 */
		public static final String withdraw_status_sync = "withdraw_status_sync";	


	}	
	/**
	 * 1.1   标识类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class IdenType{
		public static final String IdenType = "IdenType";	
		/**
		 * 商户用户id
		 */
		public static final String UID = "UID";	
		/**
		 * 钱包绑定手机号
		 */
		public static final String MOBILE = "MOBILE";	
		/**
		 * 钱包绑定邮箱
		 */
		public static final String EMAIL = "EMAIL";	

	}	
	/**
	 * 	1.1   存钱罐交易类型
	 * @author jianwei
	 * @date 2015年1月16日
	 */
	public class SavePotPayType{
		public static final String SavePotPayType = "SavePotPayType";	
		/**
		 * 申购
		 */
		public static final String IN = "IN";	
		/**
		 * 赎回
		 */
		public static final String OUT = "OUT";	
		/**
		 * 收益
		 */
		public static final String BONUS = "BONUS";	

	}
	
	/**
	 * 新浪接口响应码
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 * @version 1.0
	 */
	public class ResponseCode {

		/**
		 * 提交成功，存在业务响应的以业务响应状态为准
		 */
		public static final String APPLY_SUCCESS = "APPLY_SUCCESS";

		/**
		 * 授权失败
		 */
		public static final String AUTHORIZE_FAIL = "AUTHORIZE_FAIL";

		/**
		 * 商户该接口授权已过期
		 */
		public static final String AUTH_INVALID_DATE = "AUTH_INVALID_DATE";

		/**
		 * 添加认证信息失败
		 */
		public static final String ADD_VERIFY_FAIL = "ADD_VERIFY_FAIL";

		/**
		 * 支付或绑卡推进失败
		 */
		public static final String ADVANCE_FAILED = "ADVANCE_FAILED";

		/**
		 * 银行卡信息不存在
		 */
		public static final String BANK_ACCOUNT_NOT_EXISTS = "BANK_ACCOUNT_NOT_EXISTS";

		/**
		 * 绑定银行卡数量超限
		 */
		public static final String BANK_ACCOUNT_TOO_MANY = "BANK_ACCOUNT_TOO_MANY";

		/**
		 * 银行卡未认证
		 */
		public static final String BANK_CARD_NOT_VERIFIED = "BANK_CARD_NOT_VERIFIED";

		/**
		 * 暂不支持该银行
		 */
		public static final String BANK_CODE_NOT_SUPPORT = "BANK_CODE_NOT_SUPPORT";

		/**
		 * 银行卡未生效
		 */
		public static final String BANK_CARD_NOT_EFFECT = "BANK_CARD_NOT_EFFECT";

		/**
		 * 银行卡未签约
		 */
		public static final String BANK_CARD_NOT_SIGN = "BANK_CARD_NOT_SIGN";

		/**
		 * 银行卡信息校验失败
		 */
		public static final String BANK_INFO_VERIFY_FAILED = "BANK_INFO_VERIFY_FAILED";

		/**
		 * 绑卡失败
		 */
		public static final String BIND_CARD_FAILED = "BIND_CARD_FAILED";

		/**
		 * 业务处理中，等待通知或查询
		 */
		public static final String BIZ_PENDING = "BIZ_PENDING";

		/**
		 * 余额不足
		 */
		public static final String BLANCE_NOT_ENOUGH = "BLANCE_NOT_ENOUGH";

		/**
		 * 卡类型暂不支持
		 */
		public static final String CARD_TYPE_NOT_SUPPORT = "CARD_TYPE_NOT_SUPPORT";

		/**
		 * 证件号不存在，请提前实名认证
		 */
		public static final String CERT_NOT_EXIST = "CERT_NOT_EXIST";

		/**
		 * 证件号不匹配
		 */
		public static final String CERTNO_NOT_MATCHING = "CERTNO_NOT_MATCHING";

		/**
		 * 用户标识信息重复
		 */
		public static final String DUPLICATE_IDENTITY_ID = "DUPLICATE_IDENTITY_ID";

		/**
		 * 冻结订单号重复
		 */
		public static final String DUPLICATE_OUT_FREEZE_NO = "DUPLICATE_OUT_FREEZE_NO";

		/**
		 * 解冻订单号重复
		 */
		public static final String DUPLICATE_OUT_UNFREEZE_NO = "DUPLICATE_OUT_UNFREEZE_NO";

		/**
		 * 重复的请求号
		 */
		public static final String DUPLICATE_REQUEST_NO = "DUPLICATE_REQUEST_NO";

		/**
		 * 会员认证信息重复
		 */
		public static final String DUPLICATE_VERIFY = "DUPLICATE_VERIFY";

		/**
		 * 冻结余额失败
		 */
		public static final String FREEZE_FUND_FAIL = "FREEZE_FUND_FAIL";

		/**
		 * 冻结余额处理中，请联系管理员
		 */
		public static final String FREEZE_FUND_PROCESSING = "FREEZE_FUND_PROCESSING";

		/**
		 * 查询认证信息失败
		 */
		public static final String GET_VERIFY_FAIL = "GET_VERIFY_FAIL";

		/**
		 * 代付交易不允许退款
		 */
		public static final String HOST_PAY_NOT_SUPPORT_REFUND = "HOST_PAY_NOT_SUPPORT_REFUND";

		/**
		 * 不允许访问该类型的接口
		 */
		public static final String ILLEGAL_ACCESS_SWITCH_SYSTEM = "ILLEGAL_ACCESS_SWITCH_SYSTEM";

		/**
		 * 参数校验未通过
		 */
		public static final String ILLEGAL_ARGUMENT = "ILLEGAL_ARGUMENT";

		/**
		 * 解密失败，请检查加密字段
		 */
		public static final String ILLEGAL_DECRYPT = "ILLEGAL_DECRYPT";

		/**
		 * 用户标识信息中不存在该平台标志
		 */
		public static final String ILLEGAL_INDETITY_PALTFORMTYPE = "ILLEGAL_INDETITY_PALTFORMTYPE";

		/**
		 * 非法的商户IP或域名
		 */
		public static final String ILLEGAL_IP_OR_DOMAIN = "ILLEGAL_IP_OR_DOMAIN";

		/**
		 * 交易订单号不存在
		 */
		public static final String ILLEGAL_OUTER_TRADE_NO = "ILLEGAL_OUTER_TRADE_NO";

		/**
		 * 服务接口不存在
		 */
		public static final String ILLEGAL_SERVICE = "ILLEGAL_SERVICE";

		/**
		 * 验签未通过
		 */
		public static final String ILLEGAL_SIGN = "ILLEGAL_SIGN";

		/**
		 * 签名类型不正确
		 */
		public static final String ILLEGAL_SIGN_TYPE = "ILLEGAL_SIGN_TYPE";

		/**
		 * 用户卡信息有误
		 */
		public static final String INCORRECT_CARD_INFORMATION = "INCORRECT_CARD_INFORMATION";

		/**
		 * 超过可冻结金额
		 */
		public static final String INSUFFICIENT_FREEZE_BALANCE = "INSUFFICIENT_FREEZE_BALANCE";

		/**
		 * 超过可解冻金额
		 */
		public static final String INSUFFICIENT_UNFREEZE_BALANCE = "INSUFFICIENT_UNFREEZE_BALANCE";

		/**
		 * 用户不存在
		 */
		public static final String MEMBER_ID_NOT_EXIST = "MEMBER_ID_NOT_EXIST";

		/**
		 * 用户标识信息不存在
		 */
		public static final String MEMBER_NOT_EXIST = "MEMBER_NOT_EXIST";

		/**
		 * 无相关银行卡信息
		 */
		public static final String NO_BANK_CARD_INFO = "NO_BANK_CARD_INFO";

		/**
		 * 用户无基本账户信息或没有激活
		 */
		public static final String NO_BASIC_ACCOUNT = "NO_BASIC_ACCOUNT";

		/**
		 * 原冻结交易不存在
		 */
		public static final String NO_FUND_ORIG_FREEEZE_TRADE = "NO_FUND_ORIG_FREEEZE_TRADE";

		/**
		 * 该商户信息不存在
		 */
		public static final String NO_SUCH_MERCHANT = "NO_SUCH_MERCHANT";

		/**
		 * 订单不存在
		 */
		public static final String ORDER_NOT_EXIST = "ORDER_NOT_EXIST";

		/**
		 * 其它错误
		 */
		public static final String OTHER_ERROR = "OTHER_ERROR";

		/**
		 * 请求参数不合法
		 */
		public static final String PARAMETER_INVALID = "PARAMETER_INVALID";

		/**
		 * 合作方Id不存在
		 */
		public static final String PARTNER_ID_NOT_EXIST = "PARTNER_ID_NOT_EXIST";

		/**
		 * 支付方式不支持
		 */
		public static final String PAY_METHOD_NOT_SUPPORT = "PAY_METHOD_NOT_SUPPORT";

		/**
		 * 订单批量支付付款人信息不一致
		 */
		public static final String PAYER_INCONSISTENT = "PAYER_INCONSISTENT";

		/**
		 * 重复支付
		 */
		public static final String PAYMENT_DUPLIDATE = "PAYMENT_DUPLIDATE";

		/**
		 * 支付失败
		 */
		public static final String PAY_FAILED = "PAY_FAILED";

		/**
		 * 实名认证不通过
		 */
		public static final String REALNAME_CONFIRM_FAILED = "REALNAME_CONFIRM_FAILED";

		/**
		 * 实名不匹配
		 */
		public static final String REALNAME_NOT_MATCHING = "REALNAME_NOT_MATCHING";

		/**
		 * 请求方式不合法
		 */
		public static final String REQUEST_METHOD_NOT_VALIDATE = "REQUEST_METHOD_NOT_VALIDATE";

		/**
		 * 请求过期
		 */
		public static final String REQUEST_EXPIRED = "REQUEST_EXPIRED";

		/**
		 * 存钱罐账户开户失败
		 */
		public static final String SAVING_POT_ACCOUNT_OPEN_FAILED = "SAVING_POT_ACCOUNT_OPEN_FAILED";

		/**
		 * 系统内部错误
		 */
		public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

		/**
		 * 交易金额修改不合法
		 */
		public static final String TRADE_AMOUNT_MODIFIED = "TRADE_AMOUNT_MODIFIED";

		/**
		 * 交易关闭
		 */
		public static final String TRADE_CLOSED = "TRADE_CLOSED";

		/**
		 * 交易调用失败
		 */
		public static final String TRADE_FAILED = "TRADE_FAILED";

		/**
		 * 交易号信息有误
		 */
		public static final String TRADE_NO_MATCH_ERROR = "TRADE_NO_MATCH_ERROR";

		/**
		 * 用户银行卡信息不匹配
		 */
		public static final String USER_BANK_ACCOUNT_NOT_MATCH = "USER_BANK_ACCOUNT_NOT_MATCH";

		/**
		 * 认证信息不存在
		 */
		public static final String VERIFY_NOT_EXIST = "VERIFY_NOT_EXIST";

		/**
		 * 认证信息绑定超限
		 */
		public static final String VERIFY_BINDED_OVERRUN = "VERIFY_BINDED_OVERRUN";

		/**
		 * 安全卡当余额及存钱罐为0时才可解绑
		 */
		public static final String UNBINDING_SECURITY_CARD_FORBIDDING = "UNBINDING_SECURITY_CARD_FORBIDDING";

	}

	/**
	 * Sina会员类型
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 * @version 1.0
	 */
	public class MemberType {
		public final static String MemberType = "MemberType";
		
		/**
		 * 个人用户
		 */
		public final static String Personal = "1";
		
		/**
		 * 企业用户
		 */
		public final static String Company = "2";
	}

	/**
	 * Sina会员标识类型
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 * @version 1.0
	 */
	public class IdentityType {
		public final static String UID ="UID";
		
		/**
		 * 商户账号类型
		 */
		public final static String EMAIL ="EMAIL";
	}
	
	public class CertType{
		public final static String IC = "IC";
	}
	
	public class PayMethod{
		/**
		 * 支付方式
		 */
		public final static String PayMethod = "PayMethod";
		
		/**
		 * 网银支付
		 */
		public final static String OnlineBank = "online_bank";
		
		/**
		 * 快捷支付
		 */
		public final static String QuickPay = "quick_pay";
		
		/**
		 * 绑卡支付
		 */
		public final static String BindingPay = "binding_pay";
		
	}
	
}
