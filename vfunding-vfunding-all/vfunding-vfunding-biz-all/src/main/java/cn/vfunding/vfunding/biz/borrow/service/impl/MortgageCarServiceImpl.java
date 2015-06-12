package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarService;
@Service("mortgageCarService")
public class MortgageCarServiceImpl implements IMortgageCarService {
	@Autowired
	private MortgageCarMapper mortgageCarMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mortgageCarMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MortgageCar record) {
		return mortgageCarMapper.insert(record);
	}

	@Override
	public int insertSelective(MortgageCar record) {
		return mortgageCarMapper.insertSelective(record);
	}

	@Override
	public MortgageCar selectByPrimaryKey(Integer id) {
		return mortgageCarMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MortgageCar record) {
		return mortgageCarMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MortgageCar record) {
		return mortgageCarMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MortgageCar> selectMortgageCarListPage(PageSearch search) {
		// TODO Auto-generated method stub
		return mortgageCarMapper.selectMortgageCarListPage(search);
	}
	  //客户借款信息
	@Override
	public List<Borrow> selectAddBorrowpageList(PageSearch search){
		return mortgageCarMapper.selectAddBorrowpageListPage(search);
	}

}
