package cn.vfunding.vfunding.prd.bms.report.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.FinanceReportData;
import cn.vfunding.vfunding.biz.report.service.IFinanceReportDataService;
import cn.vfunding.vfunding.plat.utils.excel.CreateExcle;

/**
 * 
 * 
 * 	  平台数据  -->平台服务费-->原 用户红包 更名 用户提现抵扣券
 * 	 2015.04.09 wang.zeyan
 *  
 *  
 *   平台数据  -->平台服务费-->新增  用户现金红包
 *   2015.04.10 wang.zeyan
 *  
 * 
 * 
 */
@Controller
@RequestMapping(value = "/finance/reportData")
public class FinanceReportDataContorller {

	@Autowired
	private IFinanceReportDataService fpsService;

	@RequestMapping(value = "/toCashListPage", method = RequestMethod.GET)
	public ModelAndView toCashListPage() {
		ModelAndView mv = new ModelAndView("webpage/finance/cashList");

		return mv;
	}

	@RequestMapping(value = "/toTenderListPage", method = RequestMethod.GET)
	public ModelAndView toTenderListPage() {
		ModelAndView mv = new ModelAndView("webpage/finance/tenderList");

		return mv;
	}

	@RequestMapping(value = "/toUserVipListPage", method = RequestMethod.GET)
	public ModelAndView toUserVipListPage() {
		ModelAndView mv = new ModelAndView("webpage/finance/userVipList");

		return mv;
	}

	/**
	 * 
	 * 用户提现抵扣券
	 * @return
	 */
	@RequestMapping(value = "/toUserHongbaoListPage", method = RequestMethod.GET)
	public ModelAndView toUserHongbaoListPage() {
		ModelAndView mv = new ModelAndView("webpage/finance/userHongbaoList");

		return mv;
	}
	
	/**
	 * 
	 * <p>to 用户现金红包  page</p>
	 *
	 * wang.zeyan 2015年4月10日
	 * @return
	 */
	@RequestMapping(value = "/toCashRedEnvelopeListPage")
	public ModelAndView toCashRedEnvelopeListPage(){
		ModelAndView mv = new ModelAndView("webpage/finance/cashRedEnvelopeList");
		return mv;
	}

	/**
	 * 职位信息列表数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toCashListPage")
	@RequestMapping(value = "/cashList")
	@ResponseBody
	public PageResult<FinanceReportData> getCashByPage(PageSearch pageSearch,
			FinanceReportData frd, HttpServletRequest request) {
		PageResult<FinanceReportData> result = new PageResult<FinanceReportData>();
		List<FinanceReportData> list = null;
		pageSearch.setEntity(frd);
		list = this.fpsService.selectCashListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@ParentSecurity("/finance/reportData/toCashListPage")
	@RequestMapping(value = "/sumCashFees")
	@ResponseBody
	public FinanceReportData getSumCashFees(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumCashFee(frd);
		return data;
	}

	/**
	 * 数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toTenderListPage")
	@RequestMapping(value = "/tenderList")
	@ResponseBody
	public PageResult<FinanceReportData> getTenderByPage(PageSearch pageSearch,
			FinanceReportData frd) {
		PageResult<FinanceReportData> result = new PageResult<FinanceReportData>();
		List<FinanceReportData> list = null;
		pageSearch.setEntity(frd);
		list = this.fpsService.selectTenderListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toTenderListPage")
	@RequestMapping(value = "/sumTenderFees")
	@ResponseBody
	public FinanceReportData getSumTenderFee(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumTenderFee(frd);
		return data;
	}

	/**
	 * 职位信息列表数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toUserVipListPage")
	@RequestMapping(value = "/userVipList")
	@ResponseBody
	public PageResult<FinanceReportData> getVipByPage(PageSearch pageSearch,
			FinanceReportData frd) {
		PageResult<FinanceReportData> result = new PageResult<FinanceReportData>();
		List<FinanceReportData> list = null;
		pageSearch.setEntity(frd);
		list = this.fpsService.selectUserVipListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@ParentSecurity("/finance/reportData/toUserVipListPage")
	@RequestMapping(value = "/sumVipFees")
	@ResponseBody
	public FinanceReportData getSumVipFees(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumVipMoney(frd);
		return data;
	}

	/**
	 * 
	 * <p>查询现金红包</p>
	 *
	 * wang.zeyan 2015年4月10日
	 * @param pageSearch
	 * @param frd
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toCashRedEnvelopeListPage")
	@RequestMapping(value = "/cashRedEnvelopeList")
	@ResponseBody
	public PageResult<FinanceReportData> getCashRedEnvelopeListByPage(
			PageSearch pageSearch, FinanceReportData frd) {
		PageResult<FinanceReportData> result = new PageResult<FinanceReportData>();
		List<FinanceReportData> list = null;
		pageSearch.setEntity(frd);
		list = this.fpsService.selectCashRedEnvelopeListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}
	
	/**
	 * 
	 * <p>查询已使用红包总和</p>
	 *
	 * wang.zeyan 2015年4月10日
	 * @param pageSearch
	 * @param frd
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toCashRedEnvelopeListPage")
	@RequestMapping(value = "/getSumUsedRedEnvelope")
	@ResponseBody
	public FinanceReportData getSumUsedRedEnvelope(
			PageSearch pageSearch, FinanceReportData frd) {
		frd = this.fpsService.selectSumUsedRedEnvelope(frd);
		return frd;
	}
	
	@ParentSecurity("/finance/reportData/toUserHongbaoListPage")
	@RequestMapping(value = "/userHongbaoList")
	@ResponseBody
	public PageResult<FinanceReportData> getUserHongbaoListByPage(
			PageSearch pageSearch, FinanceReportData frd) {
		PageResult<FinanceReportData> result = new PageResult<FinanceReportData>();
		List<FinanceReportData> list = null;
		pageSearch.setEntity(frd);
		list = this.fpsService.selectUserHongbaoListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@ParentSecurity("/finance/reportData/toUserHongbaoListPage")
	@RequestMapping(value = "/sumUserHongbaoFees")
	@ResponseBody
	public FinanceReportData getSumUserHongbaoFees(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumUserHongbao();
		return data;
	}
	
	/**
	 * 
	 * <p>查询红包和</p>
	 *
	 * wang.zeyan 2015年4月10日getSumUsedRedEnvelope
	 * @param frd
	 * @return
	 */
	@ParentSecurity("/finance/reportData/toCashRedEnvelopeListPage")
	@RequestMapping(value = "/sumCashRedEnvelope")
	@ResponseBody
	public FinanceReportData getSumCashRedEnvelope(FinanceReportData frd) {
		frd = this.fpsService.selectSumCashRedEnvelope(frd);
		return frd;
	}
	
	
	@ParentSecurity("/finance/reportData/toCashRedEnvelopeListPage")
	@RequestMapping(value = "/exportCashRedEnvelopeExcel")
	@ResponseBody
	public void exportCashRedEnvelopeExcel(FinanceReportData frd,
			HttpServletResponse response){
		try {
			List<FinanceReportData> list = this.fpsService.selectCashRedEnvelope(frd);
			FinanceReportData frData = this.fpsService.selectSumCashRedEnvelope(frd);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createCashRedEnvelope(list, frData, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "用户现金红包数据.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@ParentSecurity("/finance/reportData/toTenderListPage")
	@RequestMapping(value = "/exportTenderExcel")
	@ResponseBody
	public void exportTenderExcle(FinanceReportData frd,
			HttpServletResponse response) {
		try {
			List<FinanceReportData> list = this.fpsService.selectTender(frd);
			FinanceReportData frData = this.fpsService.selectSumTenderFee(frd);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createTender(list, frData, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "投标服务费数据.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@ParentSecurity("/finance/reportData/toCashListPage")
	@RequestMapping(value = "/exportCashExcel")
	@ResponseBody
	public void exportCashExcle(FinanceReportData frd,
			HttpServletResponse response) {
		try {
			List<FinanceReportData> list = this.fpsService.selectCash(frd);
			FinanceReportData frData = this.fpsService.selectSumCashFee(frd);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createCash(list, frData, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "提现服务费数据.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@ParentSecurity("/finance/reportData/toUserVipListPage")
	@RequestMapping(value = "/exportUserVipExcel")
	@ResponseBody
	public void exportUserVipExcle(FinanceReportData frd,
			HttpServletResponse response) {
		try {
			List<FinanceReportData> list = this.fpsService.selectUserVip(frd);
			FinanceReportData frData = this.fpsService.selectSumVipMoney(frd);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createUserVip(list, frData, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "VIP费用明细.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@ParentSecurity("/finance/reportData/toUserHongbaoListPage")
	@RequestMapping(value = "/exportUserHongbaoExcel")
	@ResponseBody
	public void exportUserHongbaoExcel(FinanceReportData frd,
			HttpServletResponse response) {
		try {
			List<FinanceReportData> list = this.fpsService.selectUserHongbao();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			FinanceReportData data = this.fpsService.selectSumUserHongbao();
			CreateExcle.createUserHongbao(list, data.getSumHongbao(), bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "用户提现抵扣券明细.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}