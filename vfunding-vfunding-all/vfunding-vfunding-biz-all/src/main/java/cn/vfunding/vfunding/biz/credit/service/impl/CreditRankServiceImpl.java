package cn.vfunding.vfunding.biz.credit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.credit.dao.CreditRankMapper;
import cn.vfunding.vfunding.biz.credit.model.CreditRank;
import cn.vfunding.vfunding.biz.credit.service.ICreditRankService;
@Service("creditRankService")
public class CreditRankServiceImpl implements ICreditRankService {

	@Autowired
	private CreditRankMapper mapper;
	
	private static List<CreditRank> allData;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		allData=null;
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CreditRank record) {
		allData=null;
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(CreditRank record) {
		allData=null;
		return this.mapper.insertSelective(record);
	}

	@Override
	public CreditRank selectByPrimaryKey(Integer id) {
		if(EmptyUtil.isEmpty(allData)){
			init();
		}
		
		for (CreditRank creditRank : allData) {
			if(creditRank.getId()==id){
				return creditRank;
			}
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CreditRank record) {
		allData=null;
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CreditRank record) {
		allData=null;
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public CreditRank selectByCreditValue(Integer value) {
		if(EmptyUtil.isEmpty(allData)){
			init();
		}		
		for (CreditRank creditRank : allData) {
			if(creditRank.getPoint1()== value  || creditRank.getPoint2()==value){
				return creditRank;
			}else{
				if(creditRank.getPoint1()<value && creditRank.getPoint2()>value){
					return creditRank;
				}
			}
		}		
		return null;
	}
	
	private void init(){
		allData=this.mapper.selectAll();
	}

}
