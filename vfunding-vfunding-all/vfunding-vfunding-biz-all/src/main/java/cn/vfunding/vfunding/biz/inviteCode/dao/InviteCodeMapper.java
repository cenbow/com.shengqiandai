package cn.vfunding.vfunding.biz.inviteCode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;

public interface InviteCodeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(InviteCode record);

	int insertSelective(InviteCode record);

	InviteCode selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(InviteCode record);

	int updateByPrimaryKey(InviteCode record);

	InviteCode selectByUserId(Integer userId);

	InviteCode selectByCode(String inviteCode);

	/**
	 * 根据userId 更新dialogStatus的值 wang.zeyan 2015年3月4日
	 * 
	 * @param userId
	 * @param dialogStatus
	 * @return
	 */
	int updateDialogStatusByPrimaryKey(@Param("userId") Integer userId,
			@Param("dialogStatus") Integer dialogStatus);
	
	/**
	 * 
	 * <p>select * from vf_invite_code  where dialog_status & #{compareValue} = #{value}</p>
	 *
	 * wang.zeyan 2015年4月24日
	 * @param compareValue
	 * @param value
	 * @return
	 */
	List<InviteCode> selectByStatus(@Param("compareValue")Integer compareValue,@Param("value") Integer value);
}