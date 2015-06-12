package cn.vfunding.vfunding.plat.report.controller;

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
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.FinanceReportData;
import cn.vfunding.vfunding.biz.report.service.IFinanceReportDataService;
import cn.vfunding.vfunding.plat.utils.excel.CreateExcle;

@Controller
@RequestMapping(value = "/finance/reportData")
public class FinanceReportDataContorller {

	@Autowired
	private IFinanceReportDataService fpsService;

	@RequestMapping(value = "/toCashListPage", method = RequestMethod.GET)
	public ModelAndView toCashListPage() {
		ModelAndView mv = new ModelAndView("finance/cashList");

		return mv;
	}

	@RequestMapping(value = "/toTenderListPage", method = RequestMethod.GET)
	public ModelAndView toTenderListPage() {
		ModelAndView mv = new ModelAndView("finance/tenderList");

		return mv;
	}

	@RequestMapping(value = "/toUserVipListPage", method = RequestMethod.GET)
	public ModelAndView toUserVipListPage() {
		ModelAndView mv = new ModelAndView("finance/userVipList");

		return mv;
	}

	@RequestMapping(value = "/toUserHongbaoListPage", method = RequestMethod.GET)
	public ModelAndView toUserHongbaoListPage() {
		ModelAndView mv = new ModelAndView("finance/userHongbaoList");

		return mv;
	}

	/**
	 * 职位信息列表数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
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

	@RequestMapping(value = "/sumVipFees")
	@ResponseBody
	public FinanceReportData getSumVipFees(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumVipMoney(frd);
		return data;
	}

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

	@RequestMapping(value = "/sumUserHongbaoFees")
	@ResponseBody
	public FinanceReportData getSumUserHongbaoFees(FinanceReportData frd) {
		FinanceReportData data = null;
		data = this.fpsService.selectSumUserHongbao();
		return data;
	}

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
			String file = "用户红包明细.xls";
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