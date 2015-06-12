package cn.vfunding.vfunding.prd.bms.giftbox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.excel.utils.PoiExcel2k3Helper;
import cn.vfunding.common.framework.excel.utils.PoiExcel2k7Helper;
import cn.vfunding.common.framework.excel.utils.PoiExcelHelper;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatchInfo;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxBatchInfoService;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxBatchService;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxTemplateService;
import cn.vfunding.vfunding.biz.trial.vo.GiftboxBatchSearchVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping(value = "/giftboxTiral")
public class GiftboxBatchController {
	@Autowired
	private IGiftboxBatchService giftboxBatchService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IGiftboxBatchInfoService giftboxBatchInfoService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IGiftboxTemplateService giftboxTemplateService;

	@RequestMapping("/importExcelPage")
	public ModelAndView importExcelPage() {
		ModelAndView mv = new ModelAndView("webpage/giftbox/importExcelPage");
		List<GiftboxTemplate> gt = giftboxTemplateService.selectByStatus(0);
		mv.addObject("gtList", gt);
		return mv;
	}

	@RequestMapping("/previewExcel")
	@ResponseBody
	public Map<String, Object> previewExcel(MultipartFile excelFile, HttpSession httpSession) throws IOException {
		if (excelFile != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			String fileName = excelFile.getOriginalFilename();
			PoiExcelHelper helper;
			if (fileName.indexOf(".xlsx") != -1) {
				helper = new PoiExcel2k7Helper();
			} else {
				helper = new PoiExcel2k3Helper();
			}
			ArrayList<ArrayList<String>> aas = helper.readExcel(excelFile.getInputStream(), 0);// 存放从excel读取出的行
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();// 存放有数据的行,且剔除HTML标签的数据
			ArrayList<ArrayList<String>> aasYes = new ArrayList<ArrayList<String>>();// 存放有数据的行,HTML标签未剔除的
			for (int i = 0; i < aas.size(); i++) {
				ArrayList<String> as = new ArrayList<String>(aas.get(i));
				if (i > 0) {
					if (!StringUtils.isEmpty(as.get(0)) && Integer.parseInt(as.get(0).trim()) > 0) {
						for (int j = 0; j < as.size(); j++) {
							String s = as.get(j);
							as.set(j, s.replaceAll("<(.*?)>", ""));// 剔除元素中的
																	// HTML标签
						}
						temp.add(as);
					} else {
						break;
					}
				} else {
					temp.add(as);
				}
			}
			for (int i = 0; i < temp.size(); i++) {
				aasYes.add(aas.get(i));
			}
			map.put("excelData", temp);
			map.put("excelName", excelFile.getOriginalFilename());

			httpSession.setAttribute("giftboxExcel", aasYes);
			httpSession.setAttribute("excelName", excelFile.getOriginalFilename());
			return map;
		} else {
			return null;
		}
	}

	@RequestMapping("/importExcel")
	@ResponseBody
	public Json importExcel(GiftboxBatch giftboxBatch, HttpSession httpSession) throws IOException {
		Json j = new Json();
		if (httpSession.getAttribute("giftboxExcel") != null) {
			giftboxBatch.setAddtime(new Date());
			giftboxBatch.setCreateUser(EmployeeSession.getEmpSessionEmpId());
			giftboxBatch.setStatus(0);
			ArrayList<ArrayList<String>> is = (ArrayList<ArrayList<String>>) httpSession.getAttribute("giftboxExcel");
			String excelName = (String) httpSession.getAttribute("excelName");
			httpSession.removeAttribute("giftboxExcel");
			httpSession.removeAttribute("excelName");
			j = giftboxBatchService.importExcelAndBatch(giftboxBatch, is, excelName);
		} else {
			j.setMsg("请选择上传文件！");
		}
		return j;
	}

	@RequestMapping("/tiralGiftboxListPage")
	@ResponseBody
	public ModelAndView tiralGiftboxListPage() {
		ModelAndView mv = new ModelAndView("webpage/giftbox/tiralGiftboxListPage");
		return mv;
	}

	@ParentSecurity("/giftboxTiral/tiralGiftboxListPage")
	@RequestMapping("/tiralGiftboxList")
	@ResponseBody
	public PageResult<GiftboxBatch> tiralGiftboxList(GiftboxBatchSearchVO giftboxBatchSearchVO, PageSearch pageSearch) {
		PageResult<GiftboxBatch> results = new PageResult<GiftboxBatch>();
		pageSearch.setEntity(giftboxBatchSearchVO);
		List<GiftboxBatch> gbList = this.giftboxBatchService.selectTiralGiftboxListPage(pageSearch);
		if (!gbList.isEmpty()) {
			for (GiftboxBatch gb : gbList) {
				Employee e = employeeService.selectByPrimaryKey(gb.getCreateUser());
				gb.setCreateUserName(e.getEmpName());
				GiftboxTemplate gt = giftboxTemplateService.selectByPrimaryKey(gb.getTemplateId());
				gb.setTemplateType(gt.getType());
			}
		}
		results.setRows(gbList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	@RequestMapping("/tiralGiftboxInfoList")
	@ResponseBody
	public ModelAndView tiralGiftboxInfoList(Integer batchId) {
		ModelAndView mv = new ModelAndView("webpage/giftbox/tiralGiftboxInfoList");
		GiftboxBatch gb = giftboxBatchService.selectByPrimaryKey(batchId);
		List<GiftboxBatchInfo> gbiList = giftboxBatchInfoService.selectByBacthId(batchId);
		List<List<String>> colsBody = new ArrayList<List<String>>();
		for (GiftboxBatchInfo gbi : gbiList) {
			User u = userService.selectByPrimaryKey(gbi.getReceiveUser());
			if (u != null) {
				gbi.setReceiveUserName(u.getUsername());
			}
			Employee e = employeeService.selectByPrimaryKey(gbi.getSendUser());
			if (e != null) {
				gbi.setSendUserName(e.getEmpName());
			}
			if (!StringUtils.isEmpty(gbi.getOtherdata())) {
				List<String> l = Arrays.asList(gbi.getOtherdata().split(","));
				colsBody.add(l);
			}
		}
		List<String> colsHead = giftboxTemplateService.selectTemplateParams(gb.getTemplateId());
		GiftboxTemplate gt = this.giftboxTemplateService.selectByPrimaryKey(gb.getTemplateId());
		gb.setTemplateType(gt.getType());
		mv.addObject("gbiList", gbiList);
		mv.addObject("userCount", gb.getUserCount());
		mv.addObject("moneyCount", gb.getMoneyCount());
		mv.addObject("colsHead", colsHead);
		mv.addObject("colsBody", colsBody);
		mv.addObject("gb", gb);
		return mv;
	}

	@RequestMapping("/tiralGiftboxInfo")
	@ResponseBody
	public Json tiralGiftboxInfo(GiftboxBatch giftboxBatch, String subType) {
		Json j = new Json();
		int i = 1;
		giftboxBatch.setTrialUser(EmployeeSession.getEmpSessionEmpId());
		if (subType.equals("back")) {// 审核不通过
			i = giftboxBatchService.updateByPrimaryKeySelective(giftboxBatch);
		} else if (subType.equals("ok")) {// 审核通过
			giftboxBatchService.tiralGiftbox(giftboxBatch);
		}
		if (i > 0) {
			j.setSuccess(true);
			j.setMsg("操作成功.");
		} else {
			j.setSuccess(false);
			j.setMsg("系统异常,审核失败.");
		}
		return j;
	}

}
