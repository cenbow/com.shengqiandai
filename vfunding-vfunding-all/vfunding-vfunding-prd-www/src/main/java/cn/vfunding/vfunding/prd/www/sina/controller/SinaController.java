package cn.vfunding.vfunding.prd.www.sina.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;

@Controller
@RequestMapping("/sina/query")
public class SinaController extends BaseController {
	
	@Autowired
	private IQuerySinaService querySinaService;
	
	/**
	 * 查询新浪存钱罐收益
	 * @return json
	 * @author louchen
	 */
	@RequestMapping(value = "/queryFundYield", method = RequestMethod.GET)
	@ResponseBody
	public Json queryFundYield(){
		Json json = new Json();
		String result = null;
		String yield = "4.5620";
		String date = "20150212";
		Map<String,String> map = new HashMap<String,String>();
		try {
			result = querySinaService.doQueryFundYield();
		} catch (Exception e) {
			//
		}
		if(EmptyUtil.isNotEmpty(result)){
			String[] array = result.split("\\|");
			String content = array[0];
			String[] c = content.split("\\^");
			date = c[0];
			yield = c[1];
		}
		map.put("date", date);
		map.put("yield", yield);
		json.setObj(map);
		json.setSuccess(true);
		return json;
	}
	
	
	/**
	 * 查询新浪存钱罐收益(每日缓存数据)
	 * @return json
	 * @author wang.zeyan
	 */
	@RequestMapping(value = "/queryFundYieldDayCache", method = RequestMethod.GET)
	@ResponseBody
	public Json queryFundYieldDayCache(){
		Json json = new Json();
		try {
			Map<String,Object> map = querySinaService.doQueryFundYieldDayCache();
			json.setSuccess(true);
			json.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
		
	}
	
	
}
