package cn.vfunding.vfunding.prd.bms.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Scrollpic;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.system.service.IScrollpicService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/system/banner")
public class BannerController {
	
	@Autowired
	private IScrollpicService scrollpicService;

	@RequestMapping(value = "/bannerListPage")
	public ModelAndView bannerListPage(){
		ModelAndView mv = new ModelAndView("webpage/banner/bannerList");
		boolean addOrEdit = UserAuthFilter.isPass("/system/banner/addBanner");
		mv.addObject("addOrEdit", addOrEdit);
		
		boolean canDelete = UserAuthFilter.isPass("/system/banner/delete");
		mv.addObject("canDelete", canDelete);
		
		return mv;
	}
	
	/**
	 * banner列表
	 * @author liuhuan
	 */
	@ParentSecurity("/system/banner/bannerListPage")
	@RequestMapping(value = "/bannerList")
	@ResponseBody
	public PageResult<Scrollpic> bannerList(PageSearch pageSearch,Integer status,Integer typeId){
		PageResult<Scrollpic> results = new PageResult<Scrollpic>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(status != null){
			map.put("status", status);
		}
		if(typeId != null){
			map.put("typeId", typeId);
		}
		pageSearch.setEntity(map);
		List<Scrollpic> scrollpics = scrollpicService.selectScrollpicListPage(pageSearch);
		results.setRows(scrollpics);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	/**
	 * 编辑&添加  页面
	 * @author liuhuan
	 */
	@ParentSecurity("/system/banner/bannerListPage")
	@RequestMapping(value = "/addEditBannerPage")
	public ModelAndView addBannerPage(Integer id){
		ModelAndView mv = new ModelAndView("webpage/banner/addEditBanner");
		boolean type = false;
		if(id!=null && id!=0){ //更新
			Scrollpic scrollpic = scrollpicService.selectByPrimaryKey(id.shortValue());
			List<Scrollpic> pics = scrollpicService.selectScrollPicByStatus(1,scrollpic.getTypeId().intValue(), 10);
			int sorder = 0;
			Scrollpic temp = null;
			for(int i=1;i<=pics.size();i++){
				temp = pics.get(i-1);
				if(temp.getOrder()==scrollpic.getOrder()){
					sorder = i;
				}
			}
			type = true;
			mv.addObject("sorder", sorder);
			mv.addObject("scrollpic", scrollpic);
		} else { // 插入
			type = false;
		}
		mv.addObject("type", type);
		return mv;
	}

	@Autowired
	private IAttestationService attestationService;
	/**
	 * 编辑&添加
	 * @return
	 * @author liuhuan
	 * @throws IOException 
	 */
	@NeedLogger(desc="添加或修改banner")
	@RequestMapping(value="/addBanner")
	@ResponseBody
	public Json addBanner(Scrollpic scrollpic,HttpServletRequest request,boolean type,String spic,
			MultipartHttpServletRequest multipartRequest,Integer location) throws IOException{
		Json j = new Json();
		int i = 0;
		if(scrollpic.getStatus() == 1){//显示才排序
			if(location == 1){
				Integer order = scrollpicService.selectMaxOrderByStatus(1, scrollpic.getTypeId().intValue());
				scrollpic.setOrder((short) ((
						order==null?0:order)+1));
			} else {
				List<Scrollpic> pics = scrollpicService.selectScrollPicByStatus(1,scrollpic.getTypeId().intValue(), location);
				Scrollpic temp = null;
				for(int p=1;p<=pics.size();p++){
					temp = pics.get(p-1);
					if(p!=pics.size()){
						temp.setOrder((short)(temp.getOrder()+1));
						scrollpicService.updateByPrimaryKeySelective(temp);
					} else {
						scrollpic.setOrder((short)(temp.getOrder()+1));
					}
				}
			}
		}
		MultipartFile multipartFile = multipartRequest.getFile("banner");
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartFile);
		String picPath = null;
		if(EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize()!=0){
			picPath = this.attestationService.uploadAttestation(multipartEntity);
		}
		if(type == false){ //插入
			scrollpic.setAddtime(Integer.parseInt(DateUtil.getTime()));
			scrollpic.setAddip(request.getRemoteAddr());
			scrollpic.setPic(picPath);
			i = scrollpicService.insertSelective(scrollpic);
		} else { //更新
			scrollpic.setPic(picPath);
			i = scrollpicService.updateByPrimaryKeySelective(scrollpic);
		}
		if(i > 0){
			j.setSuccess(true);
		}
		return j;
	}
	@NeedLogger(desc="删除banner")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Json delete(Integer id) throws IOException{
		Json j = new Json();
		int i = scrollpicService.deleteByPrimaryKey(id.shortValue());
		if(i>0){
			j.setSuccess(true);
		}
		return j;
	}
}
