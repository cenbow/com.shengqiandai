package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.ScrollpicMapper;
import cn.vfunding.vfunding.biz.system.model.Scrollpic;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;
import cn.vfunding.vfunding.biz.system.service.IScrollpicService;

@Service("scrollpicService")
public class ScrollpicServiceImpl implements IScrollpicService {

	@Autowired
	private ScrollpicMapper scrollpicMapper;
	/**
	 * 缓存数据
	 */
	private List<Scrollpic> allData;

	@CacheEvict(value = "selectScrollPicCache")
	@Override
	public int deleteByPrimaryKey(Short id) {
		allData = null;
		return this.scrollpicMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value = "selectScrollPicCache")
	@Override
	public int insert(Scrollpic record) {
		allData = null;
		return this.scrollpicMapper.insert(record);
	}

	@CacheEvict(value = "selectScrollPicCache")
	@Override
	public int insertSelective(Scrollpic record) {
		allData = null;
		return this.scrollpicMapper.insertSelective(record);
	}

	@Override
	public Scrollpic selectByPrimaryKey(Short id) {
		if (NullUtil.isNull(allData) || EmptyUtil.isEmpty(allData)) {
			this.init();
		}
		for (Scrollpic scrollpic : allData) {
			if (scrollpic.getId().equals(id)) {
				return scrollpic;
			}
		}
		return null;
	}

	@CacheEvict(value = "selectScrollPicCache")
	@Override
	public int updateByPrimaryKeySelective(Scrollpic record) {
		allData = null;
		return this.scrollpicMapper.updateByPrimaryKeySelective(record);
	}

	@CacheEvict(value = "selectScrollPicCache")
	@Override
	public int updateByPrimaryKey(Scrollpic record) {
		allData = null;
		return this.scrollpicMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Scrollpic> selectByTypeId(Integer typeId) {
		List<Scrollpic> result = new ArrayList<Scrollpic>();
		if (NullUtil.isNull(allData) || EmptyUtil.isEmpty(allData)) {
			
			this.init();
		}
		for (Scrollpic scrollpic : allData) {
			if (scrollpic.getTypeId().intValue() == typeId) {
				result.add(scrollpic);
			}
		}
		return result;
	}

	/**
	 * 缓存初始化
	 * 
	 * @author liuyijun
	 */
	private void init() {
		allData = this.scrollpicMapper.selectAll();
	}

	@Override
	public List<Scrollpic> selectScrollPicByStatus(Integer status, Integer typeId, Integer size) {
		return this.scrollpicMapper.selectScrollPicByStatus(status, typeId, size);
	}

	@Override
	public List<Scrollpic> selectScrollPicByTypes(Integer typeId, Integer size) {
		List<Scrollpic> result = new ArrayList<Scrollpic>();
		List<Scrollpic> newResult = new ArrayList<Scrollpic>();
		if (NullUtil.isNull(allData) || EmptyUtil.isEmpty(allData)) {
			this.init();
		}
		for (Scrollpic scrollpic : allData) {
			if (scrollpic.getTypeId().intValue() == typeId && scrollpic.getStatus() != 0) {
				result.add(scrollpic);
			}
		}
		Collections.sort(result, new Comparator<Scrollpic>() {
			@Override
			public int compare(Scrollpic o1, Scrollpic o2) {
				return o1.getAddtime().compareTo(o2.getAddtime());
			}
		});
		if (size < result.size()) {
			for (int i = 0; i < size; i++) {
				newResult.add(result.get(i));
			}
		} else {
			newResult = result;
		}
		return newResult;
	}

	@Cacheable(value = "selectScrollPicCache", key = "#typeId")
	@Override
	public List<ScrollpicMobile> selectScrollPicByTypeMobile(Integer typeId) {
		return this.scrollpicMapper.selectScrollPicByTypeMobile(typeId);
	}

	/**
	 * 后台列表
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<Scrollpic> selectScrollpicListPage(PageSearch pageSearch) {
		return this.scrollpicMapper.selectScrollpicListPage(pageSearch);
	}

	@Override
	public Integer selectMaxOrderByStatus(Integer status, Integer typeId) {
		return this.scrollpicMapper.selectMaxOrderByStatus(status, typeId);
	}
}
