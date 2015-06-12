package cn.vfunding.vfunding.biz.pdfreport.service.impl;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.pdfreport.dao.UserPdfInfoMapper;
import cn.vfunding.vfunding.biz.pdfreport.dao.UserRechargeCashInfoMapper;
import cn.vfunding.vfunding.biz.pdfreport.dao.UserTenderInfoMapper;
import cn.vfunding.vfunding.biz.pdfreport.model.UserPdfInfo;
import cn.vfunding.vfunding.biz.pdfreport.model.UserRechargeCashInfo;
import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderInfo;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserPdfFileService;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class UserPdfFileServiceImpl implements IUserPdfFileService {

	@Autowired
	private UserPdfInfoMapper pdfMapper;
	@Autowired
	private UserTenderInfoMapper tenderMapper;
	@Autowired
	private UserRechargeCashInfoMapper rcMapper;
	@Autowired
	private IInviteCodeService inviteCodeService;
	@Resource(name = "inviteRestInvokerFactory")
	private RestInvokerFactory inviteRestInvokerFactory;

	@Value("${report.image.path}")
	private String imagePath;

	@Value("${report.pdf.path}")
	private String pdfPath;

	@Value("${report.default.image.path}")
	private String defaultImagePath;

	@Value("${report.default.footer.path}")
	private String footerPath;

	@Value("${report.fonts.path}")
	private String fontsPath;

	private Integer userCount = 0;
	private DecimalFormat df = new DecimalFormat("#0.00");

	@Override
	public void createPdfFile() {

		List<UserPdfInfo> list = this.pdfMapper.selectUserInfo();
		UserPdfInfo alltender = this.pdfMapper.selectAllTenderInfo();
		for (UserPdfInfo userpdf : list) {
			Integer userid = userpdf.getTenderUserId();
			if (userid != 0) {
				userCount++;
			}
		}
		for (UserPdfInfo userpdf : list) {
			Integer userid = userpdf.getTenderUserId();
			if (userid != 0) {
				// 生成三个月有过投资动作的用户
				this.createPdf(userpdf);
			} else {
				// 生成三个月内没有投资动作的用户
				userpdf.setAllAccount(alltender.getAllAccount());
				userpdf.setAllInterest(alltender.getAllInterest());
				this.createpdfNoTender(userpdf);
			}
		}
	}

	public void createpdfNoTender(UserPdfInfo userpdf) {
		try {
			// 提现-充值明细
			List<UserRechargeCashInfo> rcList = this.rcMapper.selectUserRechargeCash(userpdf.getUserId());
			// 用户排名
			// Integer ranking =
			// this.tenderMapper.selectUserRanking(userpdf.getUserId());
			// 生成邀请用户码
			StringBuilder sb = new StringBuilder();
			sb.append("introducer=" + userpdf.getUserName());
			String inviteCode = EncryptionUtil.encrypt(sb.toString());

			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			// out = new FileOutputStream(file);
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath + userpdf.getUserId() + "pdf.pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new Color(0, 0, 0));
			// BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
			// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Paragraph titleN = new Paragraph("\n");
			// 中文大小为20，加粗
			font = new Font(bfChinese, 15, Font.BOLD);
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// Paragraph title = new Paragraph("亲爱的微积金客户：", font);
			// title.setAlignment(Element.ALIGN_LEFT);
			document.add(new Paragraph("亲爱的微积金客户：", font));
			document.add(new Paragraph("您好！", fontChinese));
			document.add(new Paragraph("很高兴您选择微积金作为您的理财小伙伴，微积金所有员工感谢您的信任！", fontChinese));
			document.add(new Paragraph("不知道什么原因您注册微积金网站后的理财行为乏善可陈。", fontChinese));
			document.add(new Paragraph("我们认为在货币长期贬值，以及CPI不断攀升的环境下，不理财比理财将面临更多的风险。", fontChinese));
			document.add(new Paragraph("你不理财，财也不会理你！所以，选择低风险、稳定、安全、高收益的微积金平台理财，将", fontChinese));
			document.add(new Paragraph("为您带来更多的收益与保障。", fontChinese));
			document.add(new Paragraph("在阅读的过程中，如有什么疑问，欢迎咨询微积金客服人员，我们将竭诚为您服务，谢谢！", fontChinese));

			// 您的账户情况
			document.add(titleN);
			List<Integer> ymd = this.getYearMonthDay();
			document.add(new Paragraph("微积金客户账户情况（截止" + ymd.get(0) + "年" + ymd.get(1) + "月" + ymd.get(2) + "日24：00）：", fontChinese));
			Cell cell = new Cell();
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			Table aTable = new Table(2, 3);
			aTable.setPadding(2);
			aTable.setAlignment(Element.ALIGN_LEFT);// 居中
			aTable.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			aTable.setBorderWidth(1);// 边框宽度
			cell = new Cell(new Paragraph("理财总额：", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph(df.format(userpdf.getAllAccount()) + "元", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph("已为客户赚取利息：", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph(df.format(userpdf.getAllInterest()) + "元", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph("已为您赚取利息：", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph(df.format(userpdf.getUserHavaInterestMoney()) + "元", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			document.add(aTable);
			Image img = null;
			// 您的充值与提现记录：
			document.add(titleN);
			document.add(new Paragraph("上月您的充值与提现记录：", fontChinese));
			Table cTable = new Table(4, rcList.size() + 2);
			cTable.setPadding(2);
			cell = new Cell(new Paragraph("充值/提现", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);
			cell = new Cell(new Paragraph("操作时间", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);
			cell = new Cell(new Paragraph("充值/提现金额", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);
			cell = new Cell(new Paragraph("手续费", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);
			if (rcList.size() == 0) {
				cell = new Cell(new Paragraph("上月没有充值/提现信息", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 水平居中
				cell.setColspan(4);
				cTable.addCell(cell);
			}
			for (UserRechargeCashInfo rc : rcList) {
				cell = new Cell(new Paragraph(rc.getActionType(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);
				cell = new Cell(new Paragraph(rc.getAddtime(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);
				cell = new Cell(new Paragraph(rc.getMoney() + "", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);
				cell = new Cell(new Paragraph(rc.getFee() + "", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);
			}
			document.add(cTable);

			// 账户安全情况
			document.add(titleN);
			document.add(new Paragraph("账户安全情况：", fontChinese));
			Table dTable = new Table(4, 1);
			dTable.setPadding(2);
			if (userpdf.getRealStatus() == 1) {
				cell = new Cell(new Paragraph("√身份证认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×身份证认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (userpdf.getPhoneStatus() == 1) {
				cell = new Cell(new Paragraph("√手机认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×手机认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (userpdf.getEmailStatus() == 1) {
				cell = new Cell(new Paragraph("√邮箱认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×邮箱认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (EmptyUtil.isNotEmpty(userpdf.getVipStatus()) && userpdf.getVipStatus() == 1) {
				cell = new Cell(new Paragraph("√VIP认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×VIP认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			document.add(dTable);
			document.add(new Paragraph("        注：√表示认证完成，×表示认证未完成。", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("您的投资走势为（对比平台用户平均投资走势）：", fontChinese));
			String fileAd = imagePath + userpdf.getUserId() + "line.jpg";
			if (new File(fileAd).exists()) {
				img = Image.getInstance(fileAd);
			} else {
				img = Image.getInstance(defaultImagePath + "defaultLine.jpg");
			}
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_CENTER);
			document.add(img);
			// 您在微积金的投资排在
			document.add(new Paragraph("你在微积金的理财行为乏善可陈，努力啊！你不理财，财不理你！", fontChinese));
			img = Image.getInstance(defaultImagePath + "fiveRanking.jpg");
			img.setAlignment(Element.ALIGN_LEFT);
			img.scalePercent(70f);
			document.add(img);

			// 最后的内容
			document.add(titleN);
			document.add(new Paragraph("微积金惠民理财计划：", fontChinese));
			document.add(new Paragraph("亲爱的微积金的小伙伴，微积金平台不仅能够为您的投资带来稳定的收益, 还可以通过你介", fontChinese));
			document.add(new Paragraph("绍的好友所产生的投资带来另一份稳定的收益。如果您对我们的服务还信赖的话，我们真挚", fontChinese));
			document.add(new Paragraph("的希望您将微积金介绍给您的朋友。", fontChinese));
			document.add(new Paragraph("好友邀请链接：", fontChinese));
			InviteCode ic = inviteCodeService.selectByUserId(userpdf.getUserId());
			document.add(new Paragraph("http://www.vfunding.cn/fp/inviteRegister/" + ic.getInviteCode(), fontChinese));
			
			document.add(new Paragraph("好友推荐惠利方式：只要您的好友通过以上链接前往我们微积金注册并投资，您将获得一定", fontChinese));
			document.add(new Paragraph("的返利；推荐的好友越多，您所获得的返利越多。", fontChinese));
			document.add(new Paragraph("具体活动解释，请参阅微积金好友推荐或微积金理财师活动，也欢迎您咨询我们的客服，祝", fontChinese));
			document.add(new Paragraph("您生活愉快，谢谢！", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("感谢您本次的阅读，感恩您对我们的一路信任与支持！", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("活动通知", fontChinese));
			document.add(new Paragraph("微积金在4月将推出“春季又出新玩法！加息连连看，一次玩过瘾！”的活动；新人礼keep moving：所有新用户首笔投资收益提升50%奖励；", fontChinese));
			document.add(new Paragraph("情暖四月天，更有“抽奖有惊喜”的活动，苹果手表/踏青基金等您来赢。", fontChinese));
			document.add(new Paragraph("活动详情：http://www.vfunding.cn/utilpage/toPage/aprilActivities", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("平台动态", fontChinese));
			document.add(new Paragraph("2015年2月10日微积金平台和新浪支付合作，实现资金全面托管，使用户资金更加安全透明，余额也生息。微积金又产生一座里程碑！感谢伙伴们对微积金的信任与支持！", fontChinese));
			document.add(new Paragraph("详情请戳：http://www.vfunding.cn/utilpage/toPage/trust", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("媒体报道", fontChinese));
			document.add(new Paragraph("点击阅读全文", fontChinese));
			document.add(new Paragraph("微积金:服务再升级，余额也生息", fontChinese));
			document.add(new Paragraph("中国财经新闻网", fontChinese));
			document.add(new Paragraph("http://www.zgcjnews.com/caijing/yaowen/2015-04-07/50386.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("网易新闻", fontChinese));
			document.add(new Paragraph("http://news.163.com/15/0407/15/AMK1TNCK00014AEE.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("中财网", fontChinese));
			document.add(new Paragraph("http://www.chinacw.com.cn/2015/0407/73782.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("P2P监管出台在即，自律与互律并行", fontChinese));
			document.add(new Paragraph("中国财经新闻网", fontChinese));
			document.add(new Paragraph("http://www.zgcjnews.com/caijing/yaowen/2015-04-07/50384.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("网易新闻", fontChinese));
			document.add(new Paragraph("http://news.163.com/15/0407/15/AMK1TQHU00014AEE.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("中财网", fontChinese));
			document.add(new Paragraph("http://www.chinacw.com.cn/2015/0407/73784.html", fontChinese));
			document.add(titleN);

			String encoding = "utf-8";

			File file = new File(footerPath);
			// 未投资用户
			// File file = new File(footerPathUninvest);

			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					document.add(new Paragraph(lineTxt, fontChinese));
				}
				read.close();
			}

			Table eTable = new Table(2, 5);
			eTable.setPadding(3);
			eTable.setWidth(101);
			eTable.setBorderWidth(0);
			eTable.setAutoFillEmptyCells(true);
			eTable.setBorder(0);
			cell = new Cell(new Paragraph("欢迎您通过以下方式与我们互动：", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			img = Image.getInstance(defaultImagePath + "twocode.jpg");
			img.setAlignment(Element.ALIGN_CENTER);
			img.scalePercent(70f);
			cell = new Cell(img);
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setRowspan(5);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("官方QQ群： 315344877", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("新浪微博：http://weibo.com/vfunding", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("微信账号：vfunding88 二维码：", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("免费电话：400-991-9999", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			document.add(eTable);
			document.add(new Paragraph("（如有疑惑或想退订本邮件，请通过以上方式与我们联系，谢谢！）", fontChinese));
			document.add(titleN);

			document.add(titleN);
			document.add(new Paragraph("扫二维码即可下载微积金APP：", fontChinese));
			img = Image.getInstance(defaultImagePath + "appTwocode.jpg");
			img.setAlignment(Element.ALIGN_CENTER);
			img.scalePercent(70f);
			document.add(img);

			Paragraph p = new Paragraph("微积金团队", fontChinese);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.add(titleN);
			p = new Paragraph(ymd.get(0) + "-" + ymd.get(1) + "-" + ymd.get(2), fontChinese);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createPdf(UserPdfInfo userpdf) {
		try {
			// 投资详细明细
			List<UserTenderInfo> tenderList = this.tenderMapper.selectUserTenderInfo(userpdf.getUserId());
			// 提现-充值明细
			List<UserRechargeCashInfo> rcList = this.rcMapper.selectUserRechargeCash(userpdf.getUserId());
			// 用户排名
			Integer ranking = this.tenderMapper.selectUserRanking(userpdf.getUserId());

			// String inviteCode =
			// this.inviteRestInvokerFactory.getRestInvoker().get(
			// "/get_invite.php?user_id=" + userpdf.getUserId(), String.class);
			// 生成邀请用户码
			StringBuilder sb = new StringBuilder();
			sb.append("introducer=" + userpdf.getUserName());
			String inviteCode = EncryptionUtil.encrypt(sb.toString());

			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			// out = new FileOutputStream(file);
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath + userpdf.getUserId() + "pdf.pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new Color(0, 0, 0));
			// BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
			// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Paragraph titleN = new Paragraph("\n");
			// 中文大小为20，加粗
			font = new Font(bfChinese, 15, Font.BOLD);
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// Paragraph title = new Paragraph("亲爱的微积金客户：", font);
			// title.setAlignment(Element.ALIGN_LEFT);
			document.add(new Paragraph("亲爱的微积金客户：", font));
			document.add(new Paragraph("您好！", fontChinese));
			document.add(new Paragraph("很高兴您选择微积金作为您的理财小伙伴，微积金所有员工感谢您的信任！", fontChinese));
			document.add(new Paragraph("接下来呈现在您面前的是我们特别为您制作的月度理财报告，让您轻松全面了解您在微积金", fontChinese));
			document.add(new Paragraph("平台的本月理财收益情况，在阅读的过程中，如有什么疑问，欢迎咨询微积金客服人员，我", fontChinese));
			document.add(new Paragraph("们将竭诚为您服务，谢谢！", fontChinese));
			document.add(titleN);

			// 您的账户情况
			document.add(titleN);
			List<Integer> ymd = this.getYearMonthDay();
			document.add(new Paragraph("您的账户情况（截止" + ymd.get(0) + "年" + ymd.get(1) + "月" + ymd.get(2) + "日24：00）：", fontChinese));
			Cell cell = new Cell();
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			Table aTable = new Table(2, 2);
			aTable.setPadding(2);
			aTable.setAlignment(Element.ALIGN_LEFT);// 居中
			aTable.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			aTable.setBorderWidth(1);// 边框宽度
			cell = new Cell(new Paragraph("账户总额：" + df.format(userpdf.getTotalMoney()), fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph("可用余额：" + df.format(userpdf.getUseMoney()), fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph("已赚利息：" + df.format(userpdf.getUserHavaInterestMoney()), fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			cell = new Cell(new Paragraph("好友返利：" + df.format(userpdf.getUserSonMoney()), fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			aTable.addCell(cell);
			document.add(aTable);
			document.add(titleN);
			document.add(new Paragraph("您的资金使用情况：", fontChinese));
			Image img = null;
			String pieImages = imagePath + userpdf.getUserId() + "pie.jpg";
			if (new File(pieImages).exists()) {
				img = Image.getInstance(pieImages);
			} else {
				img = Image.getInstance(defaultImagePath + "defaultPie.jpg");
			}
			img.setAlignment(Element.ALIGN_CENTER);
			img.scalePercent(70f);
			document.add(img);
			// 您投过的标的
			document.add(titleN);
			document.add(new Paragraph("上月您投过的标的：", fontChinese));

			Table bTable = new Table(5, tenderList.size() + 1);
			bTable.setPadding(2);
			cell = new Cell(new Paragraph("借款标题", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			bTable.addCell(cell);
			cell = new Cell(new Paragraph("投资时间", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			bTable.addCell(cell);
			cell = new Cell(new Paragraph("投资本金", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			bTable.addCell(cell);
			cell = new Cell(new Paragraph("投资收益", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			bTable.addCell(cell);
			cell = new Cell(new Paragraph("收益时间", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			bTable.addCell(cell);
			if (tenderList.size() == 0) {
				cell = new Cell(new Paragraph("上月没有投资信息", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 水平居中
				cell.setColspan(5);
				bTable.addCell(cell);
			}
			for (UserTenderInfo tender : tenderList) {
				cell = new Cell(new Paragraph(tender.getTenderName(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				bTable.addCell(cell);

				cell = new Cell(new Paragraph(tender.getTendertime(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				bTable.addCell(cell);

				cell = new Cell(new Paragraph(tender.getAccount() + "元", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				bTable.addCell(cell);

				cell = new Cell(new Paragraph(tender.getInterest() + "元", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				bTable.addCell(cell);

				cell = new Cell(new Paragraph(tender.getRepaymenttime(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				bTable.addCell(cell);
			}
			document.add(bTable);
			// 您的充值与提现记录：
			document.add(titleN);
			document.add(new Paragraph("上月您的充值与提现记录：", fontChinese));

			Table cTable = new Table(4, rcList.size() + 2);
			cTable.setPadding(2);
			cell = new Cell(new Paragraph("充值/提现", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);

			cell = new Cell(new Paragraph("操作时间", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);

			cell = new Cell(new Paragraph("充值/提现金额", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);

			cell = new Cell(new Paragraph("手续费", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cTable.addCell(cell);
			if (rcList.size() == 0) {
				cell = new Cell(new Paragraph("上月没有充值/提现信息", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 水平居中
				cell.setColspan(4);
				cTable.addCell(cell);
			}
			for (UserRechargeCashInfo rc : rcList) {

				cell = new Cell(new Paragraph(rc.getActionType(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);

				cell = new Cell(new Paragraph(rc.getAddtime(), fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);

				cell = new Cell(new Paragraph(rc.getMoney() + "", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);

				cell = new Cell(new Paragraph(rc.getFee() + "", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cTable.addCell(cell);
			}
			document.add(cTable);

			// 账户安全情况
			document.add(titleN);
			document.add(new Paragraph("账户安全情况：", fontChinese));
			Table dTable = new Table(4, 1);
			dTable.setPadding(2);
			if (userpdf.getRealStatus() == 1) {
				cell = new Cell(new Paragraph("√身份证认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×身份证认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (userpdf.getPhoneStatus() == 1) {
				cell = new Cell(new Paragraph("√手机认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×手机认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (userpdf.getEmailStatus() == 1) {
				cell = new Cell(new Paragraph("√邮箱认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×邮箱认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			if (EmptyUtil.isNotEmpty(userpdf.getVipStatus()) && userpdf.getVipStatus() == 1) {
				cell = new Cell(new Paragraph("√VIP认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			} else {
				cell = new Cell(new Paragraph("×VIP认证", fontChinese));
				cell.setUseAscender(true);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				dTable.addCell(cell);
			}
			document.add(dTable);
			document.add(new Paragraph("        注：√表示认证完成，×表示认证未完成。", fontChinese));

			document.add(titleN);
			// 您在微积金的投资排在
			document.add(new Paragraph("您在微积金的投资排在" + (ranking + 3) + "人之前", fontChinese));
			Double r = new Double(ranking) / new Double(userCount);
			if (r < 0.4d) {
				img = Image.getInstance(defaultImagePath + "twoRanking.jpg");
			} else {
				img = Image.getInstance(defaultImagePath + "fourRanking.jpg");
			}
			img.setAlignment(Element.ALIGN_LEFT);
			img.scalePercent(70f);
			document.add(img);
			document.add(new Paragraph("您的投资走势为：", fontChinese));
			String fileAd = imagePath + userpdf.getUserId() + "line.jpg";
			if (new File(fileAd).exists()) {
				img = Image.getInstance(fileAd);
			} else {
				img = Image.getInstance(defaultImagePath + "defaultLine.jpg");
			}
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_CENTER);
			document.add(img);

			// 最后的内容
			document.add(titleN);
			document.add(new Paragraph("微积金惠民理财计划：", fontChinese));
			document.add(new Paragraph("亲爱的微积金的小伙伴，微积金平台不仅能够为您的投资带来稳定的收益, 还可以通过你介", fontChinese));
			document.add(new Paragraph("绍的好友所产生的投资带来另一份稳定的收益。如果您对我们的服务还信赖的话，我们真挚", fontChinese));
			document.add(new Paragraph("的希望您将微积金介绍给您的朋友。", fontChinese));
			document.add(new Paragraph("好友邀请链接：", fontChinese));
			InviteCode ic = inviteCodeService.selectByUserId(userpdf.getUserId());
			document.add(new Paragraph("http://www.vfunding.cn/fp/inviteRegister/" + ic.getInviteCode(), fontChinese));
			document.add(new Paragraph("好友推荐惠利方式：只要您的好友通过以上链接前往我们微积金注册并投资，您将获得一定", fontChinese));
			document.add(new Paragraph("的返利；推荐的好友越多，您所获得的返利越多。", fontChinese));
			document.add(new Paragraph("具体活动解释，请参阅微积金好友推荐或微积金理财师活动，也欢迎您咨询我们的客服，祝", fontChinese));
			document.add(new Paragraph("您生活愉快，谢谢！", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("感谢您本次的阅读，感恩您对我们的一路信任与支持！", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("活动通知", fontChinese));
			document.add(new Paragraph("微积金在4月将推出“春季又出新玩法！加息连连看，一次玩过瘾！”的活动；新人礼keep moving：所有新用户首笔投资收益提升50%奖励；", fontChinese));
			document.add(new Paragraph("情暖四月天，更有“抽奖有惊喜”的活动，苹果手表/踏青基金等您来赢。", fontChinese));
			document.add(new Paragraph("活动详情：http://www.vfunding.cn/utilpage/toPage/aprilActivities", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("平台动态", fontChinese));
			document.add(new Paragraph("2015年2月10日微积金平台和新浪支付合作，实现资金全面托管，使用户资金更加安全透明，余额也生息。微积金又产生一座里程碑！感谢伙伴们对微积金的信任与支持！", fontChinese));
			document.add(new Paragraph("详情请戳：http://www.vfunding.cn/utilpage/toPage/trust", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("媒体报道", fontChinese));
			document.add(new Paragraph("点击阅读全文", fontChinese));
			document.add(new Paragraph("微积金:服务再升级，余额也生息", fontChinese));
			document.add(new Paragraph("中国财经新闻网", fontChinese));
			document.add(new Paragraph("http://www.zgcjnews.com/caijing/yaowen/2015-04-07/50386.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("网易新闻", fontChinese));
			document.add(new Paragraph("http://news.163.com/15/0407/15/AMK1TNCK00014AEE.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("中财网", fontChinese));
			document.add(new Paragraph("http://www.chinacw.com.cn/2015/0407/73782.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("P2P监管出台在即，自律与互律并行", fontChinese));
			document.add(new Paragraph("中国财经新闻网", fontChinese));
			document.add(new Paragraph("http://www.zgcjnews.com/caijing/yaowen/2015-04-07/50384.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("网易新闻", fontChinese));
			document.add(new Paragraph("http://news.163.com/15/0407/15/AMK1TQHU00014AEE.html", fontChinese));
			document.add(titleN);
			document.add(new Paragraph("中财网", fontChinese));
			document.add(new Paragraph("http://www.chinacw.com.cn/2015/0407/73784.html", fontChinese));
			document.add(titleN);

			String encoding = "utf-8";
			File file = new File(footerPath);
			// 投资用户
			// File file = new File(footerPathInvest);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					document.add(new Paragraph(lineTxt, fontChinese));
				}
				read.close();
			}

			Table eTable = new Table(2, 5);
			eTable.setPadding(3);
			eTable.setWidth(101);
			eTable.setBorderWidth(0);
			eTable.setAutoFillEmptyCells(true);
			eTable.setBorder(0);
			cell = new Cell(new Paragraph("欢迎您通过以下方式与我们互动：", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			img = Image.getInstance(defaultImagePath + "twocode.jpg");
			img.setAlignment(Element.ALIGN_CENTER);
			img.scalePercent(70f);
			cell = new Cell(img);
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setRowspan(5);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("官方QQ群： 315344877", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("新浪微博：http://weibo.com/vfunding", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("微信账号：vfunding 二维码：", fontChinese));
			cell.setUseAscender(true);
			cell.setBorderWidth(0);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			eTable.addCell(cell);
			cell = new Cell(new Paragraph("免费电话：400-991-9999", fontChinese));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setBorderWidth(0);
			eTable.addCell(cell);
			document.add(eTable);

			document.add(titleN);
			document.add(new Paragraph("扫二维码即可下载微积金APP：", fontChinese));
			img = Image.getInstance(defaultImagePath + "appTwocode.jpg");
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_LEFT);
			document.add(img);

			Paragraph p = new Paragraph("微积金团队", fontChinese);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.add(titleN);
			p = new Paragraph(ymd.get(0) + "-" + ymd.get(1) + "-" + ymd.get(2), fontChinese);
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Integer> getYearMonthDay() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
		int day = 0;
		if (month == 0) {
			year -= 1;
			month = 12;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			day = 30;
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day = 31;
		} else if (month == 2) {
			if (year % 4 > 0) {
				day = 28;
			} else if (year % 100 == 0 && year % 400 > 0) {
				day = 28;
			} else {
				day = 29;
			}
		}
		List<Integer> time = new ArrayList<Integer>();
		time.add(year);
		time.add(month);
		time.add(day);
		return time;
	}

	public static void main(String[] args) {
		// UserPdfFileServiceImpl service = new UserPdfFileServiceImpl();
		// // new File("c:/test.txt").exists()
		// service.CreatePdf(4);
		UserPdfInfo u = new UserPdfInfo();
		Double a = 16785631.3599999994000000000000000000000d;
		u.setAllAccount(a);
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println(df.format(u.getAllAccount()));
	}

}
