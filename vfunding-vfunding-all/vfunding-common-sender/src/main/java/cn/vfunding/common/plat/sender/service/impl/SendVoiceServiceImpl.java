package cn.vfunding.common.plat.sender.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.plat.sender.common.HttpRequester;
import cn.vfunding.common.plat.sender.common.HttpRespons;
import cn.vfunding.common.plat.sender.dao.SendSmsMapper;
import cn.vfunding.common.plat.sender.model.SendSms;
import cn.vfunding.common.plat.sender.service.ISendVoiceService;

@Service("sendVoiceService")
public class SendVoiceServiceImpl implements ISendVoiceService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${voice.username}")
	private String username;
	@Value("${voice.password}")
	private String password;
	@Value("${voice.url}")
	private String url;
	@Value("${voice.dstclid}")
	private String dstclid;

	@Autowired
	private SendSmsMapper smsMapper;

	@Override
	public String sendVoice(String phone, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendVoice(String phone, String content, String from,
			String type) {
		try {
			// http://60.10.148.7/2013/interface/data/call.php?action=code_callout&verifymethod=pwd&loginid=867600012&loginpwd=defe12aad396f90e6b179c239de260d4&dst=被叫号码&code=123456&dstclid=4000908595
			String sendUrl = "%s&loginid=%s&loginpwd=%s&dst=%s&code=%s&dstclid=%s";
			String md5Password = DigestUtils.md5Hex(password);
			sendUrl = String.format(sendUrl, url, username, md5Password, phone,
					content, dstclid);
			System.out.println(sendUrl);
			HttpRequester request = new HttpRequester();
			HttpRespons hr = request.sendPost(sendUrl);

			Vector<String> results = hr.getContentCollection();
			String result = results.get(results.size() - 1);
			//System.out.println(result);

			@SuppressWarnings("unchecked")
			Map<String, String> map = JSON.parseObject(result, Map.class);
			result = map.get("errorcode");
			if (result == null) {
				result = map.get("code");
			}
			SendSms vo = new SendSms();
			vo.setId(ObjectId.get());
			vo.setContent(content);
			vo.setMobile(phone);
			vo.setSendTime(new Date());
			vo.setSmsType(EmptyUtil.isNotEmpty(type) ? type : "system_voice");
			vo.setSendFrom(EmptyUtil.isNotEmpty(from) ? from : "vfunding-www");
			if (result.equals("0")) {
				vo.setStatus("success");
			} else {
				vo.setStatus("failed");
			}
			this.smsMapper.insert(vo);
			return result;
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder("短信发送失败：");
			sb.append("内容：").append(content).append(",手机：").append(phone)
					.append(",错误信息：").append(e.getMessage());
			logger.error(sb.toString());
		}
		return null;
	}

	public static void main(String[] args) {

		// http://60.10.148.7/2013/interface/data/call.php?action=code_callout&verifymethod=pwd&loginid=867600012&loginpwd=defe12aad396f90e6b179c239de260d4&dst=被叫号码&code=123456&dstclid=4000908595

		String s = "-1";
		Integer i = Integer.parseInt(s);
		System.out.println(i);
	}

}
