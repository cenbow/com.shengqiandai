package cn.vfunding.vfunding.biz.week.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.week.dao.WeekUserMapper;
import cn.vfunding.vfunding.biz.week.model.WeekUser;
import cn.vfunding.vfunding.biz.week.service.IWeekUserService;
import cn.vfunding.vfunding.biz.week.vo.WeekUserVO;

@Service("weekUserService")
public class  WeekUserServiceImpl implements IWeekUserService{
	@Autowired
	private WeekUserMapper weekUserMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountService accountService;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeekUser record) {
		return this.weekUserMapper.insert(record);
	}

	@Override
	public int insertSelective(WeekUser record) {
		return this.weekUserMapper.insertSelective(record);
	}

	@Override
	public WeekUser selectByPrimaryKey(Integer id) {
		return this.weekUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekUser record) {
		return this.weekUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WeekUser record) {
		return this.weekUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public WeekUser selectByEmpId(Integer id) {
		return this.weekUserMapper.selectByEmpId(id);
	}

	@Override
	public WeekUserVO selectWeekUserByEmpId(Integer id) {
		WeekUserVO weekUserVO = new WeekUserVO();
		weekUserVO.setWeekUser(this.selectByEmpId(id));
		if(EmptyUtil.isNotEmpty(weekUserVO.getWeekUser()) && weekUserVO.getWeekUser().getId()>0 ){
			weekUserVO.setUser(this.userService.selectByPrimaryKey(weekUserVO.getWeekUser().getVfundingUser()));
			weekUserVO.setAccount(this.accountService.selectByUserId(weekUserVO.getWeekUser().getVfundingUser()));
		}		
		return weekUserVO;
	}
   
}