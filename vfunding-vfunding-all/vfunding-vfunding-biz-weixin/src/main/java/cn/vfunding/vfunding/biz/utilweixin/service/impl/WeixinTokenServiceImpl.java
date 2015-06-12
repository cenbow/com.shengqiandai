package cn.vfunding.vfunding.biz.utilweixin.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;
import cn.vfunding.common.framework.utils.http.HttpRequester;
import cn.vfunding.common.framework.utils.http.HttpRespons;
import cn.vfunding.vfunding.biz.utilweixin.dao.WeixinTokenMapper;
import cn.vfunding.vfunding.biz.utilweixin.model.WeixinToken;
import cn.vfunding.vfunding.biz.utilweixin.service.IWeixinTokenService;
import cn.vfunding.vfunding.biz.weixin.util.Sha1Util;
import cn.vfunding.vfunding.biz.weixin.util.WeixinShareJS;

import com.alibaba.fastjson.JSONObject;

@Service("weixinTokenService")
public class WeixinTokenServiceImpl implements IWeixinTokenService {
	@Autowired
	private WeixinTokenMapper weixinTokenMapper;
	
	@Value("${weixin_appid}")
	private String weixin_appid;
	
	@Value("${weixin_secret}")
	private String weixin_secret;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weixinTokenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeixinToken record) {
		return this.weixinTokenMapper.insert(record);
	}

	@Override
	public int insertSelective(WeixinToken record) {
		return this.weixinTokenMapper.insertSelective(record);
	}

	@Override
	public WeixinToken selectByPrimaryKey(Integer id) {
		return this.weixinTokenMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeixinToken record) {
		return this.weixinTokenMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WeixinToken record) {
		return this.weixinTokenMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 第一步 获取token
	 * @return
	 */
	private String getWeixinToken() {
		try {
			WeixinToken wt = this.weixinTokenMapper.selectByPrimaryKey(1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(wt.getUpdateTime());
			cal.add(Calendar.HOUR, 1);
			if (cal.getTime().before(new Date())) {
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
						weixin_appid+"&secret="+weixin_secret;
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
	private String getWeixinTicket(String accessToken) {
		try {
			WeixinToken wt = this.weixinTokenMapper.selectByPrimaryKey(2);
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
	private WeixinShareJS getShareJS(String ticket, String url) {
		WeixinShareJS wsj = new WeixinShareJS();
		Long timestamp = System.currentTimeMillis();
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
	public synchronized void InjectWeiXinConfig(ModelAndView mv,String url){
		String token =this.getWeixinToken();
		String ticket  =this.getWeixinTicket(token);
		WeixinShareJS wsj =this.getShareJS(ticket, url);
		mv.addObject("wxConfig",wsj);
	}
	
}
