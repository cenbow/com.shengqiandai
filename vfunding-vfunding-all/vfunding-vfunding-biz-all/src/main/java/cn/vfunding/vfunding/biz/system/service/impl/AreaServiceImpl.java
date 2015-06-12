package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.system.dao.AreaMapper;
import cn.vfunding.vfunding.biz.system.model.Area;
import cn.vfunding.vfunding.biz.system.service.IAreaService;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private AreaMapper areaMapper;

	/**
	 * 缓存处理
	 */
	private static List<Area> allArea;

	/**
	 * map 缓存
	 * 
	 * @param id
	 * @return author LiLei 2014年5月17日
	 */
	private static Map<Integer, String> areaMap;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		allArea = null;
		return this.areaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Area record) {
		allArea = null;
		return this.areaMapper.insert(record);
	}

	@Override
	public int insertSelective(Area record) {
		allArea = null;
		return this.areaMapper.insertSelective(record);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		return this.areaMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		allArea = null;
		return this.areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		allArea = null;
		return this.areaMapper.updateByPrimaryKey(record);
	}

	private void initList() {
		allArea = this.areaMapper.selectAll();
	}

	@Override
	public List<Area> selectProvince() {
		List<Area> result = new ArrayList<Area>();
		if (EmptyUtil.isEmpty(allArea)) {
			this.initList();
		}
		for (Area area : allArea) {
			if (area.getPid().intValue() == 0) {
				result.add(area);
			}

		}
		return result;
	}

	@Override
	public List<Area> selectByParentId(Integer pid) {
		List<Area> result = new ArrayList<Area>();
		if (EmptyUtil.isEmpty(allArea)) {
			this.initList();
		}
		for (Area area : allArea) {
			if (area.getPid().intValue() == pid.intValue()) {
				result.add(area);
			}
		} 
		return result;
	}

	@Override
	public String getAreaNameById(Integer id) {
		if (EmptyUtil.isEmpty(allArea)) {
			this.initList();
		}
		if (areaMap == null) {
			areaMap = new HashMap<Integer, String>();
			for (Area area : allArea) {
				areaMap.put(area.getId(), area.getName());
			}
		}
		return areaMap.get(id);
	}
}
