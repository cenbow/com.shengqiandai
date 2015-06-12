package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.dao.UpfinancialMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.Upfinancial;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUpfinancialService;

@Service("upfinancialService")
public class UpfinancialServiceImpl implements IUpfinancialService {

	@Autowired
	private UpfinancialMapper upfinancialMapper;
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public Upfinancial selectByPrimaryKey(Integer id) {
		return upfinancialMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Upfinancial> selectByListPage(PageSearch pageSearch) {
		return upfinancialMapper.selectByListPage(pageSearch);
	}

	/**
	 * 审核
	 * @return
	 * @author liuhuan
	 */
	@Override
	public int updateApplyFinancial(String remark, Integer status, Upfinancial f) {
		if(status != 1 && status != 2){
			return -1; //数据错误
		}
		if(status == 1 ){ //通过
			User user = new User();
			user.setUserId(f.getUserId());
			user.setTypeId(f.getTypeId());
			userMapper.updateUserType(user);
		}
		f.setStatus(status);
		f.setRemark(remark);
		f.setVerifyTime(new Date());
		upfinancialMapper.updateByPrimaryKeySelective(f);
		return 1;
	}

	
}
