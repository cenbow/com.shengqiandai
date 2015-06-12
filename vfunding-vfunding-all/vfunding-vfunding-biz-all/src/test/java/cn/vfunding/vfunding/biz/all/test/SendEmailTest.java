package cn.vfunding.vfunding.biz.all.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvoker;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class SendEmailTest {

	@Autowired
	private UserMapper mapper;

	@Test
	public void testSend() {

		List<User> users = this.mapper.selectForEmail();
		if (EmptyUtil.isNotEmpty(users)) {
			System.out.println("总用户数："+users.size());
			RestInvoker invoker = new RestInvoker();
			invoker.setBaseURL("http://send.vfunding.cn/send/email");
			// invoker.setBaseURL("http://localhost:9006/send/email");
			int i = 1;
			for (User user : users) {
				try {
					System.out.println("******发送邮件开始******");
					Map<String, String> map = new HashMap<String, String>();
					map.put("title", "微积金平台资金托管新增功能公告");
					map.put("addressesString", user.getEmail());
					map.put("content", this.createContentByUser(user));
					System.out.println("目标邮件：" + user.getEmail());
					invoker.post("", map);
					System.out.println("发送数：" + i);
					i++;
					System.out.println("******发送邮件结束******");
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}

			}

		}

	}

	private String createContentByUser(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div  style=\"width:715px;height:1400px;margin:0 auto;margin:0;padding:0;text-decoration:none;list-style:none;font-family:微软雅黑;color:#999;\">");

		sb.append("<div  style=\"width:715px;height:146px;margin:0 auto;border-bottom:thin groove #e5e5e5;\">");
		
		sb.append("<div  style=\"width:290px;height:146px;float:left;font-size:14px;text-indent:1.2em;\">");
		
		sb.append("<div  style=\"width:280px;height:95px;float:left;background-repeat:no-repeat;border-bottom: thin dashed #e5e5e5;\"><a href=\"http://www.vfunding.cn\"><img");
		
		sb.append("src=\"http://static.vfunding.cn/email/1.gif\"></a></div>");
		sb.append("<span style=\"line-height:50px;\">如果邮件无法正常显示，请<a href=\"#\" style=\"color:#6CF;cursor:pointer;\">点击这里>></a></span></div>");
		sb.append("<div  style=\"width:273px;height:128px;float:right;background-repeat:no-repeat;\"><img src=\"http://static.vfunding.cn/email/2.gif\"/></div>");
		
		sb.append("</div>");
		sb.append("<div style=\"width:750px; height:200px\"><img src=\"http://static.vfunding.cn/email/17.gif\"/></div>");
		sb.append("<div style=\"width:715px; height:71px\"><img src=\"http://static.vfunding.cn/email/3.gif\"/></div>");
		sb.append(" <div style=\"width:715px; height:205px\"><img src=\"http://static.vfunding.cn/email/5.gif\"/></div>");
		sb.append("<div style=\"width:715px; height:72px\"><img src=\"http://static.vfunding.cn/email/8.gif\"/></div>");
		sb.append("<div style=\"width:715px; height:280px;\"><img src=\"http://static.vfunding.cn/email/9.gif\"/></div>");
		sb.append("<div style=\"width:715px; height:105px;\"><img src=\"http://static.vfunding.cn/email/10.gif\"/></div>");
		sb.append("<div style=\"width:715px; height:400px;\"><a href=\"http://www.vfunding.cn/utilpage/toPage/trust\"><img src=\"http://static.vfunding.cn/email/12.gif\"/></a></div>");
		sb.append("</div>");
		return sb.toString();
	}

}
