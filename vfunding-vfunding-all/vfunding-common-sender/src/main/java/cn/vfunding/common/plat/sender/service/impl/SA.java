package cn.vfunding.common.plat.sender.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SA {

	public static void main(String[] args) {
		try {
			Long startt = System.currentTimeMillis();
			// 短信内容
			String _content = "你的银行验证码是：510664请在5分钟内提交！";
			// 用UTF-8编码执行URLEncode
			_content = java.net.URLEncoder.encode(_content, "UTF-8");
			// 密码加密
			String pass = MD5Encode("21262.com");
			String _url = "http://3tong.net/http/SendSms";
			String _param = "Account=dh21262&Password=" + pass
					+ "&Phone=18616378803&Content=" + _content
					+ "&SubCode=&SendTime=";
			System.out.println(_url + _param);
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
			for (int i = 0; i < list.getLength(); ++i) {
				// 获得元素，将节点强制转换为元素
				Element element = (Element) list.item(i);
				// 因为节点getNodeValue的值永远为null
				// 解决方法：加上getFirstChild()
				String content = element.getElementsByTagName("response").item(0)
						.getFirstChild().getNodeValue();
				System.out.println("response: " + content);// 此处打印出书名
			}
			
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}
			System.out.println(sb);
			rd.close();
			Long end = System.currentTimeMillis();
			System.out.println("发送 短信 耗时：" + (end - startt));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

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
}
