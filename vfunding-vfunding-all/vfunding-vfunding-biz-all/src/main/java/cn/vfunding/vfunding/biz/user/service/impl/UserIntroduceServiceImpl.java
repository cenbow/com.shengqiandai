package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.user.dao.UserIntroduceMapper;
import cn.vfunding.vfunding.biz.user.model.UserIntroduce;
import cn.vfunding.vfunding.biz.user.service.IUserIntroduceService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Service("userIntroduceService")
public class UserIntroduceServiceImpl implements IUserIntroduceService{

	@Autowired
	private UserIntroduceMapper userIntroduceMapper;
	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IUserService userService;
	
	private ThreadLocal<Integer> userId;
	
	public Integer getUserId(){
		return userId.get();
	}
	
	public void setUserId(Integer userId){
		this.userId.set(userId);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userIntroduceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserIntroduce record) {
		return userIntroduceMapper.insert(record);
	}

	@Override
	public int insertSelective(UserIntroduce record) {
		return userIntroduceMapper.insertSelective(record);
	}

	@Override
	public UserIntroduce selectByPrimaryKey(Integer id) {
		return userIntroduceMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserIntroduce selectByUserId(Integer userId) {
		return userIntroduceMapper.selectByUserId(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserIntroduce record) {
		return userIntroduceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserIntroduce record) {
		return userIntroduceMapper.updateByPrimaryKey(record);
	}

	@Override
	public Boolean isUserIntroduce(Integer userId) {
		Boolean flag = null;
		if(userId!=null||userService.selectByPrimaryKey(userId)!=null){
			UserIntroduce ui = selectByUserId(userId);
			if(ui!=null){
				if(ui.getStatus().equals(2)){
					flag = false;
				}else if(ui.getIntroduceTime()!=null){
					flag = false;
				}else{
					flag = true;
				}
			}else{
				if(saveUserIntroduceData(userId)>0){
					flag = isUserIntroduce(userId);
				}
			}
		}		
		return flag;
	}
		
	private int saveUserIntroduceData(Integer userId) {
		int result = 0;
		if(userId!=null){
			UserIntroduce ui = new UserIntroduce();
			ui.setAddtime(new Date());
			ui.setUserId(userId);
			List<BorrowTender> list = borrowTenderService.selectBrrowTenderByUserId(userId);
			if(list.size()>0){
				ui.setStatus(2);	
				ui.setIntroduceTime(new Date());
			}else{
				ui.setStatus(1);	
			}
			result = insertSelective(ui);
		}
		return result;
	}

	@Override
	public int updateIntroduceTime(Integer userId) {
		if(userId!=null||userService.selectByPrimaryKey(userId)!=null){
			UserIntroduce ui = selectByUserId(userId);
			if(ui!=null&&ui.getIntroduceTime()==null){
				ui.setIntroduceTime(new Date());
				return updateByPrimaryKeySelective(ui);
			}
		}
		return 0;
	}
}