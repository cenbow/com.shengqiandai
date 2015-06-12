package cn.p2p.p2p.prd.mobile.method.all;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.ContBefore;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.utils.VerificationCodeCache;
import cn.p2p.p2p.prd.mobile.vo.VerificationCodeMapVo;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.jmssender.service.impl.VerifyCodeService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service
public class PhoneVerifyCodeMethod {
	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private DelPhoneVerifyCodeMethod delPhoneVerifyCodeMethod;

	/**
	 * 发送验证码
	 * 
	 * @param generalRequest
	 * @return
	 * 
	 *         2015年5月25日 lijianwei
	 */
	public MobileBaseResponse sendVerifyCode(GeneralRequestVO generalRequest) {
		MobileBaseResponse mbr = this.codeFileter(generalRequest);
		if (!mbr.getResponseCode().equals("success"))
			return mbr;
		String phone = generalRequest.getPhone();
		try {
			String code = this.verifyCodeService.getVerifyCode(generalRequest.getPhone(), ISendConfigUtil.SMS_REGISTER_CHECK, null);
			code = DigestUtils.md5Hex(code + "1QAZ2wsx");
			VerificationCodeCache.setCodeMap(phone, code, DateUtil.getLongTime());
			ShardedJedis jedis = null;
			try {
				jedis = shardedJedisPool.getResource();
				jedis.set(DigestUtils.md5Hex(phone + "1QAZ2wsx"), code);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				shardedJedisPool.returnResource(jedis);
			}
			this.delPhoneVerifyCodeMethod.removePhoneVerifyCode(phone);
			return new MobileBaseResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return new MobileBaseResponse("Exception", "请求异常");
		}
	}

	private MobileBaseResponse codeFileter(GeneralRequestVO generalRequest) {
		String phone = generalRequest.getPhone();
		VerificationCodeMapVo CodeMap = VerificationCodeCache.getCodeMap(phone);
		if (CodeMap == null || CodeMap.getTime() + 59 < DateUtil.getLongTime()) {
			return new MobileBaseResponse();
		} else {
			return new MobileBaseResponse("request_time_fail", "请60秒后在提交");
		}
	}

	/**
	 * 校验验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 * 
	 *         2015年5月25日 lijianwei
	 */
	@ContBefore
	public boolean checkVerifyCode(String phone, String code) {
		phone = DigestUtils.md5Hex(phone + "1QAZ2wsx");
		code = DigestUtils.md5Hex(code + "1QAZ2wsx");
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String checkCode = jedis.get(phone);
			if (code.equals(checkCode)) {
				jedis.del(phone);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return false;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.3.11");
		String output;
		jedis.set("hello", "world");
		output = jedis.get("hello");
		System.out.println(output);
		jedis.del("hello");
	}

}
