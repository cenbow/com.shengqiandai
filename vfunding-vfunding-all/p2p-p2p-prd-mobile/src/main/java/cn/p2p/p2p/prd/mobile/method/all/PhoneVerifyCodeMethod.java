package cn.p2p.p2p.prd.mobile.method.all;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.math.RandomUtils;
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
import example.SingletonClient;

@Service
public class PhoneVerifyCodeMethod {
	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private DelPhoneVerifyCodeMethod delPhoneVerifyCodeMethod;
	//短信验证码MAP
	public static HashMap<String, String> codeMap=new HashMap<String, String>();
	/**
	 * 发送验证码
	 * 
	 * @param generalRequest
	 * @return
	 * 
	 *         2015年5月25日 lijianwei
	 *         sqd发送验证码
	 *         hyc
	 */
	public MobileBaseResponse sendVerifyCode(GeneralRequestVO generalRequest) {
		String phone=generalRequest.getPhone();
		if(null!=phone&&!"".equals(phone)){
			//5.发送短信验证码
			try {
				Integer c1=RandomUtils.nextInt(10);
				Integer c2=RandomUtils.nextInt(10);
				Integer c3=RandomUtils.nextInt(10);
				Integer c4=RandomUtils.nextInt(10);
				System.out.println("------------------------------------------------------");
				System.out.println("phone:"+phone);
				String autoCode=c1.toString()+c2.toString()+c3.toString()+c4.toString();
				String codestr="你的验证码为："+autoCode;
				System.out.println(codestr);
				
				System.out.println("------------------------------------------------------");
				codeMap.put(phone, autoCode);//存入到codeMap中，方便验证
				int i = SingletonClient.getClient().sendSMS(new String[] { phone }, codestr, "",5);// 带扩展码
				//System.out.println("testSendSMS=====" + i);
				if(i==0){
					System.out.println("发送短信成功！");
					return new MobileBaseResponse("0", "发送短信成功！");
				}else if(i==-127){
					System.out.println("发送失败,计费失败0余额");
					return new MobileBaseResponse("-127", "计费失败0余额！");
				}
				
				else{
					System.out.println("未知异常:"+i);
					return new MobileBaseResponse(""+i,"未知异常");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*MobileBaseResponse mbr = this.codeFileter(generalRequest);
		if (!mbr.getResponseCode().equals("success"))
			return mbr;
		//String phone = generalRequest.getPhone();
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
		}*/
		return new MobileBaseResponse();
		
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
		/*phone = DigestUtils.md5Hex(phone + "1QAZ2wsx");
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
		}*/
		boolean flag=false;
		if(null!=code&&!"".equals(code)){
			System.out.println("mcode:"+code);
			
			String autoCode=this.codeMap.get(phone);
			System.out.println("autoCode:"+autoCode);
			if(code.equals(autoCode)){
				//验证码正确
				System.out.println("验证码正确");
				flag=true;
			}
		}
		return flag;
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
