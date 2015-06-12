package cn.vfunding.vfunding.prd.bms.funds.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.AccountRechargeVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;

/**
 * 导出
 *
 */
@Controller()
@RequestMapping(value = "/system/export")
public class ExportController {

	@Autowired
	private IBorrowService borrowService;
	
	@Autowired
	private IBorrowTenderService borrowTenderService;
	
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	
	@Autowired
	private IBorrowCollectionService borrowCollectionService;
	
	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IAccountRechargeService accountRechargeService;
	
	public WritableSheet commonSheet(WritableSheet sheet){
		sheet.setColumnView(0, 15);
		sheet.setColumnView(1, 12);
		sheet.setColumnView(2, 12);
		sheet.setColumnView(3, 15);
		sheet.setColumnView(4, 15);
		sheet.setColumnView(5, 15);
		sheet.setColumnView(6, 20);
		sheet.setColumnView(7, 20);
		sheet.setColumnView(8, 20);
		sheet.setColumnView(9, 20);
		sheet.setColumnView(10, 20);
		sheet.setColumnView(11, 20);
		return sheet;
	}
	
	/**
	 * 提现后台导出
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/waitApplyCashListPage")
	@RequestMapping(value="/cashExcel")
	public void cashExcel(HttpServletResponse response,SearchVO search){
		String filename = "提现记录";
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");
			OutputStream out = response.getOutputStream();
		
			jxl.write.Label label;
			WritableWorkbook workbook;
			try {
				workbook = Workbook.createWorkbook(out);
				WritableSheet sheet = workbook.createSheet("提现记录", 0);
				
				this.commonSheet(sheet);
				
				WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 12 ,WritableFont.BOLD);
				WritableCellFormat wcf = new WritableCellFormat(font1);
				//cf.setBackground(jxl.format.Colour.YELLOW);背景色
				wcf.setAlignment(Alignment.CENTRE);
				
				WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 10);
				WritableCellFormat wcf2 = new WritableCellFormat(font2);
				wcf2.setAlignment(Alignment.CENTRE);
				
				label = new jxl.write.Label(0, 0, "借款编号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(1, 0, "用户名称",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(2, 0, "真实姓名",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(3, 0, "提现账号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(4, 0, "提现银行",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(5, 0, "支行",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(6, 0, "提现总额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(7, 0, "到账金额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(8, 0, "手续费",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(9, 0, "红包抵扣",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(10, 0, "提现时间",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(11, 0, "状态",wcf);
				sheet.addCell(label);
				
				PageSearch pageSearch = new PageSearch();
				//时间转换
				if(search.getStartTime()!=null&&!"".equals(search.getStartTime())){
					search.setStartTime(DateUtil.getLongTimeByStringValue(search.getStartTime() + " 00:00:00").toString());
				}
				if(search.getEndTime()!=null&!"".equals(search.getEndTime())){
					search.setEndTime(DateUtil.getLongTimeByStringValue(search.getEndTime() + " 23:59:59").toString());
				}
				pageSearch.setEntity(search);
				pageSearch.setRows(5000);
				List<AccountCashVO> cashList = accountCashService.selectWaitApplyListPage(pageSearch);//提现集合
				
				BigDecimal all = new BigDecimal("0");
				for (int i = 1; i <=cashList.size(); i++) {
					AccountCashVO vo = cashList.get(i-1);
					label = new jxl.write.Label(0, i, vo.getCashId()+"",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(1, i, vo.getUserName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(2, i, vo.getRealName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(3, i, vo.getBankNum(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(4, i, vo.getBankName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(5, i, vo.getBranch(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(6, i, vo.getMoney().setScale(2).toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(7, i, vo.getAccount().setScale(2).toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(8, i, vo.getFee().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(9, i, vo.getUseHongbao().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(10, i, vo.getAddtime(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(11, i, vo.getStatus()==0?"审核中":(vo.getStatus()==1?"处理成功":(vo.getStatus()==2?"处理失败":"用户取消申请")),wcf2);
					sheet.addCell(label);
					all = all.add(vo.getMoney()).setScale(2);
				}
				label = new jxl.write.Label(0, pageSearch.getTotalResult()+2, "共有 "+pageSearch.getTotalResult()+" 条记录  提现金额合计："+all);
				sheet.addCell(label);
				
				workbook.write();
				workbook.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 后台充值导出
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/waitApplyRechargeListPage")
	@RequestMapping(value="/rechargeExcel")
	public void rechargeExcel(HttpServletResponse response,SearchVO search){
		String filename = "充值记录";
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");
			OutputStream out = response.getOutputStream();
			
			jxl.write.Label label;
			WritableWorkbook workbook;
			try {
				workbook = Workbook.createWorkbook(out);
				WritableSheet sheet = workbook.createSheet("充值记录", 0);
				
				this.commonSheet(sheet);
				
				WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 12 ,WritableFont.BOLD);
				WritableCellFormat wcf = new WritableCellFormat(font1);
				//cf.setBackground(jxl.format.Colour.YELLOW);背景色
				wcf.setAlignment(Alignment.CENTRE);
				
				WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 10);
				WritableCellFormat wcf2 = new WritableCellFormat(font2);
				wcf2.setAlignment(Alignment.CENTRE);
				
				label = new jxl.write.Label(0, 0, "充值编号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(1, 0, "流水号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(2, 0, "用户名称",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(3, 0, "真实姓名",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(4, 0, "类型",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(5, 0, "充值银行",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(6, 0, "充值金额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(7, 0, "费用",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(8, 0, "到账金额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(9, 0, "充值时间",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(10, 0, "银行返回",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(11, 0, "状态",wcf);
				sheet.addCell(label);
				
				PageSearch pageSearch = new PageSearch();
				search.setType((search.getStatus() != null && search.getStatus() == 0) ? 2 : null);
				//时间转换
				if(search.getStartTime()!=null&&!"".equals(search.getStartTime())){
					search.setStartTime(DateUtil.getLongTimeByStringValue(search.getStartTime() + " 00:00:00").toString());
				}
				if(search.getEndTime()!=null&!"".equals(search.getEndTime())){
					search.setEndTime(DateUtil.getLongTimeByStringValue(search.getEndTime() + " 23:59:59").toString());
				}
				pageSearch.setEntity(search);
				pageSearch.setRows(5000);
				List<AccountRechargeVO> rechargeList = accountRechargeService.selectWaitApplyListPage(pageSearch);
				
				BigDecimal all = new BigDecimal("0");
				for (int i = 1; i <=rechargeList.size(); i++) {
					AccountRechargeVO vo = rechargeList.get(i-1);
					label = new jxl.write.Label(0, i, vo.getRechargeId()+"",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(1, i, vo.getTradeNo(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(2, i, vo.getUserName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(3, i, vo.getRealName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(4, i, vo.getType()==1?"在线充值":"线下充值",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(5, i, vo.getBank(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(6, i, vo.getMoney().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(7, i, vo.getFee().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(8, i, vo.getMoney().subtract(new BigDecimal(vo.getFee())).toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(9, i, vo.getAddtime(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(10, i, vo.getReward(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(11, i, (vo.getStatus()==2||vo.getStatus()==0)?"处理失败":(vo.getStatus()==1?"处理成功":"error"),wcf2);
					sheet.addCell(label);
					all = all.add(vo.getMoney()).setScale(2);
				}
				label = new jxl.write.Label(0, pageSearch.getTotalResult()+2, "共有 "+pageSearch.getTotalResult()+" 条记录  充值金额合计："+all);
				sheet.addCell(label);
				
				workbook.write();
				workbook.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 后台标导出
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/allLoans")
	@RequestMapping(value="/systemBorrowExcel")
	public void systemBorrowExcel(HttpServletResponse response,SearchVO search){
		String filename = "借款标记录";
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");
			OutputStream out = response.getOutputStream();
			
			jxl.write.Label label;
			WritableWorkbook workbook;
			try {
				workbook = Workbook.createWorkbook(out);
				WritableSheet sheet = workbook.createSheet("借款标记录", 0);

				this.commonSheet(sheet);
				
				WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 12 ,WritableFont.BOLD);
				WritableCellFormat wcf = new WritableCellFormat(font1);
				//cf.setBackground(jxl.format.Colour.YELLOW);背景色
				wcf.setAlignment(Alignment.CENTRE);
				
				WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 10);
				WritableCellFormat wcf2 = new WritableCellFormat(font2);
				wcf2.setAlignment(Alignment.CENTRE);
				
				label = new jxl.write.Label(0, 0, "借款编号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(1, 0, "用户名",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(2, 0, "借款标题",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(3, 0, "借款金额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(4, 0, "净收益率",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(5, 0, "年利率",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(6, 0, "投标次数",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(7, 0, "借款期限",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(8, 0, "发标时间",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(9, 0, "初审时间",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(10, 0, "状态",wcf);
				sheet.addCell(label);
				
				PageSearch pageSearch = new PageSearch();
				search.setStatus(search.getStatus()==null?0:search.getStatus());
				//时间转换
				if(search.getStartTime()!=null&&!"".equals(search.getStartTime())){
					search.setStartTime(DateUtil.getLongTimeByStringValue(search.getStartTime() + " 00:00:00").toString());
				}
				if(search.getEndTime()!=null&!"".equals(search.getEndTime())){
					search.setEndTime(DateUtil.getLongTimeByStringValue(search.getEndTime() + " 23:59:59").toString());
				}
				pageSearch.setEntity(search);
				pageSearch.setRows(5000);
				List<VerifyBorrowVO> borrows = borrowService.selectBorrowSystemListPage(pageSearch);
				
				BigDecimal all = new BigDecimal("0");
				for (int i = 1; i <=borrows.size(); i++) {
					VerifyBorrowVO vo = borrows.get(i-1);
					label = new jxl.write.Label(0, i, vo.getBorrowId()+"",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(1, i, vo.getUserName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(2, i, vo.getBorrowName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(3, i, vo.getAccount().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(4, i, EmptyUtil.isEmpty(vo.getAprStr())?"-":vo.getAprStr().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(5, i, EmptyUtil.isEmpty(vo.getApr())?"-":vo.getApr().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(6, i, vo.getTenderTimes().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(7, i, vo.getTimeLimit().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(8, i, vo.getAddtime(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(9, i, vo.getVerifyTime().equals("1970-01-01 08:00:00")?"-":vo.getVerifyTime().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(10, i, (vo.getStatus()==3?"复审成功":(vo.getStatus()==5?"撤标"
							:(vo.getStatus()==1?"招标中":(vo.getStatus()==7?"流标":(vo.getStatus()==2?"初审不通过"
									:(vo.getStatus() == 5?"撤标":(vo.getStatus() == 0?"等待初审":"error"))))))),wcf2);
					sheet.addCell(label);
					all = all.add(vo.getAccount()).setScale(2);
				}
				label = new jxl.write.Label(0, pageSearch.getTotalResult()+2, "共有 "+pageSearch.getTotalResult()+" 条记录  借款金额合计："+all);
				sheet.addCell(label);
				
				workbook.write();
				workbook.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 还款导出
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/repayment/allRepays")
	@RequestMapping(value = "/exportBorrow")
	public void exportBorrow(HttpServletResponse response,SearchVO search){
		String filename = "还款记录";
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");
			OutputStream out = response.getOutputStream();
		
			jxl.write.Label label;
			WritableWorkbook workbook;
			try {
				workbook = Workbook.createWorkbook(out);
				WritableSheet sheet = workbook.createSheet("还款记录", 0);

				this.commonSheet(sheet);
				
				WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 12 ,WritableFont.BOLD);
				WritableCellFormat wcf = new WritableCellFormat(font1);
				//cf.setBackground(jxl.format.Colour.YELLOW);背景色
				wcf.setAlignment(Alignment.CENTRE);
				
				WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 10);
				WritableCellFormat wcf2 = new WritableCellFormat(font2);
				wcf2.setAlignment(Alignment.CENTRE);
				
				label = new jxl.write.Label(0, 0, "还款编号",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(1, 0, "借款标题",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(2, 0, "期数",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(3, 0, "还款时间",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(4, 0, "还款金额",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(5, 0, "发标人",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(6, 0, "借款人",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(7, 0, "借款期限",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(8, 0, "待还利息",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(9, 0, "滞纳金",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(10, 0, "逾期利息",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(11, 0, "应还本金",wcf);
				sheet.addCell(label);
				label = new jxl.write.Label(12, 0, "状态",wcf);
				sheet.addCell(label);
				
				PageSearch pageSearch = new PageSearch();
				search.setStatus(search.getStatus()==null?null:search.getStatus());
				//时间转换
				if(search.getStartTime()!=null&&!"".equals(search.getStartTime())){
					search.setStartTime(DateUtil.getLongTimeByStringValue(search.getStartTime() + " 00:00:00").toString());
				}
				if(search.getEndTime()!=null&!"".equals(search.getEndTime())){
					search.setEndTime(DateUtil.getLongTimeByStringValue(search.getEndTime() + " 23:59:59").toString());
				}
				pageSearch.setEntity(search);
				pageSearch.setRows(5000);
				List<BorrowRepayment> borrows = borrowRepaymentService.selectByRepaymentSystemListPage(pageSearch);
				
				BigDecimal all = new BigDecimal("0");
				for (int i = 1; i <=borrows.size(); i++) {
					BorrowRepayment vo = borrows.get(i-1);
					label = new jxl.write.Label(0, i, vo.getId()+"",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(1, i, vo.getBorrowName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(2, i, (vo.getOrder()+1)+"/"+vo.getTimeLimit(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(3, i, DateUtil.getStringDateByLongDate(Long.parseLong(vo.getRepaymentTime()+"")),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(4, i, vo.getRepaymentAccount()+"元",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(5, i, vo.getUserName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(6, i, vo.getOwnerName(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(7, i, vo.getTimeLimit().toString(),wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(8, i, vo.getInterest()+"元",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(9, i, vo.getForfeit()+"元",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(10, i, vo.getLateInterest()+"元",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(11, i, vo.getCapital()+"元",wcf2);
					sheet.addCell(label);
					label = new jxl.write.Label(12, i, vo.getStatus()==0?"未还":(vo.getStatus()==1?"已还":"error"),wcf2);
					sheet.addCell(label);
					all = all.add(new BigDecimal(vo.getRepaymentAccount()));
				}
				label = new jxl.write.Label(0, pageSearch.getTotalResult()+2, "共有 "+pageSearch.getTotalResult()+" 条记录  以上还款金额合计："+all);
				sheet.addCell(label);
				
				workbook.write();
				workbook.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
