package cn.vfunding.vfunding.prd.bms.borrow.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarService;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping(value = "/system/collateral")
public class CollateralController {
	@Autowired
	private IMortgageCarService mortgageCarService;
	@Autowired
	private IBorrowService borrowService;

	/**
	 * 抵押物信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/collateralListY")
	public ModelAndView collateralListY() {
		ModelAndView mv = new ModelAndView("webpage/collateral/collateral");
		return mv;
	}

	/**
	 * 抵押物信息列表
	 * 
	 * @return
	 */
	@ParentSecurity("/system/collateral/collateralListY")
	@RequestMapping(value = "/collateralList")
	@ResponseBody
	public PageResult<MortgageCar> collateralList(PageSearch pageSearch,
			SearchVO searchVO) {
		PageResult<MortgageCar> result = new PageResult<MortgageCar>();
		pageSearch.setEntity(searchVO);
		List<MortgageCar> cars = mortgageCarService
				.selectMortgageCarListPage(pageSearch);
		result.setRows(cars);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 借款人信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/customers")
	public ModelAndView customers() {
		ModelAndView mv = new ModelAndView("webpage/collateral/customers");
		boolean canEdit = UserAuthFilter.isPass("/system/collateral/customersOK");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}
	@ParentSecurity("/system/collateral/customers")
	@RequestMapping(value = "/customersY")
	@ResponseBody
	public PageResult<Borrow> customersY(PageSearch pageSearch,
			String ownerName, String mortgageType, String successTime,
			String successTime1) {
		PageResult<Borrow> result = new PageResult<Borrow>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerName", ownerName);
		map.put("mortgageType", mortgageType);
		map.put("successTime", successTime);
		map.put("successTime1", successTime1);
		pageSearch.setEntity(map);
		List<Borrow> vo1s = mortgageCarService
				.selectAddBorrowpageList(pageSearch);
		result.setRows(vo1s);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @return
	 */
	@ParentSecurity("/system/collateral/customers")
	@RequestMapping("/customersDetails")
	public ModelAndView customersDetails(@RequestParam("id") Integer id) {
		System.out.println(id);
		ModelAndView mv = new ModelAndView("webpage/collateral/customersOk");
		Borrow borro = borrowService.selectBorrowById(id);
		mv.addObject("booro", borro);
		MortgageCar car = mortgageCarService.selectByPrimaryKey(borro
				.getMortgageId());
		String start=null;
		String end=null;
		if (borro.getContractStart() != null && borro.getContractEnd() != null) {
		 start = new SimpleDateFormat("yyyy-MM-dd").format(borro
					.getContractStart());
		 end = new SimpleDateFormat("yyyy-MM-dd").format(borro
					.getContractEnd());
		}

		mv.addObject("start", start);
		mv.addObject("end", end);
		mv.addObject("car", car);
		return mv;
	}

	/**
	 * 保存修改
	 * 
	 * @param id
	 * @return
	 */
	@NeedLogger(desc = "修改客户信息")
	@RequestMapping("/customersOK")
	@ResponseBody
	public Json customersOK(Integer id, String ownerName, String contractStart,
			String contractEnd) {
		Json j = new Json();
		Borrow borro = borrowService.selectBorrowById(id);
		MortgageCar car = mortgageCarService.selectByPrimaryKey(borro
				.getMortgageId());
		Date contractStart1 = DateUtil.getDateToString(contractStart,
				"yyyy-MM-dd");
		Date contractEnd1 = DateUtil.getDateToString(contractEnd, "yyyy-MM-dd");
		borro.setContractStart(contractStart1);
		borro.setContractEnd(contractEnd1);
		car.setOwnerName(ownerName);
		int carnum = mortgageCarService.updateByPrimaryKey(car);
		int bonum = borrowService.updateByPrimaryKeySelective(borro);
		if (carnum > 0 && bonum > 0) {
			j.setMsg("修改成功！");
		} else{
			j.setMsg("修改失败!");
		}
		return j;
	}

}
