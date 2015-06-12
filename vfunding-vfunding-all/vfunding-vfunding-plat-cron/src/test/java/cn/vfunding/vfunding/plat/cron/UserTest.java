package cn.vfunding.vfunding.plat.cron;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.cron.service.IFinancialPlannerCronService;
import cn.vfunding.vfunding.biz.pdfreport.service.ISendEmailUserPdfService;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserPdfFileService;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserUsefundsPieService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class UserTest {

//	@Autowired
//	private IFinancialPlannerCronService fpService;

	@Autowired
	private IUserUsefundsPieService pieService;

	@Autowired
	private IUserPdfFileService pdfService;
	
	@Autowired
	private ISendEmailUserPdfService sendMailService;

//	@Resource(name = "emailRestInvokerFactory")
//	private RestInvokerFactory emailRestInvokerFactory;
//	
//	@Resource(name = "inviteRestInvokerFactory")
//	private RestInvokerFactory inviteRestInvokerFactory;

	@Test
	public void testSelectByList() {
		// this.fpService.callProcedure();
		// this.fpService.selectUserFinancialplanner();
//		this.pieService.selectUserUsefundsPie();
		this.pdfService.createPdfFile();
		
//		this.sendMailService.SendEmailUserPdf();

	}

	@Test
	public void testEmail() {
		try {
			Date date = new Date();
			int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
			int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder
					.create();
			File file = new File("C:\\6.pdf");
			InputStream input = new FileInputStream(file);
			String filePath ="c:\\6.pdf";
			String email = "258538429@qq.com";
			String fileName = "微积金用户投资报告.pdf";
			String title = year + "年" + month + "月微积金投资报告-";
			String content = "亲爱的微积金客户：<br>您好！<br>很高兴您伴随微积金（www.vfunding.cn）又度过了一个愉快的理财旅程，附件是微积金为您专程制作的上月理财投资报告，敬请查收！<br><br>温馨提示：<br>附件的投资报告为PDF格式，如果您不能正常打开，请下载有关PDF的软件或阅读器，因此给您造成的不便还请见谅。<br><span style='float: right;margin-right: 40px;'>微积金运营中心</span><br><br> ";
			this.sendEmail(filePath, email, fileName, title,
					content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendEmail(String filePath, String email, String fileName,
			String title, String content) {
//		try {
//			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder
//					.create();
//			File file = new File(filePath);
//			InputStream input = new FileInputStream(file);
//			multipartEntity.addPart("attachments", new InputStreamBody(input,
//					fileName));
//			multipartEntity.addPart("addressesString", new StringBody(email,
//					ContentType.create("text/plain", Consts.UTF_8)));
//			multipartEntity.addPart(
//					"title",
//					new StringBody(title, ContentType.create("text/plain",
//							Consts.UTF_8)));
//			multipartEntity.addPart("content", new StringBody(content,
//					ContentType.create("text/plain", Consts.UTF_8)));
//			this.emailRestInvokerFactory.getRestInvoker().postFiles(
//					"/attachment", multipartEntity);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}

	@Test
	public void testInviate() {
		try {
			//HttpRequester request = new HttpRequester();

			/*HttpRespons hr = request
					.sendPost("http://192.168.3.115/test.php?user_id=1");
			String r = hr.getContent();
			System.out.println(r);*/
//			String s = this.inviteRestInvokerFactory.getRestInvoker().get("?user_id=1", String.class);
//			System.out.println(s);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String pathMark =File.separator;
		System.out.println(pathMark);
	}
}
