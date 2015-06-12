package cn.vfunding.vfunding.prd.bms.report.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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
import cn.vfunding.vfunding.biz.report.model.OperateReportdays;
import cn.vfunding.vfunding.biz.report.model.OperateReportdaysTwo;
import cn.vfunding.vfunding.biz.report.service.IOperateReportdaysService;
import cn.vfunding.vfunding.plat.utils.excel.CreateExcle;

@Controller
@RequestMapping(value = "/operate/reportdays")
public class OperateReportdaysContorller {
    
	@Autowired
	private IOperateReportdaysService orService;

	@RequestMapping(value = "/toListPage", method = RequestMethod.GET)
	public ModelAndView toListPage() {
		ModelAndView mv = new ModelAndView("webpage/operate/reportdaysList");
		return mv;
	}

	@RequestMapping(value = "/toTwoListPage", method = RequestMethod.GET)
	public ModelAndView toListPageTwo() {
		ModelAndView mv = new ModelAndView("webpage/operate/reportdaysTwoList");

		return mv;
	}

	/**
	 * 数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/operate/reportdays/toListPage")
	@RequestMapping(value = "/list")
	@ResponseBody
	public PageResult<OperateReportdays> getExtensionModelByPage(
			PageSearch pageSearch, OperateReportdays or) {
		PageResult<OperateReportdays> result = new PageResult<OperateReportdays>();
		List<OperateReportdays> list = null;
		pageSearch.setEntity(or);
		list = this.orService.selectListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@ParentSecurity("/operate/reportdays/toListPage")
	@RequestMapping(value = "/exportExcel")
	@ResponseBody
	public void exportExcle(OperateReportdays or, HttpServletResponse response) {
		try {
			List<OperateReportdays> list = this.orService
					.selectOperateReportdays(or);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createOperateReportdays(list, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "每日平台统计数据.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			// DownloadToTxtUtil.writeTxtToResponse(bos.toString(), response,
			// file, "UTF-8");
			bos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// Struts2Utils.getResponse().getWriter().write(back);
			// log.info("修改cp业务信息成功。。。");
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	/**
	 * 数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param ExtensionModel
	 * @return
	 */
	@ParentSecurity("/operate/reportdays/toTwoListPage")
	@RequestMapping(value = "/listTwo")
	@ResponseBody
	public PageResult<OperateReportdaysTwo> getReportTwoByPage(
			PageSearch pageSearch, OperateReportdaysTwo or) {
		PageResult<OperateReportdaysTwo> result = new PageResult<OperateReportdaysTwo>();
		List<OperateReportdaysTwo> list = null;
		pageSearch.setEntity(or);
		list = this.orService.selectListPageTwo(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@ParentSecurity("/operate/reportdays/toTwoListPage")
	@RequestMapping(value = "/exportExcelTwo")
	@ResponseBody
	public void exportExcleTwo(OperateReportdaysTwo or, HttpServletResponse response) {
		try {
			List<OperateReportdaysTwo> list = this.orService
					.selectOperateReportdaysTwo(or);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			CreateExcle.createOperateReportdaysTwo(list, bos);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "平台统计数据.xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file.getBytes("UTF-8"), "iso-8859-1"));
			response.getOutputStream().write(bos.toByteArray());
			// DownloadToTxtUtil.writeTxtToResponse(bos.toString(), response,
			// file, "UTF-8");
			bos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// Struts2Utils.getResponse().getWriter().write(back);
			// log.info("修改cp业务信息成功。。。");
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}