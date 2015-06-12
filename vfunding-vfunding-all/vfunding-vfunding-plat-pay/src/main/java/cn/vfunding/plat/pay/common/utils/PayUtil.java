package cn.vfunding.plat.pay.common.utils;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

import cn.vfunding.common.framework.lock.LockUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.plat.pay.utils.llpay.Md5Algorithm;
import cn.vfunding.plat.pay.vo.llpay.ReturnInfo;
import cn.vfunding.plat.pay.vo.sinapay.SinaPayInfo;
import cn.vfunding.plat.pay.vo.sinapay.SinaPayReturnInfo;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;

public class PayUtil {

	/**
	 * 创建并保存充值对象，如果状态为1则更新用户的可用金额
	 * 
	 * @param tradeNo
	 * @param bankReturn
	 * @param money
	 * @param remark
	 * @param status
	 * @param payment
	 * @param userId
	 *            2014年5月4日 liuyijun
	 */
	public static void createAndSaveRecharge(String tradeNo, String bankReturn,
			BigDecimal money, String remark, byte status, short payment,
			Integer userId, IAccountRechargeService accountRecharageService) {
		AccountRecharge recharge = new AccountRecharge();
		recharge.setTradeNo(tradeNo);
		recharge.setAddtime(new Integer(DateUtil.getTime()));
		recharge.setBankReturn(bankReturn);
		recharge.setUserId(userId);
		recharge.setMoney(money);
		recharge.setRemark(remark);
		recharge.setStatus(status);
		recharge.setPayment(payment);
		recharge.setType((byte) 1);
		accountRecharageService.insertSelective(recharge);
	}

	/**
	 * 充值成功公用方法
	 * 
	 * @param tradeNo
	 * @param money
	 * @param payment
	 * @param userId
	 * @param from
	 *            2014年5月4日 liuyijun
	 * @throws Exception 
	 */
	public static void doRechargeSuccess(String tradeNo, BigDecimal money,
			short payment, Integer userId, String from,
			IAccountRechargeService accountRecharageService,LockUtil lockUtil) throws Exception {
		
		InterProcessLock lock = lockUtil.getLock("/lock/recharge");
		try {
			if (lock.acquire(120, TimeUnit.SECONDS)) {
				AccountRecharge re = accountRecharageService
						.selectByTradeNo(tradeNo);
				if (EmptyUtil.isNotEmpty(re)) {
					if (re.getStatus() != 1) {
						re.setStatus((byte) 1);
						re.setBankReturn("充值成功");
						re.setType((byte) 1);
						accountRecharageService.updateStatus(re);
					}
				} else {
					createAndSaveRecharge(tradeNo, "充值成功", money, from, (byte) 1,
							(short) payment, userId, accountRecharageService);
				}
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			lock.release();
		}

	}

	/**
	 * 根据返回的信息创建签名信息
	 * 
	 * @param info
	 * @return
	 * @throws Exception
	 *             2014年9月2日 liuyijun
	 */
	public static String getSignByReturnInfo(ReturnInfo info, String key)
			throws Exception {
		StringBuffer strBuf = new StringBuffer();

		if (EmptyUtil.isNotEmpty(info.getBank_code())) {
			strBuf.append("&bank_code=" + info.getBank_code());
		}

		strBuf.append("&dt_order=" + info.getDt_order());

		if (EmptyUtil.isNotEmpty(info.getInfo_order())) {
			strBuf.append("&info_order=" + info.getInfo_order());
		}

		strBuf.append("&money_order=");
		strBuf.append(info.getMoney_order());

		strBuf.append("&no_order=");
		strBuf.append(info.getNo_order());

		strBuf.append("&oid_partner=");
		strBuf.append(info.getOid_partner());

		if (EmptyUtil.isNotEmpty(info.getOid_paybill())) {
			strBuf.append("&oid_paybill=" + info.getOid_paybill());
		}

		if (EmptyUtil.isNotEmpty(info.getPay_type())) {
			strBuf.append("&pay_type=" + info.getPay_type());
		}

		if (EmptyUtil.isNotEmpty(info.getResult_pay())) {
			strBuf.append("&result_pay=" + info.getResult_pay());
		}

		if (EmptyUtil.isNotEmpty(info.getSettle_date())) {
			strBuf.append("&settle_date=" + info.getSettle_date());
		}

		strBuf.append("&sign_type=");
		strBuf.append(info.getSign_type());

		String sign_src = strBuf.toString();
		if (sign_src.startsWith("&")) {
			sign_src = sign_src.substring(1);
		}
		String sign = "";
		sign_src += "&key=" + key;
		sign = Md5Algorithm.getInstance().md5Digest(sign_src.getBytes("utf-8"));
		return sign;
	}

	public static String getSignStrByWeiboPayInfo(SinaPayInfo payInfo) {
		StringBuffer result = new StringBuffer();
		result.append("inputCharset=");
		result.append(payInfo.getInputCharset());
		result.append("&bgUrl=");
		result.append(payInfo.getBgUrl());
		result.append("&cancelUrl=");
		result.append(payInfo.getCancelUrl());

		result.append("&version=");
		result.append(payInfo.getVersion());
		result.append("&language=");
		result.append(payInfo.getLanguage());
		result.append("&signType=");
		result.append(payInfo.getSignType());

		result.append("&merchantAcctId=");
		result.append(payInfo.getMerchantAcctId());
		result.append("&orderId=");
		result.append(payInfo.getOrderId());
		result.append("&orderAmount=");
		result.append(payInfo.getOrderAmount());
		result.append("&orderTime=");
		result.append(payInfo.getOrderTime());
		result.append("&payType="+payInfo.getPayType());
		
		if (EmptyUtil.isNotEmpty(payInfo.getBankId())) {
			result.append("&bankId=");
			result.append(payInfo.getBankId());
		}

		result.append("&pid=");
		result.append(payInfo.getPid());
		return result.toString();
	}

	public static String getSignStrBySinaPayReturnInfo(
			SinaPayReturnInfo returnInfo) {
		StringBuffer result = new StringBuffer();
		result.append("merchantAcctId=");
		result.append(returnInfo.getMerchantAcctId());
		result.append("&version=");
		result.append(returnInfo.getVersion());
		result.append("&language=");
		result.append(returnInfo.getLanguage());
		result.append("&signType=");
		result.append(returnInfo.getSignType());

		if (EmptyUtil.isNotEmpty(returnInfo.getPayType())) {
			result.append("&payType=");
			result.append(returnInfo.getPayType());
		}

		if (EmptyUtil.isNotEmpty(returnInfo.getBankId())) {
			result.append("&bankId=");
			result.append(returnInfo.getBankId());
		}

		result.append("&orderId=");
		result.append(returnInfo.getOrderId());
		result.append("&orderTime=");
		result.append(returnInfo.getOrderTime());
		result.append("&orderAmount=");
		result.append(returnInfo.getOrderAmount());
		result.append("&dealId=");
		result.append(returnInfo.getDealId());

		if (EmptyUtil.isNotEmpty(returnInfo.getBankDealId())) {
			result.append("&bankDealId=");
			result.append(returnInfo.getBankDealId());
		}

		result.append("&dealTime=");
		result.append(returnInfo.getDealTime());
		result.append("&payAmount=");
		result.append(returnInfo.getPayAmount());
		result.append("&fee=");
		result.append(returnInfo.getFee());
		if (EmptyUtil.isNotEmpty(returnInfo.getExt1())) {
			result.append("&ext1=");
			result.append(returnInfo.getExt1());
		}

		if (EmptyUtil.isNotEmpty(returnInfo.getExt2())) {
			result.append("&ext2=");
			result.append(returnInfo.getExt2());
		}

		result.append("&payResult=");
		result.append(returnInfo.getPayResult());

		if (EmptyUtil.isNotEmpty(returnInfo.getPayIp())) {
			result.append("&payIp=");
			result.append(returnInfo.getPayIp());
		}
		if (EmptyUtil.isNotEmpty(returnInfo.getErrCode())) {
			result.append("&errCode=");
			result.append(returnInfo.getErrCode());
		}
		return result.toString();
	}

}
