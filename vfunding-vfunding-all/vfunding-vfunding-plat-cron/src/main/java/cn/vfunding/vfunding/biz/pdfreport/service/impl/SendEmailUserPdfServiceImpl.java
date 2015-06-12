package cn.vfunding.vfunding.biz.pdfreport.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.pdfreport.dao.UserPdfInfoMapper;
import cn.vfunding.vfunding.biz.pdfreport.model.UserPdfInfo;
import cn.vfunding.vfunding.biz.pdfreport.service.ISendEmailUserPdfService;

@Service
public class SendEmailUserPdfServiceImpl implements ISendEmailUserPdfService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserPdfInfoMapper pdfMapper;

	@Resource(name = "emailRestInvokerFactory")
	private RestInvokerFactory emailRestInvokerFactory;

	@Value("${report.pdf.path}")
	private String pdfPath;

	@Value("${report.default.pdf.path}")
	private String defaultPdfPath;

	@Override
	public void SendEmailUserPdf() {
		// TODO Auto-generated method stub
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
		if (month == 0) {
			year -= 1;
			month = 12;
		}
		try {
			// List<UserPdfInfo> pdfList = this.pdfMapper.selectSendEmailUser();
			List<UserPdfInfo> list = this.pdfMapper.selectUserInfo();
			for (UserPdfInfo userPdfInfo : list) {
				String filePath = pdfPath + userPdfInfo.getUserId() + "pdf.pdf";
				String email = userPdfInfo.getUserEmail();
				String fileName = "微积金用户投资报告.pdf";
				String title = year + "年" + month + "月微积金投资报告-"
						+ userPdfInfo.getUserName();
				Integer userid = userPdfInfo.getTenderUserId();
				String content = "";
				if (userid != 0) {
					// 生成三个月有过投资动作的用户
					content = "<div class='main' style=' width: 660px; margin:20px auto 40px; overflow: hidden;  padding: 0 40px 60px;  background-color: #fff;'>"
							+ "<p class='hello' style='height: 80px; line-height: 80px; font-size: 20px; font-weight: bold;'>Hello,小伙伴</p>"
							+ "<p class='n-cnt' style='line-height: 30px; '>阳春三月已过，感谢您选择微积金，感谢您的陪伴，没有您的支持与理解，就没有微积金的成功</p>"
							+ "<p class='n-cnt' style='line-height: 30px; '> 情暖四月天，微积金在本月将推出“春季又出新玩法！加息连连看，一次玩过瘾！”的活动；</p>"
							+ "<p class='n-cnt' style='line-height: 30px; '>更有“抽奖有惊喜”的活动，苹果手表/踏青基金等您来赢。</p>"
							+ "<p class='n-cnt' style='line-height: 30px; '>活动详情：<a href='http://www.vfunding.cn/utilpage/toPage/aprilActivities'>http://www.vfunding.cn/utilpage/toPage/aprilActivities</a></p>"
							+ "<p class='n-cnt' style='line-height: 30px; '>感谢您的阅读，附件是您的3月的月度投资报告，请您查收，祝您生活愉快！</p>"
							+ "<p class='team' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>微积金团队</p>"
							+ "<p class='time' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>2015年4月10日</p>"
							+ "<p class='time' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>微信号:vfunding88</p>"
							+ "</div>";
				} else {
					// 生成三个月内没有投资动作的用户
					content = "<div class='main' style=' width: 660px; margin:20px auto 40px; overflow: hidden; padding: 0 40px 60px; background-color: #fff;'>"
							+ "<p class='hello' style='height: 80px; line-height: 80px; font-size: 20px; font-weight: bold;'>Hello,小伙伴</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>阳春三月已过，遗憾的是少了您的参与,微积金的3月活动福利来不及惠及您。</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>情暖四月天，新人礼继续：所有新用户首笔投资收益提升50%；</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>微积金在本月将推出“春季又出新玩法！加息连连看，一次玩过瘾！”的活动；</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>更有“抽奖有惊喜”的活动，苹果手表/踏青基金等您来赢。</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>活动详情：<a href='http://www.vfunding.cn/utilpage/toPage/aprilActivities'>http://www.vfunding.cn/utilpage/toPage/aprilActivities</a></p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>股市潮起潮落，不要让钱Duang~Duang的石沉大海，</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>擦亮双眼，谨慎投资，微积金让您稳中求收益。</p>"
							+ "<p class='n-cnt' style='line-height: 30px;'>感谢您的阅读，附件是您的3月的月度投资报告，请您查收，祝您生活愉快！</p>"
							+ "<p class='team' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>微积金团队</p>"
							+ "<p class='time' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>2015年4月10日</p>"
							+ "<p class='time' style='text-align: right; line-height: 40px; margin-top: 10px; padding-right: 80px;'>微信号:vfunding88</p>"
							+ "</div>";
				}
				if (EmptyUtil.isNotEmpty(email) && !email.equals("")) {
					if (new File(filePath).exists()) {
						this.sendEmail(filePath, email, fileName, title,
								content);
						Thread.sleep(500);
					} else {
						logger.info("用户" + userPdfInfo.getUserName()
								+ "没有生成PDF文件");
					}
				} else {
					logger.info("用户" + userPdfInfo.getUserName() + "没有email验证");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void sendEmail(String filePath, String email, String fileName,
			String title, String content) {
		try {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder
					.create();
			File file = new File(filePath);
			InputStream input = new FileInputStream(file);
			multipartEntity.addPart("attachments", new InputStreamBody(input,
					fileName));
			multipartEntity.addPart("addressesString", new StringBody(email,
					ContentType.create("text/plain", Consts.UTF_8)));
			multipartEntity.addPart(
					"title",
					new StringBody(title, ContentType.create("text/plain",
							Consts.UTF_8)));
			multipartEntity.addPart("content", new StringBody(content,
					ContentType.create("text/plain", Consts.UTF_8)));
			this.emailRestInvokerFactory.getRestInvoker().postFiles(
					"/attachment", multipartEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
