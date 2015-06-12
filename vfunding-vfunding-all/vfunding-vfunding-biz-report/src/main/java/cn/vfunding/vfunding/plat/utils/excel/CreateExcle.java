package cn.vfunding.vfunding.plat.utils.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;

import cn.vfunding.vfunding.biz.report.model.FinanceReportData;
import cn.vfunding.vfunding.biz.report.model.OperateReportdays;
import cn.vfunding.vfunding.biz.report.model.OperateReportdaysTwo;

public class CreateExcle {

	public static void createOperateReportdays(List<OperateReportdays> list,
			ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			// 打开文件
			// WritableWorkbook book = Workbook.createWorkbook(new
			// File(filePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("每日平台数据", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			sheet.addCell(new Label(0, 0, "用户总数"));
			sheet.addCell(new Label(1, 0, "当月新增用户"));
			sheet.addCell(new Label(2, 0, "当日新增用户"));
			sheet.addCell(new Label(3, 0, "当日用户登录数"));
			sheet.addCell(new Label(4, 0, "当日登录总数"));
			sheet.addCell(new Label(5, 0, "当前充值总人数"));
			sheet.addCell(new Label(6, 0, "当日充值人数"));
			sheet.addCell(new Label(7, 0, "当日投标人数"));
			sheet.addCell(new Label(8, 0, "当日充值总额"));
			sheet.addCell(new Label(9, 0, "当前充值总额"));
			sheet.addCell(new Label(10, 0, "当日投标总额"));
			sheet.addCell(new Label(11, 0, "当日用户总余额"));
			sheet.addCell(new Label(12, 0, "当日发标总额"));
			sheet.addCell(new Label(13, 0, "当前总标数"));
			sheet.addCell(new Label(14, 0, "当前投标总额"));
			sheet.addCell(new Label(15, 0, "当前成功总标数"));
			sheet.addCell(new Label(16, 0, "统计时间"));
			int numberCount = 0;
			for (int i = 0; i < list.size(); i++) {
				OperateReportdays data = list.get(i);
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getZyh()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDyxzyh()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrxzyh()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDryhdls()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrdlzs()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqczzrs()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrczrs()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrtbrs()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrczze()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqczze()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrtbze()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDryhzye()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrfbze()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqzbs()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqptbze()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqcgzbs()));
				sheet.addCell(new Label(numberCount++, i + 1, data
						.getFromaddtimeStr()));
				numberCount = 0;
			}
			book.write();
			out.flush();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createOperateReportdaysTwo(
			List<OperateReportdaysTwo> list, ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			// 打开文件
			// WritableWorkbook book = Workbook.createWorkbook(new
			// File(filePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("每周平台数据明细", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Integer lableColumn = 0;
			sheet.addCell(new Label(lableColumn++, 0, "累计注册用户"));
			sheet.addCell(new Label(lableColumn++, 0, "当月注册用户数"));
			sheet.addCell(new Label(lableColumn++, 0, "累计发表数"));
			sheet.addCell(new Label(lableColumn++, 0, "当日发表总额"));
			sheet.addCell(new Label(lableColumn++, 0, "当日普通用户注册人数"));
			sheet.addCell(new Label(lableColumn++, 0, "当日集团用户注册人数"));
			sheet.addCell(new Label(lableColumn++, 0, "累计外部投资金额"));
			sheet.addCell(new Label(lableColumn++, 0, "累计外部投资人数"));
			sheet.addCell(new Label(lableColumn++, 0, "累计集团投资金额"));
			sheet.addCell(new Label(lableColumn++, 0, "累计集团投资人数"));
			sheet.addCell(new Label(lableColumn++, 0, "当前已经还款金额"));
			sheet.addCell(new Label(lableColumn++, 0, "当前累计投资金额"));
			sheet.addCell(new Label(lableColumn++, 0, "当月还款金额"));
			sheet.addCell(new Label(lableColumn++, 0, "当月应还款标数"));
			sheet.addCell(new Label(lableColumn++, 0, "当月发表数"));
			sheet.addCell(new Label(lableColumn++, 0, "当日发表数"));
			sheet.addCell(new Label(lableColumn++, 0, "当月发表金额"));
			sheet.addCell(new Label(lableColumn++, 0, "累计发表金额"));
			sheet.addCell(new Label(lableColumn++, 0, "统计时间"));
			int numberCount = 0;
			for (int i = 0; i < list.size(); i++) {
				OperateReportdaysTwo data = list.get(i);
				// 累计注册用户
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getZyh()));
				// 当月注册用户数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDyxzyh()));
				// 累计发表数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDqzbs()));
				// 当日发表总额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getDrfbze()));
				// 当日普通用户注册人数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getOrdinaryusersday()));
				// 当日集团用户注册人数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getInternalusersday()));
				// 累计外部投资金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getOrdinaryuserssumaccount()));
				// 累计外部投资人数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getTenderordinaryuserscount()));
				// 累计集团投资金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getInternaluserssumaccount()));
				// 累计集团投资人数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getTenderinternaluserscount()));
				// 当前已经还款金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getRepaymentsumyesaccount()));
				// 当前累计投资金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getTendersumaccount()));
				// 当月还款金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getRepaymentsumyesaccountmonth()));
				// 当月应还款标数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getBorrowsumcountmonth()));
				// 当月发表数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getBorrowcountmonth()));
				// 当日发表数
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getBorrowcountday()));
				// 当月发表金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getBorrowsumaccountmonth()));
				// 累计发表金额
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getBorrowsumaccount()));

				sheet.addCell(new Label(numberCount++, i + 1, data
						.getFromaddtimeStr()));
				numberCount = 0;
			}
			book.write();
			out.flush();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTender(List<FinanceReportData> list,
			FinanceReportData frdata, ByteArrayOutputStream out) {
		try {
			int rowIndex = 0;

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("投标服务费");
			// cell 默认宽度
			sheet.setDefaultColumnWidth(20);
			// 居中样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// excel 第一行数据
			Object[] firstCellValues = new Object[] { "用户名", "标的名称", "用户类型",
					"平台服务费率", "平台服务费", "担保服务费", "期限", "投标时间", "预计回款时间",
					"实际回款时间" };
			createExcelRow(rowIndex++, sheet, cellStyle, firstCellValues);

			// excel 中间数据
			for (int i = 0; i < list.size(); i++) {
				FinanceReportData frd = list.get(i);

				String usertype, rateFeeStr, timeLimitStr;
				if (frd.getTypeId() != null
						&& (frd.getTypeId() == 31 || frd.getTypeId() == 27))
					usertype = "集团用户";
				else
					usertype = "普通用户";

				rateFeeStr = frd.getRateFee() + "%";
				timeLimitStr = frd.getTimeLimit() + "个月";

				Object[] values = new Object[] { frd.getUserName(),
						frd.getBorrowName(), usertype, rateFeeStr,
						frd.getServiceFees(), frd.getGuaranteeFees(),
						timeLimitStr, frd.getAddTime(), frd.getRepayTime(),
						frd.getRepayYestime() };
				createExcelRow(rowIndex++, sheet, cellStyle, values);

			}

			Object[] sumServiceFeesCellValues = new Object[] { "平台服务费总计:",
					frdata.getSumServiceFees() };
			createExcelRow(rowIndex++, sheet, cellStyle,
					sumServiceFeesCellValues);

			Object[] sumGuaranteeFeesCellValues = new Object[] { "担保服务费总计:",
					frdata.getSumGuaranteeFees() };
			createExcelRow(rowIndex++, sheet, cellStyle,
					sumGuaranteeFeesCellValues);

			Object[] sumWaitServiceFeesCellValues = new Object[] {
					"待收平台服务费总计:", frdata.getSumWaitServiceFees() };
			createExcelRow(rowIndex++, sheet, cellStyle,
					sumWaitServiceFeesCellValues);

			Object[] sumWaitGuaranteeFeesCellValues = new Object[] {
					"待收担保服务费总计:", frdata.getSumWaitGuaranteeFees() };
			createExcelRow(rowIndex++, sheet, cellStyle,
					sumWaitGuaranteeFeesCellValues);

			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * try { WorkbookSettings ws = new WorkbookSettings();
	 * ws.setEncoding("iso8859-1"); WritableWorkbook book =
	 * Workbook.createWorkbook(out, ws); // 打开文件 // WritableWorkbook book =
	 * Workbook.createWorkbook(new // File(filePath)); //
	 * 生成名为“第一页”的工作表，参数0表示这是第一页 WritableSheet sheet = book.createSheet("投标服务费",
	 * 0); // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0) // 以及单元格内容为test int lableColumn =
	 * 0; sheet.addCell(new Label(lableColumn++, 0, "用户帐号")); sheet.addCell(new
	 * Label(lableColumn++, 0, "用户类型")); sheet.addCell(new Label(lableColumn++,
	 * 0, "平台服务费率")); sheet.addCell(new Label(lableColumn++, 0, "平台服务费"));
	 * sheet.addCell(new Label(lableColumn++, 0, "担保服务费")); sheet.addCell(new
	 * Label(lableColumn, 0, "投标时间"));
	 * 
	 * int numberCount = 0; for (int i = 0; i < list.size(); i++) {
	 * FinanceReportData data = list.get(i); sheet.addCell(new
	 * Label(numberCount++, i + 1, data .getUserName())); if (data.getTypeId()
	 * == 27 || data.getTypeId() == 31) { sheet.addCell(new Label(numberCount++,
	 * i + 1, "集团用户")); } else { sheet.addCell(new Label(numberCount++, i + 1,
	 * "普通用户")); } sheet.addCell(new Label(numberCount++, i + 1,
	 * data.getRateFee() + "%")); sheet.addCell(new
	 * jxl.write.Number(numberCount++, i + 1, data .getServiceFees()));
	 * sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
	 * .getGuaranteeFees())); sheet.addCell(new Label(numberCount++, i + 1,
	 * data.getAddTime())); numberCount = 0; } int endRow = list.size() + 1;
	 * sheet.addCell(new Label(0, endRow, "总计：")); sheet.addCell(new
	 * jxl.write.Number(3, endRow, frdata .getSumServiceFees()));
	 * sheet.addCell(new jxl.write.Number(4, endRow, frdata
	 * .getSumGuaranteeFees())); book.write(); out.flush(); book.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 */

	public static void createCash(List<FinanceReportData> list,
			FinanceReportData frdata, ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			// 打开文件
			// WritableWorkbook book = Workbook.createWorkbook(new
			// File(filePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("提现服务费", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			int lableColumn = 0;
			sheet.addCell(new Label(lableColumn++, 0, "用户帐号"));
			sheet.addCell(new Label(lableColumn++, 0, "提现手续费"));
			sheet.addCell(new Label(lableColumn++, 0, "提现所使用红包"));
			sheet.addCell(new Label(lableColumn, 0, "提现时间"));

			int numberCount = 0;
			for (int i = 0; i < list.size(); i++) {
				FinanceReportData data = list.get(i);
				sheet.addCell(new Label(numberCount++, i + 1, data
						.getUserName()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getCashFees()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getHongbao()));
				sheet.addCell(new Label(numberCount++, i + 1, data.getAddTime()));
				numberCount = 0;
			}
			int endRow = list.size() + 1;
			sheet.addCell(new Label(0, endRow, "总计："));
			sheet.addCell(new jxl.write.Number(1, endRow, frdata
					.getSumCashFees()));
			sheet.addCell(new jxl.write.Number(2, endRow, frdata
					.getSumHongbao()));
			book.write();
			out.flush();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createUserVip(List<FinanceReportData> list,
			FinanceReportData frdata, ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			// 打开文件
			// WritableWorkbook book = Workbook.createWorkbook(new
			// File(filePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("用户VIP费用", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			int lableColumn = 0;
			sheet.addCell(new Label(lableColumn++, 0, "用户帐号"));
			sheet.addCell(new Label(lableColumn++, 0, "VIP金额"));
			sheet.addCell(new Label(lableColumn++, 0, "VIP备注"));
			sheet.addCell(new Label(lableColumn, 0, "VIP注册时间"));

			int numberCount = 0;
			for (int i = 0; i < list.size(); i++) {
				FinanceReportData data = list.get(i);
				sheet.addCell(new Label(numberCount++, i + 1, data
						.getUserName()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getVipMoney()));
				sheet.addCell(new Label(numberCount++, i + 1, data
						.getVipRemark()));
				sheet.addCell(new Label(numberCount++, i + 1, data.getAddTime()));
				numberCount = 0;
			}
			int endRow = list.size() + 1;
			sheet.addCell(new Label(0, endRow, "总计："));
			sheet.addCell(new jxl.write.Number(1, endRow, frdata
					.getSumVipMoney()));
			book.write();
			out.flush();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createUserHongbao(List<FinanceReportData> list,
			Double sumMomey, ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			// 打开文件
			// WritableWorkbook book = Workbook.createWorkbook(new
			// File(filePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("用户红包明细", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			int lableColumn = 0;
			sheet.addCell(new Label(lableColumn++, 0, "用户姓名"));
			sheet.addCell(new Label(lableColumn, 0, "提现抵扣券金额"));
			int numberCount = 0;
			for (int i = 0; i < list.size(); i++) {
				FinanceReportData data = list.get(i);
				sheet.addCell(new Label(numberCount++, i + 1, data
						.getUserName()));
				sheet.addCell(new jxl.write.Number(numberCount++, i + 1, data
						.getHongbao()));
				numberCount = 0;
			}
			int endRow = list.size() + 1;
			sheet.addCell(new Label(0, endRow, "总计："));
			sheet.addCell(new jxl.write.Number(lableColumn, endRow, sumMomey));
			book.write();
			out.flush();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * 创建用户现金红包Excel
	 * </p>
	 * 
	 * wang.zeyan 2015年4月13日
	 * 
	 * @param list
	 * @param frData
	 * @param out
	 */
	public static void createCashRedEnvelope(List<FinanceReportData> list,
			FinanceReportData frData, ByteArrayOutputStream out) {
		try {
			int rowIndex = 0;

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("用户红包明细");
			// cell 默认宽度
			sheet.setDefaultColumnWidth(20);
			// 居中样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// excel 第一行数据
			Object[] titleCellValues = new Object[] { "用户姓名", "未使用红包", "已使用红包",
					"已过期红包" };
			createExcelRow(rowIndex++, sheet, cellStyle, titleCellValues);

			// excel 中间数据
			for (int i = 0; i < list.size(); i++) {
				FinanceReportData frd = list.get(i);

				Object[] bodyCellValues = new Object[] { frd.getUserName(),
						frd.getUnusedRedEnvelope(), frd.getUsedRedEnvelope(),
						frd.getExpiredRedEnvelope() };
				createExcelRow(rowIndex++, sheet, cellStyle, bodyCellValues);

			}

			// excel 最后一行数据
			Object[] lastCellValues = new Object[] { "总计:",
					frData.getUnusedRedEnvelope(), frData.getUsedRedEnvelope(),
					frData.getExpiredRedEnvelope() };
			createExcelRow(rowIndex++, sheet, cellStyle, lastCellValues);

			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0); // 创建第1行，也就是输出表头
			row.createCell(0).setCellValue(new HSSFRichTextString("123"));
			row.createCell(0).setCellValue(new HSSFRichTextString("321"));
			FileOutputStream fileOut = new FileOutputStream("/tmp/workbook.xls");
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void createCpData(List<CpDataBean> list, String date,
	// ByteArrayOutputStream out) {
	// try {
	// HSSFWorkbook workbook = new HSSFWorkbook();
	// HSSFSheet sheet = workbook.createSheet("sheet1");
	// HSSFRow row = sheet.createRow(0); // 创建第1行，也就是输出表头
	// row.createCell(0).setCellValue(new HSSFRichTextString("123"));
	// row.createCell(1).setCellValue(new HSSFRichTextString("321"));
	// workbook.write(out);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	private static void createExcelRow(int rowIndex, HSSFSheet sheet,
			HSSFCellStyle style, Object... cellValue) {
		HSSFRow bodyRow = sheet.createRow(rowIndex);
		if (cellValue == null)
			return;
		for (int i = 0; i < cellValue.length; i++) {
			HSSFCell cell = bodyRow.createCell(i);
			cell.setCellStyle(style);
			Object val = cellValue[i];
			if (val instanceof String)
				cell.setCellValue(String.valueOf(val));
			else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			}
		}
	}
}
