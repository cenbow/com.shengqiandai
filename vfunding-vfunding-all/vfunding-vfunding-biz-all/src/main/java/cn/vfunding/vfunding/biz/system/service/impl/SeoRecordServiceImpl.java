package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.SeoRecordMapper;
import cn.vfunding.vfunding.biz.system.model.SeoRecord;
import cn.vfunding.vfunding.biz.system.model.SeoRecordWithBLOBs;
import cn.vfunding.vfunding.biz.system.service.ISeoRecordService;

@Service("seoRecordService")
public class SeoRecordServiceImpl implements ISeoRecordService {

	@Autowired
	private SeoRecordMapper seoRecordMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return seoRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SeoRecordWithBLOBs record) {
		return seoRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(SeoRecordWithBLOBs record) {
		return seoRecordMapper.insertSelective(record);
	}

	@Override
	public SeoRecordWithBLOBs selectByPrimaryKey(Integer id) {
		return seoRecordMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<SeoRecordWithBLOBs> selectListByUrl(String url) {
		return seoRecordMapper.selectListByUrl(url);
	}
	
	@Override
	public SeoRecordWithBLOBs selectUniqueByUrl(String url) {
		return seoRecordMapper.selectUniqueByUrl(url);
	}

	@Override
	public int updateByPrimaryKeySelective(SeoRecordWithBLOBs record) {
		return seoRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(SeoRecordWithBLOBs record) {
		return seoRecordMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(SeoRecord record) {
		return seoRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SeoRecord> selectSystemSeoRecordListPage(PageSearch pageSearch) {
		return seoRecordMapper.selectSystemSeoRecordListPage(pageSearch);
	}

	
}
