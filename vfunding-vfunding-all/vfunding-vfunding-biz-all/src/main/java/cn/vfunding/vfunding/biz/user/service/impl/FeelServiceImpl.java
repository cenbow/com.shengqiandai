package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.system.dao.FeelMapper;
import cn.vfunding.vfunding.biz.system.model.Feel;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IFeelService;

@Service("feelService")
public class FeelServiceImpl implements IFeelService {

	@Autowired
	private FeelMapper feelMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int updateByPrimaryKeySelective(Feel record) {
		return feelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insert(Feel record) {
		return feelMapper.insert(record);
	}

	@Override
	public Feel selectByPrimaryKey(Integer id) {
		return feelMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Feel> selectByUserId(Integer userId) {
		return feelMapper.selectByUserId(userId);
	}

	@Override
	public Feel selectByStatus(Integer status) {
		return feelMapper.selectByStatus(status);
	}

	@Override
	public Feel selectByCode(String code) {
		return feelMapper.selectByCode(code);
	}

	@Override
	public int updateByPrimaryKey(Feel record) {
		return feelMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Feel> selectByNoStatus(Feel feel) {
		return feelMapper.selectByNoStatus(feel);
	}

	@Override
	public List<Feel> selectAll() {
		return feelMapper.selectAll();
	}
	@Override
	public List<FeelVO> selectFeelsListPage(PageSearch pageSearch) {
		return feelMapper.selectFeelsListPage(pageSearch);
	}

	@Override
	public synchronized String updateBatchUserId(Integer startNo,Integer endNo,String username,Integer type) {
		String result = "";
		// type:1为网站用户；2为虚拟组用户
		User user = userMapper.selectByUserName(username);
		if(type == 1 && user == null){
			result = "该理财师不存在，绑定失败";
		} else {
			List<Feel> feels = feelMapper.selectByNo(startNo,endNo);
			if(!feels.isEmpty()){
				try {
					for(Feel f : feels){
						if(f.getUserId()!=null){
							return "该卡号已绑定理财师";
						} else if(f.getGroupName()!=null){
							return "该卡号已绑定虚拟组";
						} else if(type == 1 && f.getUserId() != null){
							return "改区间已被绑定，请重新选择";
						} else if(type == 2 && f.getGroupName() != null){
							return "改区间已被绑定，请重新选择";
						} else {}
					}
					
					for(Feel f : feels){
						if(type == 1 && f.getUserId() == null){
							f.setUserId(user.getUserId());
						} else if(type == 2 && f.getGroupName() == null){
							f.setGroupName(username);
						} else {}
						feelMapper.updateByPrimaryKeySelective(f);
					}
					result = "绑定成功.";
				} catch (Exception e) {
					result = "绑定失败.";
				}
			} else {
				result = "绑定失败范围有误.";
			}
		}
		return result;
	}
	
	
	
}
