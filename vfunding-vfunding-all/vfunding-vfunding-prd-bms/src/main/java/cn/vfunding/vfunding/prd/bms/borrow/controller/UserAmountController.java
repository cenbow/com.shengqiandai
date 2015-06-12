package cn.vfunding.vfunding.prd.bms.borrow.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.vo.AmountVO;
import cn.vfunding.vfunding.biz.user.model.UserAmountapply;
import cn.vfunding.vfunding.biz.user.model.UserAmountapplyWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserAmountLogService;
import cn.vfunding.vfunding.biz.user.service.IUserAmountapplyService;
import cn.vfunding.vfunding.prd.bms.utils.ExportExcel;

/**
 * 申请额度
 */
@Controller
@RequestMapping("/system/amount")
public class UserAmountController extends BaseController {

	@Autowired
	private IUserAmountapplyService userAmountapplyService;
	@Autowired
	private IUserAmountLogService userAmountLogService;
	@Autowired
	private IAccountService accountService;

	/**
	 * 后台审核列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/userAmountListPage")
	@RequestMapping(value = "/amountApplyList")
	@ResponseBody
	public PageResult<UserAmountapply> amountApplyList(PageSearch pageSearch,
			AmountVO amountVO) {
		PageResult<UserAmountapply> results = new PageResult<UserAmountapply>();
		pageSearch.setEntity(amountVO);
		List<UserAmountapply> applyList = userAmountapplyService
				.selectAmountApplyListPage(pageSearch);
		results.setRows(applyList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 后台审核列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/excelUserAmount")
	@RequestMapping(value = "/excelUserAmount")
	@ResponseBody
	public void excelUserAmount(PageSearch pageSearch, AmountVO amountVO,
			HttpServletResponse response) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			List<UserAmountapplyWithBLOBs> applyList = userAmountapplyService
					.selectByParam(null);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			String file = "审批列表.xls";
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			listMap.add(getMap("用户名", "username"));
			listMap.add(getMap("手机号", "phone"));
			listMap.add(getMap("真实姓名", "realname"));
			listMap.add(getMap("内容", "content"));
			listMap.add(getMap("借款期限", "limit"));
			listMap.add(getMap("申请额度", "account"));
			listMap.add(getMap("抵押品种(1:汽车,3:房产)", "mortgagetypeId"));
			listMap.add(getMap("申请时间", "addtime"));
			ExportExcel.createExcel(listMap, applyList, bos);
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

	private Map<String, String> getMap(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return map;
	}

	/**
	 * 审核详情
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/userAmountListPage")
	@RequestMapping("/amountDetail")
	public ModelAndView amountDetail(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("webpage/borrow/userAmountDetail");
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("amountId", id);
		pageSearch.setEntity(map);
		UserAmountapply applyDetail = null;
		try {
			applyDetail = userAmountapplyService.selectAmountApplyListPage(
					pageSearch).get(0);
		} catch (Exception e) {
		}
		mv.addObject("applyDetail", applyDetail);
		return mv;
	}

	/**
	 * 后台审核额度
	 * 
	 * @return
	 * @author liuhuan
	 */
	@NeedLogger(desc = "借款额度审批")
	@RequestMapping(value = "applyAmount")
	@ResponseBody
	public Json applyAmount(AmountVO amountVO, HttpServletRequest request) {
		Json j = new Json();
		if (EmptyUtil.isEmpty(amountVO.getRemark())
				&& EmptyUtil.isEmpty(amountVO.getStatus())) {
			j.setMsg("审核备注不能为空");
			return j;
		}
		// UserAmountapply record =
		// userAmountapplyService.selectByPrimaryKey(apply.getId());
		/*
		 * AmountVO amountVO = new AmountVO();
		 * amountVO.setAmountId(apply.getId());
		 * amountVO.setAccount(apply.getAccount()); amountVO.setAdminUserId(1);
		 * // TODO session amountVO.setIp(request.getRemoteAddr());
		 * amountVO.setRemark(apply.getVerifyRemark());
		 * amountVO.setStatus(apply.getStatus());
		 */

		amountVO.setAdminUserId(EmployeeSession.getEmpSessionEmpId());
		amountVO.setIp(request.getRemoteAddr());
		int i = userAmountLogService.updateApplyAmount(amountVO);
		if (i == 1 || i == 0) {
			j.setSuccess(true);
			j.setMsg("审核成功");
		} else if (i == -1) {
			j.setMsg("请勿重复审核");
		}
		return j;
	}

}
