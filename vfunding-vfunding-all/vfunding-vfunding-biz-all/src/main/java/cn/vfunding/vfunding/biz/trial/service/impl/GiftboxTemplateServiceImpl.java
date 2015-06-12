package cn.vfunding.vfunding.biz.trial.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.trial.dao.GiftboxTemplateMapper;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxTemplateService;

@Service("giftboxTemplateService")
public class GiftboxTemplateServiceImpl implements IGiftboxTemplateService {
	@Autowired
	private GiftboxTemplateMapper giftboxTemplateMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return giftboxTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(GiftboxTemplate record) {
		return giftboxTemplateMapper.insertSelective(record);
	}

	@Override
	public GiftboxTemplate selectByPrimaryKey(Integer id) {
		return giftboxTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GiftboxTemplate record) {
		return giftboxTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<String> selectTemplateParams(Integer templateId) {
		GiftboxTemplate gt = giftboxTemplateMapper.selectByPrimaryKey(templateId);
		String params = gt.getParams();
		List<String> p = new ArrayList<String>();
		if(!StringUtils.isEmpty(params)){
			p = Arrays.asList(params.split(","));
		}
		return p;
	}

	@Override
	public List<GiftboxTemplate> selectByStatus(Integer status) {
		return this.giftboxTemplateMapper.selectByStatus(status);
	}

	@Override
	public List<GiftboxTemplate> selectAll(PageSearch pageSearch) {
		return this.giftboxTemplateMapper.selectAll(pageSearch);
	}

}
