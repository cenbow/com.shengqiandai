package cn.vfunding.common.plat.sender.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.plat.sender.common.HttpRequester;
import cn.vfunding.common.plat.sender.common.HttpRespons;
import cn.vfunding.common.plat.sender.dao.SendSmsMapper;
import cn.vfunding.common.plat.sender.model.SendSms;
import cn.vfunding.common.plat.sender.properties.util.SmsTempletPropertiesUtil;
import cn.vfunding.common.plat.sender.service.ISendSmsService;

@Service("sendSmsService")
public class SendSmsService implements ISendSmsService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${sms.username}")
	private String username;
	@Value("${sms.password}")
	private String password;
	@Value("${sms.url}")
	private String url;

	@Value("${sms.username2}")
	private String username2;
	@Value("${sms.password2}")
	private String password2;
	@Value("${sms.url2}")
	private String url2;

	@Value("${sms.type}")
	private Integer smsType;

	@Autowired
	private SendSmsMapper smsMapper;

	@Override
	public String sendSms(String phone, String content) {
		return this.send(content, phone, null, null);
	}

	@Override
	public String sendSms(String phone, String content, String from, String type) {
		return this.send(content, phone, from, type);
	}

	@Override
	public String sendSmsByTemplet(SendCommon common) {
		String result = "";
		String templetMessage = SmsTempletPropertiesUtil.getValue(common
				.getSendMessageKey());
		if (EmptyUtil.isNotEmpty(templetMessage)) {
			if (EmptyUtil.isNotEmpty(common.getParams())) {
				String content = MessageFormat.format(templetMessage,
						common.getParams());
				result = this.send(content, common.getPhone(),
						"vfunding-vfunding", "system");
			} else {
				result = this.send(templetMessage, common.getPhone(),
						"vfunding-vfunding", "system");
			}
		}
		return result;
	}

	/**
	 * 发送短消息并记录信息
	 * 
	 * @param content
	 * @param phone
	 * @param from
	 * @param type
	 * @return
	 */
	private String send(String content, String phone, String from, String type) {
		String result = "-1";
		try {
			SendSms vo = new SendSms();
			vo.setId(ObjectId.get());
			vo.setContent(content);
			if (smsType == 1) {
				result = this.send1(content, phone, from, type);
			} else if (smsType == 2) {
				result = this.send2(content, phone, from, type);
			}
			vo.setMobile(phone);
			vo.setSendTime(new Date());
			vo.setSmsType(EmptyUtil.isNotEmpty(type) ? type : "system");
			vo.setSendFrom(EmptyUtil.isNotEmpty(from) ? from : "vfunding_www");
			vo.setStatus(result);
			this.smsMapper.insert(vo);
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder("短信发送失败：");
			sb.append("内容：").append(content).append(",手机：").append(phone)
					.append(",错误信息：").append(e.getMessage());
			logger.error(sb.toString());
		}
		return result;
	}

	private String send1(String content, String phone, String from, String type) {
		try {
			DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp = format2.format(new Date());
			String md5Password = md5(username, password);
			String auth = md5(md5Password, timestamp);
			if (!content.startsWith("【")) {
				content = "【微积金】" + content;
			}
			content = URLEncoder.encode(content, "utf-8");
			String sendUrl = "%s?phone=%s&content=%s&auth=%s&username=%s&timestamp=%s&need_report=1";
			sendUrl = String.format(sendUrl, url, phone, content, auth,
					username, timestamp);
			HttpRequester request = new HttpRequester();
			HttpRespons hr = request.sendPost(sendUrl);
			Vector<String> results = hr.getContentCollection();
			String result = results.get(results.size() - 1);
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}

	private String send2(String content, String phone, String from, String type) {
		try {
			// 用UTF-8编码执行URLEncode
			String _content = java.net.URLEncoder.encode(content, "UTF-8");
			// 密码加密
			String pass = MD5Encode(password2);
			String _url = url2;
			String _param = "Account=" + username2 + "&Password=" + pass
					+ "&Phone=" + phone + "&Content=" + _content
					+ "&SubCode=&SendTime=";
			URL url = null;
			HttpURLConnection urlConn = null;
			url = new URL(_url);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			out.write(_param.getBytes("UTF-8"));
			out.flush();
			out.close();
			// step 1:获得DOM解析器工厂
			// 工厂的作用是创建具体的解析器
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// step 2：获得具体的dom解析器
			DocumentBuilder db = dbf.newDocumentBuilder();
			// step 3:解析一个xml文档，获得Document对象（根节点）
			// 此文档放在项目目录下即可
			Document document = db.parse(urlConn.getInputStream());
			// 根据标签名访问节点
			NodeList list = document.getElementsByTagName("result");
			// 遍历每一个节点
			if (list.getLength() >= 0) {
				Element element = (Element) list.item(0);
				// 因为节点getNodeValue的值永远为null
				// 解决方法：加上getFirstChild()
				String result = element.getElementsByTagName("response")
						.item(0).getFirstChild().getNodeValue();
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "-1";
	}

	public static String toHexString(byte[] data) {
		if (data == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			String s = Integer.toHexString(b & 0xff);
			if (s.length() == 1) {
				sb.append("0").append(s);
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	public static String md5(String s1, String s2) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s1.getBytes("utf-8"));
			md.update(s2.getBytes("utf-8"));
			return toHexString(md.digest());

		} catch (Throwable thr) {
			thr.toString();
		}
		return null;
	}

	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	public static void main(String[] args) {

	}

}
