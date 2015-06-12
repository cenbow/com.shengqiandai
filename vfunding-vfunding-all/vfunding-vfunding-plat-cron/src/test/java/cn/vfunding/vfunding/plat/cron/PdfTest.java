package cn.vfunding.vfunding.plat.cron;

import java.awt.Color;
import java.io.FileOutputStream;

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

public class PdfTest {

	public static void main(String[] args) {
		PdfTest t = new PdfTest();
		t.CreatePdf();

	}

	public void CreatePdf() {
		// TODO Auto-generated method stub
		// File file = new File(fileName);
		// FileOutputStream out = null;
		try {
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);

			// out = new FileOutputStream(file);
			PdfWriter.getInstance(document, new FileOutputStream("C:\\6.pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 18,
					Font.BOLD, new Color(0, 0, 0));
			Font fontChinese = null;
			// BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
			// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont(
					"c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			Paragraph titleN = new Paragraph("\n");
			// 中文大小为20，加粗
			font = new Font(bfChinese, 15, Font.BOLD);
			fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// Paragraph title = new Paragraph("亲爱的微积金客户：", font);
			// title.setAlignment(Element.ALIGN_LEFT);
			document.add(new Paragraph("亲爱的微积金客户：", font));
			document.add(new Paragraph("您好！", fontChinese));
			document.add(new Paragraph("很高兴您选择微积金作为您的理财小伙伴，微积金所有员工感谢您的信任！",
					fontChinese));
			document.add(new Paragraph(
					"接下来呈现在您面前的是我们特别为您制作的月度理财报告，让您轻松全面了解您在微积金", fontChinese));
			document.add(new Paragraph(
					"平台的本月理财收益情况，在阅读的过程中，如有什么疑问，欢迎咨询微积金客服人员，我", fontChinese));
			document.add(new Paragraph("们将竭诚为您服务，谢谢！", fontChinese));
			document.add(titleN);

			// 您的账户情况
			document.add(titleN);
			document.add(new Paragraph("您的账户情况（截止2014年2月28日24：00）：",
					fontChinese));
			Table aTable = new Table(2, 2);
			aTable.setAlignment(Element.ALIGN_LEFT);// 居中
			aTable.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			aTable.setBorderWidth(1);// 边框宽度
			aTable.addCell(new Paragraph("账户总额：10085", fontChinese));
			aTable.addCell(new Paragraph("可用余额：185", fontChinese));
			aTable.addCell(new Paragraph("已赚利息：85", fontChinese));
			aTable.addCell(new Paragraph("待收利息：35", fontChinese));
			document.add(aTable);
			document.add(new Paragraph("您的资金使用情况：", fontChinese));
			Image img = Image.getInstance("C:\\1.jpg");
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_CENTER);
			document.add(img);

			// 您投过的标的
			document.add(titleN);
			document.add(new Paragraph("您投过的标的：", fontChinese));
			Table bTable = new Table(5, 2);

			bTable.addCell(new Paragraph("借款标题", fontChinese));
			bTable.addCell(new Paragraph("投资时间", fontChinese));
			bTable.addCell(new Paragraph("投资本金", fontChinese));
			bTable.addCell(new Paragraph("投资收益", fontChinese));
			bTable.addCell(new Paragraph("收益时间", fontChinese));

			bTable.addCell(new Paragraph("伊特福林肯质押——信用极好，按时还款，谢谢！", fontChinese));
			bTable.addCell(new Paragraph("2014-02-27", fontChinese));
			bTable.addCell(new Paragraph("10000元", fontChinese));
			bTable.addCell(new Paragraph("85元", fontChinese));
			bTable.addCell(new Paragraph("2014-03-01", fontChinese));
			document.add(bTable);

			// 您的充值与提现记录：
			document.add(titleN);
			document.add(new Paragraph("您的充值与提现记录：", fontChinese));
			Table cTable = new Table(4, 2);
			cTable.addCell(new Paragraph("充值/提现", fontChinese));
			cTable.addCell(new Paragraph("充值/提现时间", fontChinese));
			cTable.addCell(new Paragraph("充值金额", fontChinese));
			cTable.addCell(new Paragraph("手续费", fontChinese));

			cTable.addCell(new Paragraph("充值", fontChinese));
			cTable.addCell(new Paragraph("2014-2-26", fontChinese));
			cTable.addCell(new Paragraph("20000", fontChinese));
			cTable.addCell(new Paragraph("0", fontChinese));
			document.add(cTable);

			// 账户安全情况
			document.add(titleN);
			document.add(new Paragraph("账户安全情况：", fontChinese));
			Table dTable = new Table(4, 1);

			dTable.addCell(new Paragraph("√身份证认证", fontChinese));
			dTable.addCell(new Paragraph("√手机认证", fontChinese));
			dTable.addCell(new Paragraph("√邮箱认证", fontChinese));
			dTable.addCell(new Paragraph("×VIP认证", fontChinese));
			document.add(dTable);
			//
			document.add(titleN);
			document.add(new Paragraph("您在微积金的投资排在51人之前", fontChinese));
			img = Image.getInstance("C:\\3.jpg");
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_LEFT);
			document.add(img);

			document.add(new Paragraph("您的投资走势为：", fontChinese));
			img = Image.getInstance("C:\\2.jpg");
			img.scalePercent(70f);
			img.setAlignment(Element.ALIGN_LEFT);
			document.add(img);

			// 最后的内容
			document.add(titleN);
			document.add(new Paragraph("微积金惠民理财计划：", fontChinese));
			document.add(new Paragraph(
					"亲爱的微积金的小伙伴，微积金平台不仅能够为您的投资带来稳定的收益, 还可以通过你介", fontChinese));
			document.add(new Paragraph(
					"绍的好友所产生的投资带来另一份稳定的收益。如果您对我们的服务还信赖的话，我们真挚", fontChinese));
			document.add(new Paragraph("的希望您将微积金介绍给您的朋友。", fontChinese));
			document.add(new Paragraph(
					"好友邀请链接：www.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
					fontChinese));
			document.add(new Paragraph(
					"好友推荐惠利方式：只要您的好友通过以上链接前往我们微积金注册并投资，您将获得一定", fontChinese));
			document.add(new Paragraph("的返利；推荐的好友越多，您所获得的返利越多。", fontChinese));
			document.add(new Paragraph(
					"具体活动解释，请参阅微积金好友推荐或微积金理财师活动，也欢迎您咨询我们的客服，祝", fontChinese));
			document.add(new Paragraph("您生活愉快，谢谢！", fontChinese));
			document.add(new Paragraph(
					"亲爱的XXXX，再次感谢您花费宝贵的时间阅读投资报告，也希望您对我们提出宝贵的改进", fontChinese));
			document.add(new Paragraph("的意见。您的满意是我们前进的最大动力。", fontChinese));
			Cell cell = new Cell();
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
			img = Image.getInstance("c:\\twocode.jpg");
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
			cell.setBorderWidth(0);
			eTable.addCell(cell);
			document.add(eTable);
			
			
			document.add(new Paragraph("欢迎您通过以下方式与我们互动：", fontChinese));
			document.add(new Paragraph("官方QQ群： 315344877", fontChinese));
			document.add(new Paragraph("新浪微博：http://weibo.com/vfunding",
					fontChinese));
			document.add(new Paragraph("微信账号：vfunding 二维码：", fontChinese));
			document.add(new Paragraph("免费电话：400-991-9999", fontChinese));
			document.add(new Paragraph("微积金团队", fontChinese));
			document.add(new Paragraph("20140228", fontChinese));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// if (out != null) {
			// try {
			// // 关闭输出文件流
			// out.close();
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }
			// }
		}
	}

}
