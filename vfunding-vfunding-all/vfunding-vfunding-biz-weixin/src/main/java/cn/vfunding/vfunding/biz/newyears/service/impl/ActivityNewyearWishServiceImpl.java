package cn.vfunding.vfunding.biz.newyears.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.newyears.dao.ActivityNewyearWishMapper;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearWish;
import cn.vfunding.vfunding.biz.newyears.service.IActivityNewyearWishService;

@Service("activityNewyearWishService")
public class ActivityNewyearWishServiceImpl implements IActivityNewyearWishService {

	@Autowired
	private ActivityNewyearWishMapper activityNewyearWishMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.activityNewyearWishMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityNewyearWish record) {
		return this.activityNewyearWishMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityNewyearWish record) {
		return this.activityNewyearWishMapper.insertSelective(record);
	}

	@Override
	public ActivityNewyearWish selectByPrimaryKey(Integer id) {
		return this.activityNewyearWishMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityNewyearWish record) {
		return this.activityNewyearWishMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityNewyearWish record) {
		return this.updateByPrimaryKey(record);
	}

	@Override
	public ActivityNewyearWish selectByPhone(String phone) {
		return this.activityNewyearWishMapper.selectByPhone(phone);
	}

}