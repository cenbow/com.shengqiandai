package cn.vfunding.vfunding.biz.returns.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.vfunding.biz.returns.model.Returnfee;

public interface ReturnfeeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Returnfee record);

	int insertSelective(Returnfee record);

	Returnfee selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Returnfee record);

	int updateByPrimaryKey(Returnfee record);

	Returnfee selectByUserTypeId(Integer id);

	List<Returnfee> selectAll();

	BigDecimal selectReturnfeeByMonth(Integer monthLimit);

}