package cn.vfunding.vfunding.biz.bms_employee.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.dao.UserEmpMapper;
import cn.vfunding.vfunding.biz.bms_employee.model.UserEmp;
import cn.vfunding.vfunding.biz.bms_employee.service.IUserEmpService;
@Service("userEmpService")
public class UserEmpServiceImpl implements IUserEmpService{
	
	@Autowired
	private UserEmpMapper userEmpMapper;
	
	@Override
	public UserEmp selectByUserId(Integer id) {
		return userEmpMapper.selectByUserId(id);
	}
	@Override
	public int insert(UserEmp record) {
		return userEmpMapper.insert(record);
	}
	@Override
	public int insertSelective(UserEmp record) {
		return userEmpMapper.insertSelective(record);
	}
	@Override
	public UserEmp selectByPrimaryKey(Integer id) {
		return userEmpMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(UserEmp record) {
		return userEmpMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(UserEmp record) {
		return userEmpMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<UserEmp> selectFriendByUserEmpListPage(PageSearch pageSearch) {
		return userEmpMapper.selectFriendByUserEmpListPage(pageSearch);
	}
	
	/**
	 * 手动分配客服
	 * @param userIds 需要分配的用户id
	 * @param empId 分配后的客服id
	 * @return
	 * @author liuhuan
	 */
	@Override
	public int updateChangeUserEmp(String[] userIds, String empId, String updateRemark) {
		Date time = new Date();
		for(String userId : userIds){
			UserEmp userEmp = userEmpMapper.selectByUserId(Integer.parseInt(userId));
			userEmp.setUpdateDate(time);
			userEmp.setUpdateRemark((EmptyUtil.isNotEmpty(userEmp.getUpdateRemark())?userEmp.getUpdateRemark():"")
					+updateRemark+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"];");
			userEmp.setEmpId(Integer.parseInt(empId));
			userEmpMapper.updateByPrimaryKeySelective(userEmp);
		}
		return 1;
	}
	
}
