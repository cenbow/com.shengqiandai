package cn.vfunding.vfunding.prd.bms.giftbox.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxTemplateService;
import cn.vfunding.vfunding.biz.trial.vo.TemplateSearchVO;

@Controller
@RequestMapping("/giftboxTemplate")
public class GiftboxTemplateController {
	@Autowired
	private IGiftboxTemplateService giftboxTemplateService;
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/templateListPage")
	public ModelAndView templateListPage(){
		return new ModelAndView(
				"webpage/giftbox/templateListPage");
	}
	
	@RequestMapping("/templateList")
	@ResponseBody
	public PageResult<GiftboxTemplate> templateList(TemplateSearchVO templateSearchVO, PageSearch pageSearch){
		PageResult<GiftboxTemplate> results = new PageResult<GiftboxTemplate>();
		pageSearch.setEntity(templateSearchVO);
		List<GiftboxTemplate> gtList = giftboxTemplateService.selectAll(pageSearch);
		if (!gtList.isEmpty()) {
			for (GiftboxTemplate gt : gtList) {
				Employee e = employeeService.selectByPrimaryKey(gt.getCraeteUser());
				if(e != null){
					gt.setCreateUserName(e.getEmpName());
				}
			}
		}
		results.setRows(gtList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	
	@RequestMapping(value = "/templateAddOrEditPage")
	@ResponseBody
	public ModelAndView templateAddOrEditPage(GiftboxTemplate giftboxTemplate) {
		ModelAndView mv = new ModelAndView("webpage/giftbox/templateAddOrEditPage");
		this.initTemplate(mv,giftboxTemplate);
		return mv;
	}
	
	
	@RequestMapping("/templateAddOrEdit")
	@ResponseBody
	public Json templateAddOrEdit(GiftboxTemplate giftboxTemplate){
		Json j = new Json();
		int i = 0;
		if(giftboxTemplate.getId() == null){
			giftboxTemplate.setCraeteUser(EmployeeSession.getEmpSessionEmpId());
			giftboxTemplate.setAddtime(new Date());
			i = giftboxTemplateService.insertSelective(giftboxTemplate);
		}else{
			i = giftboxTemplateService.updateByPrimaryKeySelective(giftboxTemplate);
		}
		if(i>0){
			j.setMsg("操作成功！");
			j.setSuccess(true);
		}else{
			j.setMsg("操作失败！");
		}
		return j;
	}
	
	private void initTemplate(ModelAndView mv,GiftboxTemplate giftboxTemplate){
		if(giftboxTemplate.getId() != null){
			giftboxTemplate = giftboxTemplateService.selectByPrimaryKey(giftboxTemplate.getId());
			mv.addObject("template", giftboxTemplate);
		}else{
			mv.addObject("template", giftboxTemplate);
		}
	}
}
