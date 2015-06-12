package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarPicMapper;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarPicService;
@Service("mortgageCarPicService")
public class MortgageCarPicServiceImpl implements IMortgageCarPicService {
	
	@Autowired
	private MortgageCarPicMapper mortgageCarPicMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mortgageCarPicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MortgageCarPic record) {
		return mortgageCarPicMapper.insert(record);
	}

	@Override
	public int insertSelective(MortgageCarPic record) {
		return mortgageCarPicMapper.insertSelective(record);
	}

	@Override
	public MortgageCarPic selectByPrimaryKey(Integer id) {
		return mortgageCarPicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MortgageCarPic record) {
		return mortgageCarPicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MortgageCarPic record) {
		return mortgageCarPicMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MortgageCarPic> selectBorrowPic(MortgageCarPic pic) {
		return mortgageCarPicMapper.selectBorrowPic(pic);
	}

	@Override
	public List<MortgageCarPic> selectBorrowPicList(MortgageCarPic pic) {
		return mortgageCarPicMapper.selectBorrowPicList(pic);
	}

}
