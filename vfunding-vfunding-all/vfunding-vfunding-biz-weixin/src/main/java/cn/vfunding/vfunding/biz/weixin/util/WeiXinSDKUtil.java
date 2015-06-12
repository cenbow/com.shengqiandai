package cn.vfunding.vfunding.biz.weixin.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;
import cn.vfunding.common.framework.utils.http.HttpRequester;
import cn.vfunding.common.framework.utils.http.HttpRespons;
import cn.vfunding.vfunding.biz.utilweixin.dao.WeixinTokenMapper;
import cn.vfunding.vfunding.biz.utilweixin.model.WeixinToken;

import com.alibaba.fastjson.JSONObject;

public class WeiXinSDKUtil {

	@Autowired
	public static WeixinTokenMapper weixinTokenMapper;

	/**
	 * 第一步 获取token
	 * @return
	 */
	private static String getWeixinToken() {
		try {
			WeixinToken wt = weixinTokenMapper.selectByPrimaryKey(1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(wt.getUpdateTime());
			cal.add(Calendar.HOUR, 1);
			if (cal.getTime().before(new Date())) {
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb3c6caa5e78052d0&secret=14ab924e0e4736dbb38431a9b19bb12f";
				HttpRequester httpRequest = new HttpRequester();
				HttpRespons hr;
				hr = httpRequest.sendPost(url);
				String results = hr.getContent();
				String accessToken = (String) JSONObject.parseObject(results)
						.get("access_token");
				wt.setAccessToken(accessToken);
				wt.setUpdateTime(new Date());
				weixinTokenMapper.updateByPrimaryKey(wt);
				return accessToken;
			} else {
				return wt.getAccessToken();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 第二步 根据token获取ticket
	 * @param accessToken
	 * @return
	 */
	private static String getWeixinTicket(String accessToken) {
		try {
			WeixinToken wt = weixinTokenMapper.selectByPrimaryKey(2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(wt.getUpdateTime());
			cal.add(Calendar.HOUR, 1);
			if (cal.getTime().before(new Date())) {
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
						+ accessToken + "&type=jsapi";
				HttpRequester httpRequest = new HttpRequester();
				HttpRespons hr;
				hr = httpRequest.sendPost(url);
				String results = hr.getContent();
				String Ticket = (String) JSONObject.parseObject(results).get(
						"ticket");
				wt.setAccessToken(Ticket);
				wt.setUpdateTime(new Date());
				weixinTokenMapper.updateByPrimaryKey(wt);
				return Ticket;
			} else {
				return wt.getAccessToken();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 第三步 根据ticket生成分享链接
	 * @param ticket
	 * @param url
	 * @return
	 */
	private static WeixinShareJS getShareJS(String ticket, String url) {
		WeixinShareJS wsj = new WeixinShareJS();
		Long timestamp = System.currentTimeMillis()/1000;
		String wxStr = "jsapi_ticket=" + ticket
				+ "&noncestr=vfunding&timestamp=" + timestamp + "&url=" + url;
		String signature = Sha1Util.getSha1(wxStr);
		wsj.setNonceStr("vfunding");
		wsj.setUrl(url);
		wsj.setTimestamp(timestamp.toString());
		wsj.setSignature(signature);
		wsj.setAppid(WorkerPropertiesUtil.getValue("weixin_appid"));
		return wsj;
	}

	/**
	 * 微信sdk写入ModelAndView
	 * @param mv
	 * @param url
	 */
	public static void InjectWeiXinConfig(ModelAndView mv,String url){
		String token =WeiXinSDKUtil.getWeixinToken();
		String ticket  =WeiXinSDKUtil.getWeixinTicket(token);
		WeixinShareJS wsj =WeiXinSDKUtil.getShareJS(ticket, url);
		mv.addObject("wxConfig",wsj);
	}
	
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse("2015-02-16 21:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR, 1);
			System.out.println();
			System.out.println(Sha1Util.getSha1("123"));
			if (cal.getTime().before(new Date())) {
				System.out.println("yes");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
