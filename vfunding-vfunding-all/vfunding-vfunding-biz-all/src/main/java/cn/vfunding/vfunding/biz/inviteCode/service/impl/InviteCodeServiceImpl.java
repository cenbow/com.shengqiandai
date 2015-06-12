package cn.vfunding.vfunding.biz.inviteCode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.inviteCode.dao.InviteCodeMapper;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.inviteCode.status.DialogStatusIndex;
import cn.vfunding.vfunding.biz.inviteCode.status.StatusUtil;

@Service("inviteCodeService")
public class InviteCodeServiceImpl implements IInviteCodeService {
	@Autowired
	private InviteCodeMapper inviteCodeMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return inviteCodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(InviteCode record) {
		// TODO Auto-generated method stub
		return inviteCodeMapper.insertSelective(record);
	}

	@Override
	public InviteCode selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return inviteCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(InviteCode record) {
		// TODO Auto-generated method stub
		return inviteCodeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public InviteCode selectByUserId(Integer userId) {
		return inviteCodeMapper.selectByUserId(userId);
	}

	@Override
	public InviteCode selectByCode(String inviteCode) {
		// TODO Auto-generated method stub
		return inviteCodeMapper.selectByCode(inviteCode);
	}

	@Override
	public int updateDialogStatusByPrimaryKey(Integer userId,
			Integer dialogStatusIndex) {

		Integer dialogStatusValue = StatusUtil.getStatusUpdateValue(
				getDialogStatusValue(userId), dialogStatusIndex);
		return inviteCodeMapper.updateDialogStatusByPrimaryKey(userId,
				dialogStatusValue);
	}
	
	@Override
	public int getDialogStatus(Integer userId, Integer dialogStatusIndex) {

		return StatusUtil.booleanCompare(getDialogStatusValue(userId),
				dialogStatusIndex) ? 1 : 0;
	}

	private int getDialogStatusValue(Integer userId) {
		InviteCode ic = selectByUserId(userId);
		if (ic == null)
			throw new RuntimeException("比较的对象不存在!");
		if (ic.getDialogStatus() == null)
			return 0;
		return ic.getDialogStatus();
	}

	@Override
	public Map<String, Object> getDialogStatusMap(Integer userId,
			Integer dialogStatusIndex) {
		String key = DialogStatusIndex.dialogMap.get(dialogStatusIndex);
		int value = getDialogStatus(userId, dialogStatusIndex);
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put(key, value);
		/**
		 * 如果警告未弹出过，则弹出，并更新状态
		 */
		if(value == 0)
			updateDialogStatusByPrimaryKey(userId, dialogStatusIndex);
		return map;
	}

	@Override
	public boolean isLeapDialog(Integer userId, Integer dialogStatusIndex) {
		
		return StatusUtil.booleanCompare(getDialogStatusValue(userId), dialogStatusIndex);
	}

	@Override
	public List<InviteCode> selectByStatus(Integer dialogStatusIndex, boolean status) {
		int compareValue = StatusUtil.getStatusCompareValue(dialogStatusIndex);
		int value = status ? compareValue : 0;
		return inviteCodeMapper.selectByStatus(compareValue, value);
	}

}
